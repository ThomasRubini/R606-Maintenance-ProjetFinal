package dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class HashMapCatalog implements SupermarketCatalog {

    private final Map<Product, Double> data = new HashMap<>();

    @Override
    public void addProduct(Product product, double price) {
        data.put(product, price);
    }

    @Override
    public double getUnitPrice(Product product) {
        return data.get(product);
    }
}
