package com.uguryazici.uwallet.controller;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.exception.AccountNotFoundException;
import com.uguryazici.uwallet.exception.PlayerNotFoundException;
import com.uguryazici.uwallet.model.AccountDTO;
import com.uguryazici.uwallet.service.AccountService;
import com.uguryazici.uwallet.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@ControllerAdvice
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlayerService playerService;

    @PostMapping(path = "/addAccountToPlayer")
    public ResponseEntity<Account> addAccountToPlayer(@RequestBody AccountDTO accountDTO) {
        Account account = new Account();
        account.setName(accountDTO.getName());
        account.setBalance(accountDTO.getBalance());
        Optional<Player> player = playerService.findById(accountDTO.getPlayerId());

        player.orElseThrow(PlayerNotFoundException::new);

        account.setPlayer(player.get());
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Iterable<Account>> listPlayer() {
        Iterable<Account> all = accountService.findAll();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable long accountId) {
        Optional<Account> account = accountService.findById(accountId);

        account.orElseThrow(AccountNotFoundException::new);

        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{accountId}")
    public ResponseEntity deleteById(@PathVariable Long accountId) {
        try {
            accountService.deleteById(accountId);
        }
        catch (Exception e){
            throw new AccountNotFoundException();
        }

        return new ResponseEntity<>("Account Deleted.",HttpStatus.OK);
    }

    @PostMapping(path = "/debit")
    public ResponseEntity<Account> debit(@RequestParam("accountId") Long accountId, @RequestParam("amount") BigDecimal amount) {
        Optional<Account> account = accountService.findById(accountId);
        account.orElseThrow(AccountNotFoundException::new);
        accountService.makeDebit(account.get(), amount);
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }

    @PostMapping(path = "/credit")
    public ResponseEntity<Account> credit(@RequestParam("accountId") Long accountId, @RequestParam("amount") BigDecimal amount) {
        Optional<Account> account = accountService.findById(accountId);
        account.orElseThrow(AccountNotFoundException::new);
        accountService.makeCredit(account.get(), amount);
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }

}
