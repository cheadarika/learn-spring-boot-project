package org.springclass.springclassproject.service.implement;

import lombok.RequiredArgsConstructor;
import org.springclass.springclassproject.controller.request.AccountUpdateRequest;
import org.springclass.springclassproject.exception.ResourceNotFoundException;
import org.springclass.springclassproject.model.AccountEntity;
import org.springclass.springclassproject.repository.AccountRepository;
import org.springclass.springclassproject.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountEntity saveAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Page<AccountEntity> findAll(int page, int size) {
        final var pageable = PageRequest.of(page, size);
        return accountRepository.findAll(pageable);
    }

    @Override
    public AccountEntity findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("404", "Account id %d not found".formatted(id)));
    }

    @Override
    public AccountEntity findByAccountNumber(String accountNumber) {
        final var account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("404", "Account number %s not found".formatted(accountNumber));
        }
        return account;
    }

    @Override
    public List<AccountEntity> findByAccountType(String accountType) {
        return accountRepository.findByAccountType(accountType);
    }

    @Override
    public AccountEntity update(Long id, AccountUpdateRequest account) {
        final var entity = findById(id);
        entity.setAccountNumber(account.getAccountNumber());
        entity.setAccountHolderName(account.getAccountHolderName());
        entity.setAccountType(account.getAccountType());
        return accountRepository.save(entity);
    }

    @Override
    public void deleteAccount(Long id) { accountRepository.deleteById(id); }
}
