import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


public class BasicSorting  {       

    public static void main(String[] args) {        
        long result = new SortingAlgorithms().execute();                
        System.out.printf("%s: %d ", SortingAlgorithms.class.getName(), result);
    }

    public long execute() {
        List<Integer> list = new LinkedList<>();        
        try(Timing t = new Timing("Initialization")) {            
            IntStream range = IntStream.rangeClosed(1, 520_000);
            range.forEach(list::add);             
        }
                        
        Integer[] array = null;
        try(Timing t = new Timing("Convert to array")) {            
            array = list.toArray(new Integer[1]);            
        }         
        
        int[] arrayUnboxed = null;
        try(Timing t = new Timing("Convert to unboxed array")) {            
            arrayUnboxed = list.stream().mapToInt(Integer::intValue).toArray();
        }

        shuffle_snapshot(array);
        try(Timing t = new Timing("sort an array of size " + array.length)) {
            Arrays.sort(array);
        }                

        return list.size();
    }

    private void shuffle_snapshot(Integer[] array) {
        shuffle(array);
        snapshot(array);
    }               

    private void shuffle(Integer[] array) {
        try(Timing t = new Timing("Shuffle array of size " + array.length)) {
            Random rnd = ThreadLocalRandom.current();
            for(int i = array.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i+1);
                int tmp = array[index];
                array[index] = array[i];
                array[i] = tmp;                
            }
        }
    }
    
    private void snapshot(Integer[] array) {
        try(Timing t = new Timing("Snapshot 50 elements")) {
            int size = array.length;
            for(int i = size / 2; i < size / 2 + 50; i++) 
                    System.out.printf("%d\t", array[i]);
                System.out.println("\n");
        }
    }

    private void snapshot(List<Integer> list, int start_idx, int elements) {
        int end_idx = start_idx + elements;     
        try(Timing t = new Timing("Snapshot 50 elements")) {
            for(int i=start_idx; i < end_idx; i++)
                System.out.printf("%d\t", list.get(i));
            System.out.println("\n");
        }
    }  
    
    private class Timing implements AutoCloseable {
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
