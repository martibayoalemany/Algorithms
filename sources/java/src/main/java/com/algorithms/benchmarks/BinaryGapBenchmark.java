package com.algorithms.benchmarks;

import com.algorithms.codility.BinaryGap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 2)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BinaryGapBenchmark {

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(BinaryGap.class.getSimpleName())
                .forks(8)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

    @State(Scope.Benchmark)
    public static class SharedCounters {
        public final AtomicInteger value = new AtomicInteger(20);
    }

    @State(Scope.Thread)
    public static class ThreadCounters {
        public Integer value = 20;
    }

    @Benchmark
    public void binaryGap(SharedCounters sc, ThreadCounters tc) {
        new BinaryGap().execute(200_0000);
    }

    @Benchmark
    public void binaryGapLarge() {
        new BinaryGap().execute(200_000_0000);
    }

    @Benchmark
    public void binaryGap2(SharedCounters sc, ThreadCounters tc) {

        new BinaryGap().execute2(200_0000);
    }

    @Benchmark
    public void binaryGapLarge2() {
        new BinaryGap().execute2(200_000_0000);
    }
}
