// ! this service as a Service(the bussiness;logic) in a class(implementation)

package com.kanesa.restapi.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.IterableUtils;
// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanesa.restapi.product.controller.dto.request.inputProduct;
import com.kanesa.restapi.product.controller.dto.response.outputProduct;
import com.kanesa.restapi.product.repository.productRepository;
import com.kanesa.restapi.product.repository.model.products;

// * if use a Constructor
/*
 * import lombok.Data;
 * 
 * @Data
 */

@Service
public class productServiceImpl implements productService {

  // * Constructor
  /*
   * private final productRepository repository;
   * 
   * public productImplService(productRepository repository) { this.repository = repository; }
   */

  // * @AutoWired as a Contructor
  @Autowired
  private productRepository product_repository;

  /*
   * ! ! [Error] Org.ModelMapper no Bean found; consider defining a bean of type !
   * 'org.modelmapper.ModelMapper' in your configuration
   */
  // @Autowired
  // private ModelMapper modelMapper;


  @Override
  public outputProduct addOne(inputProduct inputproduct) throws Exception {

    // * convert the inputProduct's model to products's model
    // products product = this.modelMapper.map(inputproduct, products.class);
    products product = products.builder().name(inputproduct.getName())
        .price(inputproduct.getPrice()).desc(inputproduct.getDesc()).build();

    if (product.getName().isEmpty() && product.getPrice() == 0) {
      System.out.println("name and price is empty!");
      throw new NullPointerException("name and price is null!");
    } else {
      // * add to DB use method "save()"
      this.product_repository.save(product);

      // * convert the products's model to outputProduct's model
      // * return the reponse (outputProduct)
      // return this.modelMapper.map(product, outputProduct.class);
      return outputProduct.builder().id(product.getId()).name(product.getName())
          .desc(product.getDesc()).price(product.getPrice()).build();
    }
  }

  @Override
  public outputProduct getOne(long id) throws Exception {
    /*
     * * we want to find the id from products model(DB), so the type data is products 'cause the
     * return of "findById()" is Optional, so we need the temporary variable("resultTemp") needs to
     * accommondate the data of model
     */

    Optional<products> resultTemp = this.product_repository.findById(id);
    // * [Validation] if the id is not exist, return null
    if (resultTemp.isEmpty()) {
      System.out.println("The Data isn't exist!");
      throw new NullPointerException("Data is null!");
    } else {
      /*
       * * [Validation] if the id is exist,cause resultTemp is 'Optional' convert to 'products', to
       * make it sure and get the resultTemp Data
       */
      products result = resultTemp.get();

      // * [Mapping] convert the the model of 'result' to the model of 'outputProduct'
      // ? tips? use a modelMapper, or builder for mapping to easier
      // * manually
      /*
       * outputProduct outputproduct = new outputProduct(); outputproduct.setId(result.getId());
       * outputproduct.setName(result.getName()); outputproduct.setDesc(result.getDesc());
       * outputproduct.setPrice(result.getPrice());
       */

      // * return the data of model 'output_product' with builder
      // return this.modelMapper.map(result, outputProduct.class);
      return outputProduct.builder().id(result.getId()).name(result.getName())
          .desc(result.getDesc()).price(result.getPrice()).build();
    }
  }

  @Override
  public List<outputProduct> getAll() {
    Iterable<products> products = this.product_repository.findAll();

    List<products> productList = IterableUtils.toList(products);

    List<outputProduct> result = new ArrayList<>();
    productList.forEach(p -> {
      // result.add(this.modelMapper.map(p, outputProduct.class));
      result.add(outputProduct.builder().id(p.getId()).name(p.getName()).desc(p.getDesc())
          .price(p.getPrice()).build());
    });

    return result;
  }

  @Override
  public outputProduct updateOne(long id, inputProduct newItem) {

    Optional<products> resultTemp = this.product_repository.findById(id);

    if (resultTemp.isEmpty()) {
      System.out.println("The Data isn't exist!");
      throw new NullPointerException("Data is null!");
    } else {
      products result = resultTemp.map(oldItem -> {
        oldItem.setName(newItem.getName());
        oldItem.setDesc(newItem.getDesc());
        oldItem.setPrice(newItem.getPrice());
        return oldItem;
      }).orElseGet(() -> {
        // return this.modelMapper.map(newItem, products.class);
        return products.builder().name(newItem.getName()).desc(newItem.getDesc())
            .price(newItem.getPrice()).build();
      });

      this.product_repository.save(result);
      // (oldItem) -> {
      // products updated = oldItem.updateWith(newItem);
      // return repository.save(updated);
      // }
      // return this.modelMapper.map(result, outputProduct.class);
      return outputProduct.builder().id(result.getId()).name(result.getName())
          .desc(result.getDesc()).price(result.getPrice()).build();
    }
  }

  @Override
  public boolean deleteOne(long id) throws Exception {
    // try {
    // if (this.product_repository.existsById(id)) {
    // this.product_repository.deleteById(id);
    // return true;
    // }

    // } catch (Exception e) {
    // System.out.println(e.getMessage());

    // }
    // return false;
    Optional<products> resultTemp = this.product_repository.findById(id);

    if (resultTemp.isEmpty()) {
      System.out.println("The Data isn't exist!");
      throw new NullPointerException("Data is null!");
    } else {
      products result = resultTemp.get();

      this.product_repository.delete(result);
      return true;
    }
    // if (this.product_repository.existsById(id)) {
    // this.product_repository.deleteById(id);
    // return true;
    // } else {
    // System.out.println("The Data isn't exist");
    // return false;
    // }
  }
}
