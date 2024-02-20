package dojo.supermarket.model;

import dojo.supermarket.model.offers.FiveForAmount;
import dojo.supermarket.model.offers.TenPercentDiscount;
import dojo.supermarket.model.offers.ThreeForTwo;
import dojo.supermarket.model.offers.TwoForAmount;

/**
 * Represents an offer applicable to a product in the supermarket.
 */
public abstract class Offer {

    SpecialOfferType offerType;
    private final Product product;
    protected double argument;

    /**
     * Constructs a new offer with the specified type, product, and argument.
     * @param offerType The type of special offer.
     * @param product The product to which the offer applies.
     * @param argument The argument or parameter associated with the offer (e.g., discount percentage).
     */
    protected Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    /**
     * Retrieves the discount applicable for this offer, given a unit price and a quantity.
     * @param unitPrice The unit price of the product.
     * @param quantity The quantity of the product to which the offer applies.
     * @return The discount applicable, or null if no discount is applicable.
     */
    public abstract Discount getDiscount(double unitPrice, int quantity);

    /**
     * Factory method to create an instance of a specific type of offer.
     * @param offerType The type of special offer.
     * @param product The product to which the offer applies.
     * @param argument The argument or parameter associated with the offer (e.g., discount percentage).
     * @return An instance of the specific offer type.
     */
    public static Offer create(SpecialOfferType offerType, Product product, double argument) {
        return switch(offerType){
            case THREE_FOR_TWO -> new ThreeForTwo(product, argument);
            case TEN_PERCENT_DISCOUNT -> new TenPercentDiscount(product, argument);
            case TWO_FOR_AMOUNT -> new TwoForAmount(product, argument);
            case FIVE_FOR_AMOUNT -> new FiveForAmount(product, argument);
        };
    }

    /**
     * Retrieves the product associated with this offer.
     * @return The product to which the offer applies.
     */
    protected Product getProduct() {
        return product;
    }
}
