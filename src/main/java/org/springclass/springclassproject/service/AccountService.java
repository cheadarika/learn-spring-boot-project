package org.springclass.springclassproject.service;

import org.springclass.springclassproject.controller.request.AccountUpdateRequest;
import org.springclass.springclassproject.model.AccountEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountEntity saveAccount(AccountEntity account);
    AccountEntity update(Long id, AccountUpdateRequest account);
    List<AccountEntity> findAll();
    Page<AccountEntity> findAll(int page, int size);
    AccountEntity findById(Long id);
    AccountEntity findByAccountNumber(String accountNumber);
    List<AccountEntity> findByAccountType(String accountType);
    void deleteAccount(Long id);
}
