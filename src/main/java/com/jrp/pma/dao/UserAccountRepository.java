package com.jrp.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.entities.UserAccount;

//N.B: PagingAndSortingRepository extends CrudRepository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{

}
