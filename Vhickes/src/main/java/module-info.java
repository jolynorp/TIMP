module com.joly.vhickes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.joly.vhickes to javafx.fxml;
    opens com.joly.vhickes.controller to javafx.fxml;
    exports com.joly.vhickes;
}