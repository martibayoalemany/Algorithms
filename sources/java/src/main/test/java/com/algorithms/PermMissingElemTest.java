package com.algorithms;

import com.algorithms.codility.PermMissingElem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PermMissingElemTest {
    protected final Integer[] data;
    protected final Integer expected;

    public PermMissingElemTest(Integer[] data, Integer expected) {
        this.data = data;
        this.expected = expected;
    }

    @Parameters(name = "{index}: solution({0})={1}")
    public static Collection<Object[]> data() {
        Random rnd = new Random();
        Integer[] longParam = new Integer[1_000_0000];
        Arrays.setAll(longParam, s -> rnd.nextInt());

        return Arrays.asList(new Object[][]{
                {new Integer[]{3, 5, 6, 2, 1}, 4},
                {new Integer[]{3, 6, 4, 2, 1}, 5},
                {new Integer[]{1,2,3,4,5,6,7,9}, 8},
                {IntStream.rangeClosed(1, 200_000).filter(s -> s != 100_000).boxed().toArray(Integer[]::new), 100_000}

        });
    }



    @Test
    public void solution() throws Exception {
        Integer actual = PermMissingElem.solution(data);
        assertThat(actual, is(equalTo(expected)));
    }

}