package lk.ijse.groupchatapp2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import lk.ijse.groupchatapp2.Controller.ClientController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    ServerSocket serverSocket;
    List<ClientHandler> clientHandlers = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        serverSocket = new ServerSocket(3000);
        System.out.println("Server Waiting");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Accepted");

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandlers.add(clientHandler);
            clientHandler.start();
        }
    }

    private void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(message);
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        public String SenderName="";
        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = dataInputStream.readUTF();
                    System.out.println(message);

//                    String formattedMessage = SenderName + ": " + message; // Add the sender's name to the message

                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            try {
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
