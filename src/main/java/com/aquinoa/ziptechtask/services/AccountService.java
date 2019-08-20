package com.aquinoa.ziptechtask.services;

import com.aquinoa.ziptechtask.domains.AccountDao;
import com.aquinoa.ziptechtask.exceptions.BusinessRuleException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;

public interface AccountService {

  public AccountDao createAccount(AccountDao account) throws BusinessRuleException, InvalidFieldException;
}
