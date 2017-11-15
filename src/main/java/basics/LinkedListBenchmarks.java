package basics;

import org.openjdk.jmh.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinkedListBenchmarks {
    private List<Integer> targetList;
    private LinkedList<Integer> targetlinkedList;

    public static void main(String[] args) {
        LinkedListBenchmarks lb = new LinkedListBenchmarks();

        lb.targetList = IntStream.rangeClosed(0, 200_000).boxed().collect(Collectors.toList());
        lb.targetlinkedList = new LinkedList<>();
        IntStream.rangeClosed(0, 200_000).forEach(lb.targetlinkedList::add);
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public void removeFromStart() {
        IntStream.rangeClosed(0, 10_0000).forEach(this.targetList::remove);
    }



    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public void removeFromStartLinked() {
        IntStream.rangeClosed(0, 10_0000).forEach(this.targetlinkedList::remove);
    }

}
