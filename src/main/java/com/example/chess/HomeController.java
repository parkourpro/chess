package com.example.chess;

import com.example.chess.chess.ChessMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

// Trong trang home.fxml Controller
public class HomeController implements Initializable {

    private String usernameLogined;
    private String characternameLogined;
    @FXML
    private Label characternameLabel;
    @FXML
    private Label eloLabel;
    @FXML
    private Button logoutButton;
//    @FXML
//    private Button searchplayerButton;

    // Phương thức để nhận thông tin người dùng
    public void initData(String username) {
//        ConnectionManager.connect();
        usernameLogined = username;
        String request = "profile," + username;
        System.out.println(request);

        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        System.out.println("Server profile response: " + response);

        assert response != null;
        String[] parts = response.split(",");
        String charactername = parts[2];
        characternameLogined = charactername;
        String elo = parts[3];
        characternameLabel.setText(charactername);
        eloLabel.setText(elo);
    }

    @FXML
    public void logout() throws IOException {
        String request = "logout," + usernameLogined;
        System.out.println(request);

        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        if (response != null && response.equals("success")) {
            System.out.println("Sign out success");
        } else {
            System.out.println("Login fail");
        }


        // Load the FXML file for the registration window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Create a new stage for the registration window
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(root));
        loginStage.show();

        //get login stage
        Stage homeStage = (Stage) logoutButton.getScene().getWindow();

        // Close login stage
        homeStage.close();
    }

    @FXML
    public void createroom() throws IOException {
        // Create a new room in the database
        String createRoomRequest = "createroom," + usernameLogined;
        String roomResponse = ConnectionManager.sendRequest(createRoomRequest);

        // Process the room creation response from the server
        if (roomResponse != null) {
            // Extract room ID from the response
            String[] parts = roomResponse.split(",");
            int roomId = Integer.parseInt(parts[1]);
            System.out.println("roomId: " + roomId);
            // Update the current room ID for the user
//            String updateRoomRequest = "updateroom," + usernameLogined + "," + roomId;
//            ConnectionManager.sendRequest(updateRoomRequest);

            // Load the FXML file for the room window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
            Parent root = loader.load();

            // Set the room ID in the controller
            RoomController roomController = loader.getController();
            roomController.initData(String.valueOf(roomId), characternameLogined);

            // Create a new stage for the room window
            Stage roomStage = new Stage();
            roomStage.setScene(new Scene(root));
//            roomStage.initStyle(StageStyle.UNDECORATED);
            roomStage.show();
        } else {
            System.out.println("Failed to create room");
        }
    }

    @FXML
    public void searchPlayer() {
        try {
            // Load the FXML file for the registration window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage searchStage = new Stage();
            searchStage.setScene(new Scene(root));
            searchStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void randommatch(){
        System.out.println(111);
//        ChessMain.start();
    }
    @FXML
    public void viewrankingtop(){
        int a = 10;
        String viewrankingtopRequest = "viewrankingtop," + ;
        String roomResponse = ConnectionManager.sendRequest(createRoomRequest);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Khởi tạo
    }
}
