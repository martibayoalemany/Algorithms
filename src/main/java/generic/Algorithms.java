//package generic;

import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


public class Algorithms  {
    
    public static void main(String[] args) {        
        //long result = new Algorithms().execute();                
        new Algorithms().execute_sorting();                
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

        shuffle(array);       
        try(Timing t = new Timing("Custom insertion sort of size "  + array.length)) {    
            int n = array.length;
            for(int i = 2; i < n; i ++ ){
                for(int k = i; k > 1 && array[k] < array[k-1]; k--) {
                    Integer tmp = array[k];
                    array[k] = array [k-1];
                    array[k-1] = tmp;
                }
            }
        }
        
        shuffle(array);       
        try(Timing t = new Timing("Custom insertion sort 1 of size "  + array.length)) {                
            int end = array.length;
            for(int i = 2; i < end; i ++ ){
                for(int k = i; k > 1 && array[k] < array[k-1]; k--) {
                    Integer tmp = array[k];
                    array[k] = array [k-1];
                    array[k-1] = tmp;
                }
            }
        }

        shuffle(array);       
        try(Timing t = new Timing("Custom insertion sort 2 of size "  + array.length)) {                
            insertion_sort(array);
        }
        
        // Full selection
        shuffle(array);
        custom_selection_sort(array, "full");                

        // Full bubble
        shuffle(array);
        custom_bubble_sort(array, "full");        

        // Partial and bubble
        partial_shuffle(array);
        custom_bubble_sort(array, "partial");        

        // Partial and selection
        partial_shuffle(array);
        custom_selection_sort(array, "partial");

        // Shell sort
        partial_shuffle(array);
        try(Timing t = new Timing("Shell sort "  + array.length + " - partial")) {    
            int n = array.length;
            int h = 1;
            while(h < n) h = 3 * h + 1;
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
        
    }   
    private void insertion_sort(Integer[] array) {        
        int end = array.length;
        for(int i = 2; i < end; i ++ ){
            for(int k = i; k > 1 && array[k] < array[k-1]; k--) {
                Integer tmp = array[k];
                array[k] = array [k-1];
                array[k-1] = tmp;
            }
        }        
    }

    private Integer[] insertion_sort(Integer[] array, int start, int end) {        
        for(int i = start + 1; i < end; i ++ ){
            for(int k = i; k > 1 && array[k] < array[k-1]; k--) {
                Integer tmp = array[k];
                array[k] = array [k-1];
                array[k-1] = tmp;
            }
        }
        return array;
    }
    private Integer[] custom_bubble_sort(Integer[] array, String msg) {
        try(Timing t = new Timing("Custom bubble sort of size "  + array.length + " - " + msg)) {    
                    
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
        return array;
    }

    private Integer[] custom_selection_sort(Integer[] array, String msg) {
        try(Timing t = new Timing("Custom selection sort of size "  + array.length + " - " + msg)) {    
            int n = array.length;                     
            for(int i = 1; i < n; i ++ ){
                int k = i;
                for(int j = i+1; j < n; j++) {
                    if(array[j] < array[k])
                        k = j;                        
                }
                Integer tmp = array[i];
                array[i] = array[k];
                array[k] = tmp;
            }   
        }
        return array;
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
                System.out.printf("--- %-55s ---\t [%,d s / %,d ns / %,d ms]\n", message, dura.getSeconds(), dura.getNano(), (lend -lstart));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }    
}
