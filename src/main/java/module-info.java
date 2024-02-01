module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.google.gson;


    opens com.example.chess to javafx.fxml;
    exports com.example.chess;

    opens com.example.chess.chess to javafx.fxml;
    exports com.example.chess.chess;
}

