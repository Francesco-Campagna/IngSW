package com.example.carparts.Handler;

import com.example.carparts.Database.DBConnection;
import com.example.carparts.Message;
import com.example.carparts.Model.Product;
import com.example.carparts.View.SceneHandler;

import java.sql.SQLException;

public class ManageProductHandler {


    private static ManageProductHandler instance = null;
    private ManageProductHandler() {}

    public static ManageProductHandler getInstance() {
        if (instance == null) {
            instance = new ManageProductHandler();
        }
        return instance;
    }

    public boolean manageProduct(boolean checkBox, String idProdotto, String nome, String descrizione, String prezzo, String venditore, String disponibilita) throws SQLException {
        boolean addCondition = !checkBox && !idProdotto.isEmpty() && !nome.isEmpty() && !descrizione.isEmpty() && !prezzo.isEmpty() && !venditore.isEmpty() && !disponibilita.isEmpty();
        boolean removeCondition = checkBox && !idProdotto.isEmpty() && nome.isEmpty() && descrizione.isEmpty() && prezzo.isEmpty() && venditore.isEmpty() && disponibilita.isEmpty();
        if (!addCondition && !removeCondition){
            SceneHandler.getInstance().showAlert("Attenzione", Message.general_product_error, 0);
            return false;
        }
        Product p = null;
        boolean clearCondition = false;
        if (addCondition){
            try{
                Integer.parseInt(prezzo);
                Integer.parseInt(disponibilita);
            }catch (Exception e) {
                SceneHandler.getInstance().showAlert("Attenzione", Message.add_product_int_error, 0);
                return false;
            }
            p = new Product(idProdotto, nome, descrizione, Double.parseDouble(prezzo), venditore, Integer.parseInt(disponibilita));
            Product tmp = DBConnection.getInstance().getProductByID(idProdotto);
            if(tmp == null){
                if (DBConnection.getInstance().addProductToDB(p)){
                    clearCondition = true;
                    SceneHandler.getInstance().showAlert("Successo", Message.add_product_success, 1);
                } else{
                    SceneHandler.getInstance().showAlert("Attenzione", Message.add_product_image_error, 0);
                    return false;
                }
            }else{
                SceneHandler.getInstance().showAlert("Attenzione", Message.add_product_error, 0);
            }
        } else if (removeCondition) {
            p = DBConnection.getInstance().getProductByID(idProdotto);
            if (p != null){
                DBConnection.getInstance().removeProductFromDB(p);
                clearCondition = true;
                SceneHandler.getInstance().showAlert("Successo", Message.remove_product_success, 1);
            }else{
                SceneHandler.getInstance().showAlert("Attenzione", Message.remove_product_error, 0);
            }
        }
        if (clearCondition){
            return true;
        }
        return false;
    }

}
