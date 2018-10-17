package com.uguryazici.uwallet.service;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.repository.AccountRepository;
import com.uguryazici.uwallet.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceTest {
    @TestConfiguration
    static class AccountServiceImplTestConf {
        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void makeCreditTest() {
        Account account = new Account();
        account.setName("ugur");
        account.setBalance(new BigDecimal(23));

        when(accountService.makeCredit(account, new BigDecimal(43))).thenReturn(account);

        Account account1 = accountService.makeCredit(account, new BigDecimal(43));
        assertEquals(account.getBalance(), account1.getBalance());
    }

    @Test
    public void makeDebidTest() {
        Account account = new Account();
        account.setName("ugur");
        account.setBalance(new BigDecimal(67));

        when(accountService.makeDebit(account, new BigDecimal(43))).thenReturn(account);

        Account account1 = accountService.makeCredit(account, new BigDecimal(43));
        assertEquals(account.getBalance(), account1.getBalance());
    }
}
