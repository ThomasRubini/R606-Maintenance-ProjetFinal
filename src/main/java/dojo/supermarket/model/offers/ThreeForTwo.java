package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class ThreeForTwo extends Offer {
    public ThreeForTwo(Product product, double argument) {
        super(SpecialOfferType.THREE_FOR_TWO, product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, int quantity) {
        if (quantity > 2) {
            double discountAmount = quantity / 3 * unitPrice;
            return new Discount(this.getProduct(), "3 for 2", -discountAmount);
        }
        return null;
    }
}
