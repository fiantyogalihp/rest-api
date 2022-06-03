package com.kanesa.restapi.product.repository;

import org.springframework.data.repository.CrudRepository;
import com.kanesa.restapi.product.repository.model.products;

public interface productRepository extends CrudRepository<products, Long> {


}
