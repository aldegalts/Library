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
        String[] parts = input.trim().split(" +");
        if (parts.length < 2) {
            System.err.println("Некорректная команда для книги");
            return;
        }

        switch (parts[1]) {
            case "create" -> handleCreate(parts);
            case "get" -> handleGet(parts);
            case "update" -> handleUpdate(parts);
            case "delete" -> handleDelete(parts);
            case "metric" -> handleMetric(parts);
            default -> System.err.println("Неизвестная команда книги");
        }
    }

    @Override
    public String getDescription() {
        return """
           book create <id> <title> <author> <year> — добавить книгу
           book get <id> — показать данные книги
           book update <id> <title> <author> <year> <available> — обновить данные книги
           book delete <id> — удалить книгу
           book metric /some_metric — вывести метрику
           """;
    }

    private void handleCreate(String[] parts) {
        if (parts.length < 6) {
            System.err.println("Использование: book create <id> <title> <author> <year>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        if (bookService.findById(id) != null) {
            System.err.printf("Книга с id=%d уже существует%n", id);
            return;
        }

        bookService.createBook(id, parts[3], parts[4], Integer.parseInt(parts[5]));
        System.out.println("Книга успешно добавлена");
    }

    private void handleGet(String[] parts) {
        if (parts.length < 3) {
            System.err.println("Использование: book get <id>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        Book book = bookService.findById(id);
        if (book == null) {
            System.err.printf("Книга с id=%d не найдена%n", id);
            return;
        }

        System.out.printf("Книга: id=%d, title=%s, author=%s, year=%d, доступна=%s%n",
                book.getId(), book.getTitle(), book.getAuthor(),
                book.getYear(), book.isAvailable() ? "да" : "нет");
    }

    private void handleUpdate(String[] parts) {
        if (parts.length < 7) {
            System.err.println("Использование: book update <id> <title> <author> <year> <available>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        Book book = bookService.findById(id);
        if (book == null) {
            System.err.printf("Книга с id=%d не найдена, обновление невозможно%n", id);
            return;
        }

        bookService.updateBookData(id, parts[3], parts[4], Integer.parseInt(parts[5]),
                Boolean.parseBoolean(parts[6]));
        System.out.println("Данные книги обновлены");
    }

    private void handleDelete(String[] parts) {
        if (parts.length < 3) {
            System.err.println("Использование: book delete <id>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        Book book = bookService.findById(id);
        if (book == null) {
            System.err.printf("Книга с id=%d не найдена, удаление невозможно%n", id);
            return;
        }

        bookService.deleteById(id);
        System.out.printf("Книга с id=%d удалена%n", id);
    }

    private void handleMetric(String[] parts) {
        if (parts.length < 3) {
            System.err.println("Использование: book metric /some_metric");
            return;
        }
        // TODO: добавить обработку метрик
        System.out.println("Метрика ... " + parts[2]);
    }

    private Long parsePositiveId(String value) {
        try {
            long id = Long.parseLong(value);
            if (id <= 0) {
                System.err.println("ID должен быть положительным числом");
                return null;
            }
            return id;
        } catch (NumberFormatException e) {
            System.err.println("Некорректный формат ID, требуется число");
            return null;
        }
    }
}
