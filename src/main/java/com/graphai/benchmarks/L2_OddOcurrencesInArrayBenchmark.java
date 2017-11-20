package com.graphai.benchmarks;

import com.graphai.codility.L2_OddOccurrencesInArray;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 2)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class L2_OddOcurrencesInArrayBenchmark {

    @State(Scope.Thread)
    public static class Datas {
        public int[] target;
        public Datas() {
            final int elements = 600;
            int[] origin = new int[elements / 2];
            target = new int[2 * elements];
            Arrays.setAll(origin, s -> ThreadLocalRandom.current().nextInt(0, 1_000_000_000));
            System.arraycopy(origin, 0, target, 0, origin.length);
            System.arraycopy(origin, 0, target, elements - 1, origin.length);
        }

    }

    @Benchmark
    public void solution1(Datas data) {
        L2_OddOccurrencesInArray.solution(data.target);
    }

    @Benchmark
    public void solution3(Datas data) {
        L2_OddOccurrencesInArray.solution3(data.target);
    }
}
