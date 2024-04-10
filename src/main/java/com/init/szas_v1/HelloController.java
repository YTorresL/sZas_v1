package com.init.szas_v1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static com.init.szas_v1.HelloApplication.administracion;

public class HelloController {
    @FXML
    private Button homeButton;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField pass;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHomeClick() throws IOException, SQLException {
        boolean Logged = administracion.Login(usuario.getText(), pass.getText());
        if (Logged) {
            // to home
            HelloApplication.home();
            //close login
            homeButton.getScene().getWindow().hide();
        } else {
            welcomeText.setText("Usuario o contraseña incorrectos");
        }
    }
}
