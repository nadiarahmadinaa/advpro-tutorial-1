package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ReadableRepository<Product>, WritableRepository<Product> {
    private final List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Optional<Product> findById(String productId) {
        return productData.stream().filter(p -> p.getProductId().equals(productId)).findFirst();
    }

    @Override
    public void update(String id, Product updatedProduct) {
        productData.removeIf(p -> p.getProductId().equals(id));
        productData.add(updatedProduct);
    }

    @Override
    public void delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}

