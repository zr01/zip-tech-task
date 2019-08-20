package com.aquinoa.ziptechtask.services;

import com.aquinoa.ziptechtask.domains.AccountDao;
import com.aquinoa.ziptechtask.exceptions.BusinessRuleException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;

public interface AccountService {

  /**
   * Create an account for a specified user.<br/>
   * The account can only be created if account.user.monthlySalary - account.user.monthlyExpenses
   * are < 1000<br/>
   * The account.creditLimit can only be up to 1000.<br/>
   * 
   * @param account
   * @return
   * @throws BusinessRuleException - when monthly net amount is not met
   * @throws InvalidFieldException - when the user does not exist
   * @throws NullPointerException - when the dao is null
   */
  public AccountDao createAccount(AccountDao account)
      throws BusinessRuleException, InvalidFieldException, NullPointerException;
}
