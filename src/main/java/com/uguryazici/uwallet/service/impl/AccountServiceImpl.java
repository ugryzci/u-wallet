package com.uguryazici.uwallet.service.impl;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.exception.InsufficientBalanceException;
import com.uguryazici.uwallet.repository.AccountRepository;
import com.uguryazici.uwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteById(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public Account makeDebit(Account account, BigDecimal amount) {
        if (checkDebitPossible(account, amount)) {
            account.setBalance(account.getBalance().add(amount.negate()));
            accountRepository.save(account);
        }
        return account;
    }

    @Override
    public Account makeCredit(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account;
    }

    private boolean checkDebitPossible(Account account, BigDecimal amount) {
        if (account.getBalance().add(amount.negate()).compareTo(BigDecimal.ZERO) < 0)
            throw new InsufficientBalanceException();
        return true;
    }
}
