package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository {
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
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();
    }

    @Override
    public void update(String id, Product updatedProduct) {
        for (Product existingProduct : productData) {
            if (existingProduct.getProductId().equals(id)) {
                existingProduct.setProductName(updatedProduct.getProductName());
                existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
                return;
            }
        }
    }

    @Override
    public void deleteById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
