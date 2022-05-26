package com.kanesa.restapi.controller;

import com.kanesa.restapi.model.hello;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class helloController {

  @GetMapping
  public ResponseEntity<hello> getHello() {
    return new ResponseEntity<hello>(new hello(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
