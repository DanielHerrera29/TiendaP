module Actividad11 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.Controller to javafx.fxml;
    opens co.edu.poli.View to javafx.graphics, javafx.fxml;
    
    exports co.edu.poli.Controller;
}
