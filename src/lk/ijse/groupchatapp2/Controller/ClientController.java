package lk.ijse.groupchatapp2.Controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientController {
    public JFXTextArea txtArea;
    public JFXTextField txtmsg;
    public  Label lblName;

    String message = "";
    String formattedMessage;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void initialize() {
        try {
            socket = new Socket("localhost", 3000);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        message = dataInputStream.readUTF();
                        if (!message.equals(formattedMessage)) {
                            Platform.runLater(() -> {
                                txtArea.appendText("\n " + message);
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void btnSend(ActionEvent actionEvent) throws IOException {
        message = txtmsg.getText().trim();
        txtmsg.clear();

        String senderName = lblName.getText(); // Get the sender's name from the label
        formattedMessage = senderName + ": " + message; // Add the sender's name to the message
        txtArea.appendText("\n(You): " + message); // Display the sender's name in the client's chat window

        dataOutputStream.writeUTF(formattedMessage);
        dataOutputStream.flush();
    }

    public void btnattchment(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Oops,You caught us! ");
        alert.setContentText("This feature is still under development!,will be Ready Soon!H");

        alert.showAndWait();
    }
}

