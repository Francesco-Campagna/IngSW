package com.example.carparts.Controller;

import com.example.carparts.Database.Authentication;
import com.example.carparts.Database.DBConnection;
import com.example.carparts.MainApplication;
import com.example.carparts.Message;
import com.example.carparts.Model.Cart;
import com.example.carparts.Model.Product;
import com.example.carparts.Model.User;
import com.example.carparts.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class BoxPageController {

    @FXML
    private Text priceText1, priceText2, priceText3, priceText4, priceText5, priceText6, priceText7, priceText8, priceText9, priceText10, priceText11, priceText12, priceText13, priceText14, priceText15;

    @FXML
    private ImageView productImage1, productImage2, productImage3, productImage4, productImage5, productImage6, productImage7, productImage8, productImage9, productImage10, productImage11, productImage12, productImage13, productImage14, productImage15;

    @FXML
    private ScrollPane scrollPaneBoxPage;

    @FXML
    private Text sellerText1, sellerText2, sellerText3, sellerText4, sellerText5, sellerText6, sellerText7, sellerText8, sellerText9, sellerText10, sellerText11, sellerText12, sellerText13, sellerText14, sellerText15;

    @FXML
    private Text titleText1, titleText2, titleText3, titleText4, titleText5, titleText6, titleText7, titleText8, titleText9, titleText10, titleText11, titleText12, titleText13, titleText14, titleText15;

    @FXML
    private VBox totalVboxBoxPage;

    @FXML
    private VBox vBoxProduct1, vBoxProduct2, vBoxProduct3, vBoxProduct4, vBoxProduct5, vBoxProduct6, vBoxProduct7, vBoxProduct8, vBoxProduct9, vBoxProduct10, vBoxProduct11, vBoxProduct12, vBoxProduct13, vBoxProduct14, vBoxProduct15;

    ImageView[] productImages;
    Text[] titleTextArray;
    Text[] priceArray;
    Text[] sellerTextArray;
    VBox[] vBoxes;

    void arrayInitialize(){
        productImages = new ImageView[]{productImage1, productImage2, productImage3, productImage4, productImage5, productImage6, productImage7, productImage8, productImage9, productImage10, productImage11, productImage12, productImage13, productImage14, productImage15};
        titleTextArray = new Text[]{titleText1, titleText2, titleText3, titleText4, titleText5, titleText6, titleText7, titleText8, titleText9, titleText10, titleText11, titleText12, titleText13, titleText14, titleText15};
        priceArray = new Text[]{priceText1, priceText2, priceText3, priceText4, priceText5, priceText6, priceText7, priceText8, priceText9, priceText10, priceText11, priceText12, priceText13, priceText14, priceText15};
        sellerTextArray = new Text[]{sellerText1,sellerText2,sellerText3, sellerText4,sellerText5,sellerText6,sellerText7,sellerText8,sellerText9,sellerText10,sellerText11,sellerText12,sellerText13,sellerText14,sellerText15};
        vBoxes = new VBox[]{vBoxProduct1, vBoxProduct2, vBoxProduct3, vBoxProduct4, vBoxProduct5, vBoxProduct6, vBoxProduct7, vBoxProduct8, vBoxProduct9, vBoxProduct10, vBoxProduct11, vBoxProduct12, vBoxProduct13, vBoxProduct14, vBoxProduct15, totalVboxBoxPage};
    }


    private void addTocart(String id){
        User user = Authentication.getInstance().getUser();
        if (user != null) {
            Product p = DBConnection.getInstance().getProductByID(id);
            Cart.getInstance().addProduct(p);
            SceneHandler.getInstance().setHomeScene();
        }else{
            SceneHandler.getInstance().showAlert("Attenzione", Message.login_to_get_product, 1);
        }

    }



    void loadHomePageImage() throws SQLException {
        DBConnection.getInstance().createHomePageProduct();
        ArrayList<Product> products = DBConnection.getInstance().getProduct();
        int nProdotti = DBConnection.getInstance().nProdotti;
        for (int i = 0; i < nProdotti; i++) {
            String url = "immagini/"+ products.get(i).id()+".jpg";
            Image image = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream(url)));
            productImages[i].setImage(image);
            String id = products.get(i).id();
            titleTextArray[i].setText(products.get(i).name() + " " + products.get(i).description());
            priceArray[i].setText(products.get(i).price() + "€");
            sellerTextArray[i].setText("Venditore: " + products.get(i).seller() + ", " + products.get(i).disponibility() + " disponibili");

            vBoxes[i].setOnMouseClicked(event -> addTocart(id));
        }
    }

    void removeExcessBox(int size){
        for (int i = size; i < 15; i++){
            vBoxes[i].setVisible(false);
        }
    }

    @FXML
    void initialize() throws SQLException {
        arrayInitialize();
        loadHomePageImage();
        removeExcessBox(DBConnection.getInstance().nProdotti);
    }

}
