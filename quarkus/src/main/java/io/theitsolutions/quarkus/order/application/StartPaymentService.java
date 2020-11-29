package io.theitsolutions.quarkus.order.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.theitsolutions.quarkus.cart.application.checkoutdata.ContactDetailsStepCommand;
import io.theitsolutions.quarkus.cart.domain.Cart;
import io.theitsolutions.quarkus.cart.domain.CartCheckoutData;
import io.theitsolutions.quarkus.cart.domain.CartItem;
import io.theitsolutions.quarkus.cart.port.in.ManageCartCheckoutDataPort;
import io.theitsolutions.quarkus.cart.port.out.CartManagementPort;
import io.theitsolutions.quarkus.order.domain.Order;
import io.theitsolutions.quarkus.order.domain.OrderItem;
import io.theitsolutions.quarkus.order.domain.OrderState;
import io.theitsolutions.quarkus.order.domain.PaymentInfo;
import io.theitsolutions.quarkus.order.port.OrderManagementPort;
import io.theitsolutions.quarkus.order.port.PaymentPort;
import io.theitsolutions.quarkus.product.application.ProductService;
import io.theitsolutions.quarkus.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class StartPaymentService {

    static final String CONTACT_DETAILS = "contact-details";
    private final ProductService productService;
    private final ManageCartCheckoutDataPort manageCartCheckoutDataPort;
    private final ObjectMapper objectMapper;
    private final OrderManagementPort orderManagementPort;
    private final CartManagementPort cartManagementPort;
    private final PaymentPort paymentPort;

    @Transactional
    public StartedOrder startPayment(String cartName, String paymentMethod) throws JsonProcessingException {
        log.info("Starting payment with cart name:[{}] and payment method:[{}]", cartName, paymentMethod);
        Cart cart = cartManagementPort.getCartByName(cartName);
        CartCheckoutData cartAndCheckoutStep = manageCartCheckoutDataPort.findByCartAndCheckoutStep(cart, CONTACT_DETAILS);
        ContactDetailsStepCommand contactDetailsStepCommand = objectMapper.readValue(cartAndCheckoutStep.getCheckoutData(), ContactDetailsStepCommand.class);
        Order order = createOrder(cart, contactDetailsStepCommand);
        String paymentId = UUID.randomUUID().toString();
        String startPaymentUrl = paymentPort.startPayment(order, paymentId, paymentMethod);
        initPaymentInfoForOrder(order, paymentId);
        orderManagementPort.save(order);
        return new StartedOrder(startPaymentUrl, order.getOrderNumber());
    }

    private void initPaymentInfoForOrder(Order order, String paymentId) {
        PaymentInfo paymentInfo = PaymentInfo.paymentInit(order);
        paymentInfo.setPaymentReference(paymentId);
        order.addPaymentInfo(paymentInfo);
    }

    private Order createOrder(Cart cart, ContactDetailsStepCommand contactDetailsStepCommand) {
        List<OrderItem> orderItems = convertCartItemsToOrderItems(cart.getItems());
        BigDecimal totalPrice = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = Order.builder()
                .cartId(cart.getId())
                .customerFirstName(contactDetailsStepCommand.getFirstName())
                .customerLastName(contactDetailsStepCommand.getLastName())
                .customerEmail(contactDetailsStepCommand.getEmail())
                .customerCountry(contactDetailsStepCommand.getCountry())
                .customerCity(contactDetailsStepCommand.getCity())
                .customerStreet(contactDetailsStepCommand.getStreet())
                .customerZip(contactDetailsStepCommand.getZip())
                .customerPhoneNumber(contactDetailsStepCommand.getPhoneNumber())
                .orderNumber(UUID.randomUUID().toString())
                .orderItems(new LinkedList<>())
                .paymentInfos(new LinkedList<>())
                .totalPrice(totalPrice)
                .orderDate(LocalDateTime.now())
                .orderState(OrderState.INIT)
                .build();
        order.addOrderItems(orderItems);
        return order;
    }

    private List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

    private OrderItem mapToOrderItem(CartItem cartItem) {
        Product product = productService.getById(cartItem.getProductId());
        return OrderItem.builder()
                .productId(cartItem.getProductId())
                .productName(product.getName())
                .quantity(cartItem.getQuantity())
                .unitPrice(product.getPrice())
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .build();
    }

}
