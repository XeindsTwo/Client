module ru.relex.client {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens ru.relex.client to javafx.fxml;
    exports ru.relex.client;
}