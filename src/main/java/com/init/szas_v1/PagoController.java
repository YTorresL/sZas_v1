package com.init.szas_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PagoController {
    @FXML
    private TextField referencia;
    @FXML
    private ComboBox<String> metodo;
    @FXML
    private TextField estudiante;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField monto;
    @FXML
    private Button agregar;
    @FXML
    private Label mensaje;
    private DBconn conexion = null;
    private Statement declaracion;

    public void initialize() throws SQLException {
        conexion = new DBconn();
        declaracion = conexion.conn.createStatement();
        // Agrega opciones al ComboBox genero
        ObservableList<String> opcionesInstructor = FXCollections.observableArrayList(
                "Efectivo", "Transferencia", "Tarjeta"
        );
        metodo.setItems(opcionesInstructor);
    }

    public void agrPago() {
        try {
            if (referencia.getText().length() > 1 && metodo.getValue() != null && !estudiante.getText().isEmpty() && descripcion.getText().length() > 1 && monto.getText().length() > 1) {
                String consutaIDorCedula;
                String resultID = "";
                if (estudiante.getText().length() > 6) {
                    consutaIDorCedula = "SELECT * FROM estudiantes WHERE `Cedula` = '" + estudiante.getText() + "'";
                    ResultSet result = declaracion.executeQuery(consutaIDorCedula);
                    if (result.next()) {
                        resultID = result.getString("Id_Estudiante");
                    } else {
                        mensaje.setText("No se encontró ningún estudiante con esa cédula");
                        return;
                    }
                } else {
                    consutaIDorCedula = "SELECT * FROM estudiantes WHERE `Id_Estudiante` = '" + estudiante.getText() + "'";
                    ResultSet result = declaracion.executeQuery(consutaIDorCedula);
                    if (result.next()) {
                        resultID = result.getString("Id_Estudiante");
                    } else {
                        mensaje.setText("No se encontró ningún estudiante con ese ID");
                        return;
                    }
                }
                String consulta = "INSERT INTO pagos (`Referencia_Bancaria`,`Metodo_Pago`,`Id_Estudiante`,`descripcion`,`Monto`) VALUES ('" + referencia.getText() + "','" + metodo.getValue() + "','" + resultID + "','" + descripcion.getText() + "','" + monto.getText() + "')";
                int filasAfectadas = declaracion.executeUpdate(consulta);
                if (filasAfectadas > 0) {
                    mensaje.getScene().getWindow().hide();
                } else {
                    mensaje.setText("Error al agregar el pago");
                }
            } else {
                mensaje.setText("Por favor, llene todos los campos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
