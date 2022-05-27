package com.kanesa.restapi.product_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// ! client model
public class outputProduct {
  private String name;
  private String desc;
  private int price;
}
