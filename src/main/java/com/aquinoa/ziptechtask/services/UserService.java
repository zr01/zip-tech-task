package com.aquinoa.ziptechtask.services;

import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;

public interface UserService {

  public UserDao createUser(UserDao user) throws InvalidFieldException, NullPointerException;
}
