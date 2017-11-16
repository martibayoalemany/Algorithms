package com.graphai;

public class L1_BinaryGap {

    public static void main(String[] args) {
        int value = 654345;
        int result = new L1_BinaryGap().execute(value);
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
}
