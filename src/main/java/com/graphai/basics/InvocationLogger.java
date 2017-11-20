package com.graphai.basics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

import static java.lang.ClassLoader.getSystemClassLoader;

public class InvocationLogger implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("%s (%s)\n ", method.getName(),
                args != null ?
                Stream.of(args)
                        .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                        .toString() : "");


        return null;
    }

    @SuppressWarnings("unchecked")
    public static <U> U make(Class<U> type) {
        return (U) Proxy.newProxyInstance(
                getSystemClassLoader(),
                new Class[]{type},
                new InvocationLogger());
    }
}