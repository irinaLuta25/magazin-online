package service;

import repository.Repository;

import java.util.*;

public abstract class AbstractService<T> implements Manageable<T> {
    protected Set<T> items = new LinkedHashSet<>();
    protected Repository<T> repository;

    public AbstractService(Repository<T> repository) {
        this.repository = repository;
        this.items.addAll(repository.load());
    }

    @Override
    public void add(T item) {
        if (items.contains(item)) {
            System.out.println("Item already exists!");
        } else {
            items.add(item);
            System.out.println("Item " + item +  " added!");
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
        if(this.items.isEmpty()) {
            System.out.println("no items");
        }
        return new ArrayList<>(items);
    }

    @Override
    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("No items to display.\n");
            return;
        }

//        System.out.println("\n--- Displaying all " + items.iterator().next().getClass().getSimpleName() + " entries ---");
        for (T item : items) {
            System.out.println(item);
            System.out.println("------------------");
        }
    }

}
