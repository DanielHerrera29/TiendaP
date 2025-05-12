module Tienda {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.View to javafx.fxml;
    opens co.edu.poli.Controller to javafx.fxml;
    opens co.edu.poli.Model to javafx.base, javafx.controls;

    exports co.edu.poli.View;
    exports co.edu.poli.Controller;
}


