package io.theitsolutions.shop.cart.application.checkoutdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.theitsolutions.shop.cart.domain.Cart;
import io.theitsolutions.shop.cart.domain.CartCheckoutData;
import io.theitsolutions.shop.cart.port.in.ManageCartCheckoutDataPort;
import io.theitsolutions.shop.cart.port.out.CartManagementPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactDetailsStepService {

    static final String CONTACT_DETAILS = "contact-details";
    private final ManageCartCheckoutDataPort manageCartCheckoutDataPort;
    private final ObjectMapper objectMapper;
    private final CartManagementPort cartManagementPort;

    @Transactional
    public void addContactDetailsToCart(ContactDetailsStepCommand contactDetailsStepCommand) throws JsonProcessingException {
        log.info("Adding contact details as checkout data to cart:[{}] and details:[{}]", contactDetailsStepCommand.getCartName(), contactDetailsStepCommand);
        Cart cart = cartManagementPort.getCartByName(contactDetailsStepCommand.getCartName());
        String rawCheckoutData = objectMapper.writeValueAsString(contactDetailsStepCommand);
        CartCheckoutData cartCheckoutData;
        try {
            cartCheckoutData = manageCartCheckoutDataPort.findByCartAndCheckoutStep(cart, CONTACT_DETAILS);
            cartCheckoutData.setCheckoutData(rawCheckoutData);
        } catch (NoResultException e) {
            cartCheckoutData = CartCheckoutData.builder()
                    .cart(cart)
                    .checkoutStep(CONTACT_DETAILS)
                    .checkoutData(rawCheckoutData)
                    .build();
        }
        manageCartCheckoutDataPort.save(cartCheckoutData);
    }

}
