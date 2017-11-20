package com.graphai.basics;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class JavaBasics {
    public static class City {
        private String name;

        City(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        public String toString() {
            return getName();
        }
    }

    public static class Person {
        private City city;
        private String lastName;

        Person(City city, String lastName) {
            this.city = city;
            this.lastName = lastName;
        }

        String getLastName() {
            return lastName;
        }

        City getCity() {
            return city;
        }

        public String toString() {
            return getCity() + " " + getLastName();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (city != null ? !city.equals(person.city) : person.city != null) return false;
            return lastName != null ? lastName.equals(person.lastName) : person.lastName == null;
        }

        @Override
        public int hashCode() {
            int result = city != null ? city.hashCode() : 0;
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int value = 12345678;
        while (value > 0) {
            System.out.printf("%d\n", value % 10);
            value = value / 10;
        }

        List<String> results = IntStream.rangeClosed(0, 200).collect(ArrayList::new,
                (list, s2 ) -> {
                    if(s2 % 3 == 0 && s2 % 5 == 0)
                        list.add(String.format("%d:FizzBuzz", s2)) ;
                    else if(s2 % 3 == 0)
                        list.add(String.format("%d:Fizz", s2)) ;
                    else if(s2 % 5 == 0)
                        list.add(String.format("%d:Buzz", s2)) ;
                }, ArrayList::addAll);

        results.forEach(System.out::println);

        System.out.println(" --- Grouping --- ");
        List<Person> people =
                Arrays.asList(new Person(new City("London"), "Smith"),
                        new Person(new City("Bristol"), "Smith"),
                        new Person(new City("Bristol"), "Smithee"),
                        new Person(new City("Bristol"), "Andrea"));
        Map<City, Set<String>> namesByCity
                = people.stream().collect(groupingBy(Person::getCity,
                mapping(Person::getLastName, toSet())));

        Map<City, List<Person>> namesByCity2
                = people.stream().collect(groupingBy(Person::getCity));

        Map<String, Map<City, List<Person>>> groupedPeople
                = people.stream().collect(groupingBy(Person::getLastName,
                groupingBy(Person::getCity)));

        System.out.println("First key / LastName");
        groupedPeople.keySet().stream().forEach(System.out::println);
        System.out.println("Second key / City");
        groupedPeople.values().stream().flatMap(s -> Stream.of(s.keySet())).forEach(System.out::println);


        // ---> io.reactivex


        Observable<Long> l = Observable.fromIterable(() -> LongStream.rangeClosed(0,100).iterator())
                                .concatWith(Observable.empty())
                                .delay(1000, TimeUnit.MICROSECONDS);

        Observer<Long> obsLong = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.printf("onNext %d\n", aLong);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.printf("onError");
            }

            @Override
            public void onComplete() {
                System.out.printf("onComplete");
            }
        };

        l.subscribe(InvocationLogger.make(Observer.class));
        //l.subscribe(obsLong);

        Thread.sleep(200);
        System.out.println(l.lastOrError().blockingGet());

    }
}
