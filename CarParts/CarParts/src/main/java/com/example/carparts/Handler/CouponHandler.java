package com.example.carparts.Handler;

public class CouponHandler {

    private static CouponHandler instance = null;
    private static double saldo = 0;

    private CouponHandler() {}

    public static CouponHandler getInstance() {
        if (instance == null) {
            instance = new CouponHandler();
        }
        return instance;
    }

    public double getBalance(){
        return saldo;
    }

    public void updateBalance(double x, String type){
        if (type == "sum"){
            saldo += x;
        }else if(type == "sub"){
            saldo -= x;
        }
    }
}
