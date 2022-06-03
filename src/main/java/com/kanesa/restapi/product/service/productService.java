// ! this service as interface for product controller to product service (the bridge;interface) in a
// interface

package com.kanesa.restapi.product.service;

import java.util.List;
import javax.validation.constraints.NotBlank;
import com.kanesa.restapi.product.controller.dto.request.inputProduct;
import com.kanesa.restapi.product.controller.dto.response.outputProduct;


public interface productService {

  @NotBlank(message = "product details is cannot be empty!")
  outputProduct addOne(inputProduct inputproduct);

  outputProduct getOne(long id);

  List<outputProduct> getAll();

  @NotBlank(message = "product details is cannot be empty!")
  outputProduct updateOne(long id, inputProduct inputproduct);

  boolean deleteOne(long id);

}
