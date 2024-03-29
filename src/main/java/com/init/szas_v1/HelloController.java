package com.init.szas_v1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button homeButton;

    @FXML
    protected void onHomeClick() throws IOException {
        HelloApplication.home();
        homeButton.getScene().getWindow().hide();

    }
}
