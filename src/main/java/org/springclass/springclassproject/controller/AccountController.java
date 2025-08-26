package org.springclass.springclassproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springclass.springclassproject.annotation.AuditFilter;
import org.springclass.springclassproject.controller.request.AccountCreateRequest;
import org.springclass.springclassproject.controller.request.AccountUpdateRequest;
import org.springclass.springclassproject.controller.respnse.AccountResponse;
import org.springclass.springclassproject.model.AccountEntity;
import org.springclass.springclassproject.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody @Valid AccountCreateRequest body) {
        final var request = body.toAccountEntity();
        final var result = accountService.saveAccount(request);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<Page<AccountEntity>> getAccounts() {
        return ResponseEntity.ok().body(accountService.findAll(0,10));
    }

    @AuditFilter
    @GetMapping("/list")
    public ResponseEntity<List<AccountEntity>> getList() {
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        log.info(">>>>> Get account by id: {}", id);
        final var account = accountService.findById(id);
         return ResponseEntity.ok().body(new AccountResponse(account));
    }

    @GetMapping("/account-number/{accountNumber}")
    public ResponseEntity<AccountResponse> getByAccountNumber(@PathVariable String accountNumber) {
        log.info(">>>>> Get account by account number: {}", accountNumber);
        return ResponseEntity.ok().body(new AccountResponse(accountService.findByAccountNumber(accountNumber)));
    }

    @GetMapping("/account-type/{accountType}")
    public ResponseEntity<List<AccountEntity>> getByAccountType(@PathVariable String accountType) {
        log.info(">>>>> Get account by account type: {}", accountType);
        return ResponseEntity.ok().body(accountService.findByAccountType(accountType));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable(value = "id") Long id,
                                                         @RequestBody @Valid AccountUpdateRequest body) {
        log.info(">>>>> Update account by id: {} - request {}", id, body);
        final var result = accountService.update(id, body);
        return ResponseEntity.ok().body(new AccountResponse(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccountById(@PathVariable(value = "id") Long id) {
        log.info(">>>>> Delete account by id: {}", id);
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}
