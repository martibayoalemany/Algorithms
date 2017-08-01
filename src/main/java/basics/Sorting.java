import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sorting {
    private Integer[] tmp_array;
    private boolean long_running;

    public static void main(String[] args) {        
        Integer[] sample_sizes = null;
        for(String arg : args)
            System.out.println(arg);
        if (args != null && args.length > 0) {
            switch(args[0]) {
                case "range":
                    int step = Integer.parseInt(args[1]);
                    int size = Integer.parseInt(args[2]);
                    sample_sizes = newIntegerArray(step, step, size);
                    break;
                default:
                    sample_sizes = Stream.of(args).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                    System.out.println(args[0]);
                    break;
            }
        }
        if(sample_sizes != null)
            for(int size : sample_sizes)
                System.out.printf("%d\t", size);
        System.out.println("------------------");
        new Sorting().execute_sorting(sample_sizes);
    }

    public static Integer[] newIntegerArray(int size) {
        return Stream.iterate(1, n -> n + 1).limit(size).toArray(Integer[]::new);
    }

    public static Integer[] newIntegerArray(final int start, final int step, int size) {
        return Stream.iterate(start, n -> n + step).limit(size / step).toArray(Integer[]::new);
    }

    public void execute_sorting(Integer[] sample_sizes) {
        long_running = false;
        final Runtime rt = Runtime.getRuntime();
        System.out.printf("Total Memory: %,d\n Free Memory: %,d\n Max Memory: %,d\n", rt.totalMemory(), rt.freeMemory(),
                rt.maxMemory());
        //final Stream<Integer> sizess = Stream.of(20_000, 200_000, 2_000_000, 20_000_000);
        final Stream<Integer> sizess = sample_sizes == null ? Stream.of(20_000) : Stream.of(sample_sizes);
        final List<Integer> sizes = sizess.collect(Collectors.toList());
        sizes.stream().forEach((size) -> {
            for (int shuffler = 1; shuffler <= 1; shuffler++) {
                if(long_running) {
                    check_sorting("selection ", (s) -> selection_sort(s), size, shuffler);
                    check_sorting("insertion ", (s) -> insertion_sort(s), size, shuffler);
                    check_sorting("bubble ", (s) -> bubble_sort(s), size, shuffler);
                    check_sorting("shell ", (s) -> shell_sort(s), size, shuffler);
                }
                check_sorting("merge sort ", (s) -> merge_sort(s, 0, s.length), size, shuffler);
                check_sorting("Arrays.sort ", (s) -> Arrays.sort(s), size, shuffler);
                check_sorting("Arrays.parallelSort ", (s) -> Arrays.parallelSort(s), size, shuffler);
                check_sorting("Linked Hashmap", (s) -> linked_hash_map_sort(s), size, shuffler);

                // Stream + parallel + Sort
                Consumer<Integer[]> stream_parallel_sort = new Consumer<Integer[]>() {
                    public void accept(Integer[] arrays) {
                        LinkedList<Integer> list = new LinkedList<>();
                        Stream.of(arrays).parallel().sorted().forEachOrdered(list::add);
                        int i = 0;
                        for (Integer item : list)
                            arrays[i++] = item;
                    }
                };

                check_sorting("Stream + parallel + sort", stream_parallel_sort, size, shuffler);

            }

        });

        System.out.printf("--------- %d sorted measurements (speed) ------- \n", timings.size());
        for(String line : timings.stream().sorted().map(Timing::toString).toArray(String[]::new)) 
            System.out.printf(line);
        
        System.out.printf("--------- %d sorted measurements (memory) ------- \n", timings.size());
        for(String line : timings.stream().sorted((a,b) -> a.getMem().compareTo(b.getMem())).map(Timing::toString).toArray(String[]::new)) 
            System.out.printf(line);
    }

    private void check_sorting(final String msg, Consumer<Integer[]> consumer, Integer size,
            final int shuffler_factor) {
        Integer[] array = newIntegerArray(size);
        String dataKind = String.format("/ %d", shuffler_factor);

        shuffle(array, shuffler_factor);

        try (Timing t = new Timing(msg, dataKind, size)) {
            consumer.accept(array);
        }

        assert_sorted(array, 80);
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
     * It iterates from 1 to end if an item is to be swapped, it swaps backwards from i to 1
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
     * - It iterates from 0 to n and per each iteration from i to n
     * - It swaps consecutive elements pairwise from i to n 
     * - It stops iteration if no element was swapped
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

    /**
     * - It divides the problem in small arrays
     * - Each small arrays is divided by left and right and merged in order
     */
    private void merge_sort(Integer[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex)
            return;
        int tmp_size = higherIndex - lowerIndex + 1;
        if (tmp_size > 0 && (tmp_array == null || tmp_array.length < tmp_size))
            tmp_array = new Integer[higherIndex - lowerIndex + 1];

        int middle = (lowerIndex + higherIndex) / 2;
        merge_sort(array, lowerIndex, middle);
        merge_sort(array, middle + 1, higherIndex);

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

    /**
     * It relies on the performance of the LinkedHashMap.get method for the sorting.
     * Internally the LinkedHashMap splits the data into trees depending on the size of the data.
     */
    private void linked_hash_map_sort(Integer[] array) {

        final Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            // Increases the count of one
            Integer result = map.computeIfPresent(array[i], (k, v) -> v + 1);
            if (result == null)
                map.put(array[i], 1);
            minValue = minValue > array[i] ? array[i] : minValue;
            maxValue = maxValue < array[i] ? array[i] : maxValue;
        }

        int idx = 0;
        for (int i = minValue; i <= maxValue; i++) {
            Integer value = map.get(i);
            if (value != null)
                for (int j = 0; j < value; j++)
                    array[idx++] = i;
        }
    }

    /**
     * Prints an snapshot of 8 shuffles from :Integer[] array:
     * Shuffles with :array.length: changes and prints an snapshot again
     */
    private void snapshot_shuffle(Integer[] array) {
        snapshot(array);
        shuffle(array, 1);
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
    private void shuffle(Integer[] array, int partial_factor) {
        if (array.length == 0)
            return;
        Random rnd = ThreadLocalRandom.current();
        for (int i = (array.length / partial_factor) - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
    }

    private void assert_sorted(final Integer[] array, int limit) {
        __assert_sorted(array, 1, limit);
        __assert_sorted(array, array.length - limit, array.length);
    }

    private void __assert_sorted(final Integer[] array, int start, int end) {
        // there is no definition for sorting only one element
        if(start == end) return;
        Predicate<Integer> isNotSorted = i -> 0 < i - 1 && i < array.length
                && ((Integer) array[i - 1]).compareTo((Integer) array[i]) > 0;
        if (IntStream.range(start, end).mapToObj(i -> array[i]).anyMatch(isNotSorted)) {
            IntStream.range(start, start + 6).forEach(i -> System.out.printf(" %,d\t", array[i]));
            IntStream.range(end - 6, end).forEach(i -> System.out.printf(" %,d\t", array[i]));
            System.out.println("\n======================");
        }
    }

    private static List<Timing> timings;    
    private static AtomicBoolean isFirstMessage;
    static {
        isFirstMessage = new AtomicBoolean(false);
        timings = new ArrayList<Timing>();        
    }

    /**
     * It provides an AutoCloseable, to be used with try(new Timing()), for duration and memory usage logging
     */
    public class Timing implements AutoCloseable, Comparable<Timing> {
        private final long elements;
        private final String name;
        private final String dataKind;
        private final String message;        

        private long lstart;
        private long lend;
        private Instant start;
        private Instant end;
        private Duration duration;
        private long memUsedBefore;
        private long memUsedAfter;
        private String formatHeader;
        private String formatValue;
        private int[] valuesFormatWidth;        

        public Long getMillis() {
            return lend - lstart;
        }

        public Long getMem() {
            return memUsedAfter - memUsedBefore;
        }

        public Timing(String message) {
            this.elements = 0;
            this.name = "";
            this.dataKind = "";
            this.message = message;
            this.start = Instant.now();
            this.lstart = System.currentTimeMillis();
            timings.add(this);
        }

        private String repeat(int count, String with) {
            return new String(new char[count]).replace("\0", with);
        }        

        public Timing(String name, String dataKind, int elements) {
            this.elements = elements;
            this.dataKind = dataKind;
            this.name = name;
            this.message = "";

            // Formating
            this.formatHeader = " %-30s | \t %-8s | \t %-12s | \t %-12s | \t %-12s | \t %-12s | \t %-10s \n";
            this.formatValue = " %-30s | \t %-8s | \t %-,12d | \t %,-12d | \t %,-12d | \t %,-12d | \t %,-10d \n";
            this.valuesFormatWidth = new int[] { 30, 8, 12, 12, 12, 12, 10 };

            // Timing
            this.lstart = System.currentTimeMillis();
            this.start = Instant.now();
            if (isFirstMessage.compareAndSet(false, true))
                printHeader();

            // Memory
            Runtime r = Runtime.getRuntime();
            memUsedBefore = r.totalMemory() - r.freeMemory();
            timings.add(this);
        }

        private void printHeader() {
            System.out.printf(formatHeader, "name", "shuffle", "elements", "duration_ms", "p_duration_s",
                    "p_duration_ns", "memory");

            // initialize            
            int i = 0;
            String[] seps = new String[valuesFormatWidth.length];
            for (int length : valuesFormatWidth)
                seps[i++] = this.repeat(length, "-");

            i = 0;
            StringTokenizer st = new StringTokenizer(formatHeader.trim(), "|");
            System.out.printf(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            while (st.hasMoreTokens())
                sb.append(String.format(st.nextToken() + "|", seps[i++]));
            sb.append("\n");
            System.out.printf(sb.toString());
        }

        @Override
        public void close() {
            this.end = Instant.now();
            this.lend = System.currentTimeMillis();
            this.duration = Duration.between(start, end);
            // Memory
            Runtime r = Runtime.getRuntime();
            memUsedAfter = r.totalMemory() - r.freeMemory();
            if (message.equals("")) {                
                System.out.printf(toString());
            } else {
                System.out.printf("--- %-58s ---\t [%,d s / %,d ns / %,d ms]\n", message, duration.getSeconds(),
                        duration.getNano(), (lend - lstart));

            }
        }

        @Override
        public String toString() {
            return String.format(this.formatValue, name, dataKind, elements, (lend - lstart), duration.getSeconds(),
                    duration.getNano(), (memUsedAfter - memUsedBefore));
        }

        @Override
        public int hashCode() {             
            int hash = 1;
            hash = (int)elements;
            hash = (hash << 5) + name.hashCode();
            hash = (hash << 5) + dataKind.hashCode();
            hash = (hash << 5) + message.hashCode();
            return hash;
        }

        @Override
        public int compareTo(Timing other) {
            if (other == null)
                throw new NullPointerException();
            return this.getMillis().compareTo(other.getMillis());
        }

        @Override
        public boolean equals(Object other) {
            Thread.currentThread().dumpStack();
            if (other == null || other.getClass() != getClass()) {
                return false;
            } else if (other == this) {
                return true;
            } else {
                return this.hashCode() == other.hashCode();
            }
        }
    }
}
