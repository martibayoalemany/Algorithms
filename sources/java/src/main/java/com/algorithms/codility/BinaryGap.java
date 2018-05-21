package com.algorithms.codility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryGap {

    public static void main(String[] args) {
        System.out.printf("Result %d \n", new BinaryGap().execute(654345));
        System.out.printf("Result %d \n", new BinaryGap().execute2(654345));
    }

    public Integer execute(Integer value) {
        while (value > 0 && value % 2 == 0)
            value = value / 2;

        int currentGap = 0;
        int maxGap = 0;
        while (value > 0) {
            int remainder = value % 2;
            if (remainder == 0) {
                currentGap++;
            } else if (currentGap != 0) {
                maxGap = Math.max(currentGap, maxGap);
                currentGap = 0;
            }
            value = value / 2;
        }
        return maxGap;
    }

    public int execute2(Integer value) {

        List<Integer> gaps = new ArrayList<>();
        Integer gap = -1;
        while( (value = value / 2) > 0 ) {
            boolean is1 = value % 2 == 1;
            boolean is0 = !is1;
            if( is0 && gap == -1) continue;
            if( is1 && gap == -1) gap = 0;
            if( is0 && gap >= 0) gap = gap + 1;
            if( is1 && gap >= 0) {
                gaps.add(gap);
                gap = 0;
            }
        }
        return gaps.stream().max(Comparator.comparing(Integer::intValue)).orElse(-1);
    }

    public int execute3(Integer value) {
        Integer maxGap = -1;
        Integer gap = -1;
        do {
            int remainder = value % 2;
            if (gap == -1) {
                if (remainder == 1) gap = 0;
            } else if (remainder == 0) {
                gap++;
            } else {
                gap = 0;
                maxGap = Math.max(gap, maxGap);
            }
        } while((value = value / 2) > 0);
        return maxGap;
    }
}