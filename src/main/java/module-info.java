module org.example.ung_dung_hoc_tieng_anh {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens org.example.ung_dung_hoc_tieng_anh to javafx.fxml;
    exports org.example.ung_dung_hoc_tieng_anh;
}