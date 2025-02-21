package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProductPage() {
        String view = productController.createProductPage(model);
        assertEquals("CreateProduct", view);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String view = productController.createProductPost(product, model);
        verify(productService).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void testProductListPage() {
        when(productService.findAll()).thenReturn(List.of(new Product(), new Product()));
        String view = productController.productListPage(model);
        assertEquals("ProductList", view);
        verify(model).addAttribute(eq("products"), any(List.class));
    }

    @Test
    void testDeleteProduct() {
        String view = productController.deleteProduct("123");
        verify(productService).deleteById("123");
        assertEquals("redirect:/product/list", view);
    }

    @Test
    void testEditProductPage_Success() {
        Product product = new Product();
        when(productService.findById("123")).thenReturn(Optional.of(product));

        String view = productController.editProductPage("123", model);
        assertEquals("EditProduct", view);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_Failure() {
        when(productService.findById("123")).thenReturn(Optional.empty());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productController.editProductPage("123", model);
        });
        assertEquals("Invalid product ID: 123", exception.getMessage());
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String view = productController.editProductPost(product);
        verify(productService).update(product);
        assertEquals("redirect:/product/list", view);
    }
}
