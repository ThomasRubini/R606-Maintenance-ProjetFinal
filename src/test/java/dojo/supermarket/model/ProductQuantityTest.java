package dojo.supermarket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link ProductQuantity}.
 */
class ProductQuantityTest {

    private static final Product PRODUCT = new Product("Farine", ProductUnit.EACH);
    private static final double QUANTITY = 3.0;

    private ProductQuantity productQuantity;

    @BeforeEach
    void setUp() {
        productQuantity = new ProductQuantity(PRODUCT, QUANTITY);
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        assertEquals(PRODUCT, productQuantity.getProduct());
        assertEquals(QUANTITY, productQuantity.getQuantity());
    }

    @Test
    void twoInstancesWithSameDataShouldBeEqual() {
        assertEquals(productQuantity, new ProductQuantity(PRODUCT, QUANTITY));
    }

    @Test
    void testToStringShouldReturnAnAppropriateRepresentation() {
        assertEquals("ProductQuantity{product=" + PRODUCT + ", quantity=" + QUANTITY + '}',
                productQuantity.toString());
    }
}
