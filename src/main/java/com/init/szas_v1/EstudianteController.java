package com.init.szas_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EstudianteController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField telefono;
    @FXML
    private TextField correo;
    @FXML
    private TextField direccion;
    @FXML
    private ComboBox<String> genero;
    @FXML
    private DatePicker fecha_nacimiento;
    @FXML
    private TextField lugar_nacimiento;
    @FXML
    private TextField cedula;
    @FXML
    private TextField id_estudiante;
    @FXML
    private Button agregar;
    @FXML
    private Label mensaje;
    private DBconn conexion = null;
    private Statement declaracion;

    // Método para inicializar el controlador
    @FXML
    public void initialize() throws SQLException {
        conexion = new DBconn();
        declaracion = conexion.conn.createStatement();
        String query = "SELECT * FROM estudiantes";
        ResultSet result = declaracion.executeQuery(query);
        // Agrega opciones al ComboBox genero
        ObservableList<String> opcionesInstructor = FXCollections.observableArrayList(
                "Femenino", "Masculino"
        );
        genero.setItems(opcionesInstructor);
    }

    public void agrStudiante() {
        try {
            if (nombre.getText().length() > 1 && apellido.getText().length() > 1 && cedula.getText().length() > 1 && telefono.getText().length() > 1 && direccion.getText().length() > 1 && correo.getText().length() > 1 && lugar_nacimiento.getText().length() > 1) {
                String consulta = "INSERT INTO estudiantes (`Nombre`,`Apellido`,`Cedula`,`Telefono`,`Direccion`,`Email`,`FNacimiento`,`Lugar_Nacimiento`, `Sexo`) VALUES ('" + nombre.getText() + "','" + apellido.getText() + "','" + cedula.getText() + "','" + telefono.getText() + "','" + direccion.getText() + "','" + correo.getText() + "','" + fecha_nacimiento.getValue() + "','" + lugar_nacimiento.getText() + "','" + genero.getValue() + "')";
                int filasAfectadas = declaracion.executeUpdate(consulta);
                if (filasAfectadas > 0) {
                    System.out.println("Estudiante agregado");
                    agregar.getScene().getWindow().hide();
                } else {
                    mensaje.setText("Error al agregar estudiante");
                    System.out.println("Error al agregar estudiante");
                }
            } else {
                mensaje.setText("Error, complete los campos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void editEstudiante() {
        try {
            // Verifica si al menos uno de los campos editados no está vacío
            if ((!nombre.getText().isEmpty() || !apellido.getText().isEmpty() || !cedula.getText().isEmpty() || !telefono.getText().isEmpty() || !direccion.getText().isEmpty() || !correo.getText().isEmpty() || !lugar_nacimiento.getText().isEmpty())) {
                // Construye la consulta SQL para actualizar el estudiante
                StringBuilder consulta = new StringBuilder("UPDATE estudiantes SET ");

                // Verifica y agrega el nombre si está editado y no está vacío
                if (!nombre.getText().isEmpty()) {
                    consulta.append("Nombre = '").append(nombre.getText()).append("', ");
                }

                // Verifica y agrega el apellido si está editado y no está vacío
                if (!apellido.getText().isEmpty()) {
                    consulta.append("Apellido = '").append(apellido.getText()).append("', ");
                }

                // Verifica y agrega la cédula si está editada y no está vacía
                if (!cedula.getText().isEmpty()) {
                    consulta.append("Cedula = '").append(cedula.getText()).append("', ");
                }

                // Verifica y agrega el teléfono si está editado y no está vacío
                if (!telefono.getText().isEmpty()) {
                    consulta.append("Telefono = '").append(telefono.getText()).append("', ");
                }

                // Verifica y agrega la dirección si está editada y no está vacía
                if (!direccion.getText().isEmpty()) {
                    consulta.append("Direccion = '").append(direccion.getText()).append("', ");
                }

                // Verifica y agrega el correo si está editado y no está vacío
                if (!correo.getText().isEmpty()) {
                    consulta.append("Email = '").append(correo.getText()).append("', ");
                }

                // Verifica y agrega la fecha de nacimiento si está editada y no está vacía
                if (fecha_nacimiento.getValue() != null) {
                    consulta.append("FNacimiento = '").append(fecha_nacimiento.getValue()).append("', ");
                }

                // Verifica y agrega el lugar de nacimiento si está editado y no está vacío
                if (!lugar_nacimiento.getText().isEmpty()) {
                    consulta.append("Lugar_Nacimiento = '").append(lugar_nacimiento.getText()).append("', ");
                }

                // Verifica y agrega el género si está editado y no está vacío
                if (genero.getValue() != null) {
                    consulta.append("Sexo = '").append(genero.getValue()).append("', ");
                }

                // Elimina la última coma de la consulta
                consulta.deleteCharAt(consulta.length() - 2);

                // Agrega la condición WHERE para especificar el estudiante a editar
                consulta.append(" WHERE Id_Estudiante = ").append(id_estudiante.getText());

                // Ejecuta la consulta SQL para editar el estudiante
                int filasAfectadas = declaracion.executeUpdate(consulta.toString());
                if (filasAfectadas > 0) {
                    System.out.println("Estudiante editado");
                    agregar.getScene().getWindow().hide();
                } else {
                    mensaje.setText("Error al editar estudiante");
                    System.out.println("Error al editar estudiante");
                }
            } else {
                mensaje.setText("Error, complete al menos un campo para editar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
