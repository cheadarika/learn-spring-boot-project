package org.springclass.springclassproject.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springclass.springclassproject.model.AccountEntity;

@Value
public class AccountUpdateRequest {
    @NotNull
    String accountNumber;
    String accountHolderName;
    String accountType;
}
