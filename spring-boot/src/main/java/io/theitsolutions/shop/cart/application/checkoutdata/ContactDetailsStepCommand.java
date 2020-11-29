package io.theitsolutions.shop.cart.application.checkoutdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ContactDetailsStepCommand {
    String cartName;
    String firstName;
    String lastName;
    String country;
    String zip;
    String city;
    String street;
    String email;
    String phoneNumber;
}
