package test.com.graphai;

import src.main.com.algorithms.codility.L3_CyclicRotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RunWith(Parameterized.class)
public class L3_CyclicRotationTest {
    private final int K;
    private Integer[] data;

    @Parameters
    public static Collection<Object[]> data() {
        Random rnd = new Random();
        Integer[] longParam = new Integer[100];
        Arrays.setAll(longParam, s -> rnd.nextInt());

        return Arrays.asList(new Object[][]{
                {new Integer[]{3, 8, 9, 7, 6}}, {new Integer[0]}, {new Integer[]{0, 1}}, {longParam}
        });
    }

    public L3_CyclicRotationTest(Integer[] data) {
        this.data = data;
        this.K = 3;
    }

    @Test
    public void solution() throws Exception {
        Integer[] result = L3_CyclicRotation.solution(data, K);
        System.out.printf("--- Rotation by %d ----\n", K);
        System.out.println(Stream.of(data).map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println("===");
        trace(result);
    }

    private void trace(Integer[] result) {
        Iterator<Integer> fromStart = Stream.of(result).collect(Collectors.toCollection(LinkedList::new)).iterator();
        Iterator<Integer> fromEnd = Stream.of(result).collect(Collectors.toCollection(LinkedList::new)).descendingIterator();


        String startStr = StreamSupport.stream(Spliterators.spliteratorUnknownSize(fromStart, Spliterator.ORDERED), false)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String endStr = StreamSupport.stream(Spliterators.spliteratorUnknownSize(fromEnd, Spliterator.ORDERED), false)
                .map(String::valueOf).limit(K)
                .collect(Collectors.joining(","));

        System.out.println(startStr);
        System.out.println("---");
        System.out.println(endStr);
    }

}