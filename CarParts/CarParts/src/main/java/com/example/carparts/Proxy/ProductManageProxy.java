package com.example.carparts.Proxy;

import com.example.carparts.Message;
import com.example.carparts.View.SceneHandler;
import java.io.IOException;
import java.sql.SQLException;

public class ProductManageProxy implements ProductManage{
    private ProductManage productService;
    private static boolean isAdmin = false;

    public ProductManageProxy() {
        this.productService = new ProductManageImpl();
    }

    @Override
    public boolean gestisciProdotti(boolean checkBox, String idProdotto, String nome, String descrizione, String prezzo, String venditore, String disponibilita) throws IOException, SQLException {
        if (isAdmin) {
            productService.gestisciProdotti(checkBox, idProdotto, nome, descrizione, prezzo, venditore, disponibilita);
        } else {
            SceneHandler.getInstance().showAlert("Attenzione", Message.not_admin_error, 0);
        }
        return false;
    }

    public static void setAdmin(boolean value){
        isAdmin = value;
    }

}
