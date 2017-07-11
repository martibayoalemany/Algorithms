package generic;

import generic.Timing;
import java.time.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


public class SortingAlgorithms  {       

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

        //---
        shuffle_snapshot(array);           
        Integer[] target = null;
        try(Timing t = new Timing("sort an array of size " + array.length)) {                    
            int size = array.length;
            target = new Integer[size];
            System.out.println(target.length);
            System.out.println(array.length);
            Arrays.mergeSort(array.mapToObject(Object::new), target.mapToObject(Object::new), 0, size -1, -1);            
        }
        snapshot(target);
        //---
        
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
}
