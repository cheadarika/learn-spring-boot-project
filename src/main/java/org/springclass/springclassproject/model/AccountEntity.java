package org.springclass.springclassproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mas_account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;
    @Column(name = "account_type")
    private String accountType;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime created;
    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updated;

    public AccountEntity() {}

    public AccountEntity(String accountNumber, String accountHolderName, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
    }

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { updated = LocalDateTime.now(); }
}
