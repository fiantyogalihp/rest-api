package com.kanesa.restapi.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.IterableUtils;
// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kanesa.restapi.category.controller.dto.request.inputCategory;
import com.kanesa.restapi.category.controller.dto.response.outputCategory;
import com.kanesa.restapi.category.repository.categoryRepository;
import com.kanesa.restapi.category.repository.model.category;

@Service
public class categoryServiceImpl implements categoryService {

  @Autowired
  private categoryRepository category_repository;

  // ! [Issue] org.modelmapper.ModelMapper no Bean found; consider defining a bean of type
  // 'org.modelmapper.ModelMapper' in your configuration
  // @Autowired
  // private ModelMapper mapper;

  @Override
  public outputCategory addOne(inputCategory inputCategory) throws Exception {
    category Category = category.builder().name(inputCategory.getName()).build();

    if (Category.getName().isEmpty()) {
      System.out.println("name is empty!");
      throw new NullPointerException("name cannot be empty!");
    } else {
      this.category_repository.save(Category);

      return outputCategory.builder().id(Category.getId()).name(Category.getName()).build();
    }

  }

  @Override
  public List<outputCategory> getAll() {

    Iterable<category> categories = this.category_repository.findAll();

    // ? Convert Iterable<category> to List<category> (no Library)
    // List<category> categoryList = new ArrayList<>();
    // for (category Category : categories) {
    // categoryList.add(Category);
    // }

    List<category> categoryList = IterableUtils.toList(categories);

    List<outputCategory> resultCategories = new ArrayList<>();
    categoryList.forEach(category -> {
      resultCategories
          .add(outputCategory.builder().id(category.getId()).name(category.getName()).build());
    });
    return resultCategories;
  }

  @Override
  public boolean deleteOne(long id) throws Exception {
    Optional<category> resultTemp = this.category_repository.findById(id);

    if (resultTemp.isEmpty()) {
      System.out.println("The Data isn't exist!");
      throw new NullPointerException("Data is null!");
    } else {
      category result = resultTemp.get();

      this.category_repository.delete(result);
      return true;
    }
  }
}
