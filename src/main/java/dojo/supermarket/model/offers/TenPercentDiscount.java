package dojo.supermarket.model.offers;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class TenPercentDiscount extends Offer {
    public TenPercentDiscount(Product product, double argument) {
        super(SpecialOfferType.TEN_PERCENT_DISCOUNT, product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, int quantity) {
        return new Discount(this.getProduct(), this.argument + "% off", -quantity * unitPrice * this.argument / 100.0);
    }
}
