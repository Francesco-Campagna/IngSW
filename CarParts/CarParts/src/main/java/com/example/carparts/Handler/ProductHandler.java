package com.example.carparts.Handler;

import com.example.carparts.Model.Product;

public class ProductHandler {
    private static ProductHandler instance = null;
    private Product product = null;

    private ProductHandler() {}

    public static ProductHandler getInstance() {
        if(instance == null)
            instance = new ProductHandler();
        return instance;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
    public void setNullProduct(){this.product = null;}
}
