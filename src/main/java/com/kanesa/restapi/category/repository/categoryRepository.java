package com.kanesa.restapi.category.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kanesa.restapi.category.repository.model.category;

@Repository
public interface categoryRepository extends CrudRepository<category, Long> {

}
