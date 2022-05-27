package com.kanesa.restapi.controller;

import com.kanesa.restapi.model.hello;
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
}
