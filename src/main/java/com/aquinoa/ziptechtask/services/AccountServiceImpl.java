package com.aquinoa.ziptechtask.services;

import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aquinoa.ziptechtask.domains.AccountDao;
import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.BusinessRuleException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;
import com.aquinoa.ziptechtask.repositories.AccountRepository;
import lombok.NonNull;

@Service
public class AccountServiceImpl implements AccountService {

  static final Logger l = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Autowired
  AccountRepository accountRepository;

  @Override
  public AccountDao createAccount(@NonNull AccountDao account)
      throws BusinessRuleException, InvalidFieldException {
    if (account.getCreditLimit() <= 0 || account.getCreditLimit() > 1000) {
      throw new BusinessRuleException("Invalid credit limit being applied.");
    }

    UserDao user = account.getUser();
    if (user == null) {
      throw new InvalidFieldException("User ID", "Invalid user tied to account.");
    }

    if (user.getMonthlySalary() - user.getMonthlyExpenses() < 1000) {
      throw new BusinessRuleException("Insufficient net monthly allowance.");
    }

    // Default variables
    account.setCreatedBy(user);
    account.setCreatedOn(ZonedDateTime.now());
    return accountRepository.save(account);
  }

}
