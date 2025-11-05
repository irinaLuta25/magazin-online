package repository;

import file.FileManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbstractBinaryRepository<T> implements Repository<T> {
    protected final String filename;

    public AbstractBinaryRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Collection<T> items) {
        FileManager.writeObject(filename,items);
    }

    @Override
    public List<T> load() {
        List<T> items = FileManager.readObject(filename,List.class);
        return items !=null ? items : new ArrayList<>();
    }
}
