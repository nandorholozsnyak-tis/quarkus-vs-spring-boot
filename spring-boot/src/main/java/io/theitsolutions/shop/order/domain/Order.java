package io.theitsolutions.shop.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String orderNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @NotNull
    private LocalDateTime orderDate;

    @NotBlank
    private String customerFirstName;

    @NotBlank
    private String customerLastName;

    @NotBlank
    private String customerCountry;

    @NotBlank
    private String customerZip;

    @NotBlank
    private String customerCity;

    @NotBlank
    private String customerStreet;

    @NotBlank
    private String customerEmail;

    @NotBlank
    private String customerPhoneNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @NotNull
    private BigDecimal totalPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "order")
    private List<PaymentInfo> paymentInfos = new ArrayList<>();

    @NotNull
    private Long cartId;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null);
    }

    public void addPaymentInfo(PaymentInfo paymentInfo) {
        paymentInfos.add(paymentInfo);
        paymentInfo.setOrder(this);
    }

    public void removePaymentInfo(PaymentInfo paymentInfo) {
        paymentInfos.remove(paymentInfo);
        paymentInfo.setOrder(null);
    }

    public PaymentInfo getInitOrPendingPaymentInfo() {
        return paymentInfos.stream()
                .filter(paymentInfo -> paymentInfo.getPaymentState() == PaymentState.PAYMENT_INIT || paymentInfo.getPaymentState() == PaymentState.PAYMENT_PENDING)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No init or pending payment info exists for order"));
    }

    public PaymentInfo getLastPaymentInfo() {
        return paymentInfos.get(paymentInfos.size() - 1);
    }

    public void addOrderItems(List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> orderItem.setOrder(this));
        this.orderItems.addAll(orderItems);
    }
}
