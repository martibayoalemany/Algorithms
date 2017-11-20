package com.graphai.codility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class L3_PermMissingElem {

    public static int solution(Integer[] A) {
        long len = A.length;
        long sum = 0;
        for (int i = 0; i < len; i++) {
            sum += A[i];
        }
        return (int) ((len + 1) * (len + 2) / 2 - sum);
    }
}