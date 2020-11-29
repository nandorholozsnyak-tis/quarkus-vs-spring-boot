package io.theitsolutions.shop.cart.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.theitsolutions.shop.cart.adapter.in.web.domain.contactdetails.ContactDetailsMapper;
import io.theitsolutions.shop.cart.adapter.in.web.domain.contactdetails.ContactDetailsRequest;
import io.theitsolutions.shop.cart.application.checkoutdata.ContactDetailsStepCommand;
import io.theitsolutions.shop.cart.application.checkoutdata.ContactDetailsStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsMapper contactDetailsMapper;
    private final ContactDetailsStepService contactDetailsStepService;

    @PostMapping(path = "/checkout-contact-details", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postContactDetails(@RequestBody @Valid ContactDetailsRequest request) throws JsonProcessingException {
        ContactDetailsStepCommand command = contactDetailsMapper.mapToCommand(request.getContactDetails());
        contactDetailsStepService.addContactDetailsToCart(command);
        return ResponseEntity.ok().build();
    }
}
