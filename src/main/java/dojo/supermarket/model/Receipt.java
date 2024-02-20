package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a receipt.
 *
 * <p>
 * A receipt is composed of items and discounts which can be added one by one
 * stored in separated lists.
 * </p>
 */
public class Receipt {

    private final List<ReceiptItem> items = new ArrayList<>();
    private final List<Discount> discounts = new ArrayList<>();

    /**
     * Get the total price.
     *
     * <p>
     * The total price is composed of the total price of all items on which the
     * discount amount is subtracted.
     * </p>
     *
     * <p>
     * If there is no item and no discount, {@code 0.0} is returned; if there
     * is no items and a discount, a negative double will be returned.
     * </p>
     *
     * @return the total price, which can be lowed or equal to {@code 0.0}
     */
    public double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : items) {
            total += item.getTotalPrice();
        }
        for (Discount discount : discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    /**
     * Add a product to the list of items.
     *
     * <p>
     * A {@link ReceiptItem} will be built with the given parameters and added
     * to the items list.
     * </p>
     *
     * @param p          the product to add
     * @param quantity   the quantity to use
     * @param price      the price to use
     * @param totalPrice the total price to use
     */
    public void addProduct(Product p, double quantity, double price, double totalPrice) {
        items.add(new ReceiptItem(p, quantity, price, totalPrice));
    }

    /**
     * Get the list of items.
     *
     * @return the list of items, unmodifiable
     */
    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Add a discount to the list of discounts.
     *
     * @param discount the discount to add
     */
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    /**
     * Get the list of discounts.
     *
     * @return the list of discounts, unmodifiable
     */
    public List<Discount> getDiscounts() {
        return Collections.unmodifiableList(discounts);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "items=" + items +
                ", discounts=" + discounts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(items, receipt.items) && Objects.equals(discounts, receipt.discounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, discounts);
    }
}
