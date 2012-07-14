package de.akquinet.listbenchmark;

import org.junit.Test;

public class ListBenchmarkTest {

    @Test()
    public void testArrayListBenchmark() {

        final ListBenchmark listBenchmark = new ListBenchmark("ArrayList", new ArrayListProvider<Integer>());

        System.out.println("benchmarkResult = " + listBenchmark.computeBenchmark());
    }

    @Test()
    public void testArrayListIgnoreInitBenchmark() {

        final ListBenchmark listBenchmark = new ListBenchmark("ArrayList-Ignore Init",
                                                              new ArrayListIgnoreInitProvider<Integer>());

        System.out.println("benchmarkResult = " + listBenchmark.computeBenchmark());
    }

    @Test()
    public void testLinkedListBenchmark() {

        final ListBenchmark listBenchmark = new ListBenchmark("LinkedList", new LinkedListProvider());

        System.out.println("benchmarkResult = " + listBenchmark.computeBenchmark());
    }
}
