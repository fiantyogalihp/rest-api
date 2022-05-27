package com.kanesa.restapi.product_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// ! Database model
public class product {
  private long id;
  private String name;
  private String desc;
  private int price;


  // * constructor as a overloading
  // * this as a @NoArgsConstructor
  // public product(long id, String name, String desc, int price) {
  // this.id = id;
  // this.name = name;
  // this.desc = desc;
  // this.price = price;
  // }

  // * this as a @AllArgsConstructor
  // public product() {}
}
