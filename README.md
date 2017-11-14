[![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/graphai/graphai)
# Algorithms / programming languages

[Languages ranking](http://spectrum.ieee.org/static/interactive-the-top-programming-languages-2017)

## Statistics with jupyter

```
source scripts/activate
# Setups the python environment accordingly
py_env 
cd stats
jupyter notebook

```

## Sorting algorithms and java
TO-FIX: Stream + Parallel + Sort - timing is not the real one

TO-DO: sqlite3 with an index sorts faster than java in memory, check the basics for data and in-memory solutions

<<<<<<< HEAD
![Comparison](stats/figures/sort_comparison.png)

[Pyplot stats for java and sorting](stats/Java_sorting.md)
=======
```
usr/lib/jni/libnative-platform-curses.so: undefined symbol: tgetent
sudo -s dpkg -i libnative-platform-jni_0.11-5_amd64.deb
```

### Dependency management (java)
```
mvn dependency:copy -DoutputDirectory=requirements_mvn -Dartfifact=nz.ac.waikato.cms.weka:weka-stable:3.8.1:jar:sources
gradle getRequirements
```

### ArrayList vs LinkedList (java)
####  ArrayList
```
initialization [46,717,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [661,835,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [39,749,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [658,547,000 ns]
```
#### LinkedList
```
initialization [122,772,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [351,818,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [139,877,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [423,006,000 ns]
```

#### Sorting
```
--- Initialization                                             ---	 [0 s / 499,000,000 ns / 498 ms]
--- Convert to array                                           ---	 [0 s / 1,000,000 ns / 1 ms]
--- Convert to unboxed array                                   ---	 [0 s / 48,000,000 ns / 48 ms]
--- Java Arrays.sort of size 20000                             ---	 [0 s / 78,000,000 ns / 78 ms]
--- Insertion sort 0 of size 20000                             ---	 [1 s / 772,000,000 ns / 1,772 ms]
--- Insertion sort 1 of size 20000                             ---	 [2 s / 224,000,000 ns / 2,224 ms]
--- Insertion sort 2 of size 20000                             ---	 [1 s / 354,000,000 ns / 1,355 ms]
--- Selection sort of size 20000 - full                        ---	 [2 s / 599,000,000 ns / 2,599 ms]
--- Selection sort of size 20000 - partial                     ---	 [2 s / 184,000,000 ns / 2,184 ms]
--- Bubble sort of size 20000 -  full                          ---	 [5 s / 246,000,000 ns / 5,246 ms]
--- Bubble sort of size 20000 -  partial                       ---	 [3 s / 356,000,000 ns / 3,356 ms]
--- Shell sort 20000 - full                                    ---	 [1 s / 768,000,000 ns / 1,768 ms]
--- Shell sort 20000 - partial                                 ---	 [0 s / 265,000,000 ns / 265 ms]
--- Merge sort 1 (copy up to higher) 20000 - full              ---	 [1 s / 606,000,000 ns / 1,606 ms]
--- Merge sort 2 (copy up to middle) 20000 - full              ---	 [0 s / 480,000,000 ns / 480 ms]
--- Merge sort 3 (array allocation once) 20,000 - full         ---	 [0 s / 198,000,000 ns / 198 ms]
--- Merge sort 4 (System.arraycopy) 20,000 - full              ---	 [0 s / 232,000,000 ns / 232 ms]
--- Merge sort 3 (array allocation once) 200,000 - full        ---	 [30 s / 429,000,000 ns / 30,429 ms]
--- Merge sort 4 (System.arraycopy) 200,000 - full             ---	 [27 s / 851,000,000 ns / 27,851 ms]
--- Java Arrays.sort of size 200,000 - full                    ---	 [1 s / 300,000,000 ns / 1,300 ms]
```
>>>>>>> 3537cc7cb8ed7a65767fc70657d78c4c7be1a04b

### Binary gap stats 
```
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

