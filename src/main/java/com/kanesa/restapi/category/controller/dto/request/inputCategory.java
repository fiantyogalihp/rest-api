package com.kanesa.restapi.category.controller.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
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
public class inputCategory {

  // ? regex: alphabet and space
  @Pattern(regexp = "[^A-Za-z0-9]+$", message = "alphabet and space only")
  @NotBlank(message = "Category 'name' must not be blank")
  @Column(name = "name")
  private String name;

}
