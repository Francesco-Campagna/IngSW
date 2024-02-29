package com.example.carparts.Controller;

import com.example.carparts.Database.Authentication;
import com.example.carparts.Database.DBConnection;
import com.example.carparts.Handler.ManageProductHandler;
import com.example.carparts.Message;
import com.example.carparts.Model.Cart;
import com.example.carparts.Model.User;
import com.example.carparts.Proxy.ProductManage;
import com.example.carparts.Proxy.ProductManageProxy;
import com.example.carparts.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.concurrent.*;

public class LoginController {

    @FXML
    private Pane boxLogin;

    @FXML
    private ImageView carDocLogoIcon, returnHomeImage;

    @FXML
    private TextField emailField;

    @FXML
    private Text forgotPassword, registerNow, wrongLoginText;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginUpperPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    void enterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            ActionEvent ignored = new ActionEvent();
            login(ignored);
        }
    }

    @FXML
    void forgotPasswordAction(MouseEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        try {
            boolean value = DBConnection.getInstance().checkLogin(emailField.getText(), passwordField.getText());
            User user = new User(emailField.getText(), "Nome", "Cognome", "0", passwordField.getText(), Cart.getInstance());
            Authentication.getInstance().login(user);
            SceneHandler.getInstance().setHomeScene();
            if (value){
                ProductManageProxy.setAdmin(true);
                SceneHandler.getInstance().closeAdminStage();
                SceneHandler.getInstance().setAdminScene();
            }
        } catch (Exception e){
            wrongLoginText.setVisible(true);
            SceneHandler.getInstance().showAlert("Error", Message.returnHome_error, 0);
        }
        emailField.clear();
        passwordField.clear();
    }

    @FXML
    void registerNowAction(MouseEvent event) throws Exception {
        SceneHandler.getInstance().setRegistrationScene();
    }

    @FXML
    void resetWrongText(MouseEvent event) {
        wrongLoginText.setVisible(false);
    }

    @FXML
    void returnHomeAction(MouseEvent event) {
        SceneHandler.getInstance().setHomeScene();
    }

}
