package src.main.com.algorithms.functional_patterns;

public class Tuple<T, U> {
    public T _1;
    public U _2;

    public Tuple(T t, U u) {
        _1 = t;
        _2 = u;
    }
}