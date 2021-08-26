package org.jayhenri.cloud9.interfaces.controller.customer;

import java.util.Set;
import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.exception.invalid.InvalidOrdersException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.OrdersNotFoundException;
import org.jayhenri.cloud9.model.customer.Orders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Orders controller i.
 */
@RequestMapping("api/orders")
public interface OrdersControllerI {

    /**
     * Add response entity.
     *
     * @param orders     the orders
     * @param customerId the customer id
     * @return the response entity
     * @throws InvalidOrdersException the invalid orders exception
     */
    @PostMapping(value = "/add/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Orders orders, @PathVariable UUID customerId) throws InvalidOrdersException;

    /**
     * Update response entity.
     *
     * @param orders   the orders
     * @param ordersId the orders id
     * @return the response entity
     * @throws OrdersNotFoundException the orders not found exception
     * @throws InvalidOrdersException  the invalid orders exception
     */
    @PutMapping(value = "/update/{ordersId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Orders orders, @PathVariable UUID ordersId) throws OrdersNotFoundException, InvalidOrdersException;

    /**
     * List response entity.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/list/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Orders>> list(@PathVariable UUID customerId) throws CustomerNotFoundException;

    /**
     * Get response entity.
     *
     * @param orderId the order id
     * @return the response entity
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     * @throws OrdersNotFoundException   the orders not found exception
     * @throws InvalidOrdersException    the invalid orders exception
     */
    @GetMapping(value = "/get/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Orders> get(@PathVariable UUID orderId)
            throws InvalidNameException, CustomerNotFoundException, OrdersNotFoundException, InvalidOrdersException;
}
