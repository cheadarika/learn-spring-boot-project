package org.springclass.springclassproject.controller.respnse;

import lombok.Value;
import org.springclass.springclassproject.model.AccountEntity;

import java.time.LocalDateTime;

@Value
public class AccountResponse {
    Long id;
    String accountNumber;
    String accountHolderName;
    String accountType;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public AccountResponse (AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.accountNumber = accountEntity.getAccountNumber();
        this.accountHolderName = accountEntity.getAccountHolderName();
        this.accountType = accountEntity.getAccountType();
        this.createdAt = accountEntity.getCreated();
        this.updatedAt = accountEntity.getUpdated();
    }
}
