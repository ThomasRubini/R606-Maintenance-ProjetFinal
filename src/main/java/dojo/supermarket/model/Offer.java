package dojo.supermarket.model;

import dojo.supermarket.model.offers.FiveForAmount;
import dojo.supermarket.model.offers.TenPercentDiscount;
import dojo.supermarket.model.offers.ThreeForTwo;
import dojo.supermarket.model.offers.TwoForAmount;

public abstract class Offer {

    SpecialOfferType offerType;
    private final Product product;
    protected double argument;

    protected Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    public abstract Discount getDiscount(double unitPrice, int quantity);

    public static Offer create(SpecialOfferType offerType, Product product, double argument) {
        return switch(offerType){
            case THREE_FOR_TWO -> new ThreeForTwo(product, argument);
            case TEN_PERCENT_DISCOUNT -> new TenPercentDiscount(product, argument);
            case TWO_FOR_AMOUNT -> new TwoForAmount(product, argument);
            case FIVE_FOR_AMOUNT -> new FiveForAmount(product, argument);
        };
    }

    protected Product getProduct() {
        return product;
    }
}
