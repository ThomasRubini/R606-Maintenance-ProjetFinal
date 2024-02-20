package dojo.supermarket.model;

import java.util.Objects;

/**
 * Data class for a product.
 *
 * <p>
 * A product is composed of a name and a product unit.
 * </p>
 */
public class Product {

    private final String name;
    private final ProductUnit unit;

    /**
     * Create a {@link Product} instance.
     *
     * @param name the name of the product
     * @param unit {@link ProductUnit the unit} of the product
     */
    public Product(String name, ProductUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    /**
     * Get the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Get the unit of the product.
     *
     * @return the unit of the product
     */
    public ProductUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                unit == product.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }
}
