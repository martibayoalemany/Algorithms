[![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/graphai/graphai) 

# Algorithms / development languages


## Sorting algorithms and java
![Comparison](stats/figures/sort_comparison.png) 

[Pyplot stats for java and sorting](stats/Java_sorting.md)

## Sorting available with the java sdk
```java
Arrays.sort(s)
Arrays.parallelSort(s)
                                    
// Stream + parallel + Sort
Consumer<Integer[]> stream_parallel_sort = new Consumer<Integer[]>() {
    public void accept(Integer[] arrays) {
        LinkedList<Integer> list = new LinkedList<>();
        Stream.of(arrays).parallel().sorted().forEachOrdered(list::add);        
    }
};
```

```java
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
```


### Binary gap stats 
```bash
scripts/stat_algs.sh
```

```
real 0:00.00    user 0.00        sys 0.00       c
real 0:00.00    user 0.00        sys 0.00       perl
real 0:00.02    user 0.01        sys 0.00       php7
real 0:00.06    user 0.06        sys 0.00       python
real 0:00.09    user 0.08        sys 0.00       ruby
real 0:00.11    user 0.08        sys 0.02       c_sharp
real 0:00.13    user 0.11        sys 0.00       nodejs
real 0:00.13    user 0.10        sys 0.02       java
real 0:00.39    user 0.33        sys 0.04       perl6
real 0:00.31    user 0.23        sys 0.15       octave
real 0:00.61    user 0.58        sys 0.01       nodejs_slow
```


### ArrayList vs LinkedList (Java)
####  ArrayList
```
initialization [46,717,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [661,835,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [39,749,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [658,547,000 ns]
```

#### LinkedList
According to the documentation a LinkedList is a doubly linked list

```
initialization [122,772,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [351,818,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [139,877,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [423,006,000 ns]
```
