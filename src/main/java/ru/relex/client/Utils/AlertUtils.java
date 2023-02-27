package ru.relex.client.Utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertUtils {
    private static final Alert alert = new Alert(Alert.AlertType.WARNING);

    public static void showWarning(String message) {
        alert.setTitle("Ошибка в заполнении");
        alert.setHeaderText("Введите корректные значения полей");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showNotSelected() {
        alert.setTitle("Ничего не выбрано");
        alert.setHeaderText("Отсутсвует выбранная запись");
        alert.setContentText("Пожалуйста выберите запись в таблице");
        alert.showAndWait();
    }

    public void serverNotRun(Stage stage) {
        alert.setTitle("Ошибка запуска");
        alert.setHeaderText("Сервер не был запущен");
        alert.setContentText("Пожалуйста перезагрузите программу, чтобы программа работала успешно");
        alert.showAndWait();
    }
}
