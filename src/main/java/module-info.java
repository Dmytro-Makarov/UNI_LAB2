module uni.makarov.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jaxen;
    requires java.xml;


    opens uni.makarov.lab2 to javafx.fxml;
    exports uni.makarov.lab2;
}