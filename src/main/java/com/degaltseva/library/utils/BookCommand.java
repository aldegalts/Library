package com.degaltseva.library.utils;

import com.degaltseva.library.entity.Book;
import com.degaltseva.library.service.BookService;
import org.springframework.stereotype.Component;

@Component
public class BookCommand implements Command {

    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(String input) {
        return input.startsWith("book ");
    }

    @Override
    public void execute(String input) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            System.err.println("Некорректная команда для книги");
            return;
        }

        switch (parts[1]) {
            case "create" -> {
                if (parts.length < 6) {
                    System.err.println("Использование: book create <id> <title> <author> <year>");
                    return;
                }
                bookService.createBook(
                        Long.parseLong(parts[2]),
                        parts[3],
                        parts[4],
                        Integer.parseInt(parts[5])
                );
                System.out.println("Книга успешно добавлена");
            }
            case "read" -> {
                if (parts.length < 3) {
                    System.err.println("Использование: book read <id>");
                    return;
                }
                Book book = bookService.findById(Long.parseLong(parts[2]));
                if (book != null) {
                    System.out.printf("Книга: id=%d, title=%s, author=%s, year=%d, доступна=%s%n",
                            book.getId(), book.getTitle(), book.getAuthor(),
                            book.getYear(), book.isAvailable() ? "да" : "нет");
                } else {
                    System.err.println("Книга не найдена");
                }
            }
            case "update" -> {
                if (parts.length < 7) {
                    System.err.println("Использование: book update <id> <title> <author> <year> <available>");
                    return;
                }
                Long id = Long.parseLong(parts[2]);
                String title = parts[3];
                String author = parts[4];
                int year = Integer.parseInt(parts[5]);
                boolean available = Boolean.parseBoolean(parts[6]);
                bookService.updateBookData(id, title, author, year, available);
                System.out.println("Данные книги обновлены");
            }
            case "delete" -> {
                if (parts.length < 3) {
                    System.err.println("Использование: book delete <id>");
                    return;
                }
                bookService.deleteById(Long.parseLong(parts[2]));
                System.out.println("Книга удалена");
            }
            default -> System.err.println("Неизвестная команда книги");
        }
    }
}
