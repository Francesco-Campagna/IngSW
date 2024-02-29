package com.example.carparts.Controller;

import com.example.carparts.Facade.FacadeHandler;
import com.example.carparts.Handler.ProductHandler;
import com.example.carparts.MainApplication;
import com.example.carparts.Message;
import com.example.carparts.Database.Authentication;
import com.example.carparts.Model.Cart;
import com.example.carparts.Model.User;
import com.example.carparts.Proxy.ProductManageProxy;
import com.example.carparts.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class HomePageController {

    @FXML
    private HBox accessHbox, bottomBoxPage, bottomPane;

    @FXML
    private ImageView carPartsIcon, cartIcon, loginIcon, logoutImage, wishlistIcon;

    @FXML
    private Text generalCondition, textUpgrade, priceText, privacyInformation, loginText;

    @FXML
    private Separator logoutSeparator;
    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane totalAnchorPane, upperPane;

    @FXML
    void LoginAction(MouseEvent event) {
        if(Authentication.getInstance().settedUser()){
            SceneHandler.getInstance().showAlert("Attenzione", Message.already_login_error, 1);
        }else{
            SceneHandler.getInstance().setLoginScene();
        }
    }

    @FXML
    void cartAction(MouseEvent event) {
        try {
            bottomPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource("fxmls/cart.fxml"))));
            priceText.setVisible(false);
        } catch (Exception e){
            SceneHandler.getInstance().showAlert("Error", Message.cart_error, 0);
        }
    }

    @FXML
    void enterPressed(KeyEvent event) {

    }

    @FXML
    void generalConditionAction(MouseEvent event) {

    }

    @FXML
    void getHomeAction(MouseEvent event) {
        try {
            SceneHandler.getInstance().setHomeScene();
        } catch (Exception e){
            SceneHandler.getInstance().showAlert("Error" , Message.returnHome_error, 0);
        }
    }

    @FXML
    void logoutAction(MouseEvent event) {
        FacadeHandler.getInstance().setNullProduct();
        Authentication.getInstance().logout();
        ProductManageProxy.setAdmin(false);
        initialize();
    }

    @FXML
    void privacyInformationAction(MouseEvent event) {

    }

    @FXML
    void searchButtonAction(ActionEvent event) {

    }


    void updateCartPrice() {
        User user = Authentication.getInstance().getUser();
        if (user != null) {
            double totalCartPrice = Cart.getInstance().getTotalCart();
            priceText.setText(totalCartPrice + "€");
        } else {
            priceText.setText("0,00€");
        }
    }

    void login() {
        logoutImage.setVisible(true);
        loginText.setText("Account");
        String email = Authentication.getInstance().getUser().email();
    }

    void logout() {
        logoutImage.setVisible(false);
        loginText.setText("Accesso");
        priceText.setText("0,00€");
        Cart.getInstance().clearCart();
        SceneHandler.getInstance().closeAdminStage();
    }

    void initializeCartText() {
        priceText.setVisible(true);
        updateCartPrice();
    }

    @FXML
    void initialize() {
        try{
            bottomPane.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource("fxmls/box_page.fxml"))));
            } catch (IOException ex) {
            ex.printStackTrace();
            SceneHandler.getInstance().showAlert("Error", Message.box_page_error, 0);
        }
        if (Authentication.getInstance().settedUser()){
            login();
        }
        else{
            logout();
        }
        initializeCartText();
    }

}
