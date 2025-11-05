package com.degaltseva.library.controller;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.service.BookService;
import com.degaltseva.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления книгами.
 * Отображает список книг, позволяет добавлять, редактировать, удалять и искать книги.
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<BookEntity> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/books";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/book-form";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") BookEntity book) {
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookEntity book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/book-form";
    }

    @PostMapping("/{id}/update")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") BookEntity book) {
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String title,
                              @RequestParam int startYear,
                              @RequestParam int endYear,
                              Model model) {
        List<BookEntity> books = bookService.findByTitleAndYearRange(title, startYear, endYear);
        model.addAttribute("books", books);
        return "book/books";
    }
}
