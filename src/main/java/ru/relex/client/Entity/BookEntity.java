package ru.relex.client.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEntity implements Cloneable {
    private Long id;
    private String author;
    private String title;
    private String publishing;
    private String typeBook;
    private String year;

    public static BookEntity getNullObject() {
        return BookEntity.builder()
                .id(null)
                .author("")
                .typeBook("")
                .publishing("")
                .year("")
                .title("")
                .build();
    }
    @Override
    public BookEntity clone() {
        try {
            BookEntity book = (BookEntity) super.clone();
            return (BookEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }
}
