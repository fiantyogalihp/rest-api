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
import org.springframework.stereotype.Service;
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
@Service
public class productController extends baseController {
  // ? extends baseController to catch the Exception

  @Autowired
  private productService service;

  @Autowired
  private baseController baseController;

  // @Autowired
  // private ModelMapper modelMapper;

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
  public ResponseEntity<baseResponse<outputProduct>> getDetailProduct(@PathVariable long id)
      throws Exception {

    // * Base Response
    baseResponse<outputProduct> response = new baseResponse<>();
    try {
      if (id != 0) {
        response.setCode(HttpStatus.OK.value());
        response.setSuccessOrNot(true);
        response.setMessage("Success");
        outputProduct product = this.service.getOne(id);
        response.setData(product);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("Data not found");
      } else {
        this.baseController.handleExceptionError(e);
      }
    }
    return new ResponseEntity<baseResponse<outputProduct>>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/products/add")
  public ResponseEntity<baseResponse<outputProduct>> addProduct(
      @Valid @RequestBody inputProduct inputproduct) throws Exception {

    baseResponse<outputProduct> response = new baseResponse<>();
    try {
      if (inputproduct.getName().isEmpty() && inputproduct.getPrice() == 0) {
        response.setCode(HttpStatus.OK.value());
        response.setSuccessOrNot(true);
        response.setMessage("Success");
        outputProduct product = this.service.addOne(inputproduct);
        response.setData(product);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("name / price cannot be empty");
      } else {
        this.baseController.handleExceptionError(e);
      }
    }
    return new ResponseEntity<baseResponse<outputProduct>>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/products")
  public ResponseEntity<baseResponse<List<outputProduct>>> getAllProduct() throws Exception {

    baseResponse<List<outputProduct>> response = new baseResponse<>();
    try {
      if (this.service.getAll() != null) {
        response.setCode(HttpStatus.OK.value());
        response.setSuccessOrNot(true);
        response.setMessage("Success");
        List<outputProduct> products = this.service.getAll();
        response.setData(products);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("Data not found");
      } else {
        this.baseController.handleExceptionError(e);
      }
    }
    return new ResponseEntity<baseResponse<List<outputProduct>>>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "/products/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable long id) throws Exception {
    try {
      if (id != 0 && this.service.getOne(id) != null) {
        this.service.deleteOne(id);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("Data not found");
      } else {
        this.baseController.handleExceptionError(e);
      }
      return new ResponseEntity<String>("Failed", HttpStatus.NOT_FOUND);
    }
    return null;
  }

  @PatchMapping(value = "/products/{id}", consumes = "application/json")
  public ResponseEntity<baseResponse<outputProduct>> updateProduct(@Valid @PathVariable long id,
      @RequestBody inputProduct inputproduct) throws Exception {

    baseResponse<outputProduct> response = new baseResponse<>();
    try {
      if (id != 0 && inputproduct != null) {
        response.setCode(HttpStatus.OK.value());
        response.setSuccessOrNot(true);
        response.setMessage("Success");
        outputProduct product = this.service.updateOne(id, inputproduct);
        response.setData(product);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("update cannot be blank");
      } else {
        this.baseController.handleExceptionError(e);
      }
    }

    return new ResponseEntity<baseResponse<outputProduct>>(response, HttpStatus.OK);
  }

}
