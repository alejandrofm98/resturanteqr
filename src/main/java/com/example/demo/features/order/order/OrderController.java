package com.example.demo.features.order.order;

import static com.example.demo.common.utils.Constants.CONSTANT_DELETE;
import static com.example.demo.common.utils.Constants.CONSTANT_GET;
import static com.example.demo.common.utils.Constants.CONSTANT_POST;
import static com.example.demo.common.utils.Constants.CONSTANT_PUT;
import static com.example.demo.common.utils.Constants.CONSTANT_SECURE_URL;

import com.example.demo.common.config.Log4j2Config;
import com.example.demo.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private static final String ORDER_TEXT = "/order";
  private final OrderService orderService;

  @GetMapping("/order/{bussinesUuid}/{id}")
  public ResponseEntity<ApiResponse> getOrder(@PathVariable String bussinesUuid,
      @PathVariable Long id) {
    OrderResponse order = orderService.getOrderByIdAndBusinessUuidDTO(id,bussinesUuid);
    Log4j2Config.logRequestInfo(CONSTANT_GET, CONSTANT_SECURE_URL + ORDER_TEXT,
        "Successfully consulted order",
        order.toString());
    ApiResponse apiResponse = ApiResponse.builder()
        .response(order)
        .build();
    return ResponseEntity.ok(apiResponse);
  }


  @PostMapping(value = "/order")
  public ResponseEntity<ApiResponse> createOrderAndOrderLine(
      @RequestBody OrderRequest orderRequest) {
    OrderResponse order = orderService.createOrder(orderRequest);
    Log4j2Config.logRequestInfo(CONSTANT_POST, CONSTANT_SECURE_URL + ORDER_TEXT,
        "Successfully inserted order",
        order.toString());
    ApiResponse apiResponse = ApiResponse.builder()
        .response(order)
        .build();
    return ResponseEntity.ok(apiResponse);
  }

  @PutMapping("/order")
  public ResponseEntity<ApiResponse> updateOrder(@RequestBody Order order) {
    OrderResponse result = orderService.saveOrder(order);
    Log4j2Config.logRequestInfo(CONSTANT_PUT, CONSTANT_SECURE_URL + ORDER_TEXT,
        "Successfully updated order",
        result.toString());
    ApiResponse apiResponse = ApiResponse.builder()
        .response(result)
        .build();
    return ResponseEntity.ok(apiResponse);
  }

  @DeleteMapping("/order/{id}")
  public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);
    Log4j2Config.logRequestInfo(CONSTANT_DELETE, CONSTANT_SECURE_URL + ORDER_TEXT,
        "Successfully deleted order with id " + id, null);
    return ResponseEntity.noContent().build();

  }


  @GetMapping("/orders/{bussinesUuid}")
  public ResponseEntity<ApiResponse> getOrdersByBusiness(@PathVariable String bussinesUuid) {
    ApiResponse apiResponse = ApiResponse.builder()
        .response(orderService.getOrdersByBusinessUuid(bussinesUuid))
        .build();

    return ResponseEntity.ok(apiResponse);
  }

}
