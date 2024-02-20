package dojo.supermarket.model;

import java.util.Objects;

/**
 * Data class representing a discount.
 *
 * <p>
 * A discount is composed of a description, a discount amount and a
 * product on which the discount should be applied.
 * </p>
 */
public class Discount {

    private final String description;
    private final double discountAmount;
    private final Product product;

    /**
     * Create a {@link Discount} instance.
     *
     * @param product        the product on which the discount should be applied
     * @param description    the description of the discount
     * @param discountAmount the amount of the discount
     */
    public Discount(Product product, String description, double discountAmount) {
        this.product = product;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    /**
     * Get the description of the discount.
     *
     * @return the description of the discount
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the amount of the discount.
     *
     * @return the amount of the discount
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Get the product on which the discount should be applied.
     *
     * @return the product on which the discount should be applied
     */
    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "description='" + description + '\'' +
                ", discountAmount=" + discountAmount +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Double.compare(discountAmount, discount.discountAmount) == 0 && Objects.equals(description, discount.description) && Objects.equals(product, discount.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, discountAmount, product);
    }
}
