import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This code contains unfinished code
 */
@Deprecated
public class _Sorting {

    public void array_methods() {
        String msg = "";
        List<Integer> list = new LinkedList<>();
        IntStream range = IntStream.rangeClosed(1, 20_000);
        range.forEach(list::add);

        // List -> Integer[]
        Integer[] array = array = list.toArray(new Integer[1]);

        // List -> int[]
        int[] arrayUnboxed = list.stream().mapToInt(Integer::intValue).toArray();
    }

    private final ExecutorService executors = Executors.newCachedThreadPool();

    private void merge_sort_parallel(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;

        int middle = (lowerIndex + higherIndex) / 2;
        Future f1 = executors.submit(() -> merge_sort(array, lowerIndex, middle));
        Future f2 = executors.submit(() -> merge_sort(array, middle + 1, higherIndex));
        try {
            f2.get();
            f1.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            return;
        }

        // Copy up tho the middle
        for (int i = lowerIndex, j = 0; i <= middle && i < array.length; i++, j++)
            this.tmp_array[i] = array[i];

        // Compare the copy from 0..length with the elements from m.length and swap accordingly
        int i = lowerIndex, j = middle + 1, k = lowerIndex;
        while (i <= middle && j <= higherIndex && j < array.length)
            array[k++] = tmp_array[i] <= array[j] ? tmp_array[i++] : array[j++];

        while (i <= middle)
            array[k++] = tmp_array[i++];

    }

    private final ForkJoinPool forkJoin = new ForkJoinPool();

    public class MergeSortRecursive extends RecursiveAction {
        protected final int sThreshold = 100_000;
        private int mLength = 20_000;
        private Integer[] _array;
        private Integer[] _tmp_array;
        private int lowerIndex;
        private int higherIndex;

        private MergeSortRecursive() {

        }

        public MergeSortRecursive(Integer[] array, int lowerIndex, int higherIndex) {
            this._array = array;
            this._tmp_array = new Integer[array.length];
            this.lowerIndex = lowerIndex;
            this.higherIndex = higherIndex;
        }

        private void computeDirectly() {
            merge_sort(lowerIndex, higherIndex);
        }

        protected void compute() {
            if (mLength < sThreshold) {
                computeDirectly();
                return;
            }

            int split = mLength / 2;

            MergeSortRecursive firstHalf = new MergeSortRecursive(_array, lowerIndex, split);
            MergeSortRecursive secondHalf = new MergeSortRecursive(_array, split + 1, higherIndex);
            firstHalf.fork();
            secondHalf.fork();
            secondHalf.join();
            firstHalf.join();

            // Copy all elements up to the middle
            for (int i = lowerIndex, j = 0; i <= middle && i < array.length; i++, j++)
                this._tmp_array[i] = _array[i];

            // Compare the copy from 0..length with the elements from m.length and swap accordingly
            int i = lowerIndex, j = middle + 1, k = lowerIndex;
            while (i <= middle && j <= higherIndex && j < array.length)
                _array[k++] = _tmp_array[i] <= _array[j] ? _tmp_array[i++] : _array[j++];

            while (i <= middle)
                _array[k++] = _tmp_array[i++];
        }
    }
}