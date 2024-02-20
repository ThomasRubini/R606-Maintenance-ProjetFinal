package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link Product}.
 */
class ProductTest {

    private static final String PRODUCT_NAME = "Riz";
    private static final ProductUnit PRODUCT_UNIT = ProductUnit.EACH;

    @Test
    void buildInstanceShouldntThrowAnything() {
        assertDoesNotThrow(() -> new Product(PRODUCT_NAME, PRODUCT_UNIT));
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        Product p = new Product(PRODUCT_NAME, PRODUCT_UNIT);
        assertEquals(PRODUCT_NAME, p.getName());
        assertEquals(PRODUCT_UNIT, p.getUnit());
    }

    @Test
    void twoInstancesWithSameDataShouldBeEqual() {
        Product product1 = new Product(PRODUCT_NAME, PRODUCT_UNIT);
        Product product2 = new Product(PRODUCT_NAME, PRODUCT_UNIT);
        assertEquals(product1, product2);
    }

    @Test
    void testToStringShouldReturnAnAppropriateRepresentation() {
        Product product = new Product(PRODUCT_NAME, PRODUCT_UNIT);
        assertEquals("Product{name='" + PRODUCT_NAME + '\'' + ", unit=" + PRODUCT_UNIT + '}',
                product.toString());
    }
}
