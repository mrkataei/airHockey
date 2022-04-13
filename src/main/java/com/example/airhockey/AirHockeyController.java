package com.example.airhockey;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AirHockeyController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}