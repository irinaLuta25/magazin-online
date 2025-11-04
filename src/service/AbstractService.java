package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractService<T> implements Manageable<T> {
    protected Set<T> items = new HashSet<>();

    @Override
    public void add(T item) {
        if (items.contains(item)) {
            System.out.println("Item already exists!");
        } else {
            items.add(item);
            System.out.println("Item added!");
        }
    }

    @Override
    public void remove(T item) {
        if (items.remove(item)) {
            System.out.println("Item deleted!");
        } else {
            System.out.println("Item not found!");
        }
    }

    @Override
    public void update(T item) {
        if (items.remove(item)) {
            items.add(item);
            System.out.println("Item updated!");
        } else {
            System.out.println("Item not found!");
        }
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(items);
    }
}
