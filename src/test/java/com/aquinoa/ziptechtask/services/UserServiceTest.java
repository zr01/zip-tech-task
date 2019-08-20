package com.aquinoa.ziptechtask.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;
import com.aquinoa.ziptechtask.domains.UserDao;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;
import com.aquinoa.ziptechtask.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  static final Logger l = LoggerFactory.getLogger(UserServiceTest.class);
  
  UserServiceImpl service = new UserServiceImpl();
  
  @Mock
  UserRepository userRepository;
  
  @Before
  public void setup() {
    ReflectionTestUtils.setField(service, "userRepository", userRepository);
  }
  
  @Test
  public void testSuccess() throws InvalidFieldException {
    UserDao request = UserDao.builder()
        .emailAddress("test-email")
        .name("test-name")
        .monthlyExpenses(0)
        .monthlySalary(0)
        .build();
    
    when(userRepository.save(any(UserDao.class)))
      .thenReturn(request.toBuilder().id(1L).createdOn(ZonedDateTime.now()).build());
    
    UserDao result = service.createUser(request);
    assertEquals(1, result.getId().longValue());
    assertEquals("test-name", result.getName());
  }
  
  @Test(expected = InvalidFieldException.class)
  public void testInvalidMonthlyExpense() throws InvalidFieldException {
    service.createUser(UserDao.builder().monthlyExpenses(-1).build());
  }
  
  @Test(expected = InvalidFieldException.class)
  public void testInvalidMonthlySalary() throws InvalidFieldException {
    service.createUser(UserDao.builder().monthlyExpenses(1).monthlySalary(-1).build());
  }
  
  @Test(expected = InvalidFieldException.class)
  public void testDuplicateUser() throws InvalidFieldException {
    UserDao request = UserDao.builder()
        .emailAddress("test-email")
        .name("test-name")
        .monthlyExpenses(1)
        .monthlySalary(1)
        .build();

    when(userRepository.findOneByEmailAddress(anyString()))
      .thenReturn(Optional.of(request));
    
    service.createUser(request);
  }
  
  @Test(expected = NullPointerException.class)
  public void testNullUser() throws InvalidFieldException, NullPointerException {
    service.createUser(null);
  }
}
