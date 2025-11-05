package repository;

import java.util.Collection;
import java.util.List;

public interface Repository<T> {
    void save(Collection<T> items);
    List<T> load();
}
