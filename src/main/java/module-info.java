module co.edu.poli.Ejercicio {
	  requires javafx.controls;
	  requires java.sql;
	  requires javafx.fxml;

    opens co.edu.poli.Ejercicio.controller to javafx.fxml;
    exports co.edu.poli.Ejercicio.controller;
    
    opens co.edu.poli.Ejercicio.view to javafx.fxml;

    exports co.edu.poli.Ejercicio.view ;
    
    opens co.edu.poli.Ejercicio.model to javafx.base;  
    opens co.edu.poli.Ejercicio.services to javafx.base; 

    exports co.edu.poli.Ejercicio.model ;
    exports co.edu.poli.Ejercicio.services ;
}

