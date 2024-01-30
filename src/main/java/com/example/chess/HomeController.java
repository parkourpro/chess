package com.example.chess;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    @FXML
    private Label characternameLabel;
    @FXML
    private Label eloLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button searchplayerButton;

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
    public void searchPlayer(){
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Khởi tạo
    }
}
