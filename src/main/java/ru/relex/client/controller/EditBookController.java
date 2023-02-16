package ru.relex.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.relex.client.Entity.BookEntity;

import java.io.IOException;

import static ru.relex.client.controller.MainScreenController.booksData;
import static ru.relex.client.controller.MainScreenController.updateBook;

public class EditBookController {

    @FXML
    private TextField bookAuthorField;

    @FXML
    private TextField bookNameField;

    @FXML
    private TextField bookPublisherField;

    @FXML
    private TextField bookTypeBookField;

    @FXML
    private TextField bookYearField;

    private Stage editDialogStage;
    private BookEntity book;
    private Long bookId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editDialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(BookEntity book, Long bookId) {
        this.book = book;
        this.bookId = bookId;

        bookNameField.setText(book.getTitle());
        bookAuthorField.setText(book.getAuthor());
        bookPublisherField.setText(book.getPublishing());
        bookYearField.setText(String.valueOf(book.getYear()));
        bookTypeBookField.setText(book.getTypeBook());
    }

    @FXML
    private void saveBook() throws IOException {
        if (isInputValid()) {
            book.setTitle(bookNameField.getText());
            book.setAuthor(bookAuthorField.getText());
            book.setPublishing(bookPublisherField.getText());
            book.setYear(Integer.parseInt(bookYearField.getText()));
            book.setTypeBook(bookTypeBookField.getText());

            okClicked = true;
            editDialogStage.close();
            booksData.set(Math.toIntExact(bookId), book);
            updateBook(book);
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        editDialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (bookNameField.getText() == null || bookNameField.getText().length() == 0) {
            errorMessage += "Введено неверное значение названия книги\n";
        }
        if (bookAuthorField.getText() == null || bookAuthorField.getText().length() == 0) {
            errorMessage += "Введено неверно имя автора\n";
        }
        if (bookPublisherField.getText() == null || bookPublisherField.getText().length() == 0) {
            errorMessage += "Введено неверно название издательства\n";
        }
        if (bookTypeBookField.getText() == null || bookTypeBookField.getText().length() == 0) {
            errorMessage += "Введен неверно жанр книги\n";
        }
        if (bookYearField.getText() == null || bookYearField.getText().length() == 0) {
            errorMessage += "Введен неверно год издания книги\n";
        } else {
            try {
                Integer.parseInt(bookYearField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Некорректный ввод значения года издания книги (только целочисленный тип)\n";
            }
        }
        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editDialogStage);
            alert.setTitle("Ошибка заполнения данных");
            alert.setHeaderText("Укажите корректные данные для полей информации о книге");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
