package com.kanesa.restapi.controller;

import java.util.ArrayList;
import java.util.List;
import com.kanesa.restapi.model.hello;
import com.kanesa.restapi.product_api.dto.response.outputProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class apiController {

  @GetMapping("/hello")
  public ResponseEntity<hello> getHello() {
    hello h = new hello();
    h.setName("bapak raka");

    return new ResponseEntity<hello>(new hello(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/products")
  // * 'cause product is many(list of product) we use List<product> type data
  public ResponseEntity<List<outputProduct>> getProducts() {

    // * add a new item
    List<outputProduct> products = new ArrayList<>();
    products.add(new outputProduct("sapu", "kondisi: new", 10000));
    products.add(new outputProduct("sulak wulu", "kondisi: second", 6000));

    return new ResponseEntity<List<outputProduct>>(products, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
