package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link HashMapCatalog}.
 */
class HashMapCatalogTest {

    @Test
    void buildInstanceShouldntThrowAnything() {
        assertDoesNotThrow(HashMapCatalog::new);
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        HashMapCatalog hashMapCatalog = new HashMapCatalog();
        Product product = new Product("Cable", ProductUnit.EACH);
        double price = 13.5;
        hashMapCatalog.addProduct(product, price);
        assertEquals(price, hashMapCatalog.getUnitPrice(product));
    }
}
