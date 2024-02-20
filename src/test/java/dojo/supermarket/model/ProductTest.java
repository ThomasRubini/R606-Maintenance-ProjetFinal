package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    @Test
    void buildInstanceShouldntThrowAnything() {
        assertDoesNotThrow(() -> new Product("product", ProductUnit.KILO));
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        String productName = "Riz";
        ProductUnit productUnit = ProductUnit.EACH;
        Product p = new Product(productName, productUnit);
        assertEquals(productName, p.getName());
        assertEquals(productUnit, p.getUnit());
    }

    @Test
    void twoInstancesWithSameDataShouldBeEqual() {
        String productName = "Riz";
        ProductUnit productUnit = ProductUnit.EACH;
        Product product1 = new Product(productName, productUnit);
        Product product2 = new Product(productName, productUnit);
        assertEquals(product1, product2);
    }

    @Test
    void testToStringShouldReturnAnAppropriateRepresentation() {
        String productName = "Riz";
        ProductUnit productUnit = ProductUnit.EACH;
        Product product = new Product(productName, productUnit);
        assertEquals("Product{name='" + productName + '\'' + ", unit=" + productUnit + '}',
                product.toString());
    }
}
