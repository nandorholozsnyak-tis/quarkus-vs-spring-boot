package io.theitsolutions.quarkus.order.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.theitsolutions.quarkus.order.adapter.in.web.domain.CommitOrderRequest;
import io.theitsolutions.quarkus.order.adapter.in.web.domain.CommitOrderResponse;
import io.theitsolutions.quarkus.order.adapter.in.web.domain.StartPaymentRequest;
import io.theitsolutions.quarkus.order.adapter.in.web.domain.StartPaymentResponse;
import io.theitsolutions.quarkus.order.application.CommitOrderService;
import io.theitsolutions.quarkus.order.application.StartPaymentService;
import io.theitsolutions.quarkus.order.application.StartedOrder;
import io.theitsolutions.quarkus.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Path("/api/v1")
public class OrderController {

    private final StartPaymentService startPaymentService;
    private final CommitOrderService commitOrderService;

    @POST
    @Path("/orders/start-payment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startPayment(@Valid StartPaymentRequest startPaymentRequest) throws JsonProcessingException {
        StartedOrder startedOrder = startPaymentService.startPayment(startPaymentRequest.getCartName(), startPaymentRequest.getPaymentMethod());
        return Response.ok(new StartPaymentResponse(startedOrder.getPaymentUrl(), startedOrder.getOrderNumber())).build();
    }

    @POST
    @Path("/orders/commit-order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response commitOrder(@Valid CommitOrderRequest request) throws JsonProcessingException {
        Order order = commitOrderService.commitOrder(request.getOrderNumber());
        return Response.ok(CommitOrderResponse.builder()
                .orderState(order.getOrderState().name())
                .paymentState(order.getLastPaymentInfo().getPaymentState().name())
                .build())
                .build();
    }

}
