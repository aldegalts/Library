package com.degaltseva.library.controller;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для работы с отображением списка книг в веб-интерфейсе.
 * <p>
 * Обрабатывает HTTP-запросы, связанные с просмотром данных о книгах,
 * и возвращает HTML-представления на основе шаблонов Thymeleaf.
 */
@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Отображает список всех книг в виде HTML-таблицы.
     * URL: /books
     */
    @GetMapping("/books")
    public String showBookList(Model model) {
        Iterable<BookEntity> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }
}
