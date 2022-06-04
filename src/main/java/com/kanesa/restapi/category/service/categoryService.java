package com.kanesa.restapi.category.service;

import java.util.List;
import com.kanesa.restapi.category.controller.dto.request.inputCategory;
import com.kanesa.restapi.category.controller.dto.response.outputCategory;

public interface categoryService {

  outputCategory addOne(inputCategory inputCategory) throws Exception;

  List<outputCategory> getAll();

  boolean deleteOne(long id) throws Exception;

}
