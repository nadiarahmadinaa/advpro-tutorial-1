package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    Optional<Product> findById(String productId);
    void update(String id, Product updatedProduct);
    void deleteById(String productId);
}
