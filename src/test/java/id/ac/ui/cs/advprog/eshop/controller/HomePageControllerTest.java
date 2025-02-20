package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomePageControllerTest {

    @Test
    void testHomePage() {
        HomePageController controller = new HomePageController();
        String viewName = controller.homePage();
        assertEquals("homePage", viewName);
    }
}
