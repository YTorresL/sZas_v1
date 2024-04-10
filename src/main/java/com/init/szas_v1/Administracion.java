package com.init.szas_v1;

import java.sql.SQLException;
import java.sql.Statement;

public class employees {

    public String User;
    private String Password;
    Statement stmt;

    DBconn conn;

    public administracion(DBconn conn) {

        this.conn = conn;
    }
    public boolean Login(String user, String password) throws SQLException {
        this.User = user;
        this.Password = password;
        conn = new DBconn();
        stmt = conn.conn.createStatement();
        String query = "SELECT * FROM employees WHERE user = '" + User + "' AND password = '" + Password + "'";
        System.out.println(query);
        return stmt.execute(query);
    }
}
