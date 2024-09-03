module com.joly.vhickes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.joly.vhickes to javafx.fxml;
    opens com.joly.vhickes.controller to javafx.fxml;
    opens com.joly.vhickes.model.dto to java.base;
    opens com.joly.vhickes.view.components to javafx.fxml;

    exports com.joly.vhickes;
    exports com.joly.vhickes.model.dto;
}