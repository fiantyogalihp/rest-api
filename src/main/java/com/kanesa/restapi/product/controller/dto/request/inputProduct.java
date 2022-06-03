package com.kanesa.restapi.product.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
// ! client model
public class inputProduct {
  @Pattern(regexp = "[^A-Za-z0-9]+$", message = "name must be alphanumeric")
  @NotBlank(message = "Product 'name' cannot be empty! and the characters must be at least 2")
  private String name;

  private String desc;

  @NotEmpty(message = "Product 'price' is required! and cannot be empty")
  private int price;
}
