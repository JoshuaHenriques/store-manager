package org.jayhenri.store_manager.interfaces.service.customer;

/**
 * The interface Address service i.
 */
public interface AddressServiceI {

    /**
     * Is valid postal code boolean.
     *
     * @param postalCode the postal code
     * @return the boolean
     */
    boolean isValidPostalCode(String postalCode);
}
