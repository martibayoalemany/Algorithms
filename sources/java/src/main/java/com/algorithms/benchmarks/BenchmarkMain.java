package com.algorithms.benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkMain {
    public static void main(String[] args) throws RunnerException {
        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(BinaryGapBenchmark.class.getSimpleName())
                //.include(OddOcurrencesInArrayBenchmark.class.getSimpleName())
                //.include(LinkedListBenchmarks.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
