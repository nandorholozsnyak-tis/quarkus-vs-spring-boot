package io.theitsolutions.quarkus.cart.adapter.in.web.domain.contactdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsRequestModel {

    private String cartName;
    private String firstName;
    private String lastName;
    private String country;
    private String zip;
    private String city;
    private String street;
    private String email;
    private String phoneNumber;

}
