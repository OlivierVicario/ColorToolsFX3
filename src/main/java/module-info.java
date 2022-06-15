module vic.colortools {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    requires org.apache.logging.log4j;

    opens vic.colortools to javafx.fxml;
    exports vic.colortools;
    exports vic.colortools.controller;
    opens vic.colortools.controller to javafx.fxml;
}
