package ru.relex.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.relex.client.Entity.BookEntity;
import ru.relex.client.controller.EditBookController;
import ru.relex.client.controller.MainScreenController;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainScreen.fxml"));
            AnchorPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Электронная библиотека");

            MainScreenController controller = loader.getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean showPersonEditDialog(BookEntity book) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditBook.fxml"));
            AnchorPane anchorPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("Редактирование книги");
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);

            EditBookController controller = loader.getController();
            controller.setEditDialogStage(dialogStage);
            controller.setLabels(book);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}