package io.theitsolutions.shop.cart.adapter.in.web.domain.contactdetails;

import io.theitsolutions.shop.cart.application.checkoutdata.ContactDetailsStepCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactDetailsMapper {

    ContactDetailsStepCommand mapToCommand(ContactDetailsRequestModel contactDetailsRequestModel);

}
