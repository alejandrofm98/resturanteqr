package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  Integer id;
  String name;
  String description;
  Float price;
  Integer category;
  Integer status;

}
