package com.init.szas_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static DBconn conn = null;
    public static Administracion administracion = null;

    public static void home() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        HelloController controller = fxmlLoader.getController();
        controller.table_load_estudiantes(); // Llama al método table_load() del controlador
        controller.table_load_cursos(); // Llama al método table_load() del controlador
        controller.table_load_pagos(); // Llama al método table_load() del controlador
        controller.table_load_instructores(); // Llama al método table_load() del controlador
        controller.table_load_matriculas(); // Llama al método table_load() del controlador
        stage.setTitle("Sistema de finanzas! SZas v1.0 - Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        conn = new DBconn();
        administracion = new Administracion(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Sistema de finanzas! SZas v1.0");
        stage.setScene(scene);
        stage.show();

    }

    public static void AgrEstudiantes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Agregar Estudiante");
        stage.setScene(scene);
        stage.show();
    }

    public static void AgrCursos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Agregar curso");
        stage.setScene(scene);
        stage.show();
    }

    public static void AgrInstructores() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addInstructor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Agregar Instructor");
        stage.setScene(scene);
        stage.show();
    }

    public static void AgrMatriculas() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addEnrollment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Agregar Matricula");
        stage.setScene(scene);
        stage.show();
    }

    public static void AgrPagos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource   ("addPay.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Agregar Pago");
        stage.setScene(scene);
        stage.show();
    }

    public static void EditEstudiantes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Modificar Estudiante");
        stage.setScene(scene);
        stage.show();
    }

    public static void EditCursos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Modificar Curso");
        stage.setScene(scene);
        stage.show();
    }

    public static void EditInstructores() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editInstructor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Modificar Instructor");
        stage.setScene(scene);
        stage.show();
    }

  public static void DesEstudiantes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Deshabilitar Estudiante");
        stage.setScene(scene);
        stage.show();
    }

    public static void DesCursos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Deshabilitar Curso");
        stage.setScene(scene);
        stage.show();
    }

    public static void DesInstructores() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offInstructor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Deshabilitar Instructor");
        stage.setScene(scene);
        stage.show();
    }

    public static void DesMatriculas() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offEnrollment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Deshabilitar Matricula");
        stage.setScene(scene);
        stage.show();
    }

    public static void CanPagos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offPay.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Sistema de finanzas! SZas v1.0 -  Cancelar Pago");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
