package com.graphai;

import java.util.LinkedHashMap;
import java.util.Map;

class PermMissingElem {

    public static void main(String[] args) {
        int[] array = {2,3,1,5};
        System.out.printf("Solution: %d", solution(array));

    }

    public static int solution(int[] A) {
        final Map<Integer, Boolean> map = new LinkedHashMap<>();
        int max_value = Integer.MIN_VALUE;
        int min_value = Integer.MAX_VALUE;
        for(int value : A) {
            map.put(value, true);
            min_value = min_value > value ? value : min_value;
            max_value = max_value < value ? value : max_value;
        }

        for(int i = min_value; i < max_value; i++) {
            if(map.computeIfPresent(i, (k, v) -> true) == null)
                return i;
        }

        return -1;
    }
}