# Algorithms project
### Code runner
```
ext install code-runner
```
ctrl-alt-n - run
ctrl-alt-m - stop execution
ctrl-alt-k - run custom command


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
dotnet new console -o Algorithms
cd Algorithms
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

### Books

* Java 9 Data Structures and Algorithms - Debasish Ray Chawdhuri