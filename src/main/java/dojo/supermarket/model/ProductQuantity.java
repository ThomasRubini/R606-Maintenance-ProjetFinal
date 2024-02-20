package dojo.supermarket.model;

import java.util.Objects;

/**
 * Data class for a product quantity.
 *
 * <p>
 * A product quantity is composed of a product and a quantity.
 * </p>
 */
public class ProductQuantity {

    private final Product product;
    private final double quantity;

    /**
     * Construct a new {@link ProductQuantity} instance.
     *
     * @param product  the product
     * @param quantity the quantity of the product
     */
    public ProductQuantity(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
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
     * Get the quantity.
     *
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantity that = (ProductQuantity) o;
        return Double.compare(quantity, that.quantity) == 0 && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    @Override
    public String toString() {
        return "ProductQuantity{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
