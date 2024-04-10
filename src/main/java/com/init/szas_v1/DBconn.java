package com.init.szas_v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLData;

public class DBconn {
    Connection conn = null;
    String user = "root";
    String password = "root";
    String bd = "szas";
    String ip = "localhost";
    String port = "3306";

    String url = "jdbc:mysql://" + ip + ":" + port + "/" + bd;

    public DBconn() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
}
