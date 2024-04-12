package com.init.szas_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

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

    // Agrega la tabla de estudiantes

    @FXML
    private TableView<Estudiante> tableEstudiantes;
    @FXML
    private TableColumn<Estudiante, String> estIdentificacion;
    @FXML
    private TableColumn<Estudiante, String> estCedula;
    @FXML
    private TableColumn<Estudiante, String> estNombre;
    @FXML
    private TableColumn<Estudiante, String> estApellido;
    @FXML
    private TableColumn<Estudiante, String> estEdad;
    @FXML
    private TableColumn<Estudiante, String> estGenero;
    @FXML
    private TableColumn<Estudiante, String> estTelefono;
    @FXML
    private TableColumn<Estudiante, String> estDireccion;
    @FXML
    private TableColumn<Estudiante, String> estCorreo;
    @FXML
    private TableColumn<Estudiante, String> estNacimiento;
    private ObservableList<Estudiante> observableEstudiantes;

    // Agrega la tabla de cursos
    @FXML
    private TableView<Curso> tableCursos;
    @FXML
    private TableColumn<Curso, String> curIdentificacion;
    @FXML
    private TableColumn<Curso, String> curNombre;
    @FXML
    private TableColumn<Curso, String> curInstructor;
    @FXML
    private TableColumn<Curso, String> curCosto;
    // Agrega la tabla de pagos
    @FXML
    private TableView<Pago> tablePagos;
    @FXML
    private TableColumn<Pago, String> pagIdentificacion;
    @FXML
    private TableColumn<Pago, String> pagEstudiante;
    @FXML
    private TableColumn<Pago, String> pagReferencia;
    @FXML
    private TableColumn<Pago, String> pagFecha;
    @FXML
    private TableColumn<Pago, String> pagMonto;
    @FXML
    private TableColumn<Pago, String> pagDescripcion;
    @FXML
    private TableColumn<Pago, String> pagMetodo;
    // Agrega la tabla de instructores
    @FXML
    private TableView<Instructor> tableInstructores;
    @FXML
    private TableColumn<Instructor, String> insIdentificacion;
    @FXML
    private TableColumn<Instructor, String> insNombre;
    @FXML
    private TableColumn<Instructor, String> insApellido;
    @FXML
    private TableColumn<Instructor, String> insCedula;
    @FXML
    private TableColumn<Instructor, String> insTelefono;
    @FXML
    private TableColumn<Instructor, String> insCorreo;
    @FXML
    private TableColumn<Instructor, String> insDireccion;
    @FXML
    private TableColumn<Instructor, String> insEspecialidad;
    @FXML
    private TableColumn<Instructor, String> insGenero;
    // Agrega la tabla de matriculas
    @FXML
    private TableView<Matricula> tableMatriculas;
    @FXML
    private TableColumn<Matricula, String> matIdentificacion;
    @FXML
    private TableColumn<Matricula, String> matEstudiante;
    @FXML
    private TableColumn<Matricula, String> matCurso;
    @FXML
    private TableColumn<Matricula, String> matFecha;
    @FXML
    private TableColumn<Matricula, String> matEstado;
    @FXML
    private TextField textoBusqueda;

    private DBconn conexion = null;
    private Statement declaracion;

    // Agrega el gráfico de barras para los pagos de los últimos 5 meses
    @FXML
    private BarChart<String, Number> barChartPagos;

    @FXML
    private CategoryAxis xAxisPagos;

    @FXML
    private NumberAxis yAxisPagos;

    private ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();

    public void initialize() {
        System.out.println("Initialize method started");

        // Verifica la inicialización de los componentes FXML
        if (xAxisPagos == null || yAxisPagos == null) {
            System.err.println("Error: Los ejes del gráfico no han sido inicializados correctamente.");
            return;
        }

        // Agrega los datos de pagos al gráfico de barras
        try {
            conexion = new DBconn();
            declaracion = conexion.conn.createStatement();

            // Consulta SQL para obtener los datos de los pagos de los últimos 5 meses
            String consulta = "SELECT MONTH(Fecha_Pago) AS Mes, SUM(Monto) AS Total " +
                    "FROM pagos " +
                    "WHERE Fecha_Pago >= DATE_SUB(CURDATE(), INTERVAL 5 MONTH) " +
                    "GROUP BY Mes";
            ResultSet resultSet = declaracion.executeQuery(consulta);

            System.out.println("Consulta: " + consulta);

            // Mapea los nombres de los meses para el eje X del gráfico
            String[] nombresMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

            // Crea una serie de datos para el gráfico de barras
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            // Itera sobre los resultados de la consulta y agrega los datos a la serie
            while (resultSet.next()) {
                int mes = resultSet.getInt("Mes");
                double totalPagado = resultSet.getDouble("Total");
                series.getData().add(new XYChart.Data<>(nombresMeses[mes - 1], totalPagado));
            }

            // Agrega la serie de datos al gráfico de barras
            data.add(series);
            if (barChartPagos != null) { // Verifica si el gráfico de barras está inicializado
                barChartPagos.setData(data);
            } else {
                System.err.println("Error: El gráfico de barras no ha sido inicializado correctamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    @FXML
    protected void onHomeClick() throws IOException, SQLException {
        boolean Logged = administracion.iniciarSesion(usuario.getText(), pass.getText());
        if (Logged) {
            HelloApplication.home();
            homeButton.getScene().getWindow().hide();
        } else {
            welcomeText.setText("Usuario o contraseña incorrectos");
        }
    }

    @FXML
    protected void agrEstudiantesClick() throws IOException {
        HelloApplication.AgrEstudiantes();
    }
    @FXML
    protected void agrCursosClick() throws IOException {
        HelloApplication.AgrCursos();
    }
    @FXML
    protected void agrPagosClick() throws IOException {
        HelloApplication.AgrPagos();
    }
    @FXML
    protected void agrInstructoresClick() throws IOException {
        HelloApplication.AgrInstructores();
    }
    @FXML
    protected void agrMatriculasClick() throws IOException {
        HelloApplication.AgrMatriculas();
    }
    @FXML
    protected void editEstudiantesClick() throws IOException {
        HelloApplication.EditEstudiantes();
    }
    @FXML
    protected void editCursosClick() throws IOException {
        HelloApplication.EditCursos();
    }
    @FXML
    protected void editInstructoresClick() throws IOException {
        HelloApplication.EditInstructores();
    }
    @FXML
    protected void desEstudianteClick() throws IOException {
        HelloApplication.DesEstudiantes();
    }
    @FXML
    protected void desCursosClick() throws IOException {
        HelloApplication.DesCursos();
    }
    @FXML
    protected void desInstructoresClick() throws IOException {
        HelloApplication.DesInstructores();
    }
    @FXML
    protected void desMatriculasClick() throws IOException {
        HelloApplication.DesMatriculas();
    }
    @FXML
    protected void canPagosClick() throws IOException {
        HelloApplication.CanPagos();
    }
    @FXML
    protected void actEstudiantesClick() {
        table_load_estudiantes();
    }
    @FXML
    protected void actCursosClick() {
        table_load_cursos();
    }
    @FXML
    protected void actPagosClick() {
        table_load_pagos();
    }
    @FXML
    protected void actInstructoresClick() {
        table_load_instructores();
    }
    @FXML
    protected void actMatriculasClick() {
        table_load_matriculas();
    }
    private void initTableEstudiantes() {
        estIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        estCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
        estNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        estApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        estEdad.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        estGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        estTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        estDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        estCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
        estNacimiento.setCellValueFactory(new PropertyValueFactory<>("LugarNacimiento"));
        // Agrega las demás columnas aquí según sea necesario
    }

    public void table_load_estudiantes() {
        try {
            initTableEstudiantes(); // Inicializa la tabla
            observableEstudiantes = FXCollections.observableArrayList();

            conexion = new DBconn();
            declaracion = conexion.conn.createStatement();
            String query = "SELECT * FROM estudiantes";
            ResultSet result = declaracion.executeQuery(query);

            while (result.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdentificacion(result.getString("Id_Estudiante"));
                estudiante.setCedula(result.getString("Cedula"));
                estudiante.setNombre(result.getString("Nombre"));
                estudiante.setApellido(result.getString("Apellido"));
                estudiante.setGenero(result.getString("Sexo"));
                estudiante.setTelefono(result.getString("Telefono"));
                estudiante.setDireccion(result.getString("Direccion"));
                estudiante.setCorreo(result.getString("Email"));
                estudiante.setLugarNacimiento(result.getString("Lugar_Nacimiento"));
                // Fecha de nacimiento obtenida del resultado de la consulta
                Date fechaNacimiento = result.getDate("FNacimiento");

                // Convierte la fecha de nacimiento a LocalDate
                LocalDate fechaNacimientoLocalDate = fechaNacimiento.toLocalDate();

                // Calcula la edad a partir de la fecha de nacimiento
                LocalDate fechaActual = LocalDate.now();
                Period periodo = Period.between(fechaNacimientoLocalDate, fechaActual);
                int edad = periodo.getYears();

                // Establece la edad en el objeto Estudiante
                estudiante.setEdad(Integer.toString(edad));

                // Asigna los demás campos de acuerdo a las columnas de la tabla
                observableEstudiantes.add(estudiante);
            }
            tableEstudiantes.setItems(observableEstudiantes);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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

    @FXML
    protected void buscarEstudiante() {
        if (textoBusqueda.getText().length() > 0) {
            try {
                // Realiza la búsqueda en la base de datos
                conexion = new DBconn();
                declaracion = conexion.conn.createStatement();
                String query = "SELECT * FROM estudiantes WHERE Cedula LIKE '%" + textoBusqueda.getText() + "%' OR Id_Estudiante LIKE '%" + textoBusqueda.getText() + "%'";
                ResultSet result = declaracion.executeQuery(query);
                System.out.println("Consulta: " + query);

                // Limpia la tabla antes de agregar los resultados de la búsqueda
                observableEstudiantes.clear();

                while (result.next()) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdentificacion(result.getString("Id_Estudiante"));
                    estudiante.setCedula(result.getString("Cedula"));
                    estudiante.setNombre(result.getString("Nombre"));
                    estudiante.setApellido(result.getString("Apellido"));
                    estudiante.setGenero(result.getString("Sexo"));
                    estudiante.setTelefono(result.getString("Telefono"));
                    estudiante.setDireccion(result.getString("Direccion"));
                    estudiante.setCorreo(result.getString("Email"));
                    estudiante.setLugarNacimiento(result.getString("Lugar_Nacimiento"));

                    // Fecha de nacimiento obtenida del resultado de la consulta
                    Date fechaNacimiento = result.getDate("FNacimiento");

                    // Convierte la fecha de nacimiento a LocalDate
                    LocalDate fechaNacimientoLocalDate = fechaNacimiento.toLocalDate();

                    // Calcula la edad a partir de la fecha de nacimiento
                    LocalDate fechaActual = LocalDate.now();
                    Period periodo = Period.between(fechaNacimientoLocalDate, fechaActual);
                    int edad = periodo.getYears();

                    // Establece la edad en el objeto Estudiante
                    estudiante.setEdad(Integer.toString(edad));

                    // Asigna los demás campos de acuerdo a las columnas de la tabla
                    observableEstudiantes.add(estudiante);
                }
                tableEstudiantes.setItems(observableEstudiantes);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
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
        } else {
            // Si el campo de búsqueda está vacío, se carga la tabla completa
            table_load_estudiantes();
        }
    }
    private void initTableCursos() {
        curIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        curNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        curInstructor.setCellValueFactory(new PropertyValueFactory<>("Instructor"));
        curCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        // Agrega las demás columnas aquí según sea necesario
    }
    public void table_load_cursos() {
        try {
            initTableCursos(); // Inicializa la tabla
            ObservableList<Curso> observableCursos = FXCollections.observableArrayList();

            conexion = new DBconn();
            String query = "SELECT * FROM cursos";
            ResultSet result = conexion.conn.createStatement().executeQuery(query);

            while (result.next()) {
                Curso curso = new Curso();
                curso.setIdentificacion(result.getString("Id_Curso"));
                curso.setNombre(result.getString("Nombre_Curso"));
                // Obtiene el nombre del instructor
                String queryInstructor = "SELECT Nombre, Apellido FROM instructores WHERE Id_Instructor = " + result.getString("Id_Instructor");
                ResultSet resultInstructor = conexion.conn.createStatement().executeQuery(queryInstructor);
                if (!resultInstructor.next()) {
                    curso.setInstructor("No asignado");
                } else {
                    curso.setInstructor(resultInstructor.getString("Nombre")  + " " + resultInstructor.getString("Apellido"));
                }
                curso.setCosto(result.getString("costo"));
                // Asigna los demás campos de acuerdo a las columnas de la tabla
                observableCursos.add(curso);
            }
            tableCursos.setItems(observableCursos);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initTablePagos() {
        pagIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        pagEstudiante.setCellValueFactory(new PropertyValueFactory<>("Estudiante"));
        pagReferencia.setCellValueFactory(new PropertyValueFactory<>("Referencia"));
        pagFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        pagMonto.setCellValueFactory(new PropertyValueFactory<>("Monto"));
        pagDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        pagMetodo.setCellValueFactory(new PropertyValueFactory<>("Metodo"));
        // Agrega las demás columnas aquí según sea necesario
    }

    public void table_load_pagos() {
        try {
            initTablePagos(); // Inicializa la tabla
            ObservableList<Pago> observablePagos = FXCollections.observableArrayList();

            conexion = new DBconn();
            String query = "SELECT * FROM pagos";
            ResultSet result = conexion.conn.createStatement().executeQuery(query);

            while (result.next()) {
                Pago pago = new Pago();
                pago.setIdentificacion(result.getString("Id_Pago"));
                String queryEstudiante = "SELECT Nombre, Apellido FROM estudiantes WHERE Id_Estudiante = " + result.getString("Id_Estudiante");
                ResultSet resultEstudiante = conexion.conn.createStatement().executeQuery(queryEstudiante);
                if (!resultEstudiante.next()) {
                    pago.setEstudiante("No asignado");
                } else {
                    pago.setEstudiante(resultEstudiante.getString("Nombre")  + " " + resultEstudiante.getString("Apellido"));
                }
                pago.setReferencia(result.getString("Referencia_Bancaria"));
                pago.setFecha(result.getString("Fecha_Pago"));
                pago.setMonto(result.getString("Monto"));
                pago.setDescripcion(result.getString("descripcion"));
                pago.setMetodo(result.getString("Metodo_Pago"));
                // Asigna los demás campos de acuerdo a las columnas de la tabla
                observablePagos.add(pago);
            }
            tablePagos.setItems(observablePagos);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initTableInstructores() {
        insIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        insNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        insApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        insCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
        insTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        insCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
        insDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        insEspecialidad.setCellValueFactory(new PropertyValueFactory<>("Especialidad"));
        insGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        // Agrega las demás columnas aquí según sea necesario
    }

    public void table_load_instructores() {
        try {
            initTableInstructores(); // Inicializa la tabla
            ObservableList<Instructor> observableInstructores = FXCollections.observableArrayList();

            conexion = new DBconn();
            String query = "SELECT * FROM instructores";
            ResultSet result = conexion.conn.createStatement().executeQuery(query);

            while (result.next()) {
                Instructor instructor = new Instructor();
                instructor.setIdentificacion(result.getString("Id_Instructor"));
                instructor.setNombre(result.getString("Nombre"));
                instructor.setApellido(result.getString("Apellido"));
                instructor.setCedula(result.getString("Cedula"));
                instructor.setTelefono(result.getString("Telefono"));
                instructor.setCorreo(result.getString("Email"));
                instructor.setDireccion(result.getString("Direccion"));
                instructor.setEspecialidad(result.getString("Especializacion"));
                instructor.setGenero(result.getString("Sexo"));
                // Asigna los demás campos de acuerdo a las columnas de la tabla
                observableInstructores.add(instructor);
            }
            tableInstructores.setItems(observableInstructores);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
    private void initTableMatriculas() {
        matIdentificacion.setCellValueFactory(new PropertyValueFactory<>("id_matricula"));
        matEstudiante.setCellValueFactory(new PropertyValueFactory<>("id_estudiante"));
        matCurso.setCellValueFactory(new PropertyValueFactory<>("id_curso"));
        matFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_matricula"));
        matEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        // Agrega las demás columnas aquí según sea necesario
    }
    public void table_load_matriculas() {
        try {
            initTableMatriculas(); // Inicializa la tabla
            ObservableList<Matricula> observableMatriculas = FXCollections.observableArrayList();

            conexion = new DBconn();
            String query = "SELECT * FROM matricula";
            ResultSet result = conexion.conn.createStatement().executeQuery(query);

            while (result.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdentificacion(result.getString("Id_Matricula"));
                String queryEstudiante = "SELECT Nombre, Apellido FROM estudiantes WHERE Id_Estudiante = " + result.getString("Id_Estudiante");
                ResultSet resultEstudiante = conexion.conn.createStatement().executeQuery(queryEstudiante);
                if (!resultEstudiante.next()) {
                    matricula.setEstudiante("No asignado");
                } else {
                    matricula.setEstudiante(resultEstudiante.getString("Nombre")  + " " + resultEstudiante.getString("Apellido"));
                }
                String queryCurso = "SELECT Nombre_Curso FROM cursos WHERE Id_Curso = " + result.getString("Id_Curso");
                ResultSet resultCurso = conexion.conn.createStatement().executeQuery(queryCurso);
                if (!resultCurso.next()) {
                    matricula.setCurso("No asignado");
                } else {
                    matricula.setCurso(resultCurso.getString("Nombre_Curso"));
                }
                matricula.setFecha(result.getString("Fecha_Matricula"));
                matricula.setEstado(result.getString("Estado"));
                // Asigna los demás campos de acuerdo a las columnas de la tabla
                observableMatriculas.add(matricula);
            }
            tableMatriculas.setItems(observableMatriculas);
        } catch (SQLException ex) {
            ex.printStackTrace();
}
        finally {
            try {
                if (conexion != null) {
                    conexion.conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
