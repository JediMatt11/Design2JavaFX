module com.example.design2javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.design2javafx to javafx.fxml;
    exports com.example.design2javafx;
}