package com.kanesa.restapi.standard_response.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class baseResponse<T> {
  private int code;
  private String message;
  private boolean successOrNot;
  private T data;
}
