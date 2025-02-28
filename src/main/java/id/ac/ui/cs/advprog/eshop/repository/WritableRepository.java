package id.ac.ui.cs.advprog.eshop.repository;

public interface WritableRepository<T> {
    T create(T entity);
    void update(String id, T updatedEntity);
    void delete(String id);
}
