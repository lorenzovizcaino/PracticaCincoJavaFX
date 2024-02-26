module com.example.practicacincojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practicacincojavafx to javafx.fxml;
    exports com.example.practicacincojavafx;
}