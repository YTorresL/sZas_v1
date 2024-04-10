package com.init.szas_v1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Administracion {

    private String User;
    private String Password;
    private Statement stmt;

    public DBconn conn;

    public Administracion(DBconn conn) {
        this.conn = conn;
    }

    public boolean Login(String user, String password) throws SQLException {
        this.User = user;
        this.Password = password;

        try {
            stmt = conn.conn.createStatement();
            String query = "SELECT * FROM administracion WHERE usuario = '" + User + "' AND contrasena = '" + Password + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            return rs.next(); // Retorna true si hay al menos un resultado en la consulta, de lo contrario retorna false.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null) {
                stmt.close(); // Cierra el statement cuando haya terminado de usarlo
            }
        }
    }
}
