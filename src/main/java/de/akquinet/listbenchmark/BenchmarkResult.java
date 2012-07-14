package de.akquinet.listbenchmark;

/**
 *
 *
 */
public class BenchmarkResult {
    private final String name;
    private final long durationMs;
    private final long memoryConsumption;

    public BenchmarkResult(String name, final long durationMs, final long memoryConsumption) {
        this.name = name;
        this.durationMs = durationMs;
        this.memoryConsumption = memoryConsumption;
    }


    @Override
    public String toString() {
        return name + ": " + durationMs + "\t" + memoryConsumption + "\n";
    }
}
