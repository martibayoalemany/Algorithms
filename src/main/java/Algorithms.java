import utils.Timing;
import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


public class Algorithms  {
    private Instant start;
    private Instant end;
    private String message;

    public static void main(String[] args) {        
        long result = new Algorithms().execute();                
        System.out.printf("%s: %d ", Algorithms.class.getName(), result);
    }
    
    private void start(String message) {
        this.message = message;
        this.start = Instant.now();        
    }
    private void end() {
        end = Instant.now();
        long nano = Duration.between(start, end).getNano();
        System.out.printf("%s [%,d ns]\n", message, nano);
    }

    public long execute() {
        LinkedList<Integer> list = new LinkedList<>();
        {
            start("initialization");            
            IntStream range = IntStream.rangeClosed(1, 520_000);
            range.forEach(list::add);
            end();            
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

        snapshot(array);
        shuffle(array);
        snapshot(array); 
        try(Timing t = new Timing("sort an array of size " + array.length)) {
            Arrays.sort(array);
        }
        snapshot(array); 
        shuffle(array);                

        return list.size();
    }
        
    private void snapshot(Integer[] array) {
        try(Timing t = new Timing("Snapshot 50 elements")) {
            int size = array.length;
            for(int i = size / 2; i < size / 2 + 50; i++) 
                    System.out.printf("%d\t", array[i]);
                System.out.println("\n");
        }
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

    private void snapshot(LinkedList<Integer> list, int start_idx, int elements) {
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
}
