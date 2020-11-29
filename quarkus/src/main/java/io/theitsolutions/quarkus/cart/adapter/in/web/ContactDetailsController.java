package io.theitsolutions.quarkus.cart.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.theitsolutions.quarkus.cart.adapter.in.web.domain.contactdetails.ContactDetailsMapper;
import io.theitsolutions.quarkus.cart.adapter.in.web.domain.contactdetails.ContactDetailsRequest;
import io.theitsolutions.quarkus.cart.application.checkoutdata.ContactDetailsStepCommand;
import io.theitsolutions.quarkus.cart.application.checkoutdata.ContactDetailsStepService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
@ApplicationScoped
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsMapper contactDetailsMapper;
    private final ContactDetailsStepService contactDetailsStepService;

    @POST
    @Path("/checkout-contact-details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postContactDetails(@Valid ContactDetailsRequest request) throws JsonProcessingException {
        ContactDetailsStepCommand command = contactDetailsMapper.mapToCommand(request.getContactDetails());
        contactDetailsStepService.addContactDetailsToCart(command);
        return Response.ok().build();
    }
}
