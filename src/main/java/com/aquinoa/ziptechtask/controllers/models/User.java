package com.aquinoa.ziptechtask.controllers.models;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.aquinoa.ziptechtask.commons.RequestModel;
import com.aquinoa.ziptechtask.commons.ResponseModel;
import com.aquinoa.ziptechtask.domains.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements RequestModel<UserDao>, ResponseModel<UserDao, User> {

  public Long id;
  public String email;
  public String name;
  @JsonProperty("monthly_salary")
  public double monthlySalary;
  @JsonProperty("monthly_expenses")
  public double monthlyExpenses;

  @Override
  public UserDao toDb() {
    return UserDao.builder().emailAddress(email).name(name).monthlySalary(monthlySalary)
        .monthlyExpenses(monthlyExpenses).build();
  }

  @Override
  public User toModel(@NonNull UserDao dao) {
    return User.builder().email(dao.getEmailAddress()).name(dao.getName())
        .monthlyExpenses(dao.getMonthlyExpenses()).monthlySalary(dao.getMonthlySalary())
        .id(dao.getId()).build();
  }

  @Override
  public List<User> toModel(@NonNull List<UserDao> list) {
    if (list.size() == 0) {
      return Collections.<User>emptyList();
    }
    return list.stream()
        .map(dao -> toModel(dao))
        .collect(Collectors.toList());
  }

}
