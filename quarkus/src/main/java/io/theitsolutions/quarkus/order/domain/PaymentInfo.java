package io.theitsolutions.quarkus.order.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_info")
public class PaymentInfo {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @NotNull
    private LocalDateTime paymentStart;

    private LocalDateTime paymentFinish;

    @NotBlank
    private String paymentReference;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    @NotBlank
    private String internalPaymentId;

    private String message;

    private String providerData;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentInfo)) return false;
        return id != null && id.equals(((PanacheEntity) o).id);
    }

    public int hashCode() {
        return 31;
    }

    public static PaymentInfo paymentInit(Order order) {
        return PaymentInfo.builder()
                .order(order)
                .paymentState(PaymentState.PAYMENT_INIT)
                .paymentStart(LocalDateTime.now())
                .internalPaymentId(UUID.randomUUID().toString())
                .build();
    }

    public static PaymentInfo ofPaymentInfo(PaymentInfo paymentInfo) {
        return PaymentInfo.builder()
                .order(paymentInfo.getOrder())
                .internalPaymentId(paymentInfo.getInternalPaymentId())
                .paymentReference(paymentInfo.getPaymentReference())
                .paymentStart(paymentInfo.getPaymentStart())
                .build();
    }

}
