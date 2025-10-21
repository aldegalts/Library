package com.degaltseva.library.service.impl;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.entity.CategoryEntity;
import com.degaltseva.library.repository.BookRepository;
import com.degaltseva.library.repository.CategoryRepository;
import com.degaltseva.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               BookRepository bookRepository,
                               PlatformTransactionManager transactionManager) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteCategoryWithBooks(Long categoryId) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            CategoryEntity category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));

            List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
            for (BookEntity book : books) {
                if (book.getCategory() != null && book.getCategory().getCategoryId().equals(categoryId)) {
                    bookRepository.delete(book);
                }
            }

            categoryRepository.delete(category);

            transactionManager.commit(status);

        } catch (RuntimeException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
