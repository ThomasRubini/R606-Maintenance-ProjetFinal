package dojo.supermarket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {

    private Receipt receipt;
    private Product product;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
        product = new Product("Sac", ProductUnit.EACH);
    }

    @Test
    void addingAProductShouldAddAProduct() {
        double quantity = 30.0;
        double price = 4.75;
        double totalPrice = 142.5;

        receipt.addProduct(product, quantity, price, totalPrice);
        List<ReceiptItem> receipts = receipt.getItems();
        assertEquals(1, receipts.size());

        ReceiptItem receiptItem = receipts.get(0);
        assertEquals(quantity, receiptItem.getQuantity());
        assertEquals(product, receiptItem.getProduct());
        assertEquals(price, receiptItem.getPrice());
        assertEquals(totalPrice, receiptItem.getTotalPrice());
    }

    @Test
    void addADiscountShouldAddADiscount() {
        String description = "C'est la rentrée ! -30 % sur les sacs";
        double discountAmount = -42.75;
        Discount discount = new Discount(product, description, discountAmount);

        receipt.addDiscount(discount);
        List<Discount> discounts = receipt.getDiscounts();
        assertEquals(1, discounts.size());

        Discount receiptDiscount = discounts.get(0);
        assertEquals(product, receiptDiscount.getProduct());
        assertEquals(description, receiptDiscount.getDescription());
        assertEquals(discountAmount, receiptDiscount.getDiscountAmount());
    }

    @Test
    void defaultTotalPriceShouldBeEqualToZero() {
        assertEquals(0.0, receipt.getTotalPrice());
    }

    @Test
    void addAProductWithADiscountShouldApplyTheDiscount() {
        double quantity = 30.0;
        double price = 4.75;
        double totalPrice = 142.5;
        String description = "C'est la rentrée ! -30 % sur les sacs";
        double discountAmount = -42.75;

        Discount discount = new Discount(product, description, discountAmount);
        receipt.addDiscount(discount);
        receipt.addProduct(product, quantity, price, totalPrice);

        assertEquals(totalPrice + discountAmount, receipt.getTotalPrice());
    }
}
