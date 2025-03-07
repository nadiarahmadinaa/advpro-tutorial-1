package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("123");

        productService.create(product);

        verify(productRepository).create(product);
    }

    @Test
    void testFindAll() {
        Iterator<Product> iterator = List.of(new Product(), new Product()).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById_Success() {
        Product product = new Product();
        when(productRepository.findById("123")).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findById("123");
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void testFindById_Failure() {
        when(productRepository.findById("123")).thenReturn(Optional.empty());

        Optional<Product> result = productService.findById("123");
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteById() {
        productService.deleteById("123");
        verify(productRepository).delete("123");
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("456");

        productService.update(product.getProductId(), product);

        verify(productRepository).update(product.getProductId(), product);
    }
}
