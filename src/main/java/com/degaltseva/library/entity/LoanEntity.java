package com.degaltseva.library.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность, описывающая факт выдачи книги пользователю.
 * <p>
 * Хранит информацию: кто взял, какую книгу, когда выдано, срок возврата, дата возврата и статус.
 */
@Entity
@Table(name = "Loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(name = "issue_date", nullable = false)
    private LocalDateTime issueDate = LocalDateTime.now();

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private LoanStatusEntity status;

    @OneToOne(mappedBy = "loan", fetch = FetchType.LAZY)
    private FineEntity fine;

    public LoanEntity() {

    }

    public LoanEntity(UserEntity user, BookEntity book, LocalDateTime dueDate, LocalDateTime returnDate) {
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatusEntity getStatus() {
        return status;
    }

    public FineEntity getFine() {
        return fine;
    }
}
