package com.kanesa.restapi.product_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
// ! client model
public class outputProduct {
  private long id;
  private String name;
  private String desc;
  private int price;
}
