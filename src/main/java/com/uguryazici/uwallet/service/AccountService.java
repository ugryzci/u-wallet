package com.uguryazici.uwallet.service;

import com.uguryazici.uwallet.entity.Account;

import java.math.BigDecimal;
import java.nio.channels.AcceptPendingException;
import java.util.Optional;

public interface AccountService {
    Account save(Account account);

    Iterable<Account> findAll();

    Optional<Account> findById(long id);

    void deleteById(Long accountId);

    Account makeDebit(Account account, BigDecimal amount);

    Account makeCredit(Account account, BigDecimal amount);
}
