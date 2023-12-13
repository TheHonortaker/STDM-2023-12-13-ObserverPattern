package main.util;

public interface IObserver<T> {
    void update(T observable);
}
