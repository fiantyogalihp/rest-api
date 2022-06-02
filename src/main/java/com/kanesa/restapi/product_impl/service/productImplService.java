// ! this service as a Service(the bussiness;logic) in a class(implementation)

package com.kanesa.restapi.product_impl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kanesa.restapi.product_api.dto.request.inputProduct;
import com.kanesa.restapi.product_api.dto.response.outputProduct;
import com.kanesa.restapi.product_api.service.productService;
import com.kanesa.restapi.product_impl.model.products;
import com.kanesa.restapi.product_impl.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// * if use a Constructor
/*
 * import lombok.Data;
 * 
 * @Data
 */

@Service
public class productImplService implements productService {

  // * Constructor
  /*
   * private final productRepository repository;
   * 
   * public productImplService(productRepository repository) { this.repository = repository; }
   */

  // * @AutoWired as a Contructor
  @Autowired
  productRepository product_repository;

  @Override
  public outputProduct addOne(inputProduct inputproduct) {

    // * convert the inputProduct's model to products's model
    products product = products.builder().name(inputproduct.getName())
        .price(inputproduct.getPrice()).desc(inputproduct.getDesc()).build();

    // * add to DB use method "save()"
    this.product_repository.save(product);

    // * return the reponse (outputProduct)
    return outputProduct.builder().id(product.getId()).name(product.getName())
        .desc(product.getDesc()).price(product.getPrice()).build();
  }

  @Override
  public outputProduct getOne(long id) {
    /*
     * * we want to find the id from products model(DB), so the type data is products 'cause the
     * return of "findById()" is Optional, so we need the temporary variable("resultTemp") needs to
     * accommondate the data of model
     */

    Optional<products> resultTemp = this.product_repository.findById(id);
    // * [Validation] if the id is not exist, return null
    if (resultTemp.isEmpty()) {
      System.out.println("The Data isn't exist");
      return null;
    }
    /*
     * * [Validation] if the id is exist,cause resultTemp is 'Optional' convert to 'products', to
     * make it sure and get the resultTemp Data
     */
    products result = resultTemp.get();

    // * [Mapping] convert the the model of 'result' to the model of 'outputProduct'
    // ? tips? use a builder for mapping to easier
    // * manually
    /*
     * outputProduct outputproduct = new outputProduct(); outputproduct.setId(result.getId());
     * outputproduct.setName(result.getName()); outputproduct.setDesc(result.getDesc());
     * outputproduct.setPrice(result.getPrice());
     */

    // * return the data of model 'output_product' with builder
    return outputProduct.builder().id(result.getId()).name(result.getName()).desc(result.getDesc())
        .price(result.getPrice()).build();
  }

  @Override
  public List<outputProduct> getAll() {


    List<outputProduct> result = new ArrayList<>();
    this.product_repository.findAll().forEach(p -> {
      result.add(outputProduct.builder().id(p.getId()).name(p.getName()).desc(p.getDesc())
          .price(p.getPrice()).build());
    });


    // List<outputProduct> result = outputProduct.builder()
    // this.product_repository.findAll();

    // if (result.isEmpty()) {
    // System.out.println("The Data isn't exist");
    // return null;
    // }


    // Iterable<products> resultTIterable = Arrays.asList(this.product_repository.findAll());
    // List<outputProduct> result = resultTIterable.forEach((p) ->
    // products.builder().id(p.getId()).name(p.getName()).desc(p.getDesc())
    // .price(p.getPrice()).build());
    // List<products> resultList = Streamable.of(resultTIterable).toList();
    // products result = resultTIterable.iterator().next();

    // List<products> resultList = (List<products>) resultTIterable;
    // return
    // outputProduct.builder().id(result.getId()).name(result.getName()).desc(result.getDesc())
    // .price(result.getPrice()).build();
    return result;
  }

  @Override
  public outputProduct updateOne(long id, inputProduct newItem) {

    Optional<products> resultTemp = this.product_repository.findById(id);

    if (resultTemp.isEmpty()) {
      System.out.println("The Data isn't exist");
      return null;
    }

    products result = resultTemp.map(oldItem -> {
      oldItem.setName(newItem.getName());
      oldItem.setDesc(newItem.getDesc());
      oldItem.setPrice(newItem.getPrice());
      return oldItem;
    }).orElseGet(() -> {
      return products.builder().name(newItem.getName()).desc(newItem.getDesc())
          .price(newItem.getPrice()).build();
    });

    this.product_repository.save(result);
    // (oldItem) -> {
    // products updated = oldItem.updateWith(newItem);
    // return repository.save(updated);
    // }
    return outputProduct.builder().id(result.getId()).name(result.getName()).desc(result.getDesc())
        .price(result.getPrice()).build();

  }

  @Override
  public boolean deleteOne(long id) {
    try {
      if (this.product_repository.existsById(id)) {
        this.product_repository.deleteById(id);
        return true;
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());

    }
    return false;
    // if (this.product_repository.existsById(id)) {
    // this.product_repository.deleteById(id);
    // return true;
    // } else {
    // System.out.println("The Data isn't exist");
    // return false;

  }

}
