package org.springclass.springclassproject.repository;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springclass.springclassproject.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    List<AccountEntity> findByAccountHolderNameContainingIgnoreCase(String name);
    List<AccountEntity> findByAccountType(String accountType);
    @Query("SELECT COUNT(a) FROM AccountEntity a")
    long countAllAccounts();

    @Transactional
    @Modifying
    @Query("DELETE AccountEntity WHERE id = :id ")
    void deleteById(@NonNull Long id);
}
