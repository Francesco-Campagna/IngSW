package com.example.carparts.Model;

import java.util.ArrayList;

public class Buono {

    static ArrayList<String> codes = new ArrayList<>();

    public static void creaBuoni(){
        for (int i = 1; i <= 5; i++) {
            codes.add(String.valueOf(i));
        }
    }

    public static boolean checkBuono(String buono){
        return codes.contains(buono);
    }

    public static void rimuoviBuono(String buono){
        codes.remove(buono);
    }
}
