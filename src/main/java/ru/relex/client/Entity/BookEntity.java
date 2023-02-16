package ru.relex.client.Entity;

import lombok.Data;

@Data
public class BookEntity {
    private Long id;
    private String author;
    private String title;
    private String publishing;
    private String typeBook;
    private int year;
}
