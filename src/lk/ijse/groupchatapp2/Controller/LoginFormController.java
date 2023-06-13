package lk.ijse.groupchatapp2.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtname;
    String name ="";

    public void btnChat(ActionEvent actionEvent) {
      name=  txtname.getText();
      txtname.clear();
      System.out.println(name);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/groupchatapp2/View/ClientView.fxml"));
        try {
            Parent root = fxmlLoader.load();
            ClientController clientController = fxmlLoader.getController();
            clientController.lblName.setText(name);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(name+" 's Interface");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
