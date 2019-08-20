package com.aquinoa.ziptechtask.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;
import com.aquinoa.ziptechtask.domains.AccountDao;
import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.BusinessRuleException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;
import com.aquinoa.ziptechtask.repositories.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  static final Logger l = LoggerFactory.getLogger(AccountServiceTest.class);
  
  AccountServiceImpl service = new AccountServiceImpl();
  
  @Mock
  AccountRepository accountRepository;
  
  @Before
  public void setup() {
    ReflectionTestUtils.setField(service, "accountRepository", accountRepository);
  }
  
  @Test
  public void testSuccess() throws BusinessRuleException, InvalidFieldException {
    UserDao user = UserDao.builder()
        .monthlyExpenses(1000)
        .monthlySalary(3000)
        .build();
    AccountDao request = AccountDao.builder()
        .creditLimit(500)
        .user(user)
        .build();
    
    when(accountRepository.save(any(AccountDao.class)))
      .thenReturn(request.toBuilder().id(1L).build());
    
    AccountDao result = service.createAccount(request);
    assertEquals(1L, result.getId().longValue());
  }
  
  @Test(expected = BusinessRuleException.class)
  public void testNegativeCreditLimit() throws BusinessRuleException, InvalidFieldException {
    service.createAccount(AccountDao.builder().creditLimit(-1).build());
  }
  
  @Test(expected = BusinessRuleException.class)
  public void testAbove1kCreditLimit() throws BusinessRuleException, InvalidFieldException {
    service.createAccount(AccountDao.builder().creditLimit(1000.50).build());
  }
  
  @Test(expected = BusinessRuleException.class)
  public void testInsufficientMonthlyNet() throws BusinessRuleException, InvalidFieldException {
    UserDao user = UserDao.builder()
        .monthlyExpenses(5000)
        .monthlySalary(3000)
        .build();
    AccountDao request = AccountDao.builder()
        .creditLimit(500)
        .user(user)
        .build();
    
    service.createAccount(request);
  }
  
  @Test(expected = InvalidFieldException.class)
  public void testInvalidUser() throws BusinessRuleException, InvalidFieldException {
    AccountDao request = AccountDao.builder()
        .creditLimit(500)
        .build();
    
    service.createAccount(request);
  }
  
  @Test(expected = NullPointerException.class)
  public void testNullAccount() throws NullPointerException, BusinessRuleException, InvalidFieldException {
    service.createAccount(null);
  }
}
