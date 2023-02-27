package ru.relex.client.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.relex.client.Entity.BookEntity;
import ru.relex.client.Response.BaseResponse;
import ru.relex.client.Utils.HTTPUtils;

import java.io.IOException;

import static ru.relex.client.controller.MainScreenController.api;

public class BookDao {
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    public static String getBooks() throws IOException {
        return http.get(api, "all");
    }

    public static BookEntity sendBookAndGetData(BookEntity book) throws IOException {
        String response = http.post(api + "add", gson.toJson(book));
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(jsonObject.get("data"), BookEntity.class);
    }

    public static String updateBook(BookEntity book) throws IOException {
        return http.put(api + "update", gson.toJson(book));
    }

    public static BaseResponse deleteBook(BookEntity book) throws IOException {
        return http.delete(api, book.getId());
    }
}
