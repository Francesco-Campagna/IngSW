package com.example.carparts.Database;

import com.example.carparts.MainApplication;
import com.example.carparts.Message;
import com.example.carparts.Model.Product;
import com.example.carparts.View.SceneHandler;
import javafx.scene.image.Image;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DBConnection {
    private ArrayList<Product> products = new ArrayList<>();
    public int nProdotti;
    private Connection con = null;

    private static DBConnection instance = null;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public void createConnection() throws SQLException {
        String url = "jdbc:sqlite:database.db";
        con = DriverManager.getConnection(url);
        if (con != null && !con.isClosed())
            System.out.println("Connected!");
    }


    public void closeConnection() throws SQLException {
        if (con != null)
            con.close();
        con = null;
    }

    public boolean checkLogin(String email, String password) {
        if (email.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

    public void createHomePageProduct() throws SQLException {
        products.clear();
        if (this.con != null && !this.con.isClosed()) {
            String query = "select * from prodotti;";
            PreparedStatement stmt = this.con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("nome"), rs.getString("descrizione"), rs.getInt("prezzo"), rs.getString("venditore"), rs.getInt("disponibilita"));
                products.add(p);
            }
            stmt.close();
            getNumOfProductIntoDB();
        }
    }

    public ArrayList getProduct() {
        return products;
    }

    public Product getProductByID(String id) {
        for (Product product : products) {
            if (product.id().equals(id)) {
                return product;
            }
        }
        return null;
    }


    public boolean addProductToDB(Product p) throws SQLException {
        if (products.size() < 15) {
            if (!checkImage(p.id())) {
                return false;
            }
            if (con == null || con.isClosed())
                return false;
            PreparedStatement stmt = con.prepareStatement("INSERT INTO prodotti VALUES(?, ?, ?, ?, ?, ?);");
            stmt.setString(1, p.id());
            stmt.setString(2, p.name());
            stmt.setDouble(3, p.price());
            stmt.setString(4, p.seller());
            stmt.setString(5, p.description());
            stmt.setInt(6, p.disponibility());
            stmt.execute();
            stmt.close();
        } else {
            SceneHandler.getInstance().showAlert("Attenzione", Message.add_product_error, 1);
            return false;
        }
        return true;
    }


    public void getNumOfProductIntoDB() throws SQLException {
        int numeroRighe = 0;
        if (con == null || con.isClosed())
            return;
        String query = "SELECT COUNT(*) as righe FROM prodotti";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            numeroRighe = resultSet.getInt("righe");
        }
        nProdotti = numeroRighe;

    }

    public boolean removeProductFromDB(Product p) throws SQLException {
        if (con == null || con.isClosed())
            return false;
        String query = "DELETE FROM prodotti WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, p.id());
        statement.execute();
        statement.close();
        return true;
    }

    public boolean checkImage(String id) {
        try {
            Image image = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("immagini/" + id + ".jpg")));
            if (image != null) {
                return true;
            }
        }catch (Exception e){
            SceneHandler.getInstance().showAlert("Attenzione", Message.add_product_image_error, 0);
        }
        return false;
    }



}
