package com.algorithms;

import com.algorithms.codility.TapeEquilibrium;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class TapeEquilibriumTest extends ParameterizedTest {

    public TapeEquilibriumTest(Integer[] data) {
        super(data);
    }

    @Test
    public void solution2() throws Exception {
        System.out.println(Stream.of(data).map(String::valueOf).limit(200).collect(Collectors.joining(",")));
        System.out.printf(":= %d\n", TapeEquilibrium.solution2(this.data));
    }

}