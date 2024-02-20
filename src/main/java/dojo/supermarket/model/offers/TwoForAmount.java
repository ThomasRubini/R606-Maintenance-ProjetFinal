package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class TwoForAmount extends Offer {
    public TwoForAmount(Product product, double argument) {
        super(SpecialOfferType.TWO_FOR_AMOUNT, product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, int quantity) {
        if (quantity >= 2) {
            double total = this.argument * (quantity / 2) + quantity % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            return new Discount(this.getProduct(), "2 for " + this.argument, -discountN);
        }
        return null;
    }
}
