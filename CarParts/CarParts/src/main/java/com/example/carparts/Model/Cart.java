package com.example.carparts.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private ArrayList<Product> cart = new ArrayList<>();

    Set<Product> singleProductCart = new HashSet<>();

    private static Cart instance = null;

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addProduct(Product p){
        cart.add(p);
        singleProductCart.add(p);
    }

    public void removeOneProduct(Product p){
        cart.remove(p);
        checkProduct(p);
    }

    public void checkProduct(Product p){
        for (Product prod : cart){
            if (prod.id().equals(p.id())){
                return;
            }
        }
        singleProductCart.remove(p);
    }

    public void removeAllProduct(Product p){
        cart.removeIf(prod -> prod.id().equals(p.id()));
        singleProductCart.remove(p);
    }

    public Set getSingleProductCart(){
        return singleProductCart;
    }

    public ArrayList getCart(){
        return cart;
    }

    public double getTotalCart(){
        double total = 0.00;
        for (Product p : cart){
            total += p.price();
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double numeroDoubleArrotondato = bd.doubleValue();
        return numeroDoubleArrotondato;
    }

    public void clearCart(){
        cart.clear();
        singleProductCart.clear();
    }

    public String getProductQuantity(Product p){
        int quantity = 0;
        for (Product prod : cart){
            if (prod.id().equals(p.id())){
                quantity += 1;
            }
        }
        return Integer.toString(quantity);
    }



}
