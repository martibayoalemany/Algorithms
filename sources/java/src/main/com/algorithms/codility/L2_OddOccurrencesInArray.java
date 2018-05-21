package src.main.com.algorithms.codility;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class L2_OddOccurrencesInArray {
    public static void main(String[] args) {

        {
            int[] first = { 9, 3, 9, 3, 9, 7, 9 };
            int result = solution(first);
            for (int v : first)
                System.out.printf("%d\t", v);
            System.out.println("\n");
            System.out.println(result);
        }

        {
            // Integer.MAX_VALUE 2_147_483_647                        
            int half_count = 1000000;
            int[] origin = new int[half_count];
            int[] target = new int[2 * half_count + 1];
            Arrays.setAll(origin, s -> ThreadLocalRandom.current().nextInt(0, 1_000_000_000));
            System.arraycopy(origin, 0, target, 0, origin.length);
            System.arraycopy(origin, 0, target, half_count, origin.length);
            target[2 * half_count] = origin[0];
            System.out.println("Expected: " + origin[0]);
            Instant start = Instant.now();
            int result = solution(target);
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            System.out.printf("Got: %d [%d s]\n", result, duration.getSeconds());
        }
    }

    public static int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i : A)
            if (set.contains(i))
                set.remove(i);
            else
                set.add(i);
        return set.iterator().next();
    }

    /**
     * N is an odd integer within the range [1..1,000,000];
    *  each element of array A is an integer within the range [1..1,000,000,000];
    *  This solution has N**2 complexity because the wrong usage of linkedlist
     */
    @Deprecated
    public static int solution2(int[] A) {
        // is_odd byte is 0
        List<Integer> values = new LinkedList<Integer>();

        int size = 0;
        for (int value : A) {
            int position = -1;
            for (int idx = 0; idx < size; idx++)
                if (values.get(idx) == value)
                    position = idx;

            if (position != -1) {
                values.remove(position);
                size--;
            } else {
                values.add(value);
                size++;
            }
        }
        return values.get(0);
    }

    /**
    * N is an odd integer within the range [1..1,000,000];
    *  each element of array A is an integer within the range [1..1,000,000,000];
    */
    public static int solution3(int[] A) {
        // Check whether we have a match of A[i] in A[i+1..length]
        // if we have a match cancel the two values
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0)
                continue;
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] == A[j]) {
                    A[i] = 0;
                    A[j] = 0;
                    break;
                }
            }
            if (A[i] != 0)
                return A[i];
        }

        return -1;
    }
}