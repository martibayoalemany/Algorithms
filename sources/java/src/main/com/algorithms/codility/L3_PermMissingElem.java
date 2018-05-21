package src.main.com.algorithms.codility;

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