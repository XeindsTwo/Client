package ru.relex.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.relex.client.Entity.BookEntity;
import ru.relex.client.Utils.AlertUtils;
import ru.relex.client.Utils.ValidationBookUtils;
import ru.relex.client.dao.BookDao;

import java.io.IOException;

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
    private boolean okClicked = false;

    public void setEditDialogStage(Stage editDialogStage) {
        this.editDialogStage = editDialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(BookEntity book) {
        this.book = book;
        bookNameField.setText(book.getTitle());
        bookAuthorField.setText(book.getAuthor());
        bookPublisherField.setText(book.getPublishing());
        bookTypeBookField.setText(book.getTypeBook());
        bookYearField.setText(String.valueOf(book.getYear()));
    }

    @FXML
    private void saveBook() throws IOException {
        if (isInputValid()) {
            book.setTitle(bookNameField.getText());
            book.setAuthor(bookAuthorField.getText());
            book.setPublishing(bookPublisherField.getText());
            book.setTypeBook(bookTypeBookField.getText());
            book.setYear(bookYearField.getText());
            book.setId(BookDao.sendBookAndGetData(book).getId());

            okClicked = true;
            editDialogStage.close();
        }
    }

    @FXML
    void closeWindow() {
        editDialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = ValidationBookUtils.getErrorMessageFromBookFields(
                bookNameField.getText(),
                bookAuthorField.getText(),
                bookPublisherField.getText(),
                bookTypeBookField.getText(),
                bookYearField.getText()
        );
        if (errorMessage.length() == 0)
            return true;
        else {
            AlertUtils.showWarning(errorMessage);
            return false;
        }
    }
}
