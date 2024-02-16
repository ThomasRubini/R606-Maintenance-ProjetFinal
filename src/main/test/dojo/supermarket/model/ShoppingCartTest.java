package dojo.supermarket.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ShoppingCartTest {

    private static Product TOMATO = new Product("tomato", ProductUnit.EACH);
    private static Product POTATO = new Product("potato", ProductUnit.EACH);
    private static Product SALAD = new Product("salad", ProductUnit.KILO);

    public SupermarketCatalog getCatalog() {
        HashMapCatalog catalog = new HashMapCatalog();
        catalog.addProduct(TOMATO, 4);
        catalog.addProduct(POTATO, 3);
        catalog.addProduct(SALAD, 9);
        return catalog;
    }

    public Map<Product, Offer> getOffers() {
        return Map.of(TOMATO, new Offer(SpecialOfferType.TWO_FOR_AMOUNT, TOMATO, 5));
    }

    @Test
    public void testQuantitiesStayTheSame() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(TOMATO, 1);
        cart.addItemQuantity(SALAD, 1);

        Assertions.assertEquals(cart.getItems(), List.of(new ProductQuantity(TOMATO, 1), new ProductQuantity(SALAD, 1)));
        Assertions.assertEquals(cart.productQuantities(), Map.of(TOMATO, 1d, SALAD, 1d));
    }

    @Test
    public void testQuantitiesAddUp() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(TOMATO, 1);
        cart.addItemQuantity(TOMATO, 1);

        Assertions.assertEquals(cart.getItems(), List.of(new ProductQuantity(TOMATO, 1), new ProductQuantity(TOMATO, 1)));
        Assertions.assertEquals(cart.productQuantities(), Map.of(TOMATO, 2d));
    }

    @Test
    public void testBasicOffer() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(TOMATO, 1);
        cart.addItemQuantity(TOMATO, 1);

        Receipt r = new Receipt();
        cart.handleOffers(r, getOffers(), getCatalog());

        Assertions.assertEquals(r.getDiscounts(), List.of(new Discount(TOMATO, "2 for 5.0", -3)));

        System.out.println(r.getDiscounts());
    }
}
