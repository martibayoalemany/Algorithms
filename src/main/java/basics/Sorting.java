import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


public class Sorting  {
    
    public static void main(String[] args) {        
        new Sorting().execute_sorting();                
    }

    public void execute_sorting() {
        List<Integer> list = new LinkedList<>();        
        try(Timing t = new Timing("Initialization")) {            
            IntStream range = IntStream.rangeClosed(1, 20_000);
            range.forEach(list::add);
        }

        Integer[] array = null;        
        try(Timing t = new Timing("Convert to array")) {            
            array = list.toArray(new Integer[1]);            
        }                         
        
        shuffle(array);
        try(Timing t = new Timing("Java Arrays.sort of size " + array.length)) {
            Arrays.sort(array);
        }
        assert_sorted(array,80);

        for(int i = 0; i < 3; i++) {
            shuffle(array);       
            try(Timing t = new Timing("Insertion sort " + i + " of size "  + array.length)) {    
                insertion_sort(array);
            }
            assert_sorted(array,80);
        }
                
        // Full selection
        shuffle(array);
        try(Timing t = new Timing("Selection sort of size "  + array.length + " - full")) {    
            selection_sort(array);
        }
        assert_sorted(array,80);

        // Partial and selection
        partial_shuffle(array);
        try(Timing t = new Timing("Selection sort of size "  + array.length + " - partial")) {    
            selection_sort(array);
        }
        assert_sorted(array,80);

        // Full bubble
        shuffle(array);
        try(Timing t = new Timing("Bubble sort of size "  + array.length + " -  full")) {    
            bubble_sort(array);        
        }
        assert_sorted(array,80);

        // Partial bubble
        partial_shuffle(array);
        try(Timing t = new Timing("Bubble sort of size "  + array.length + " -  partial")) {    
            bubble_sort(array);        
        }
        assert_sorted(array,80);

        // Full Shell sort
        shuffle(array);
        try(Timing t = new Timing("Shell sort "  + array.length + " - full")) {    
            shell_sort(array);
        }
        assert_sorted(array,80);       
        
        // Partial Shell sort 
        partial_shuffle(array);
        try(Timing t = new Timing("Shell sort "  + array.length + " - partial")) {    
            shell_sort(array);
        }
        assert_sorted(array,80);
    }   

    private void shell_sort(Integer[] array) {
        int n = array.length;
        int h = 1;
        while(h < n) h = 3 * h;
        while(h > 0) {
            h = h / 3;                
            int k = 0;
            int iters = 0;
            while(h != 0 && k + h < n) {                    
                insertion_sort(array, k , k + h);
                k = k + h;
                iters ++;
            }                
        }
    }

    private void insertion_sort(Integer[] array) {        
        int end = array.length;
        for(int i = 1; i < end; i ++ ){
            for(int k = i; k >= 1 && array[k] < array[k-1]; k--) {                
                Integer tmp = array[k];
                array[k] = array[k-1];
                array[k-1] = tmp;                
            }
        }        
    }

    private Integer[] insertion_sort(Integer[] array, int start, int end) {                
        for(int i = start; i <= end; i ++ ){
            for(int k = i; k >= 1 && array[k] < array[k-1]; k--) {                
                Integer tmp = array[k];
                array[k] = array [k-1];
                array[k-1] = tmp;                
            }
        }
        return array;
    }

    private void bubble_sort(Integer[] array) {
        int n = array.length;                                 
        for(int i = 0; i < n; i ++ ){
            boolean swapped = false;
            for(int j = i+1; j < n; j++) {
                if(array[j] < array[i]) {
                    Integer tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    swapped = true;
                }
            }
            if(!swapped)
                break;
        }
    }

    private void selection_sort(Integer[] array) {
        int n = array.length;                     
        for(int i = 0; i < n; i ++ ){
            int k = i;
            for(int j = i + 1; j < n; j++) {
                if(array[j] < array[k])
                    k = j;                        
            }
            Integer tmp = array[i];
            array[i] = array[k];
            array[k] = tmp;
        }   
    }

