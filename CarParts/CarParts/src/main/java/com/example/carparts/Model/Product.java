package com.example.carparts.Model;

public record Product(String id, String name, String description, double price, String seller, int disponibility) {
    @Override
    public String toString() {
        return id + ";" + name + ";" + description + ";" + price + ";" + seller  + ";" + disponibility;
    }
}