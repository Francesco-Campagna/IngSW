package com.example.carparts.View;

import com.example.carparts.MainApplication;
import com.example.carparts.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneHandler {

    private final static String RESOURCE_PATH = "/com/example/carparts/";
    private final static String FXML_PATH = RESOURCE_PATH + "fxmls/";
    private final static String CSS_PATH = RESOURCE_PATH + "css/";
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Scene scene;
    private Stage stage;
    private Stage adminStage;
    private Stage couponStage;


    private static SceneHandler instance = null;
    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception {
        if(this.stage != null)
            return;
        this.stage = primaryStage;
        this.stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("Loghi/icon/piston.png"))));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "home_page.fxml"));
        this.scene = new Scene(loader.load(), null);
        this.stage.setScene(scene);
        this.stage.setTitle("CarParts");
        this.stage.setMaximized(false);
        this.stage.setMinWidth(1370);
        this.stage.setMinHeight(700);
        setCSSForScene(scene);
        this.stage.show();
        setAdminScene();
    }

    public void loadFXML(String FXMLPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + FXMLPath));
        if (scene == null) scene = new Scene(loader.load());
        else scene = new Scene(loader.load(), scene.getWidth(), scene.getHeight());
        setCSSForScene(scene);
        this.stage.setScene(scene);
        stage.show();
    }

    public static SceneHandler getInstance() {
        if(instance == null)
            instance = new SceneHandler();
        return instance;
    }

    private List<String> loadCSS() {
        List<String> resources = new ArrayList<>();
        for (String style : List.of(CSS_PATH + "dark.css", CSS_PATH + "fonts.css", CSS_PATH + "style.css")) {
            String resource = Objects.requireNonNull(SceneHandler.class.getResource(style)).toExternalForm();
            resources.add(resource);
        }
        return resources;
    }

    private void setCSSForScene(Scene scene) {
        Objects.requireNonNull(scene, "Scene cannot be null");
        List<String> resources = loadCSS();
        scene.getStylesheets().clear();
        for(String resource : resources)
            scene.getStylesheets().add(resource);
    }


    public void showAlert(String Head, String message, int type) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("Loghi/icon/piston.png"))));
        ImageView icon = new ImageView();
        if (type == 1){
            icon = new ImageView(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("Loghi/information_alert.png"))));
        } else if (type == 0) {
            icon = new ImageView(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("Loghi/denied_alert.png"))));
        }
        icon.setFitWidth(60);
        icon.setFitHeight(60);
        alert.getDialogPane().setGraphic(icon);
        alert.setTitle(Head);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.show();
    }


    public void setHomeScene() {
        try {
            loadFXML("home_page.fxml");
        } catch (Exception e){
            showAlert("Errore", Message.returnHome_error, 0);
        }
    }

    public void setLoginScene() {
        try {
            loadFXML("login_page.fxml");
        } catch (Exception e){
            showAlert("Errore", Message.login_error, 0);
        }
    }

    public void setCouponScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "buono.fxml"));
        Scene scene = new Scene(loader.load(), 360, 225);
        setCSSForScene(scene);
        couponStage = new Stage();
        couponStage.initModality(Modality.APPLICATION_MODAL);
        couponStage.setScene(scene);
        couponStage.setResizable(false);
        couponStage.show();
    }
    public void closeCouponScene() {
        if (couponStage != null) {
            couponStage.close();
        }
    }

    public void setAdminScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "manage_products.fxml"));
        Scene scene = new Scene(loader.load(), 536, 603);
        setCSSForScene(scene);
        adminStage = new Stage();
        adminStage.setScene(scene);
        adminStage.setResizable(false);
        adminStage.show();
    }

    public void closeAdminStage() {
        if (adminStage != null) {
            adminStage.close();
        }
    }

    public void setRegistrationScene() throws Exception {
        loadFXML("registration_page.fxml");
    }

    public void setRecoveryScene() throws Exception {
        loadFXML("recovery_password.fxml");
    }


}
