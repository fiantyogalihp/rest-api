package com.kanesa.restapi.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kanesa.restapi.product.repository.model.products;

@Repository
public interface productRepository extends CrudRepository<products, Long> {

}
