module com.example.chessgamejfx {
    requires javafx.controls;
    requires javafx.fxml;

    exports BoardAndGameLogic;
    exports Interface;
    exports Pieces;
    opens Interface to javafx.fxml;
}