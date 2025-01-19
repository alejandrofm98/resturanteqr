package com.example.demo.services;

import com.example.demo.dto.request.OrderLineRequest;
import com.example.demo.entity.OrderLineIngredient;
import java.util.List;

public interface OrderLineIngredientService {

  List<OrderLineIngredient> createOrderLineIngredients(OrderLineRequest orderLineRequest);
}
