package com.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.model.Tabla;



@Repository
@Scope("prototype")
public interface TablaRepository extends CrudRepository<Tabla, Long>, PagingAndSortingRepository<Tabla, Long>,
JpaRepository<Tabla, Long>, JpaSpecificationExecutor<Tabla> {
	
	



}
