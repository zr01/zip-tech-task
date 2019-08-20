package com.aquinoa.ziptechtask.services;

import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;

public interface UserService {

  /**
   * A user may be created if the e-mail does not exist yet.<br/>
   * The user.monthlySalary and user.monthlyExpenses may not be a negative number.
   * 
   * @param user
   * @return
   * @throws InvalidFieldException - If monthly salary/expense is a negative number, or e-mail is
   *         already in use
   * @throws NullPointerException - If the dao in the parameter is null
   */
  public UserDao createUser(UserDao user) throws InvalidFieldException, NullPointerException;
}
