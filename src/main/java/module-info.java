module com.init.szas_v1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.init.szas_v1 to javafx.fxml;
    exports com.init.szas_v1;

}
