module com.example.kaspifx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kaspifx to javafx.fxml;
    exports com.example.kaspifx;
}