package com.init.szas_v1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Administracion {

    private String usuario;
    private String contrasena;
    private Statement declaracion;

    public DBconn conexion;

    public Administracion(DBconn conexion) {
        this.conexion = conexion;
    }

    public boolean iniciarSesion(String usuario, String contrasena) throws SQLException {
        this.usuario = usuario;
        this.contrasena = contrasena;

        try {
            declaracion = conexion.conn.createStatement();
            String consulta = "SELECT * FROM administracion WHERE usuario = '" + this.usuario + "' AND contrasena = '" + this.contrasena + "'";
            System.out.println(consulta);
            ResultSet resultado = declaracion.executeQuery(consulta);
            return resultado.next(); // Retorna true si hay al menos un resultado en la consulta, de lo contrario retorna false.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (declaracion != null) {
                declaracion.close(); // Cierra el statement cuando haya terminado de usarlo
            }
        }
    }
}
