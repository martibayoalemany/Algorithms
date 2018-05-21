package src.main.com.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

class TapeEquilibrium {

    public static void main(String[] args) {
        int[] array = {3,1,2,4,3};
        System.out.printf("Tape Equilibrium : %d", solution(array));
    }

    public static int solution(int[] array) {
        final Map<Integer, Integer> map = new LinkedHashMap<>();
        int max_value = Integer.MIN_VALUE;
        int min_value = Integer.MAX_VALUE;
        for(int value : array) {
            Integer result = map.computeIfPresent(value, (k, v) -> v + 1);
            if (result == null)
                map.put(value, 1);
            min_value = min_value > value ? value : min_value;
            max_value = max_value < value ? value : max_value;
        }

        int[] stack = new int[2];
        int i = 0;
        for(int value = min_value; value < max_value; i++) {
            while(map.computeIfPresent(value, (k,v) -> v -1) > 0) {
                i = (i + 1) % 2;
                stack[i] += value;
            }
        }
        return Math.abs(stack[1] - stack[0]);
    }
}