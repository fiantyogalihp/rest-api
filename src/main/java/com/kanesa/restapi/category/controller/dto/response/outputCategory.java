package com.kanesa.restapi.category.controller.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
// ! client model
public class outputCategory {
  private long id;
  private String name;
}
