package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link Offer}.
 */
class OfferTest {

    @Test
    void buildInstanceShouldntThrowAnything() {
        assertDoesNotThrow(() -> Offer.create(
                SpecialOfferType.THREE_FOR_TWO,
                new Product("Lit", ProductUnit.EACH),
                3.0));
    }

    @Test
    void testGetterAndPropertiesShouldReturnDataProvidedInConstructor() {
        SpecialOfferType offerType = SpecialOfferType.THREE_FOR_TWO;
        Product product = new Product("Lit", ProductUnit.EACH);
        double argument = 3.0;
        Offer offer = Offer.create(offerType, product, argument);
        assertEquals(product, offer.getProduct());
        assertEquals(offerType, offer.offerType);
        assertEquals(argument, offer.argument);
    }
}
