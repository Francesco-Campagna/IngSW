package com.example.carparts.Proxy;

import java.io.IOException;
import java.sql.SQLException;

public interface ProductManage {
    boolean gestisciProdotti(boolean checkBox, String idProdotto, String nome, String descrizione, String prezzo, String venditore, String disponibilita) throws IOException, SQLException;
}
