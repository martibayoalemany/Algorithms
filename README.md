# Algorithms project
### Code runner
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
sudo apt install cmake
cd main/c
cmake .
make
gcc BinaryGap.c -o BinaryGap -lm
```

### Setups dotnet core repository
```
sudo sh -c 'echo "deb [arch=amd64] https://apt-mo.trafficmanager.net/repos/dotnet-release/ xenial main" > /etc/apt/sources.list.d/dotnetdev.list'
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 417A0893
sudo apt-get update
```

### Installs dotnet core
```
sudo apt install dotnet-dev-2.0.0-preview1-005977
sudo apt-get install dotnet-dev-1.0.4
```

### Creates a new app
```
dotnet new console -o src/main/cs
dotnet restore
dotnet run
```
### Builds and test the project (java)
```
gradlew build
gradlew clean test
```
### Gradle and ubuntu 17

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

### Binary gap stats 
```
scripts/stats_binary_search.sh
```
```
real 0:00.07    user 0.06        sys 0.01       java 
real 0:00.25    user 0.26        sys 0.01       nodejs 
real 0:00.00    user 0.00        sys 0.00       c
real 0:00.05    user 0.04        sys 0.01       c_sharp 
real 0:00.00    user 0.00        sys 0.00       perl 
real 0:00.21    user 0.13        sys 0.07       perl6 
real 0:00.03    user 0.02        sys 0.01       python 
real 0:00.14    user 0.16        sys 0.28       matlab/octave 
```
### Books

* Java 9 Data Structures and Algorithms - Debasish Ray Chawdhuri
