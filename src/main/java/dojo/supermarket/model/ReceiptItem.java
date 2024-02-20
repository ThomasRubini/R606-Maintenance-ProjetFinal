package dojo.supermarket.model;

import java.util.Objects;

/**
 * Data class representing a receipt item.
 *
 * <p>
 * It is composed of a product, a price, a total price and a quantity.
 * </p>
 */
public class ReceiptItem {

    private final Product product;
    private final double price;
    private final double totalPrice;
    private final double quantity;

    /**
     * Construct a {@link ReceiptItem} instance.
     *
     * @param p          the product to use
     * @param quantity   the quantity of the product
     * @param price      the price of one unit of the product
     * @param totalPrice the total price of the product
     */
    ReceiptItem(Product p, double quantity, double price, double totalPrice) {
        this.product = p;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    /**
     * Get the price of one unit of the product.
     *
     * @return the price of one unit of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Get the quantity of the product.
     *
     * @return the quantity of the product
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Get the total price of the product.
     *
     * @return the total price of the product
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptItem that)) return false;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price, totalPrice, quantity);
    }

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "product=" + product +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                '}';
    }
}
