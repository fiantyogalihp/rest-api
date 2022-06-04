package com.kanesa.restapi.product.controller.dto.response;

import com.kanesa.restapi.category.controller.dto.response.outputCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ! client model
public class outputProduct {
  private long id;
  private String name;
  private String desc;
  private Double price;
  private outputCategory outputCategory;
}
