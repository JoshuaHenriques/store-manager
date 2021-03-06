package org.jayhenri.store_manager.interfaces.controller.customer;

import org.jayhenri.store_manager.exception.alreadyexists.CreditCardAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidCreditCardException;
import org.jayhenri.store_manager.exception.invalid.InvalidOrdersException;
import org.jayhenri.store_manager.exception.notfound.CreditCardNotFoundException;
import org.jayhenri.store_manager.exception.notfound.CustomerNotFoundException;
import org.jayhenri.store_manager.model.customer.CreditCard;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

/**
 * The interface Credit card controller i.
 */
@RequestMapping("api/customer/creditCard")
public interface CreditCardControllerI {

    /**
     * Add response entity.
     *
     * @param customerId the customer id
     * @param creditCard the credit card
     * @return the response entity
     * @throws CustomerNotFoundException        the customer not found exception
     * @throws InvalidOrdersException           the invalid orders exception
     * @throws CreditCardAlreadyExistsException the credit card already exists exception
     * @throws InvalidCreditCardException       the invalid credit card exception
     */
    @PostMapping(value = "/{customerId}/creditCard/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@PathVariable UUID customerId, @RequestBody CreditCard creditCard)
            throws CustomerNotFoundException, InvalidOrdersException, CreditCardAlreadyExistsException, InvalidCreditCardException;

    /**
     * Delete response entity.
     *
     * @param customerId the customer id
     * @param cardId     the card id
     * @return the response entity
     * @throws CustomerNotFoundException   the customer not found exception
     * @throws CreditCardNotFoundException the credit card not found exception
     */
    @DeleteMapping(value = "/{customerId}/creditCard/remove/{cardId}")
    ResponseEntity<String> delete(@PathVariable UUID customerId, @PathVariable UUID cardId)
            throws CustomerNotFoundException, CreditCardNotFoundException;

    /**
     * List response entity.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{customerId}/creditCards/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<CreditCard>> list(@PathVariable UUID customerId) throws CustomerNotFoundException;
}
