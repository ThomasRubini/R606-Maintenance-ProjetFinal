package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link ReceiptItem}.
 */
class ReceiptItemTest {

    @Test
    void buildInstanceShouldntThrowAnything() {
        assertDoesNotThrow(() -> new ReceiptItem(
                new Product("Pomme", ProductUnit.KILO),
                3.0,
                2.5,
                7.5));
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        Product product = new Product("Orange", ProductUnit.KILO);
        double quantity = 2.0;
        double price = 1.25;
        double totalPrice = 2.5;
        ReceiptItem receiptItem = new ReceiptItem(product, quantity, price, totalPrice);
        assertEquals(product, receiptItem.getProduct());
        assertEquals(quantity, receiptItem.getQuantity());
        assertEquals(price, receiptItem.getPrice());
        assertEquals(totalPrice, receiptItem.getTotalPrice());
    }

    @Test
    void twoInstancesWithSameDataShouldBeEqual() {
        Product product = new Product("Banane", ProductUnit.KILO);
        double quantity = 4.0;
        double price = 0.5;
        double totalPrice = 2.0;
        ReceiptItem receiptItem1 = new ReceiptItem(product, quantity, price, totalPrice);
        ReceiptItem receiptItem2 = new ReceiptItem(product, quantity, price, totalPrice);
        assertEquals(receiptItem1, receiptItem2);
    }

    @Test
    void testToStringShouldReturnAnAppropriateRepresentation() {
        Product product = new Product("Tomate", ProductUnit.KILO);
        double quantity = 1.0;
        double price = 0.75;
        double totalPrice = 0.75;
        ReceiptItem receiptItem = new ReceiptItem(product, quantity, price, totalPrice);
        assertEquals("ReceiptItem{product=" + product + ", price=" + price + ", totalPrice="
                + totalPrice + ", quantity=" + quantity + '}', receiptItem.toString());
    }
}
