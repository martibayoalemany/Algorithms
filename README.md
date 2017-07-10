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

### Java ArrayList vs LinkedList
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

### Binary gap stats 
real 0:00.06    user 0.06        sys 0.00               npm
real 0:00.07    user 0.06        sys 0.00               java
real 0:00.00    user 0.00        sys 0.00               c
real 0:00.04    user 0.03        sys 0.01               c_sharp
real 0:00.00    user 0.00        sys 0.00               perl
real 0:00.07    user 0.03        sys 0.00               python

### Books

* Java 9 Data Structures and Algorithms - Debasish Ray Chawdhuri