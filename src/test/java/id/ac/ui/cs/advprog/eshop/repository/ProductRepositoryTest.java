package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById_Success() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Keyboard");
        product.setProductQuantity(5);
        productRepository.create(product);

        Optional<Product> foundProduct = productRepository.findById("123");
        assertTrue(foundProduct.isPresent());
        assertEquals(product.getProductId(), foundProduct.get().getProductId());
    }

    @Test
    void testFindById_Failure() {
        Optional<Product> foundProduct = productRepository.findById("999");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testUpdateProduct_Success() {
        Product product = new Product();
        product.setProductId("789");
        product.setProductName("Tablet");
        product.setProductQuantity(15);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("789");
        updatedProduct.setProductName("Updated Tablet");
        updatedProduct.setProductQuantity(20);

        productRepository.update(updatedProduct);

        Optional<Product> foundProduct = productRepository.findById("789");

        assertTrue(foundProduct.isPresent());
        assertEquals("Updated Tablet", foundProduct.get().getProductName());
        assertEquals(20, foundProduct.get().getProductQuantity());
    }

    @Test
    void testUpdateProduct_Failure_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("999");
        updatedProduct.setProductName("Non-existent");
        updatedProduct.setProductQuantity(10);

        productRepository.update(updatedProduct);

        Optional<Product> foundProduct = productRepository.findById("999");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product();
        product.setProductId("101");
        product.setProductName("Headphones");
        product.setProductQuantity(8);
        productRepository.create(product);

        productRepository.deleteById("101");

        Optional<Product> foundProduct = productRepository.findById("101");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        productRepository.deleteById("999");
        assertFalse(productRepository.findById("999").isPresent());
    }

    @Test
    void testUpdateProduct_WhenMultipleProducts_OnlyCorrectOneUpdated() {
        Product product1 = new Product();
        product1.setProductId("001");
        product1.setProductName("Laptop");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("002");
        product2.setProductName("Mouse");
        product2.setProductQuantity(5);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("003");
        product3.setProductName("Keyboard");
        product3.setProductQuantity(7);
        productRepository.create(product3);

        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId("002");
        updatedProduct2.setProductName("Wireless Mouse");
        updatedProduct2.setProductQuantity(8);

        productRepository.update(updatedProduct2);

        Optional<Product> foundProduct2 = productRepository.findById("002");
        assertTrue(foundProduct2.isPresent());
        assertEquals("Wireless Mouse", foundProduct2.get().getProductName());
        assertEquals(8, foundProduct2.get().getProductQuantity());

        Optional<Product> foundProduct1 = productRepository.findById("001");
        Optional<Product> foundProduct3 = productRepository.findById("003");

        assertTrue(foundProduct1.isPresent());
        assertEquals("Laptop", foundProduct1.get().getProductName());

        assertTrue(foundProduct3.isPresent());
        assertEquals("Keyboard", foundProduct3.get().getProductName());
    }
}
