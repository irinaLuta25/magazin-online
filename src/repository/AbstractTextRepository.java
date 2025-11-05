package repository;

import file.FileManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractTextRepository<T> implements Repository<T> {
    private final String filename;

    public AbstractTextRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Collection<T> items) {
        List<String> lines = items.stream()
                .map(this::convertToLine)
                .toList();
        FileManager.writeLines(filename,lines);
    }

    @Override
    public List<T> load() {
        List<T> items = new ArrayList<>();
        for(String line: FileManager.readLines(filename)) {
            T item = parseLine(line);
            if(item!=null) {
                items.add(item);
            }
        }

        return items;
    }

    protected abstract String convertToLine(T obj);
    protected abstract T parseLine(String line);
}
