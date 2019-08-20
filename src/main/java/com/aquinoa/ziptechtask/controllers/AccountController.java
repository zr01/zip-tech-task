package com.aquinoa.ziptechtask.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aquinoa.ziptechtask.controllers.models.Account;
import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.BusinessRuleException;
import com.aquinoa.ziptechtask.exceptions.InvalidDbRecordException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;
import com.aquinoa.ziptechtask.repositories.AccountRepository;
import com.aquinoa.ziptechtask.repositories.UserRepository;
import com.aquinoa.ziptechtask.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

  static final Logger l = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  AccountService accountService;

  @Autowired
  AccountRepository accountRepository;

  @PostMapping
  public Account postAccount(@RequestBody Account request)
      throws InvalidFieldException, BusinessRuleException {
    UserDao user = userRepository.findById(request.user.id)
        .orElseThrow(() -> new InvalidFieldException("User ID", "Invalid User ID."));
    return request.toModel(accountService.createAccount(request.toDb().setUser(user)));
  }

  @GetMapping("/{id}")
  public Account getAccount(@PathVariable Long id) throws InvalidDbRecordException {
    return Account.builder().build().toModel(accountRepository.findById(id)
        .orElseThrow(() -> new InvalidDbRecordException("Invalid Account ID")));
  }

  @GetMapping
  public List<Account> getAccountList() {
    return Account.builder().build().toModel(StreamSupport
        .stream(accountRepository.findAll().spliterator(), false).collect(Collectors.toList()));
  }
}
