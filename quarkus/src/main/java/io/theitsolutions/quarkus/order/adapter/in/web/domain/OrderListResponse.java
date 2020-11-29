package io.theitsolutions.quarkus.order.adapter.in.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse {

    private List<String> orderNumbers;


}
