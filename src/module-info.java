module Tienda {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.controller to javafx.fxml;
    exports co.edu.poli.controller;
    // exports co.edu.poli; // Si tu clase Main está directamente en este paquete
    exports co.edu.poli.View; // Si tienes una carpeta View y tu Main está ahí
}

// module Tienda {
//     requires javafx.controls;
//     requires javafx.fxml;

//     opens co.edu.poli.controller to javafx.fxml;
//     exports co.edu.poli.controller;
//     exports co.edu.poli.View;
// }
