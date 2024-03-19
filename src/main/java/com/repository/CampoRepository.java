package com.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.model.Campo;

@Repository
@Scope("prototype")
public interface CampoRepository extends CrudRepository<Campo, Long>, PagingAndSortingRepository<Campo, Long>,
		JpaRepository<Campo, Long>, JpaSpecificationExecutor<Campo> {
	
	


}