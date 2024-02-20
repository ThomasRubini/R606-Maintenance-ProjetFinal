package dojo.supermarket.model;

import java.util.Objects;

public class ProductQuantity {

    private final Product product;
    private final double quantity;

    public ProductQuantity(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

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
