package com.kanesa.restapi.category.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kanesa.restapi.category.controller.dto.request.inputCategory;
import com.kanesa.restapi.category.controller.dto.response.outputCategory;
import com.kanesa.restapi.category.service.categoryService;
import com.kanesa.restapi.standard_response.controller.baseController;
import com.kanesa.restapi.standard_response.model.baseResponse;

@RestController
@RequestMapping("/api")
public class categoryController extends baseController {

  @Autowired
  private categoryService category_service;

  @Autowired
  private baseController baseController;

  @GetMapping("/categories")
  public ResponseEntity<baseResponse<List<outputCategory>>> getAllCategory() {
    this.category_service.getAll();

    baseResponse<List<outputCategory>> response = new baseResponse<>();
    try {
      if (this.category_service.getAll() != null) {
        response.setCode(HttpStatus.OK.value());
        response.setSuccessOrNot(true);
        response.setMessage("Success!");
        List<outputCategory> categories = this.category_service.getAll();
        response.setData(categories);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("Data not found!");
      } else {
        this.baseController.handleExceptionError(e);
      }
    }

    return new ResponseEntity<baseResponse<List<outputCategory>>>(response, HttpStatus.OK);
  }

  @GetMapping("/categories/add")
  public ResponseEntity<baseResponse<outputCategory>> addCategory(
      @Valid @RequestBody inputCategory inputcategory) throws Exception {

    baseResponse<outputCategory> response = new baseResponse<>();
    try {
      if (inputcategory != null) {
        response.setCode(HttpStatus.OK.value());
        response.setSuccessOrNot(true);
        response.setMessage("Success!");
        outputCategory category = this.category_service.addOne(inputcategory);
        response.setData(category);
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        throw new NullPointerException("Data not found!");
      } else {
        this.baseController.handleExceptionError(e);
      }
    }

    return new ResponseEntity<baseResponse<outputCategory>>(response, HttpStatus.OK);
  }
}
