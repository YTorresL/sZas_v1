package com.init.szas_v1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MatriculaController {
    @FXML
    private TextField estudiante;
    @FXML
    private ComboBox<String> curso;
    @FXML
    private Button agregar;
    @FXML
    private Label mensaje;
    DBconn conexion = null;
    Statement declaracion = null;

    @FXML
    public void initialize() {
        try {
            conexion = new DBconn();
            declaracion = conexion.conn.createStatement();

            // Consulta SQL para obtener los cursos
            String consultaCursos = "SELECT Id_Curso, Nombre_Curso FROM cursos";
            ResultSet resultadoCurso = declaracion.executeQuery(consultaCursos);

            // Agrega los cursos al ComboBox mostrando solo el nombre
            while (resultadoCurso.next()) {
                curso.getItems().add(resultadoCurso.getString("Nombre_Curso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agrMatricula() {
        try {
            if (!estudiante.getText().isEmpty() && curso.getValue() != null) {
                // Busca el ID del curso seleccionado
                String consultaIdCurso = "SELECT Id_Curso FROM cursos WHERE Nombre_Curso = '" + curso.getValue() + "'";
                ResultSet resultadoIdCurso = declaracion.executeQuery(consultaIdCurso);
                String idCurso = null;
                if (resultadoIdCurso.next()) {
                    idCurso = resultadoIdCurso.getString("Id_Curso");
                }

                if (idCurso != null) {
                    // Inserta la matrícula en la base de datos
                    String consulta = "INSERT INTO matricula (Id_Estudiante, Id_Curso) VALUES ('" + estudiante.getText() + "','" + idCurso + "')";
                    int filasAfectadas = declaracion.executeUpdate(consulta);
                    if (filasAfectadas > 0) {
                        System.out.println("Matrícula agregada");
                        agregar.getScene().getWindow().hide();
                    } else {
                        mensaje.setText("Error al agregar la matrícula");
                    }
                } else {
                    mensaje.setText("No se encontró el ID del curso seleccionado");
                }
            } else {
                mensaje.setText("Por favor ingrese el ID del estudiante y seleccione un curso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje.setText("Error en la consulta SQL");
        }
    }
}
