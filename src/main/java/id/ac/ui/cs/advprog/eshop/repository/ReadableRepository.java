package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
import java.util.Optional;

public interface ReadableRepository<T> {
    Iterator<T> findAll();
    Optional<T> findById(String id);
}
