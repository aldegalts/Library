package com.degaltseva.library.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Сущность статус аренды книги.
 * <p>
 * Возможности: хранение ID и статуса аренды книги.
 */
@Entity
@Table(name = "Loan_status")
public class LoanStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_status_id")
    private Long loanStatusId;

    @Column(name = "loan_status", length = 20, nullable = false, unique = true)
    private String loanStatus;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    public LoanStatusEntity() {

    }

    public LoanStatusEntity(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Long getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(Long loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public List<LoanEntity> getLoans() {
        return loans;
    }
}
