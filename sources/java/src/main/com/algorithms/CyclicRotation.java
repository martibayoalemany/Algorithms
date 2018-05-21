package src.main.com.algorithms;

import java.util.Arrays;
import java.util.Random;

class CyclicRotation {

    public static void main(String[] args) {

        Random rnd = new Random();
        int[] longParam = new int[100];
        Arrays.setAll(longParam, s -> rnd.nextInt());
        int[][] params = new int[][] { { 3, 8, 9, 7, 6 }, {}, { 0, 1 }, longParam };
        for (int[] param : params)
            for (int i = 0; i < 20 * param.length; i++)
                trySolution(param, i);

    }

    public static void trySolution(int[] A, int K) {
        int[] result = solution(A, K);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(K).append("]").append("\t");
        for (int s : A)
            sb.append(s).append('\t');
        sb.append(" | ").append("\t");
        for (int s : result)
            sb.append(s).append('\t');
        System.out.println(sb.toString());
    }

    public static int[] solution(int[] A, int K) {
        try {
            if (K == 0 || A.length == 0)
                return A;
            int length = A.length;
            K = K % A.length;
            if (K == 0)
                return A;
            int[] target = new int[length];
            try {
                System.arraycopy(A, 0, target, K, length - K);
                System.arraycopy(A, length - K, target, 0, K);
            } catch (Exception e) {
                System.out.printf("---> [%d] / %d - Failed \n", K, A.length);
            }

            return target;
        } catch (IndexOutOfBoundsException e1) {
            e1.printStackTrace();
        } catch (ArrayStoreException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
        return new int[] {};
    }
}
