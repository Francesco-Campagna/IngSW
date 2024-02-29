package com.example.carparts.Controller;

import com.example.carparts.Database.Authentication;
import com.example.carparts.Facade.FacadeHandler;
import com.example.carparts.Handler.CouponHandler;
import com.example.carparts.Message;
import com.example.carparts.Model.Buono;
import com.example.carparts.Model.User;
import com.example.carparts.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class CouponController {

    @FXML
    private Button riscattaButton;
    @FXML
    private Text inserisciIlBuonoText;

    @FXML
    private TextField textArea;

    @FXML
    void enterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            ActionEvent ignored = new ActionEvent();
            Riscatta(ignored);
        }
    }


    @FXML
    void Riscatta(ActionEvent event) {
        if (!textArea.getText().isEmpty()) {
            boolean check = Buono.checkBuono(textArea.getText());
            if (check) {
                FacadeHandler.getInstance().updateBalance(Integer.parseInt(textArea.getText())*100, "sum");
                Buono.rimuoviBuono(textArea.getText());
                SceneHandler.getInstance().closeCouponScene();
                SceneHandler.getInstance().showAlert("Complimenti!", Message.add_coupon_success, 1);
            } else {
                SceneHandler.getInstance().showAlert("Attenzione", Message.add_coupon_error, 0);
            }
        } else {
            SceneHandler.getInstance().showAlert("Attenzione", Message.add_coupon_error, 0);
        }
        textArea.clear();
    }

}
