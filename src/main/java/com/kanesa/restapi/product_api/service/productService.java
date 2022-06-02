// ! this service as interface for product controller to product service (the bridge;interface) in a
// interface

package com.kanesa.restapi.product_api.service;

import com.kanesa.restapi.product_api.dto.request.inputProduct;
import com.kanesa.restapi.product_api.dto.response.outputProduct;
import java.util.List;


public interface productService {

  outputProduct addOne(inputProduct inputproduct);

  outputProduct getOne(long id);

  List<outputProduct> getAll();

  outputProduct updateOne(long id, inputProduct inputproduct);

  boolean deleteOne(long id);

}
