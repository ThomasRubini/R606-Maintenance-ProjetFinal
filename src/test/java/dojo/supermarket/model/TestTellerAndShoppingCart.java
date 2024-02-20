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

    private static final Product TOOTHBRUSH = new Product("toothbrush", ProductUnit.EACH);
    private static final Product APPLE = new Product("apple", ProductUnit.KILO);
    private static final Product RICE_BAG = new Product("rice bag", ProductUnit.EACH);
    private static final Product TOOTHPASTE = new Product("toothpaste", ProductUnit.EACH);
    private static final Product CHERRY_TOMATO_CAN = new Product("cherry tomatoes can", ProductUnit.EACH);


    static SupermarketCatalog getCatalog() {
        HashMapCatalog catalog = new HashMapCatalog();
        catalog.addProduct(TOOTHBRUSH, 0.99);
        catalog.addProduct(APPLE, 1.99);
        catalog.addProduct(RICE_BAG, 2.49);
        catalog.addProduct(TOOTHPASTE, 1.79);
        catalog.addProduct(CHERRY_TOMATO_CAN, 0.69);
        return catalog;
    }

    static List<TestData> getAllTestData() {
        return List.of(
                new TestData(
                        List.of(Pair.of(TOOTHBRUSH, 1d), Pair.of(APPLE, 2d)),
                        List.of(),
                        Map.of(APPLE, 2d, TOOTHBRUSH, 1d),
                        4.97,
                        List.of(new ReceiptItem(TOOTHBRUSH, 1, 0.99, 0.99),
                                new ReceiptItem(APPLE, 2, 1.99, 3.98)),
                        List.of()
                ),
                new TestData(
                        List.of(Pair.of(TOOTHBRUSH, 1d), Pair.of(APPLE, 1d), Pair.of(TOOTHBRUSH, 2d)),
                        List.of(),
                        Map.of(APPLE, 1d, TOOTHBRUSH, 3d),
                        4.96,
                        List.of(new ReceiptItem(TOOTHBRUSH, 1, 0.99, 0.99),
                                new ReceiptItem(APPLE, 1, 1.99, 1.99),
                                new ReceiptItem(TOOTHBRUSH, 2, 0.99, 1.98)),
                        List.of()
                ),
                new TestData(
                        List.of(Pair.of(TOOTHBRUSH, 2d)),
                        List.of(Offer.create(SpecialOfferType.TWO_FOR_AMOUNT, TOOTHBRUSH, 0.99)),
                        Map.of(TOOTHBRUSH, 2d),
                        0.99,
                        List.of(new ReceiptItem(TOOTHBRUSH, 2, 0.99, 1.98)),
                        List.of(new Discount(TOOTHBRUSH, "2 for 0.99", -0.99))
                ),
                new TestData(
                        List.of(Pair.of(APPLE, 5d)),
                        List.of(Offer.create(SpecialOfferType.TEN_PERCENT_DISCOUNT, APPLE, 20)),
                        Map.of(APPLE, 5d),
                        7.959999999999999,
                        List.of(new ReceiptItem(APPLE, 5, 1.99, 9.95)),
                        List.of(new Discount(APPLE, "20.0% off", -1.99))
                ),
                new TestData(
                        List.of(Pair.of(RICE_BAG, 5d)),
                        List.of(Offer.create(SpecialOfferType.TEN_PERCENT_DISCOUNT, RICE_BAG, 10)),
                        Map.of(RICE_BAG, 5d),
                        8.055,
                        List.of(new ReceiptItem(RICE_BAG, 5, 1.79, 8.95)),
                        List.of(new Discount(RICE_BAG, "10.0% off", -0.895))
                ),
                new TestData(
                        List.of(Pair.of(TOOTHPASTE, 5d)),
                        List.of(Offer.create(SpecialOfferType.FIVE_FOR_AMOUNT, TOOTHPASTE, 7.49)),
                        Map.of(TOOTHPASTE, 5d),
                        7.49,
                        List.of(new ReceiptItem(TOOTHPASTE, 5, 1.79, 8.95)),
                        List.of(new Discount(TOOTHPASTE, "5 for 7.49", -1.459999999999999))
                ),
                new TestData(
                        List.of(Pair.of(CHERRY_TOMATO_CAN, 5d)),
                        List.of(Offer.create(SpecialOfferType.TWO_FOR_AMOUNT, CHERRY_TOMATO_CAN, 0.99)),
                        Map.of(CHERRY_TOMATO_CAN, 5d),
                        2.67,
                        List.of(new ReceiptItem(CHERRY_TOMATO_CAN, 5, 0.69, 3.4499999999999997)),
                        List.of(new Discount(CHERRY_TOMATO_CAN, "2 for 0.99", -0.7799999999999998))
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
