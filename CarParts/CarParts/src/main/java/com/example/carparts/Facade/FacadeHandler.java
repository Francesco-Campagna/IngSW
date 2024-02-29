package com.example.carparts.Facade;

import com.example.carparts.Handler.CouponHandler;
import com.example.carparts.Handler.ManageProductHandler;
import com.example.carparts.Handler.ProductHandler;
import com.example.carparts.Model.Product;
import com.example.carparts.Proxy.ProductManage;
import com.example.carparts.Proxy.ProductManageProxy;

import java.io.IOException;
import java.sql.SQLException;

public class FacadeHandler {
    private static FacadeHandler instance;
    private ProductHandler productHandler;
    private CouponHandler couponHandler;

    private ManageProductHandler manageProductHandler;
    private FacadeHandler(){
        this.productHandler = ProductHandler.getInstance();
        this.couponHandler = CouponHandler.getInstance();
        this.manageProductHandler = ManageProductHandler.getInstance();
    }

    public static FacadeHandler getInstance() {
        if (instance == null) {
            instance = new FacadeHandler();
        }
        return instance;
    }

    public void setProduct(Product product) {
        productHandler.setProduct(product);
    }

    public Product getProduct() {
        return productHandler.getProduct();
    }

    public void setNullProduct() {
        productHandler.setNullProduct();
    }

    public double getBalance() {
        return couponHandler.getBalance();
    }

    public void updateBalance(double x, String type) {
        couponHandler.updateBalance(x, type);
    }
    public boolean manageProduct(boolean checkBox, String idProdotto, String nome, String descrizione, String prezzo, String venditore, String disponibilita) throws SQLException, IOException {
        ProductManage productManage = new ProductManageProxy();
        return productManage.gestisciProdotti(checkBox, idProdotto, nome, descrizione, prezzo, venditore, disponibilita);
    }
}
