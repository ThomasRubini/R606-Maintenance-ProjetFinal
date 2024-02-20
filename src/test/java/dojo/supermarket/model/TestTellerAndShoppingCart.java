package dojo.supermarket.model;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTellerAndShoppingCart {

    static class TestData {
        List<Pair<Product, Double>> products;
        List<Offer> offers;

        Map<Product, Double> quantities;
        double totalPrice;
        List<ReceiptItem> receiptItems;
        List<Discount> discounts;

        TestData(List<Pair<Product, Double>> products,
                 List<Offer> offers,
                 Map<Product, Double> quantities,
                 double totalPrice,
                 List<ReceiptItem> receiptItems,
                 List<Discount> discounts) {
            this.products = products;
            this.offers = offers;
            this.quantities = quantities;
            this.totalPrice = totalPrice;
            this.receiptItems = receiptItems;
            this.discounts = discounts;
        }
    }

    private static final Product TOMATO = new Product("tomato", ProductUnit.EACH);
    private static final Product POTATO = new Product("potato", ProductUnit.EACH);
    private static final Product SALAD = new Product("salad", ProductUnit.KILO);


    static SupermarketCatalog getCatalog() {
        HashMapCatalog catalog = new HashMapCatalog();
        catalog.addProduct(TOMATO, 4);
        catalog.addProduct(POTATO, 3);
        catalog.addProduct(SALAD, 9);
        return catalog;
    }

    static List<TestData> getAllTestData() {
        return List.of(
                new TestData(
                        List.of(Pair.of(TOMATO, 1d), Pair.of(SALAD, 2d)),
                        List.of(),
                        Map.of(SALAD, 2d, TOMATO, 1d),
                        22,
                        List.of(new ReceiptItem(TOMATO, 1, 4, 4),
                                new ReceiptItem(SALAD, 2, 9, 18)),
                        List.of()
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 1d), Pair.of(SALAD, 1d), Pair.of(TOMATO, 2d)),
                        List.of(),
                        Map.of(SALAD, 1d, TOMATO, 3d),
                        21,
                        List.of(new ReceiptItem(TOMATO, 1, 4, 4),
                                new ReceiptItem(SALAD, 1, 9, 9),
                                new ReceiptItem(TOMATO, 2, 4, 8)),
                        List.of()
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 1d), Pair.of(SALAD, 1d), Pair.of(TOMATO, 2d)),
                        List.of(new Offer(SpecialOfferType.TWO_FOR_AMOUNT, TOMATO, 5)),
                        Map.of(SALAD, 1d, TOMATO, 3d),
                        18,
                        List.of(new ReceiptItem(TOMATO, 1, 4, 4),
                                new ReceiptItem(SALAD, 1, 9, 9),
                                new ReceiptItem(TOMATO, 2, 4, 8)),
                        List.of(new Discount(TOMATO, "2 for 5.0", -3))
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 5d)),
                        List.of(new Offer(SpecialOfferType.FIVE_FOR_AMOUNT, TOMATO, 5)),
                        Map.of(TOMATO, 5d),
                        5,
                        List.of(new ReceiptItem(TOMATO, 5, 4, 20)),
                        List.of(new Discount(TOMATO, "5 for 5.0", -15))
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 5d)),
                        List.of(new Offer(SpecialOfferType.THREE_FOR_TWO, TOMATO, 5)),
                        Map.of(TOMATO, 5d),
                        16,
                        List.of(new ReceiptItem(TOMATO, 5, 4, 20)),
                        List.of(new Discount(TOMATO, "3 for 2", -4))
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 3d)),
                        List.of(new Offer(SpecialOfferType.THREE_FOR_TWO, TOMATO, 5)),
                        Map.of(TOMATO, 3d),
                        8,
                        List.of(new ReceiptItem(TOMATO, 3, 4, 12)),
                        List.of(new Discount(TOMATO, "3 for 2", -4))
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 5d)),
                        List.of(new Offer(SpecialOfferType.TEN_PERCENT_DISCOUNT, TOMATO, 5)),
                        Map.of(TOMATO, 5d),
                        19,
                        List.of(new ReceiptItem(TOMATO, 5, 4, 20)),
                        List.of(new Discount(TOMATO, "5.0% off", -1))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getAllTestData")
    void test(TestData data) {
        SupermarketCatalog catalog = getCatalog();
        Teller teller = new Teller(catalog);
        for (Offer offer : data.offers) {
            teller.addSpecialOffer(offer.offerType, offer.getProduct(), offer.argument);
        }

        ShoppingCart cart = new ShoppingCart();
        for (Pair<Product, Double> pair : data.products) {
            cart.addItemQuantity(pair.getLeft(), pair.getRight());
        }

        assertEquals(data.products.stream()
                .map(e -> new ProductQuantity(e.getLeft(), e.getRight()))
                .toList(), cart.getItems());
        assertEquals(data.quantities, cart.productQuantities());

        Receipt r = teller.checksOutArticlesFrom(cart);
        assertEquals(data.discounts, r.getDiscounts());
        assertEquals(data.receiptItems, r.getItems());
        assertEquals(data.totalPrice, r.getTotalPrice());
    }

}
