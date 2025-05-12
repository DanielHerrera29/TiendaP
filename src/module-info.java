module Tienda {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.Controller to javafx.fxml; 
    exports co.edu.poli.Controller;             

    exports co.edu.poli.View;
    exports co.edu.poli.Model;
}


