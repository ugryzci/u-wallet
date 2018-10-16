package com.uguryazici.uwallet.repository;

import com.uguryazici.uwallet.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
