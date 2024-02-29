package com.example.carparts.Proxy;

import com.example.carparts.Handler.ManageProductHandler;
import com.example.carparts.View.SceneHandler;

import java.io.IOException;
import java.sql.SQLException;

public class ProductManageImpl implements ProductManage{
    @Override
    public boolean gestisciProdotti(boolean checkBox, String idProdotto, String nome, String descrizione, String prezzo, String venditore, String disponibilita) throws SQLException {
        return ManageProductHandler.getInstance().manageProduct(checkBox, idProdotto, nome, descrizione, prezzo, venditore, disponibilita);
    }
}
