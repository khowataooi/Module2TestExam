package manager;

import java.util.List;

public interface Manager<T> {

    void add(T product);

    void edit(int id, T product);

    void delete(int id);

    List<T> findAll();

    int findById(int id);

    T findMostExpensive();

}
