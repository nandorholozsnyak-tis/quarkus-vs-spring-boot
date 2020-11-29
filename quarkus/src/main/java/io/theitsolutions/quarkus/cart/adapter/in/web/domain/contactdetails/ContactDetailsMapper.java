package io.theitsolutions.quarkus.cart.adapter.in.web.domain.contactdetails;

import io.theitsolutions.quarkus.cart.application.checkoutdata.ContactDetailsStepCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactDetailsMapper {

    ContactDetailsStepCommand mapToCommand(ContactDetailsRequestModel contactDetailsRequestModel);

}
