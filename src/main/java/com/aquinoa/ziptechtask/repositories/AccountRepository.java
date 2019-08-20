package com.aquinoa.ziptechtask.repositories;

import org.springframework.data.repository.CrudRepository;
import com.aquinoa.ziptechtask.domains.AccountDao;

public interface AccountRepository extends CrudRepository<AccountDao, Long> {

}
