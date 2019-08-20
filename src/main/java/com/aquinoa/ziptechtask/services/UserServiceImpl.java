package com.aquinoa.ziptechtask.services;

import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;
import com.aquinoa.ziptechtask.repositories.UserRepository;
import lombok.NonNull;

@Service
public class UserServiceImpl implements UserService {

  static final Logger l = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDao createUser(@NonNull UserDao user)
      throws InvalidFieldException, NullPointerException {
    // Validation
    if (user.getMonthlyExpenses() <= 0) {
      throw new InvalidFieldException("Monthly Expenses",
          "Monthly expense value must be positive.");
    }

    if (user.getMonthlySalary() <= 0) {
      throw new InvalidFieldException("Monthly Salary", "Monthly salary value must be positive.");
    }

    UserDao existing = userRepository.findOneByEmailAddress(user.getEmailAddress()).orElse(null);

    if (existing != null) {
      throw new InvalidFieldException("E-mail Address", "E-mail Address already in use.");
    }

    // Some default fields
    user.setCreatedOn(ZonedDateTime.now());
    return userRepository.save(user);
  }
}
