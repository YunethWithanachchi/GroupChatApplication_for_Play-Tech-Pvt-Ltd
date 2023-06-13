package lk.ijse.groupchatapp2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ClientInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = getClass().getClassLoader().getResource("lk/ijse/groupchatapp2/View/LoginForm.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Stage stage=new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
