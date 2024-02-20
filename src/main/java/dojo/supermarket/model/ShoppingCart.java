package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a shopping cart, containing a list of items and their quantities.
 */
public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    private final Map<Product, Double> productQuantities = new HashMap<>();

    /**
     * Retrieves a list of items in the shopping cart.
     * @return A list of ProductQuantity objects representing the items in the cart.
     */
    List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Adds a single unit of the specified product to the shopping cart.
     * @param product The product to add to the cart.
     */
    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    /**
     * Retrieves a map of product quantities in the shopping cart.
     * @return A map where keys are products and values are their quantities.
     */
    Map<Product, Double> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    /**
     * Adds a specified quantity of the given product to the shopping cart.
     * @param product The product to add to the cart.
     * @param quantity The quantity of the product to add.
     */
    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    /**
     * Applies the given offer to the specified product in the shopping cart and updates the receipt accordingly.
     * @param receipt The receipt to update with any applicable discounts.
     * @param offer The offer to apply.
     * @param p The product to which the offer applies.
     * @param unitPrice The unit price of the product.
     */
    void handleOffer(Receipt receipt, Offer offer, Product p, double unitPrice) {
        Discount maybeDiscount = offer.getDiscount(unitPrice, productQuantities.get(p).intValue());
        if (maybeDiscount != null) {
            receipt.addDiscount(maybeDiscount);
        }
    }

    /**
     * Applies all applicable offers in the provided map to the products in the shopping cart and updates the receipt.
     * @param receipt The receipt to update with any applicable discounts.
     * @param offers A map of products and their corresponding offers.
     * @param catalog The supermarket catalog used to retrieve unit prices.
     */
    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                handleOffer(receipt, offer, p, catalog.getUnitPrice(p));
            }
        }
    }
}