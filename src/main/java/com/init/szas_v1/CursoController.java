package com.init.szas_v1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoController {
    @FXML
    private TextField curso;
    @FXML
    private ComboBox<Instructor> instructor;
    @FXML
    private TextField monto;
    @FXML
    private Button agregar;
    @FXML
    private Label mensaje;
    @FXML
    private TextField idCurso;
    private DBconn conexion = null;
    private Statement declaracion;

    @FXML
    public void initialize() {
        try {
            List<Instructor> instructores = new ArrayList<>();
            conexion = new DBconn();
            declaracion = conexion.conn.createStatement();
            String consulta = "SELECT Id_Instructor, Nombre, Apellido FROM instructores";
            ResultSet resultSet = declaracion.executeQuery(consulta);
            while (resultSet.next()) {
                int id = resultSet.getInt("Id_Instructor");
                String nombre = resultSet.getString("Nombre");
                String apellido = resultSet.getString("Apellido");
                instructores.add(new Instructor(id, nombre, apellido));
            }

            instructor.setItems(FXCollections.observableArrayList(instructores));
            instructor.setConverter(new InstructorStringConverter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agrCurso() {
        try {
            conexion = new DBconn();
            declaracion = conexion.conn.createStatement();
            if (curso.getText().length() > 1 && monto.getText().length() > 1 && instructor.getValue() != null){
                int idInstructor = instructor.getValue().getId();
                String consulta = "INSERT INTO cursos (`Nombre_Curso`,`Id_Instructor`,`costo`) VALUES ('" + curso.getText() + "','" + idInstructor + "','" + monto.getText() + "')";
                int filasAfectadas = declaracion.executeUpdate(consulta);
                if (filasAfectadas > 0) {
                    System.out.println("Curso agregado");
                    agregar.getScene().getWindow().hide();
                } else {
                    mensaje.setText("Error al agregar curso");
                    System.out.println("Error al agregar curso");
                }
            } else {
                mensaje.setText("Error, complete los campos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarCurso() {
        try {
            conexion = new DBconn();
            declaracion = conexion.conn.createStatement();

            // Verifica si se han ingresado valores válidos para la edición
            if (!curso.getText().isEmpty() || !monto.getText().isEmpty() || instructor.getValue() != null) {
                // Prepara la consulta SQL para la actualización
                StringBuilder consulta = new StringBuilder("UPDATE cursos SET ");
                boolean isFirst = true;

                // Verifica y agrega el nombre del curso si está editado
                if (!curso.getText().isEmpty()) {
                    if (!isFirst) {
                        consulta.append(", ");
                    }
                    consulta.append("Nombre_Curso = '").append(curso.getText()).append("'");
                    isFirst = false;
                }

                // Verifica y agrega el ID del instructor si está seleccionado
                if (instructor.getValue() != null) {
                    if (!isFirst) {
                        consulta.append(", ");
                    }
                    consulta.append("Id_Instructor = ").append(instructor.getValue().getId());
                    isFirst = false;
                }

                // Verifica y agrega el costo del curso si está editado
                if (!monto.getText().isEmpty()) {
                    if (!isFirst) {
                        consulta.append(", ");
                    }
                    consulta.append("costo = '").append(monto.getText()).append("'");
                }

                // Agrega la condición WHERE para especificar el curso a editar
                consulta.append(" WHERE Id_Curso = '").append(idCurso.getText()).append("'");

                // Ejecuta la consulta SQL para editar el curso
                int filasAfectadas = declaracion.executeUpdate(consulta.toString());
                if (filasAfectadas > 0) {
                    System.out.println("Curso editado");
                    agregar.getScene().getWindow().hide();
                } else {
                    mensaje.setText("Error al editar curso");
                    System.out.println("Error al editar curso");
                }
            } else {
                mensaje.setText("Error, complete al menos un campo para editar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje.setText("Error al editar curso: " + e.getMessage());
        } finally {
            // Cierra la conexión y la declaración
            try {
                if (declaracion != null) {
                    declaracion.close();
                }
                if (conexion != null) {
                    conexion.conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Definición de la clase Instructor
    private static class Instructor {
        private int id;
        private String nombre;
        private String apellido;

        public Instructor(int id, String nombre, String apellido) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        @Override
        public String toString() {
            return nombre + " " + apellido;
        }
    }

    // Definición del StringConverter
    private static class InstructorStringConverter extends StringConverter<Instructor> {
        @Override
        public String toString(Instructor instructor) {
            return instructor.getNombre() + " " + instructor.getApellido();
        }

        @Override
        public Instructor fromString(String string) {
            return null; // No se necesita para este caso
        }
    }
}
