package com.example.carparts.Controller;

import com.example.carparts.Database.DBConnection;
import com.example.carparts.Facade.FacadeHandler;
import com.example.carparts.Handler.ManageProductHandler;
import com.example.carparts.Message;
import com.example.carparts.Model.Product;
import com.example.carparts.View.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class ManageProductController {

    @FXML
    private AnchorPane anchorPaneManageProd;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Text descrizioneText, nomeText, prezzoText, venditoreText, idProdottoText, gestioneProdottiText, disponibilitaText;

    @FXML
    private TextField disponibilitaArea, descrizioneArea, idProdottoArea, nomeArea, prezzoArea, venditoreArea;

    @FXML
    private Button inviaButton;

    @FXML
    void checkBoxAction(ActionEvent event) {
    }

    @FXML
    void enterPressed(KeyEvent event) throws SQLException, IOException {
        if (event.getCode() == KeyCode.ENTER){
            ActionEvent ignored = new ActionEvent();
            inviaButtonAction(ignored);
        }
    }

    @FXML
    void inviaButtonAction(ActionEvent event) throws SQLException, IOException {
        FacadeHandler.getInstance().manageProduct(checkBox.isSelected(), idProdottoArea.getText(), nomeArea.getText(), descrizioneArea.getText(), prezzoArea.getText(), venditoreArea.getText(), disponibilitaArea.getText());
        idProdottoArea.clear();
        nomeArea.clear();
        descrizioneArea.clear();
        prezzoArea.clear();
        venditoreArea.clear();
        disponibilitaArea.clear();
        checkBox.setSelected(false);
    }

}
