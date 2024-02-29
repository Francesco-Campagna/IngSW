package com.example.carparts.Controller;

import com.example.carparts.Database.Authentication;
import com.example.carparts.Facade.FacadeHandler;
import com.example.carparts.Handler.CouponHandler;
import com.example.carparts.MainApplication;
import com.example.carparts.Message;
import com.example.carparts.Model.Cart;
import com.example.carparts.Model.Product;
import com.example.carparts.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.example.carparts.View.SceneHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.sleep;

public class CartController {

    @FXML
    private TextField amountField1, amountField2, amountField3, amountField4, amountField5, amountField6;

    @FXML
    private ImageView arrowContinuePurchase;

    @FXML
    private VBox cartBoxPane, checkOutBox, vBoxCartPage;

    @FXML
    private ImageView cartImage1, cartImage2, cartImage3, cartImage4, cartImage5, cartImage6;

    @FXML
    private Text cartItemPriceText1, cartItemPriceText2, cartItemPriceText3, cartItemPriceText4, cartItemPriceText5, cartItemPriceText6;

    @FXML
    private Text continuePurchaseText;

    @FXML
    private HBox hBox1, hBox2, hBox3, hBox4, hBox5, hBox6;

    @FXML
    private Button lessButton1, lessButton2, lessButton3, lessButton4, lessButton5, lessButton6;

    @FXML
    private Button plusButton1, plusButton2, plusButton3, plusButton4, plusButton5, plusButton6;

    @FXML
    private Button purchaseButton;

    @FXML
    private Button removeItemButton1, removeItemButton2, removeItemButton3, removeItemButton4, removeItemButton5, removeItemButton6;

    @FXML
    private ScrollPane scrollPaneCart;

    @FXML
    private Text titleCartProd1, titleCartProd2, titleCartProd3, titleCartProd4, titleCartProd5, titleCartProd6;

    @FXML
    private Text totalOrderText;

    @FXML
    private Text totalPrice1, totalPrice2, totalPrice3, yourOrderText, saldoDisponibileText;

    ImageView[] imageViews;
    Text[] titleTextProd, priceText;
    Button[] removeButtons, lessButtons, plusButtons;
    TextField[] amountTextFields;
    HBox[] hBoxes;

    @FXML
    void purchaseAction(ActionEvent event){
        double balance = FacadeHandler.getInstance().getBalance();
        if(Double.parseDouble(totalPrice1.getText().replace("€", "")) <= balance){
            Cart.getInstance().clearCart();
            FacadeHandler.getInstance().updateBalance(Double.parseDouble(totalPrice1.getText().replace("€", "")), "sub");
            SceneHandler.getInstance().showAlert("Successo", Message.order_success, 1);
        }else{
            SceneHandler.getInstance().showAlert("Errore", Message.insufficient_balance_error, 0);
        }
        initialize();
        updateBalance();
    }

    @FXML
    void returnHomeAction(MouseEvent event) {
        try {
            SceneHandler.getInstance().setHomeScene();
        } catch (Exception e){
            SceneHandler.getInstance().showAlert("Error" , Message.returnHome_error, 0);
        }
    }

    @FXML
    void couponButtonAction(ActionEvent event) throws IOException {
        User user = Authentication.getInstance().getUser();
        if (user != null) {
            SceneHandler.getInstance().setCouponScene();
        }else{
            SceneHandler.getInstance().showAlert("Attenzione", Message.login_to_get_coupon, 1);
        }
    }


    void initializeObject(){
        imageViews = new ImageView[]{cartImage1, cartImage2, cartImage3, cartImage4, cartImage5, cartImage6};
        titleTextProd = new Text[]{titleCartProd1, titleCartProd2, titleCartProd3, titleCartProd4, titleCartProd5, titleCartProd6};
        priceText = new Text[]{cartItemPriceText1, cartItemPriceText2, cartItemPriceText3, cartItemPriceText4,cartItemPriceText5, cartItemPriceText6};
        removeButtons = new Button[]{removeItemButton1, removeItemButton2, removeItemButton3, removeItemButton4, removeItemButton5, removeItemButton6};
        lessButtons = new Button[]{lessButton1, lessButton2, lessButton3, lessButton4, lessButton5, lessButton6};
        plusButtons = new Button[]{plusButton1, plusButton2, plusButton3, plusButton4, plusButton5, plusButton6};
        amountTextFields = new TextField[]{amountField1, amountField2, amountField3, amountField4, amountField5, amountField6};
        hBoxes = new HBox[]{hBox1, hBox2, hBox3, hBox4, hBox5, hBox6};
    }

    void lessButtonAction(int i, Product p) {
        int amount = Integer.parseInt(amountTextFields[i].getText());
        if (amount > 1) {
            amount--;
            amountTextFields[i].setText(String.valueOf(amount));
            Cart.getInstance().removeOneProduct(p);
        }
        initializeCart();
    }
    void plusButtonAction(int i, Product p) {
        int amount = Integer.parseInt(amountTextFields[i].getText());
        amount++;
        amountTextFields[i].setText(String.valueOf(amount));
        Cart.getInstance().addProduct(p);
        initializeCart();
    }

    void removeItem(Product p) {
        for (int i = 0; i < 6; i++){
            hBoxes[i].setVisible(false);
        }
        Cart.getInstance().removeAllProduct(p);
        initializeCart();
    }

    void initializeCart(){
        Set<Product> cart = Cart.getInstance().getSingleProductCart();
        int i = 0;
        for (Product p : cart) {
            String url = "immagini/" + p.id() + ".jpg";
            Image image = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream(url)));
            imageViews[i].setImage(image);
            titleTextProd[i].setText(p.name() + " " + p.description());
            double price = p.price();
            priceText[i].setText(price + "€");
            amountTextFields[i].setText(Cart.getInstance().getProductQuantity(p));
            hBoxes[i].setVisible(true);

            int tmp = i;
            lessButtons[i].setOnMouseClicked(mouseEvent -> {
                try {
                    lessButtonAction(tmp, p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            plusButtons[i].setOnMouseClicked(mouseEvent -> {
                try {
                    plusButtonAction(tmp, p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            removeButtons[i].setOnMouseClicked(mouseEvent -> {
                try {
                    removeItem(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            i++;
        }
        updateTotalPrice();
    }

    void updateTotalPrice() {
        double totalCartPrice = Cart.getInstance().getTotalCart();
        String value = totalCartPrice+"€";
        totalPrice1.setText(value);
        totalPrice2.setText(value);
        totalPrice3.setText(value);
    }

    void updateBalance(){
        double balance = FacadeHandler.getInstance().getBalance();
        saldoDisponibileText.setText(String.valueOf(balance));
    }

    @FXML
    void initialize(){
        initializeObject();
        initializeCart();
        updateBalance();
    }

}
