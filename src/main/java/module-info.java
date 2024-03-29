module com.init.szas_v1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.init.szas_v1 to javafx.fxml;
    exports com.init.szas_v1;
}