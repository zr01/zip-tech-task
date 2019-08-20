package com.aquinoa.ziptechtask.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.aquinoa.ziptechtask.domains.UserDao;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {

  public Optional<UserDao> findOneByEmailAddress(String emailAddress);
}
