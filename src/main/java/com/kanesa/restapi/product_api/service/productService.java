// ! this service as interface for product controller to product service (the bridge;interface) in a
// interface

package com.kanesa.restapi.product_api.service;

import com.kanesa.restapi.product_api.dto.request.inputProduct;
import com.kanesa.restapi.product_api.dto.response.outputProduct;
import java.util.List;
import javax.validation.constraints.NotBlank;


public interface productService {

  @NotBlank(message = "product details is cannot be empty!")
  outputProduct addOne(inputProduct inputproduct);

  outputProduct getOne(long id);

  List<outputProduct> getAll();

  @NotBlank(message = "product details is cannot be empty!")
  outputProduct updateOne(long id, inputProduct inputproduct);

  boolean deleteOne(long id);

}
