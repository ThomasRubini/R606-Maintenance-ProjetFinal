package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link Discount}.
 */
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

    @Test
    void twoInstancesWithSameDataShouldBeEqual() {
        Product product = new Product("Téléphone Samsung", ProductUnit.EACH);
        String discountDescription = "C'est le Black Friday, profitez d'une réduction " +
                "exclusive sur nos téléphones Samsung !";
        double discountAmount = -45.0;
        Discount discount1 = new Discount(product, discountDescription, discountAmount);
        Discount discount2 = new Discount(product, discountDescription, discountAmount);
        assertEquals(discount1, discount2);
    }

    @Test
    void testToStringShouldReturnAnAppropriateRepresentation() {
        Product product = new Product("Souris gamer Razer", ProductUnit.EACH);
        String discountDescription = "C'est le Black Friday, profitez de souris gamer Razer " +
                "à petits prix !";
        double discountAmount = -35.0;
        Discount discount = new Discount(product, discountDescription, discountAmount);
        assertEquals("Discount{description='" + discountDescription + '\'' + ", discountAmount="
                + discountAmount + ", product=" + product + '}', discount.toString());
    }
}
