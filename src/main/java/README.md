

### ArrayList vs LinkedList (Java)
####  ArrayList
```
initialization [46,717,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [661,835,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [39,749,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [658,547,000 ns]
```

#### LinkedList
Acording to the documentation a LinkedList is a doubly linked list

```
initialization [122,772,000 ns]
Removing 10,000 elements 10,000 -> 20,000  [351,818,000 ns]
Removing 10,000 elements 100,000 -> 110,000  [139,877,000 ns]
Removing 10,000 elements 200,000 -> 210,000  [423,006,000 ns]
```


### Compile (java / gradle)
If you have java 8 and java 9 installed, select java 8 for gradlew
```
update-alternatives --config java
./gradlew build
./gradlew clean test
./gradlew download_requirements
```

### Setting maven (if needed)
```
cat ~/.mavenrc
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64/
```

### Dependency management (java)
```
mvn dependency:copy -DoutputDirectory=requirements_mvn -Dartifact=nz.ac.waikato.cms.weka:weka-stable:3.8.1:jar:sources

```