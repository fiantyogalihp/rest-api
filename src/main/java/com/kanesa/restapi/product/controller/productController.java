package com.kanesa.restapi.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import com.kanesa.restapi.product.controller.dto.request.inputProduct;
import com.kanesa.restapi.product.controller.dto.response.outputProduct;
import com.kanesa.restapi.product.service.productService;
import com.kanesa.restapi.standard_response.controller.baseController;
import com.kanesa.restapi.standard_response.model.baseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class productController extends baseController {
  // ? extends baseController to catch the Exception

  @Autowired
  productService service;

  @GetMapping(value = "/product_example")
  // * 'cause product is many(list of product) we use List<product> type data
  public ResponseEntity<List<outputProduct>> getProducts() {

    // * add a new item
    List<outputProduct> products = new ArrayList<>();
    products.add(new outputProduct(1, "sapu", "kondisi: new", 10000));
    products.add(new outputProduct(2, "sulak wulu", "kondisi: second", 6000));

    return new ResponseEntity<List<outputProduct>>(products, HttpStatus.OK);
  }

  @GetMapping(value = "/products/{id}")
  public ResponseEntity<baseResponse<outputProduct>> getDetailProduct(@PathVariable long id) {
    outputProduct product = service.getOne(id);

    // * Base Response
    baseResponse<outputProduct> response = new baseResponse<>();
    if (HttpStatus.OK.value() == 200) {
      response.setSuccessOrNot(true);
      response.setMessage("Success");
      response.setData(product);
    }

    return new ResponseEntity<baseResponse<outputProduct>>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/products/add")
  public ResponseEntity<baseResponse<outputProduct>> addProduct(
      @Valid @RequestBody inputProduct inputproduct) {
    outputProduct product = service.addOne(inputproduct);

    baseResponse<outputProduct> response = new baseResponse<>();
    if (HttpStatus.OK.value() == 200) {
      response.setSuccessOrNot(true);
      response.setMessage("Success");
      response.setData(product);
    }

    return new ResponseEntity<baseResponse<outputProduct>>(response, HttpStatus.OK);
  }


  @GetMapping(value = "/products")
  public ResponseEntity<baseResponse<List<outputProduct>>> getAllProduct() {
    List<outputProduct> products = service.getAll();

    baseResponse<List<outputProduct>> response = new baseResponse<>();
    if (HttpStatus.OK.value() == 200) {
      response.setSuccessOrNot(true);
      response.setMessage("Success");
      response.setData(products);
    }

    return new ResponseEntity<baseResponse<List<outputProduct>>>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "/products/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable long id) {
    service.deleteOne(id);

    return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
  }

  @PatchMapping(value = "/products/{id}", consumes = "application/json")
  public ResponseEntity<baseResponse<outputProduct>> updateProduct(@Valid @PathVariable long id,
      @RequestBody inputProduct inputproduct) {
    outputProduct product = service.updateOne(id, inputproduct);

    baseResponse<outputProduct> response = new baseResponse<>();
    if (HttpStatus.OK.value() == 200) {
      response.setSuccessOrNot(true);
      response.setMessage("Success");
      response.setData(product);
    }
    return new ResponseEntity<baseResponse<outputProduct>>(response, HttpStatus.OK);
  }

}
