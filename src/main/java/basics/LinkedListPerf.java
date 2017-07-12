import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


public class LinkedListPerf  {
    
    public static void main(String[] args) {        
        new LinkedListPerf().execute();                
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

    private void snapshot(List<Integer> list, int start_idx, int elements) {
        int end_idx = start_idx + elements;     
        try(Timing t = new Timing("Snapshot 50 elements")) {
            for(int i=start_idx; i < end_idx; i++)
                System.out.printf("%d\t", list.get(i));
            System.out.println("\n");
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
