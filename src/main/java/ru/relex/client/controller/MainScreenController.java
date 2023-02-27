package ru.relex.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import ru.relex.client.Entity.BookEntity;
import ru.relex.client.MainApp;
import ru.relex.client.Utils.AlertUtils;
import ru.relex.client.Utils.ValidationBookUtils;
import ru.relex.client.dao.BookDao;

import java.io.IOException;

public class MainScreenController {
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public final static String api = "http://localhost:1000/api/v1/book/";

    AlertUtils alerts = new AlertUtils();
    static Gson gson = new Gson();

    @Setter
    private Stage stage;

    @FXML
    private TableView<BookEntity> tableBooks;
    @FXML
    private TableColumn<BookEntity, String> bookAuthor;

    @FXML
    private TableColumn<BookEntity, String> bookChapter;

    @FXML
    private TableColumn<BookEntity, String> bookName;

    @FXML
    private TableColumn<BookEntity, String> bookPublisher;

    @FXML
    private TableColumn<BookEntity, String> bookYear;

    @FXML
    private void initialize() {
        try {
            getData();
            updateTable();
        } catch (IOException e) {
            alerts.serverNotRun(stage);
            System.exit(0);
        }
    }

    @FXML
    private void clickAddBook() throws IOException {
        BookEntity book = BookEntity.getNullObject();
        MainApp.showPersonEditDialog(book);
        if (ValidationBookUtils.validateBook(book)) {
            addBook(book);
        }
    }

    @FXML
    private void clickDeleteBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            BookDao.deleteBook(selectedBook);
            booksData.remove(selectedBook);
        } else {
            alerts.showNotSelected();
        }
    }

    @FXML
    private void clickDuplicateBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            BookEntity clonedBook = selectedBook.clone();
            clonedBook.setId(null);
            addBook(clonedBook);
        } else {
            alerts.showNotSelected();
        }
    }

    @FXML
    private void clickEditBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            MainApp.showPersonEditDialog(selectedBook);
            updateBook(selectedBook);
        } else {
            alerts.showNotSelected();
        }
    }

    private void updateTable() {
        bookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<>("publishing"));
        bookYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<>("typeBook"));
        tableBooks.setItems(booksData);
    }

    public static void getData() throws IOException {
        String response = BookDao.getBooks();
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = base.getAsJsonArray("data");
        for (JsonElement element : jsonArray) {
            BookEntity newBook = gson.fromJson(element.toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    public static void addBook(BookEntity book) throws IOException {
        Long id = BookDao.sendBookAndGetData(book).getId();
        book.setId(id);
        booksData.add(book);
        System.out.println(book);
    }

    public static void updateBook(BookEntity book) throws IOException {
        BookDao.updateBook(book);
        int bookIndex = booksData.indexOf(book);
        booksData.set(bookIndex, book);
    }
}
