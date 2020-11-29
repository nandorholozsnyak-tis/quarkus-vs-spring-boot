package io.theitsolutions.quarkus.cart.adapter.in.web.domain.contactdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsRequest {

    @Valid
    private ContactDetailsRequestModel contactDetails;

}
