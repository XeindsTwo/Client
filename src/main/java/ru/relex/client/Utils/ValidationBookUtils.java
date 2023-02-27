package ru.relex.client.Utils;

import ru.relex.client.Entity.BookEntity;

public class ValidationBookUtils {
    public static boolean validateBook(BookEntity book) {
        StringBuilder errorMessage = new StringBuilder();
        String title = book.getTitle();
        String author = book.getAuthor();
        String publishing = book.getPublishing();
        String typeBook = book.getTypeBook();
        String year = String.valueOf(book.getYear());

        if (title.isBlank() || !title.matches("[\\sA-ZА-Яa-zа-я0-9]{3,120}")) {
            errorMessage.append("Неправильно введено название книги. " +
                    "Минимальное количество символов - 3, максимальное - 120\n\n");
        }
        if (author.isBlank() || !author.matches("[\\sA-ZА-Яa-zа-я0-9]{2,120}")) {
            errorMessage.append("Неправильно введен автор книги. " +
                    "Минимальное количество символов - 2, максимальное - 120\n\n");
        }
        if (publishing.isBlank() || !publishing.matches("[\\sA-ZА-Яa-zа-я0-9]{3,70}")) {
            errorMessage.append("Неправильно введено названия издательства. " +
                    "Минимальное количество символов - 3, максимальное - 70\n\n");
        }
        if (typeBook.isBlank() || !typeBook.matches("[\\sA-ZА-Яa-zа-я0-9]{2,40}")) {
            errorMessage.append("Неправильно введен жанр книги. " +
                    "Минимальное количество символов - 3, максимальное - 40\n\n");
        }
        if (year.isBlank() || !year.matches("^\\d{3,4}")) {
            errorMessage.append("Неправильно введена дата издания книги\n");
        } else {
            try {
                Integer.parseInt(year);
            } catch (NumberFormatException e) {
                errorMessage.append("Некорректный ввод значения года издания книги (только целочисленный тип)\n");
            }
        }

        return errorMessage.length() == 0;
    }

    public static String getErrorMessageFromBookFields(String title, String author, String publishing, String typeBook, String year) {
        StringBuilder errorMessage = new StringBuilder();
        if (title.isBlank() || !title.matches("[\\sA-ZА-Яa-zа-я0-9]{3,120}")) {
            errorMessage.append("Неправильно введено название книги. " +
                    "Минимальное количество символов - 3, максимальное - 120\n\n");
        }
        if (author.isBlank() || !author.matches("[\\sA-ZА-Яa-zа-я0-9]{2,120}")) {
            errorMessage.append("Неправильно введен автор книги. " +
                    "Минимальное количество символов - 2, максимальное - 120\n\n");
        }
        if (publishing.isBlank() || !publishing.matches("[\\sA-ZА-Яa-zа-я0-9]{3,70}")) {
            errorMessage.append("Неправильно введено названия издательства. " +
                    "Минимальное количество символов - 3, максимальное - 70\n\n");
        }
        if (typeBook.isBlank() || !typeBook.matches("[\\sA-ZА-Яa-zа-я0-9]{2,40}")) {
            errorMessage.append("Неправильно введен жанр книги. " +
                    "Минимальное количество символов - 3, максимальное - 40\n\n");
        }
        if (year.isBlank() || !year.matches("\\d{3,4}")) {
            errorMessage.append("Неправильно введена дата издания книги");
        } else {
            try {
                Integer.parseInt(year);
            } catch (NumberFormatException e) {
                errorMessage.append("Некорректный ввод значения года издания книги (только целочисленный тип)\n");
            }
        }

        return errorMessage.toString();
    }
}
