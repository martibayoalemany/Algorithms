package com.graphai.codility;

public class L3_CyclicRotation {

    public static Integer[] solution(Integer[] A, int K) {
        try {
            if (K == 0 || A.length == 0)
                return A;
            int length = A.length;
            K = K % A.length;
            if (K == 0)
                return A;
            Integer[] target = new Integer[length];
            try {
                System.arraycopy(A, 0, target, K, length - K);
                System.arraycopy(A, length - K, target, 0, K);
            } catch (Exception e) {
                System.out.printf("---> [%d] / %d - Failed \n", K, A.length);
            }

            return target;
        } catch (IndexOutOfBoundsException | ArrayStoreException | NullPointerException e1) {
            e1.printStackTrace();
        }
        return new Integer[0];
    }

}
