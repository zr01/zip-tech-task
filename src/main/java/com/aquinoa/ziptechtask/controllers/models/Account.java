package com.aquinoa.ziptechtask.controllers.models;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.aquinoa.ziptechtask.commons.RequestModel;
import com.aquinoa.ziptechtask.commons.ResponseModel;
import com.aquinoa.ziptechtask.domains.AccountDao;
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
public class Account implements RequestModel<AccountDao>, ResponseModel<AccountDao, Account> {

  public Long id;
  public User user;
  @JsonProperty("credit_limit")
  public double creditLimit;

  @Override
  public Account toModel(@NonNull AccountDao dao) {
    return Account.builder().user(User.builder().build().toModel(dao.getUser()))
        .creditLimit(dao.getCreditLimit()).id(dao.getId()).build();
  }

  @Override
  public List<Account> toModel(@NonNull List<AccountDao> list) {
    if (list.size() == 0) {
      return Collections.<Account>emptyList();
    }
    return list.stream().map(dao -> toModel(dao)).collect(Collectors.toList());
  }

  @Override
  public AccountDao toDb() {
    return AccountDao.builder().creditLimit(creditLimit).build();
  }

}
