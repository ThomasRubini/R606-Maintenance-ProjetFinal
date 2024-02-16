package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptItemTest {
    @Test
    void buildInstanceShouldntThrowAnything() {
        assertDoesNotThrow(() -> new ReceiptItem(
                new Product("Banane", ProductUnit.KILO),
                4.0,
                0.50,
                2.0));
    }

    @Test
    void testGettersShouldReturnDataProvidedInConstructor() {
        Product product = new Product("Banane", ProductUnit.KILO);
        double quantity = 4.0;
        double price = 0.5;
        double totalPrice = 2.0;
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
}
