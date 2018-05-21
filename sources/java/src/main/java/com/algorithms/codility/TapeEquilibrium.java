package com.algorithms.codility;

import java.util.stream.Stream;

public class TapeEquilibrium {

    public static int solution(Integer[] array) {
        if(array.length == 0)
            return -1;
        int min = Integer.MAX_VALUE;
        int start = array[0];
        int end = Stream.of(array).skip(1).reduce(0, (s,v) -> s + v);

        for(int i = 1; i < array.length; i++ ) {
            start += array[i];
            end -= array[i];
            min = Math.min(min, Math.abs(end - start));
        }
        return min;
    }

    public static int solution2(Integer[] array) {
        if(array.length == 0)
            return -1;
        if(array.length == 1)
            return array[0];

        int start = array[0];
        int end = 0;
        for(int i= 1; i < array.length; i ++)
            end += array[i];
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < array.length; i++ ) {
            int diff = Math.abs(end - start);
            min = diff < min ? diff : min;
            start += array[i];
            end -= array[i];
        }

        // diff, start, end, array[i], min
        return min;
    }

}