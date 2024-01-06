package com.example.javafxproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginController {
    @FXML
    private Button LoginButton;
    @FXML
    private Button MoveToRegisterButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPassWordField;
    @FXML
    public void login(){
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String userName = usernameTextField.getText();
            String passWord = passwordPassWordField.getText();
            // Sending a login request to the server
            String request = "login,"+userName+","+passWord;
            out.println(request);

            // Receive and print server response
            String response = in.readLine();
            System.out.println("Server response: " + response);
            if(response.equals("success")) {
                loadHomePage();
            } else {
                // xử lý đăng nhập thất bại
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    private void moveToRegister(ActionEvent event) {
        try {
            // Load the FXML file for the registration window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage registerStage = new Stage();
            registerStage.setTitle("Registration");
            registerStage.setScene(new Scene(root));

            // Show the registration window
            registerStage.show();


            // Close the login stage
            Stage loginStage = (Stage) MoveToRegisterButton.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHomePage() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            Stage homeStage = new Stage();
            homeStage.setScene(new Scene(root));
            homeStage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
