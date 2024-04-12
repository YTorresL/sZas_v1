package com.init.szas_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.sql.Statement;

public class InstructorController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField cedula;
    @FXML
    private TextField telefono;
    @FXML
    private TextField correo;
    @FXML
    private TextField direccion;
    @FXML
    private TextField especialidad;
    @FXML
    private ComboBox<String> genero;
    @FXML
    private Label mensaje;
    @FXML
    private Button agregar;

    DBconn conexion = null;
    Statement declaracion;

    @FXML
    public void initialize() throws SQLException {
        conexion = new DBconn();
        declaracion = conexion.conn.createStatement();
        ObservableList<String> opcionesInstructor = FXCollections.observableArrayList(
                "Femenino", "Masculino"
        );
        genero.setItems(opcionesInstructor);
    }

public void agrInstructor() {
        try {
            if (nombre.getText().length() > 1 && apellido.getText().length() > 1 && cedula.getText().length() > 1 && telefono.getText().length() > 1 && direccion.getText().length() > 1 && correo.getText().length() > 1 && especialidad.getText().length() > 1) {
                String consulta = "INSERT INTO instructores (`Nombre`,`Apellido`,`Cedula`,`Telefono`,`Direccion`,`Email`,`Especializacion`, `Sexo`) VALUES ('" + nombre.getText() + "','" + apellido.getText() + "','" + cedula.getText() + "','" + telefono.getText() + "','" + direccion.getText() + "','" + correo.getText() + "','" + especialidad.getText() + "','" + genero.getValue() + "')";
                int filasAfectadas = declaracion.executeUpdate(consulta);
                if (filasAfectadas > 0) {
                    System.out.println("Instructor agregado");
                    agregar.getScene().getWindow().hide();
                } else {
                    mensaje.setText("Error al agregar el instructor");
                }
            } else {
                mensaje.setText("Por favor llene todos los campos");
            }
        } catch (SQLException e) {
            mensaje.setText("Error al agregar el instructor");
        }
    }
}
