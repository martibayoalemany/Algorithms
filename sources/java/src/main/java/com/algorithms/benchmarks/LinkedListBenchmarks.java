package com.algorithms.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LinkedListBenchmarks {


    private static final int INC = 10_000;
    private static final int END = 2_000_000;

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(LinkedListBenchmarks.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

    @State(Scope.Thread)
    public static class Data {
        static List<Integer> targetList;
        static LinkedList<Integer> targetlinkedList;
        static {
            targetList = IntStream.rangeClosed(0, END).boxed().collect(Collectors.toList());
            targetlinkedList = new LinkedList<>();
            IntStream.rangeClosed(0, END).forEach(targetlinkedList::add);
        }
        public Data() {
            System.out.println(Data.class.getSimpleName());
        }
    }

    public LinkedListBenchmarks() {
        System.out.println(LinkedListBenchmarks.class.getSimpleName() + " constructor");
    }

    @Benchmark
    public void removeFromStart() {
        IntStream.rangeClosed(0, INC).forEach(Data.targetList::remove);
    }

    @Benchmark
    public void removeFromStartLinked() {
        IntStream.rangeClosed(0, INC).forEach(Data.targetlinkedList::remove);
    }

    @Benchmark
    public void removeFromEnd() {
        int currentEnd = Data.targetList.size() -1;
        IntStream.rangeClosed(currentEnd - INC, currentEnd).forEach(Data.targetList::remove);
    }

    @Benchmark
    public void removeFromEndLinked() {
        int currentEnd = Data.targetList.size() -1;
        IntStream.rangeClosed(currentEnd - INC, currentEnd).forEach(Data.targetlinkedList::remove);
    }
}
