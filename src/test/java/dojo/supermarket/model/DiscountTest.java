package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {
    @Test
    void buildInstanceShouldntThrowAnything() {
        Product product = new Product("Framework 16'", ProductUnit.EACH);
        String discountDescription = "Pour la semaine green, profitez d'une réduction " +
                "exceptionnelle sur le PC modulable Framework 16 pouces !";
        double discountAmount = -300.0;
        assertDoesNotThrow(() -> new Discount(product, discountDescription, discountAmount));
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        Product product = new Product("Imprimante Epson", ProductUnit.EACH);
        String discountDescription = "C'est le Black Friday, profitez d'une réduction " +
                "très limitée sur nos imprimantes Epson !";
        double discountAmount = -60.0;
        Discount discount = new Discount(product, discountDescription, discountAmount);
        assertEquals(product, discount.getProduct());
        assertEquals(discountDescription, discount.getDescription());
        assertEquals(discountAmount, discount.getDiscountAmount());
    }
}
