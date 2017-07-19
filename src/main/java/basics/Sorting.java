import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sorting {
    private final int PARTIAL_FACTOR = 2;

    public static void main(String[] args) {
        new Sorting().execute_sorting();
    }

    public Integer[] newIntegerArray(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.iterate(1, n -> n + 1).limit(size).forEach(list::add);
        return list.toArray(new Integer[1]);
    }

    public Object[] newObjectArray(int size) {
        return Arrays.stream(IntStream.iterate(1, n -> n + 1).limit(size).toArray()).boxed().toArray();
    }

    public int[] newIntArray(int size) {
        return Arrays.stream(IntStream.iterate(1, n -> n + 1).limit(size).toArray()).toArray();
    }

    public void array_methods() {
        String msg = "";
        List<Integer> list = new LinkedList<>();
        try (Timing t = new Timing("Initialization")) {
            IntStream range = IntStream.rangeClosed(1, 20_000);
            range.forEach(list::add);
        }

        Integer[] array = null;
        try (Timing t = new Timing("Convert to array")) {
            array = list.toArray(new Integer[1]);
            this.tmp_array = new Integer[array.length];
        }

        int[] arrayUnboxed = null;
        try (Timing t = new Timing("Convert to unboxed array")) {
            arrayUnboxed = list.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    public void execute_sorting() {
        //final Stream<Integer> sizess = Stream.of(20_000, 200_000, 2_000_000, 20_000_000);
        final Stream<Integer> sizess = Stream.of(20_000);
        final List<Integer> sizes = sizess.collect(Collectors.toList());
        sizes.stream().forEach((size) -> {
            for (int i = 0; i < 2; i++) {
                boolean shuffler = (i == 0);
                check_sorting("selection ", (s) -> selection_sort(s), size, shuffler);
                check_sorting("insertion ", (s) -> insertion_sort(s), size, shuffler);
                check_sorting("bubble ", (s) -> bubble_sort(s), size, shuffler);
                check_sorting("shell ", (s) -> shell_sort(s), size, shuffler);
                check_sorting("merge sort ", (s) -> merge_sort(s, 0, s.length), size, shuffler);
                check_sorting("merge sort 2 ", (s) -> merge_sort_2(s, 0, s.length), size, shuffler);
                check_sorting("merge sort 3 ", (s) -> merge_sort_3(s, 0, s.length), size, shuffler);
                check_sorting("Arrays.sort ", (s) -> Arrays.sort(s), size, shuffler);
                check_sorting("Arrays.parallelSort ", (s) -> Arrays.parallelSort(s), size, shuffler);
                check_sorting_int("Stream + parallel + sort ",
                        (s) -> Arrays.stream(s).parallel().sorted().toArray(), size, shuffler);
            }
        });
    }

    private void check_sorting(final String msg, Consumer<Integer[]> consumer, Integer size,
            final boolean fullShuffler) {
        Integer[] array = newIntegerArray(size);        
        String dataKind = fullShuffler ? "Full" : "Partial";        

        if (fullShuffler) {
            shuffle(array);
        } else {
            partial_shuffle(array);
        }

        try (Timing t = new Timing(msg, dataKind, size)) {
            consumer.accept(array);
        }

        assert_sorted(array, 80);
    }

    private void check_sorting_int(final String msg, Consumer<int[]> consumer, Integer size,
            final boolean fullShuffler) {
        int[] param = newIntArray(size);        
        String dataKind = fullShuffler ? "Full" : "Full";        
        shuffle(param);

        try (Timing t = new Timing(msg, dataKind, size)) {
            consumer.accept(param);
        }

        assert_sorted(param, 80);
    }

    /**
     * It swaps the item with the smallest one in the array, iterating once from 0 to n
    */
    private void selection_sort(Integer[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++)
                if (array[j] < array[k])
                    k = j;

            Integer tmp = array[i];
            array[i] = array[k];
            array[k] = tmp;
        }
    }

    /**
     * It swaps consecutives items iterating from 1 to end and in each iteration from i to 0 
     */
    private void insertion_sort(Integer[] array) {
        int end = array.length;
        for (int i = 1; i < end; i++) {
            for (int k = i; k >= 1 && array[k] < array[k - 1]; k--) {
                Integer tmp = array[k];
                array[k] = array[k - 1];
                array[k - 1] = tmp;
            }
        }
    }

    /**
     * - It swaps consecutive items iterating end times and in each iteration from i to n
     * - It stops when no swapped ocurred in a i to n iteration
     */
    private void bubble_sort(Integer[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[i]) {
                    Integer tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    /**
     * It divides the iteration recursively by 3 and applies insertion sort to every part of the interval
     */
    private void shell_sort(Integer[] array) {
        int n = array.length;
        int h = 1;
        while (h < n)
            h = 3 * h;
        while (h > 0) {
            h = h / 3;
            int k = 0;
            int iters = 0;
            while (h != 0 && k + h < n) {
                insertion_sort(array, k, k + h);
                k = k + h;
                iters++;
            }
        }
    }

    /**
    * It swaps consecutive items iterating from start to end and in each iteration from i to 0 
    */
    private void insertion_sort(Integer[] array, int start, int end) {
        for (int i = start; i <= end; i++) {
            for (int k = i; k >= 1 && array[k] < array[k - 1]; k--) {
                Integer tmp = array[k];
                array[k] = array[k - 1];
                array[k - 1] = tmp;
            }
        }
    }

    private void merge_sort(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;

        int middle = (lowerIndex + higherIndex) / 2;
        merge_sort(array, lowerIndex, middle);
        merge_sort(array, middle + 1, higherIndex);

        // Copy up to higher     
        Integer[] tmp_array = new Integer[array.length];
        for (int i = lowerIndex, j = 0; i <= higherIndex && i < array.length; i++, j++)
            tmp_array[i] = array[i];

        // Compare the copy from 0..length with the elements from m.length and swap accordingly
        int i = lowerIndex, j = middle + 1, k = lowerIndex;
        while (i <= middle && j <= higherIndex && j < array.length)
            array[k++] = tmp_array[i] <= tmp_array[j] ? tmp_array[i++] : tmp_array[j++];

        while (i <= middle)
            array[k++] = tmp_array[i++];

    }

    private void merge_sort_2(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;

        int middle = (lowerIndex + higherIndex) / 2;
        merge_sort_2(array, lowerIndex, middle);
        merge_sort_2(array, middle + 1, higherIndex);

        // Copy up tho the middle
        if (tmp_array == null || tmp_array.length < array.length)
            tmp_array = new Integer[array.length];
        for (int i = lowerIndex, j = 0; i <= middle && i < array.length; i++, j++)
            tmp_array[i] = array[i];

        // Compare the copy from 0..length with the elements from m.length and swap accordingly
        int i = lowerIndex, j = middle + 1, k = lowerIndex;
        while (i <= middle && j <= higherIndex && j < array.length)
            array[k++] = tmp_array[i] <= array[j] ? tmp_array[i++] : array[j++];

        while (i <= middle)
            array[k++] = tmp_array[i++];

    }

    private Integer[] tmp_array;

    private void merge_sort_3(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;
        if (tmp_array == null)
            tmp_array = new Integer[higherIndex - lowerIndex + 1];

        int middle = (lowerIndex + higherIndex) / 2;
        merge_sort_3(array, lowerIndex, middle);
        merge_sort_3(array, middle + 1, higherIndex);

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

    private final ExecutorService executors = Executors.newCachedThreadPool();

    /**
     * TODO: Work in progress
     */
    private void merge_sort_3_parallel(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;

        int middle = (lowerIndex + higherIndex) / 2;
        Future f1 = executors.submit(() -> merge_sort_3(array, lowerIndex, middle));
        Future f2 = executors.submit(() -> merge_sort_3(array, middle + 1, higherIndex));
        try {
            f1.get();
            f2.get();
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

    /**
     * TODO: Work in progress
     */
    public class MergeSort3Recursive extends RecursiveAction {
        protected final int sThreshold = 100_000;
        private int mLength = 200_000;
        private Integer[] array;
        private Integer[] tmp_array;
        private int lowerIndex;
        private int higherIndex;

        private MergeSort3Recursive() {

        }

        public MergeSort3Recursive(Integer[] array, int lowerIndex, int higherIndex) {
            this.array = array;
            this.tmp_array = new Integer[array.length];
            this.lowerIndex = lowerIndex;
            this.higherIndex = higherIndex;
        }

        private void computeDirectly() {
            merge_sort(lowerIndex, higherIndex);
        }

        private void merge_sort(int lowerIndex, int higherIndex) {
            if (lowerIndex >= higherIndex)
                return;

            int middle = (lowerIndex + higherIndex) / 2;
            merge_sort(lowerIndex, middle);
            merge_sort(middle + 1, higherIndex);

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

        protected void compute() {
            if (mLength < sThreshold) {
                computeDirectly();
                return;
            }

            int split = mLength / 2;

            invokeAll(new MergeSort3Recursive(array, lowerIndex, split),
                    new MergeSort3Recursive(array, split + 1, higherIndex));
        }
    }

    private void merge_sort_4(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;

        int middle = (lowerIndex + higherIndex) / 2;
        merge_sort_4(array, lowerIndex, middle);
        merge_sort_4(array, middle + 1, higherIndex);

        // Copy up tho the middle        
        System.arraycopy(array, lowerIndex, tmp_array, lowerIndex, (middle - lowerIndex));

        // Compare the copy from 0..length with the elements from m.length and swap accordingly
        int i = lowerIndex, j = middle + 1, k = lowerIndex;
        while (i <= middle && j <= higherIndex && j < array.length)
            array[k++] = tmp_array[i] <= array[j] ? tmp_array[i++] : array[j++];

        while (i <= middle)
            array[k++] = tmp_array[i++];

    }

    /**
     * Prints an snapshot of 8 shuffles from :Integer[] array:
     * Shuffles with :array.length: changes and prints an snapshot again
     */
    private void snapshot_shuffle(Integer[] array) {
        snapshot(array);
        shuffle(array);
        snapshot(array);
    }

    /**
     * Prints an snapshot of a number of :elements: from :start_idx: out of the :list:
     */
    private void snapshot(List<Integer> list, int start_idx, int elements) {
        int end_idx = start_idx + elements;
        try (Timing t = new Timing("Snapshot " + elements + " elements")) {
            for (int i = start_idx; i < end_idx; i++)
                System.out.printf("%d\t", list.get(i));
            System.out.println("\n");
        }
    }

    /**
     * Prints an snapshot of a number of 8 elements from the middle of a :Integer[] array: 
     */
    private void snapshot(Integer[] array) {
        try (Timing t = new Timing("Snapshot 8 elements")) {
            int size = array.length;
            for (int i = size / 2; i < size / 2 + 8; i++)
                System.out.printf("%d\t", array[i]);
            System.out.println("\n");
        }
    }

    /**
     * Shuffles a number of elements in an :array: of Integer
     * The number of shuffles is :array.length:
     */
    private void shuffle(Integer[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * Shuffles a number of elements in an :array: of int
     * The number of shuffles is :array.length:
     */
    public void shuffle(int[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * Shuffles a number of elements in the :array: depending on :PARTIAL_FACTOR:
     * :PARTIAL_FACTOR: = 1 - The number of shuffles is :array.length:
     * :PARTIAL_FACTOR: = 9 - The number of shuffles is :array.length:/9     
     */
    public void partial_shuffle(Integer[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = (array.length / PARTIAL_FACTOR) - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * Asserts whether the last :limit: elememts and the firsts :limit: elements are sorted
     */
    private void assert_sorted(final Object[] array, int limit) {
        __assert_sorted(array, 1, limit);
        __assert_sorted(array, array.length - limit, array.length);
    }

    private void __assert_sorted(final Object[] array, int start, int end) {
        IntPredicate isNotSorted = i -> ((Comparable) array[i - 1]).compareTo(array[i]) > 0;
        if (IntStream.range(start, end).anyMatch(isNotSorted)) {
            IntStream.range(start, start + 6).forEach(i -> System.out.printf(" %,d\t", array[i]));
            System.out.println("\n======================");
        }
    }

    private void assert_sorted(final int[] array, int limit) {
        __assert_sorted(array, 1, limit);
        __assert_sorted(array, array.length - limit, array.length);
    }

    private void __assert_sorted(final int[] array, int start, int end) {
        IntPredicate isNotSorted = i -> array[i - 1] > array[i];
        if (IntStream.range(start, end).anyMatch(isNotSorted)) {
            IntStream.range(start, start + 6).forEach(i -> System.out.printf(" %,d\t", array[i]));
            System.out.println("\n======================");
        }
    }

    /**
     * Asserts whether the last :limit: elememts and the firsts :limit: elements are sorted
     * @see __assert_sorted for a better implementation
     */
    private void assert_sorted(Integer[] array, int limit) {
        boolean sorted = true;
        int k = 0;
        for (int i = 0; i < limit; i++) {
            if (sorted && array[i] > array[i + 1]) {
                System.out.println("Array start not sorted, position " + i);
                sorted = false;
            }
            if (!sorted)
                System.out.printf("%,d \t", array[i]);
            if (!sorted && k++ > 20)
                break;
        }
        if (!sorted)
            System.out.println("\n========================");
        k = 0;
        sorted = true;
        for (int i = array.length - 1; i > (array.length - limit); i--) {
            if (sorted && array[i - 1] > array[i]) {
                System.out.println("Array end not sorted, position " + i);
                sorted = false;
            }
            if (!sorted)
                System.out.printf("%,d \t", array[i]);
            if (!sorted && k++ > limit)
                break;
        }
        if (!sorted)
            System.out.println("\n========================");
    }
    
    private static AtomicBoolean isFirstMessage;
    static {
        isFirstMessage = new AtomicBoolean(false);
    }

    public class Timing implements AutoCloseable {
        private long lstart;
        private long lend;
        private long elements;
        private String name;
        private String dataKind;
        private String message;
        private Instant start;
        private Instant end;

        public Timing(String message) {
            this.message = message;
            this.start = Instant.now();
            this.lstart = System.currentTimeMillis();
        }

        public Timing(String name, String dataKind, int elements) {
            this.message = null;
            this.name = name;
            this.dataKind = dataKind;
            this.elements = elements;  

            // Timing
            this.lstart = System.currentTimeMillis();
            this.start = Instant.now();            
            if (isFirstMessage.compareAndSet(false, true))
                System.out.printf("%-30s,\t%-8s,\t%-12s,\t %-10s\n", "name", "dataKind", "elements","duration (ms)");
        }

        @Override
        public void close() {
            this.end = Instant.now();
            this.lend = System.currentTimeMillis();
            java.time.Duration dura = Duration.between(start, end);
            if (message == null) {
                System.out.printf("%-30s,\t%-8s,\t%-12s,\t %,d\n", name, dataKind, elements, (lend - lstart));
            } else {
                System.out.printf("--- %-58s ---\t [%,d s / %,d ns / %,d ms]\n", message, dura.getSeconds(),
                        dura.getNano(), (lend - lstart));

            }

        }
    }

}
