package org.springclass.springclassproject.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springclass.springclassproject.model.AccountEntity;

@Value
public class AccountCreateRequest {
    @NotNull
    String accountNumber;
    String accountHolderName;
    String accountType;

    public AccountEntity toAccountEntity() {
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(accountNumber);
        account.setAccountHolderName(accountHolderName);
        account.setAccountType(accountType);
        return account;
    }
}
