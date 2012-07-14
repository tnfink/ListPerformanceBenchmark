package de.akquinet.listbenchmark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListBenchmark {

    private static final int NUMBER_OF_PASSES = 10000;
    private static final int NUMBER_OF_DIFFERENT_ELEMENTS_PER_PASS = 11;
    private final String name;
    private final ListProvider listProvider;
    private final List<Integer> numberOfElementsPerPass;

    public ListBenchmark(final String name, final ListProvider<Integer> listProvider) {
        this.name = name;
        this.listProvider = listProvider;

        numberOfElementsPerPass = new ArrayList<Integer>(NUMBER_OF_DIFFERENT_ELEMENTS_PER_PASS);
        for (int i = 0; i < NUMBER_OF_DIFFERENT_ELEMENTS_PER_PASS; i++) {
            numberOfElementsPerPass.add((2 << i) + 1);
        }
    }

    public List<BenchmarkResult> computeBenchmark() {

        final ArrayList<BenchmarkResult> results = new ArrayList<BenchmarkResult>(
                NUMBER_OF_DIFFERENT_ELEMENTS_PER_PASS);

        for (Iterator<Integer> iterator = numberOfElementsPerPass.iterator(); iterator.hasNext(); ) {
            final Integer numberOfElements = iterator.next();

            final BenchmarkResult benchmarkResult = computeAllPassesForOneNumberOfElements(numberOfElements);

            results.add(benchmarkResult);

        }


        return results;
    }

    private BenchmarkResult computeAllPassesForOneNumberOfElements(final Integer numberOfElements) {
        // hold lists to compute the overall memory
        Set<List<Integer>> allLists = new HashSet<List<Integer>>();

        System.gc();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final Runtime runtime = Runtime.getRuntime();
        final long benchmarkStartTime = System.currentTimeMillis();
        final long benchmarkStartMemoryUsage = runtime.totalMemory() - runtime.freeMemory();

        for (int i = 0; i < NUMBER_OF_PASSES; i++) {
            computeOnePass(numberOfElements, allLists);

            /* for debugging, it seems Linked Lists irritate the garbage collector
            if (i%1000 == 0) {
            System.out.println(numberOfElements + "-" + i +": "+ " total="+runtime.totalMemory()+"   free="+runtime
                    .freeMemory()
                    +"  " +
                                       "max="+runtime.maxMemory());
            }
            */
        }

        final long benchmarkEndTime = System.currentTimeMillis();

        final long benchmarkEndMemoryUsage = runtime.totalMemory() - runtime.freeMemory();

        final long durationMs = benchmarkEndTime - benchmarkStartTime;

        final long memoryConsumption = (benchmarkEndMemoryUsage - benchmarkStartMemoryUsage
                )/NUMBER_OF_PASSES;

        return new BenchmarkResult(name + "-" + numberOfElements, durationMs,
                                   memoryConsumption);
    }

    private void computeOnePass(final int numberOfElements, final Set<List<Integer>> allLists) {
        final List<Integer> list = listProvider.provideList(numberOfElements);

        // fill List
        for (int i = 0; i < numberOfElements; i++) {
            list.add(1);
        }

        // access elements
        long sum = 0;
        for (int i = 0; i < numberOfElements; i++) {
            sum += list.get(i);
        }

        allLists.add(list);
    }
}
