package com.example.carparts.Model;

public record User(String email, String name, String surname, String balance, String password, Cart carrello) {
    @Override
    public String toString() {
        return email + ";" + name + ";" + surname + ";" + balance + ";" + password + ";" + carrello;
    }
}

