module org.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires freetts;

    opens org.example.demo2 to javafx.fxml;
    exports org.example.demo2;
}