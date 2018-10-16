package com.uguryazici.uwallet.repository;

import com.uguryazici.uwallet.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {


}
