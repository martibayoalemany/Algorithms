package com.graphai.benchmarks;

import com.graphai.L1_BinaryGap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class AlgorithmsBenchmarks {

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(AlgorithmsBenchmarks.class.getSimpleName())
                .forks(2)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void binaryGap() {
        new L1_BinaryGap().execute(200_0000);
    }

    @Benchmark
    public void binaryGapLarge() {
        new L1_BinaryGap().execute(200_000_0000);
    }
}
