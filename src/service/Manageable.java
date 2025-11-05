package service;

import java.util.List;

public interface Manageable<T> {
    void add(T item);
    void remove(T item);
    void update(T item);
    List<T> getAll();

    void displayAll();
}
