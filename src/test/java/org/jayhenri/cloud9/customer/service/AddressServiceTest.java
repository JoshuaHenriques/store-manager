package org.jayhenri.cloud9.customer.service;

import org.jayhenri.store_manager.interfaces.service.customer.AddressServiceI;
import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.service.customer.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Address service test.
 */
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    private AddressServiceI addressService;

    @Captor
    private ArgumentCaptor<Address> captorAddress;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        addressService = new AddressService();
    }

    /**
     * Is valid postal code.
     */
    @Test
    void isValidPostalCode() {
        Boolean bool = addressService.isValidPostalCode("M1C8N3");

        assertThat(bool).isTrue();
    }

    /**
     * Is not valid postal code.
     */
    @Test
    void isNotValidPostalCode() {
        Boolean bool = addressService.isValidPostalCode("M1CM8N3");

        assertThat(bool).isFalse();
    }
}
