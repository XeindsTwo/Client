module ru.relex.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires okhttp3;
    requires com.google.gson;

    exports ru.relex.client;
    exports ru.relex.client.controller;
    exports ru.relex.client.Entity;
    exports ru.relex.client.Response;
    exports ru.relex.client.Utils;

    opens ru.relex.client to javafx.fxml;
    opens ru.relex.client.controller to javafx.fxml;
    opens ru.relex.client.Entity to com.google.gson;
    opens ru.relex.client.Response to com.google.gson;
}