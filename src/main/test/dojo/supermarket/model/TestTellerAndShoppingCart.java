package dojo.supermarket.model;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class TestTellerAndShoppingCart {

    public static class TestData {
        List<Pair<Product, Double>> products;

        Map<Product, Double> quantities;
        double totalPrice;
        List<ReceiptItem> receiptItems;
        List<Discount> discounts;

        public TestData(List<Pair<Product, Double>> products, Map<Product, Double> quantities, double totalPrice, List<ReceiptItem> receiptItems, List<Discount> discounts) {
            this.products = products;
            this.quantities = quantities;
            this.totalPrice = totalPrice;
            this.receiptItems = receiptItems;
            this.discounts = discounts;
        }
    }

    static final Product TOMATO = new Product("tomato", ProductUnit.EACH);
    static final Product POTATO = new Product("potato", ProductUnit.EACH);
    static final Product SALAD = new Product("salad", ProductUnit.KILO);


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
                        Map.of(SALAD, 2d, TOMATO, 1d),
                        22,
                        List.of(new ReceiptItem(TOMATO, 1, 4, 4), new ReceiptItem(SALAD, 2, 9, 18)),
                        List.of()
                ),
                new TestData(
                        List.of(Pair.of(TOMATO, 1d), Pair.of(SALAD, 1d), Pair.of(TOMATO, 2d)),
                        Map.of(SALAD, 1d, TOMATO, 3d),
                        21,
                        List.of(new ReceiptItem(TOMATO, 1, 4, 4), new ReceiptItem(SALAD, 1, 9, 9), new ReceiptItem(TOMATO, 2, 4, 8)),
                        List.of()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getAllTestData")
    public void testWithoutOffers(TestData data) {
        SupermarketCatalog catalog = getCatalog();
        Teller teller = new Teller(catalog);

        ShoppingCart cart = new ShoppingCart();
        for(Pair<Product, Double> pair : data.products) {
            cart.addItemQuantity(pair.getLeft(), pair.getRight());
        }

        Assertions.assertEquals(data.products.stream().map(e -> new ProductQuantity(e.getLeft(), e.getRight())).toList(), cart.getItems());
        Assertions.assertEquals(data.quantities, cart.productQuantities());

        Receipt r = teller.checksOutArticlesFrom(cart);
        Assertions.assertEquals(data.discounts, r.getDiscounts());
        Assertions.assertEquals(data.receiptItems, r.getItems());
        Assertions.assertEquals(data.totalPrice, r.getTotalPrice());
    }

}
