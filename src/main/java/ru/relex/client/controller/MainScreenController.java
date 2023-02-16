package ru.relex.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.relex.client.Entity.BookEntity;
import ru.relex.client.MainApp;
import ru.relex.client.Utils.HTTPUtils;

import java.io.IOException;


public class MainScreenController {
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public static String api = "http://localhost:1000/api/v1/book/";
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    @FXML
    private TableView<BookEntity> tableBooks;
    @FXML
    private TableColumn<BookEntity, String> bookAuthor;

    @FXML
    private TableColumn<BookEntity, Long> bookId;

    @FXML
    private TableColumn<BookEntity, String> bookChapter;

    @FXML
    private TableColumn<BookEntity, String> bookName;

    @FXML
    private TableColumn<BookEntity, String> bookPublisher;

    @FXML
    private TableColumn<BookEntity, String> bookYear;

    @FXML
    private void initialize() throws Exception {
        getData();
        updateTable();
    }

    @FXML
    private void addBook(ActionEvent event) throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        MainApp.showPersonEditDialog(tempBook, Long.valueOf(booksData.size() - 1));
        addBook(tempBook);
    }

    @FXML
    private void deleteBook(ActionEvent event) throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            System.out.println(http.delete("http://localhost:1000/api/v1/book/delete/",
                    String.valueOf(tableBooks.getSelectionModel().getSelectedItem().getId())));
            booksData.remove(selectedBook);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Не выбран элемент списка");
            alert.setHeaderText("Отсутствует выбранная книга из списка");
            alert.setContentText("Пожалуйста, выберите книгу из списка приложения");
            alert.showAndWait();
        }
    }

    @FXML
    private void editBook(ActionEvent event) throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            MainApp.showPersonEditDialog(selectedBook, Long.valueOf(booksData.indexOf(selectedBook)));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Не выбран элемент списка");
            alert.setHeaderText("Отсутствует выбранная книга из списка");
            alert.setContentText("Пожалуйста, выберите книгу из списка приложения");
            alert.showAndWait();
        }
    }

    private void updateTable() {
        bookId.setCellValueFactory(new PropertyValueFactory<BookEntity, Long>("id"));
        bookName.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publishing"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("typeBook"));
        tableBooks.setItems(booksData);
    }

    public static void getData() throws Exception {
        String result = http.get(api, "all");
        System.out.println(result);

        JsonObject base = gson.fromJson(result, JsonObject.class);
        JsonArray jsonArray = base.getAsJsonArray("data");

        for (int i = 0; i < jsonArray.size(); i++) {
            BookEntity newBook = gson.fromJson(jsonArray.get(i).toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    public static void addBook(BookEntity book) throws IOException {
        System.out.println(book.toString());
        book.setId(null);
        http.post(api + "add", gson.toJson(book).toString());
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(api + "update", gson.toJson(book).toString());
    }
}
