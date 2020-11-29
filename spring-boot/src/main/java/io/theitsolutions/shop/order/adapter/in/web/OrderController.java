package io.theitsolutions.shop.order.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.theitsolutions.shop.order.adapter.in.web.domain.CommitOrderRequest;
import io.theitsolutions.shop.order.adapter.in.web.domain.CommitOrderResponse;
import io.theitsolutions.shop.order.adapter.in.web.domain.StartPaymentRequest;
import io.theitsolutions.shop.order.adapter.in.web.domain.StartPaymentResponse;
import io.theitsolutions.shop.order.application.CommitOrderService;
import io.theitsolutions.shop.order.application.StartPaymentService;
import io.theitsolutions.shop.order.application.StartedOrder;
import io.theitsolutions.shop.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

    private final StartPaymentService startPaymentService;
    private final CommitOrderService commitOrderService;

    @PostMapping(path = "/orders/start-payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startPayment(@RequestBody @Valid StartPaymentRequest startPaymentRequest) throws JsonProcessingException {
        StartedOrder startedOrder = startPaymentService.startPayment(startPaymentRequest.getCartName(), startPaymentRequest.getPaymentMethod());
        return ResponseEntity.ok(new StartPaymentResponse(startedOrder.getPaymentUrl(), startedOrder.getOrderNumber()));
    }

    @PostMapping(path = "/orders/commit-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity commitOrder(@RequestBody @Valid CommitOrderRequest request) throws JsonProcessingException {
        Order order = commitOrderService.commitOrder(request.getOrderNumber());
        return ResponseEntity.ok(CommitOrderResponse.builder()
                .orderState(order.getOrderState().name())
                .paymentState(order.getLastPaymentInfo().getPaymentState().name())
                .build());
    }

}
