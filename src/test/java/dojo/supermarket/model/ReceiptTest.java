package dojo.supermarket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {

    private Receipt receipt;
    private static final Product PRODUCT = new Product("Sac", ProductUnit.EACH);
    private static final double QUANTITY = 30.0;
    private static final double PRICE = 4.75;
    private static final double TOTAL_PRICE = 142.5;
    private static final String DESCRIPTION = "C'est la rentrée ! -30 % sur les sacs";
    private static final double DISCOUNT_AMOUNT = -42.75;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
    }

    @Test
    void addingAProductShouldAddAProduct() {
        receipt.addProduct(PRODUCT, QUANTITY, PRICE, TOTAL_PRICE);
        List<ReceiptItem> receipts = receipt.getItems();
        assertEquals(1, receipts.size());

        ReceiptItem receiptItem = receipts.get(0);
        assertEquals(QUANTITY, receiptItem.getQuantity());
        assertEquals(PRODUCT, receiptItem.getProduct());
        assertEquals(PRICE, receiptItem.getPrice());
        assertEquals(TOTAL_PRICE, receiptItem.getTotalPrice());
    }

    @Test
    void addADiscountShouldAddADiscount() {
        Discount discount = new Discount(PRODUCT, DESCRIPTION, DISCOUNT_AMOUNT);

        receipt.addDiscount(discount);
        List<Discount> discounts = receipt.getDiscounts();
        assertEquals(1, discounts.size());

        Discount receiptDiscount = discounts.get(0);
        assertEquals(PRODUCT, receiptDiscount.getProduct());
        assertEquals(DESCRIPTION, receiptDiscount.getDescription());
        assertEquals(DISCOUNT_AMOUNT, receiptDiscount.getDiscountAmount());
    }

    @Test
    void defaultTotalPriceShouldBeEqualToZero() {
        assertEquals(0.0, receipt.getTotalPrice());
    }

    @Test
    void addAProductWithADiscountShouldApplyTheDiscount() {
        receipt.addDiscount(new Discount(PRODUCT, DESCRIPTION, DISCOUNT_AMOUNT));
        receipt.addProduct(PRODUCT, QUANTITY, PRICE, TOTAL_PRICE);

        assertEquals(TOTAL_PRICE + DISCOUNT_AMOUNT, receipt.getTotalPrice());
    }

    @Test
    void testToStringShouldReturnAnAppropriateRepresentation() {
        receipt.addDiscount(new Discount(PRODUCT, DESCRIPTION, DISCOUNT_AMOUNT));
        receipt.addProduct(PRODUCT, QUANTITY, PRICE, TOTAL_PRICE);

        assertEquals("Receipt{items=" + receipt.getItems().toString() + ", discounts="
                + receipt.getDiscounts().toString() + '}', receipt.toString());
    }

    @Test
    void twoInstancesWithSameDataShouldBeEqual() {
        receipt.addDiscount(new Discount(PRODUCT, DESCRIPTION, DISCOUNT_AMOUNT));
        receipt.addProduct(PRODUCT, QUANTITY, PRICE, TOTAL_PRICE);

        Receipt receipt2 = new Receipt();
        receipt2.addDiscount(new Discount(PRODUCT, DESCRIPTION, DISCOUNT_AMOUNT));
        receipt2.addProduct(PRODUCT, QUANTITY, PRICE, TOTAL_PRICE);

        assertEquals(receipt, receipt2);
    }
}
