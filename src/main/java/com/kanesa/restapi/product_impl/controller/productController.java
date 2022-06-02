package com.kanesa.restapi.product_impl.controller;

import java.util.ArrayList;
import java.util.List;

import com.kanesa.restapi.product_api.dto.request.inputProduct;
import com.kanesa.restapi.product_api.dto.response.outputProduct;
import com.kanesa.restapi.product_api.service.productService;

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
public class productController {

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
  public ResponseEntity<outputProduct> getDetailProduct(@PathVariable long id) {
    outputProduct product = service.getOne(id);

    return new ResponseEntity<outputProduct>(product, HttpStatus.OK);
  }

  @PostMapping(value = "/products/add")
  public ResponseEntity<outputProduct> addProduct(@RequestBody inputProduct inputproduct) {
    outputProduct product = service.addOne(inputproduct);

    return new ResponseEntity<outputProduct>(product, HttpStatus.OK);
  }


  @GetMapping(value = "/products")
  public ResponseEntity<List<outputProduct>> getAllProduct() {
    List<outputProduct> products = service.getAll();

    return new ResponseEntity<List<outputProduct>>(products, HttpStatus.OK);
  }

  @DeleteMapping(value = "/products/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable long id) {
    service.deleteOne(id);

    return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
  }

  @PatchMapping(value = "/products/{id}", consumes = "application/json")
  public ResponseEntity<outputProduct> updateProduct(@PathVariable long id,
      @RequestBody inputProduct inputproduct) {
    outputProduct product = service.updateOne(id, inputproduct);

    return new ResponseEntity<outputProduct>(product, HttpStatus.OK);
  }

}
