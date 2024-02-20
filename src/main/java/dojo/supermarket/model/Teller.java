package dojo.supermarket.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a teller in a supermarket, responsible for handling transactions and applying special offers.
 */
public class Teller {

    private final SupermarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();

    /**
     * Constructs a new Teller object with the provided supermarket catalog.
     * @param catalog The supermarket catalog used to retrieve product prices.
     */
    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    /**
     * Adds a special offer for a product to the teller's list of offers.
     * @param offerType The type of special offer.
     * @param product The product to which the offer applies.
     * @param argument The argument or parameter associated with the offer (e.g., discount percentage).
     */
    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        offers.put(product, Offer.create(offerType, product, argument));
    }

    /**
     * Processes the articles in the provided shopping cart and generates a receipt.
     * Applies any applicable special offers to calculate the final prices.
     * @param theCart The shopping cart containing the items to be checked out.
     * @return A receipt containing the details of the transaction.
     */
    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.handleOffers(receipt, offers, catalog);

        return receipt;
    }
}