package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class FiveForAmount extends Offer {
    public FiveForAmount(Product product, double argument) {
        super(SpecialOfferType.FIVE_FOR_AMOUNT, product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, int quantity) {
        if (quantity >= 5) {
            int numberOfXs = quantity / 5;
            double discountTotal = unitPrice * quantity - (this.argument * numberOfXs + quantity % 5 * unitPrice);
            return new Discount(this.getProduct(), "5 for " + this.argument, -discountTotal);
        }
        return null;
    }
}
