[![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/graphai/graphai) [![PayPal](https://img.shields.io/badge/PayPal-Donate%20a%20beer-blue.svg)](https://paypal.me/martibayoalemany)

# Algorithms 
## Results
 name                           | 	 shuffle  | 	 elements     | 	 duration_ms  | 	 p_duration_s | 	 p_duration_ns | 	 memory 
merge sort                     | 	 / 2      | 	 20,000       | 	 2            | 	 0            | 	 2,626,000    | 	 0          
 Arrays.sort                    | 	 / 2      | 	 20,000       | 	 5            | 	 0            | 	 4,053,000    | 	 0          
 Linked Hashmap                 | 	 / 3      | 	 20,000       | 	 5            | 	 0            | 	 5,120,000    | 	 2,097,152  
 Arrays.parallelSort            | 	 / 2      | 	 20,000       | 	 6            | 	 0            | 	 6,882,000    | 	 522,240    
 Arrays.sort                    | 	 / 3      | 	 20,000       | 	 6            | 	 0            | 	 5,971,000    | 	 0          
 merge sort                     | 	 / 3      | 	 20,000       | 	 8            | 	 0            | 	 7,384,000    | 	 0          
 Arrays.parallelSort            | 	 / 3      | 	 20,000       | 	 8            | 	 0            | 	 8,378,000    | 	 0          
 Linked Hashmap                 | 	 / 2      | 	 20,000       | 	 10           | 	 0            | 	 10,066,000   | 	 1,574,912  
 merge sort                     | 	 / 1      | 	 20,000       | 	 14           | 	 0            | 	 14,255,000   | 	 80,024     
 Stream + parallel + sort       | 	 / 3      | 	 20,000       | 	 15           | 	 0            | 	 15,340,000   | 	 880,824    
 Stream + parallel + sort       | 	 / 2      | 	 20,000       | 	 18           | 	 0            | 	 18,589,000   | 	 1,048,576  
 Arrays.sort                    | 	 / 1      | 	 20,000       | 	 27           | 	 0            | 	 26,756,000   | 	 126,696    
 Linked Hashmap                 | 	 / 1      | 	 20,000       | 	 32           | 	 0            | 	 32,378,000   | 	 1,729,792  
 Arrays.parallelSort            | 	 / 1      | 	 20,000       | 	 48           | 	 0            | 	 48,001,000   | 	 1,139,600  
 Stream + parallel + sort       | 	 / 1      | 	 20,000       | 	 48           | 	 0            | 	 48,803,000   | 	 752,272    
 insertion                      | 	 / 3      | 	 20,000       | 	 49           | 	 0            | 	 49,816,000   | 	 0          
 shell                          | 	 / 3      | 	 20,000       | 	 54           | 	 0            | 	 54,655,000   | 	 0          
 shell                          | 	 / 2      | 	 20,000       | 	 122          | 	 0            | 	 121,619,000  | 	 0          
 insertion                      | 	 / 2      | 	 20,000       | 	 142          | 	 0            | 	 142,802,000  | 	 0          
 bubble                         | 	 / 3      | 	 20,000       | 	 301          | 	 0            | 	 301,062,000  | 	 0          
 selection                      | 	 / 3      | 	 20,000       | 	 312          | 	 0            | 	 312,092,000  | 	 0          
 selection                      | 	 / 1      | 	 20,000       | 	 435          | 	 0            | 	 434,716,000  | 	 0          
 selection                      | 	 / 2      | 	 20,000       | 	 440          | 	 0            | 	 439,951,000  | 	 0          
 bubble                         | 	 / 2      | 	 20,000       | 	 471          | 	 0            | 	 471,216,000  | 	 0          
 insertion                      | 	 / 1      | 	 20,000       | 	 602          | 	 0            | 	 602,514,000  | 	 0          
 shell                          | 	 / 1      | 	 20,000       | 	 713          | 	 0            | 	 712,871,000  | 	 0          
 bubble                         | 	 / 1      | 	 20,000       | 	 1,292        | 	 1            | 
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
real 0:00.31    user 0.23        sys 0.15       matlab
real 0:00.61    user 0.58        sys 0.01       nodejs_slow
```

#### Sorting (Java)
```
--- Initialization                                          ---	 [0 s / 264,000,000 ns / 263 ms]
--- Convert to array                                        ---	 [0 s / 1,000,000 ns / 1 ms]
--- Java Arrays.sort of size 20000                          ---	 [0 s / 78,000,000 ns / 78 ms]
--- Custom insertion sort of size 20000                     ---	 [0 s / 926,000,000 ns / 926 ms]
--- Custom insertion sort 1 of size 20000                   ---	 [0 s / 645,000,000 ns / 645 ms]
--- Custom insertion sort 2 of size 20000                   ---	 [0 s / 671,000,000 ns / 671 ms]
--- Custom selection sort of size 20000 - full              ---	 [1 s / 288,000,000 ns / 1,288 ms]
--- Custom bubble sort of size 20000 - full                 ---	 [2 s / 511,000,000 ns / 2,511 ms]
--- Custom bubble sort of size 20000 - partial              ---	 [0 s / 212,000,000 ns / 212 ms]
--- Custom selection sort of size 20000 - partial           ---	 [1 s / 134,000,000 ns / 1,134 ms]
--- Shell sort 20000 - partial                              ---	 [0 s / 53,000,000 ns / 53 ms]
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
```
initialization [122,772,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [351,818,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [139,877,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [423,006,000 ns]
```

### Code runner (Visual Studio Code)
```
ext install code-runner
```
* ctrl-alt-n - run 
* ctrl-alt-m - stop execution 
* ctrl-alt-k - run custom command 


### Code navigation
```
sudo apt install exuberant-ctags
ctags -R -f .tags /usr/include
sudo apt install openjdk-9-source
sudo mkdir -p /usr/lib/jvm/openjdk-9/lib/src/
sudo unzip /usr/lib/jvm/openjdk-9/lib/src.zip -d /usr/lib/jvm/openjdk-9/lib/src/
ctags -R -fa .tags  /usr/lib/jvm/openjdk-8/src
ctags -R -fa .tags /usr/lib/jvm/openjdk-9/lib/src
sudo -s ln -s  /usr/lib/jvm/openjdk-8/src .java_8_src
```

### Compile  (C)
```
gcc BinaryGap.c -o BinaryGap -lm
```

```
sudo apt install cmake
cd main/c
cmake .
make
```

### Compile (dotnet)
```
sudo sh -c 'echo "deb [arch=amd64] https://apt-mo.trafficmanager.net/repos/dotnet-release/ xenial main" > /etc/apt/sources.list.d/dotnetdev.list'
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 417A0893
sudo apt-get update
```
```
sudo apt install dotnet-dev-2.0.0-preview1-005977
```

### Creates a new app (dotnet)
```
dotnet new console -o src/main/cs
dotnet restore
dotnet run
```

### Compile (java / gradle)
```
gradlew build
gradlew clean test
```

### Gradle and ubuntu 17
There seems to be some issues with tgetent which get solved by installing libnative-jni manually
```
usr/lib/jni/libnative-platform-curses.so: undefined symbol: tgetent
sudo -s dpkg -i libnative-platform-jni_0.11-5_amd64.deb
```

### Setting maven (if needed)
```
cat ~/.mavenrc
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64/
```

### Dependency management (java)
```
mvn dependency:copy -DoutputDirectory=requirements_mvn -Dartfifact=nz.ac.waikato.cms.weka:weka-stable:3.8.1:jar:sources
gradle getRequirements
```