    public long execute() {
        List<Integer> list = new LinkedList<>();        
        try(Timing t = new Timing("Initialization")) {            
            IntStream range = IntStream.rangeClosed(1, 520_000);
            range.forEach(list::add);
        }
                
        remove(list, 10_000, 10_000);
        remove(list, 100_000, 10_000);
        remove(list, 200_000, 10_000);
        snapshot(list,100_000, 50);

        Integer[] array = null;
        try(Timing t = new Timing("Convert to array")) {            
            array = list.toArray(new Integer[1]);            
        }         

        int[] arrayUnboxed = null;
        try(Timing t = new Timing("Convert to unboxed array")) {            
            arrayUnboxed = list.stream().mapToInt(Integer::intValue).toArray();
        }        

        return list.size();
    }

    private void snapshot_shuffle(Integer[] array) {
        snapshot(array);
        shuffle(array);
        snapshot(array);
    }

    private void snapshot(Integer[] array) {
        try(Timing t = new Timing("Snapshot 8 elements")) {
            int size = array.length;
            for(int i = size / 2; i < size / 2 + 8; i++) 
                    System.out.printf("%d\t", array[i]);
                System.out.println("\n");
        }
    }

    private void shuffle(Integer[] array) {
        //try(Timing t = new Timing("Shuffle array of size " + array.length)) {
            Random rnd = ThreadLocalRandom.current();
            for(int i = array.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i+1);
                int tmp = array[index];
                array[index] = array[i];
                array[i] = tmp;                
            }
        //}
    }

    private void partial_shuffle(Integer[] array) {
        //try(Timing t = new Timing("Partial shuffle array of size " + array.length)) {
            Random rnd = ThreadLocalRandom.current();
            for(int i = (array.length / 8) - 1; i > 0; i--) {
                int index = rnd.nextInt(i+1);
                int tmp = array[index];
                array[index] = array[i];
                array[i] = tmp;                
            }
        //}
    }


    private void snapshot(List<Integer> list, int start_idx, int elements) {
        int end_idx = start_idx + elements;     
        try(Timing t = new Timing("Snapshot 50 elements")) {
            for(int i=start_idx; i < end_idx; i++)
                System.out.printf("%d\t", list.get(i));
            System.out.println("\n");
        }
    }

    private void remove(List<Integer> list, int start_idx, int elements) {                               
        int end_idx = start_idx + elements;             
        try(Timing t = new Timing(String.format("Removing %,d elements %,d -> %,d ", elements, start_idx, end_idx))) {                            
            for(int i=start_idx; i < end_idx; i++)
                list.remove(i);                
        }
        catch(Exception e) {
            e.printStackTrace();
        }        
    }

    private void assert_sorted(Integer[] array, int limit) {        
        boolean sorted = true;
        int k = 0;
        for(int i = 0; i < limit; i++) {
            if(sorted && array[i] > array[i+1]) {
                System.out.println("Array start not sorted, position " + i);                
                sorted = false;
            }            
            if(!sorted) System.out.printf("%,d \t", array[i]);
            if(!sorted && k++ > 20) break;
        }        
        if(!sorted) System.out.println("\n========================");
        k = 0;
        sorted = true;
        for(int i=array.length -1; i > (array.length-20); i--) {
            if(sorted && array[i-1] > array[i]) {
                System.out.println("Array end not sorted, position " + i);                
                sorted = false;
            }
            if(!sorted) System.out.printf("%,d \t", array[i]);
            if(!sorted && k++ > 20) break;
        }        
        if(!sorted) System.out.println("\n========================");
    }

    
    public class Timing implements AutoCloseable {
        private long lstart;
        private long lend;
        private Instant start;        
        private Instant end;
        private String message;

        public Timing(String message) {
            this.message = message;
            this.start = Instant.now();            
            this.lstart = System.currentTimeMillis();
        }

        @Override
        public void close() {
            try {
                this.end = Instant.now();
                this.lend = System.currentTimeMillis();                
                java.time.Duration dura = Duration.between(start, end);                
                System.out.printf("--- %-58s ---\t [%,d s / %,d ns / %,d ms]\n", 
                                    message, 
                                    dura.getSeconds(), 
                                    dura.getNano(), (
                                        lend -lstart));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }    
}
