// ! Database model !!
package com.kanesa.restapi.product_impl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class product {
  private long id;
  private String name;
  private String desc;
  private int price;


  // * constructor as a overloading
  // * this as a @AllArgsConstructor
  // public product(long id, String name, String desc, int price) {
  // this.id = id;
  // this.name = name;
  // this.desc = desc;
  // this.price = price;
  // }

  // * this as a @NoArgsConstructor
  // public product() {}
}
