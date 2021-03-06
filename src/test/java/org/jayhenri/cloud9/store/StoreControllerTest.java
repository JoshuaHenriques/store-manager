package org.jayhenri.cloud9.store;

import org.jayhenri.store_manager.controller.store.StoreController;
import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.StoreAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.invalid.InvalidLoginException;
import org.jayhenri.store_manager.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.store_manager.exception.invalid.InvalidStoreException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.exception.notfound.LoginNotFoundException;
import org.jayhenri.store_manager.exception.notfound.StoreNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.ControllerI;
import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.interfaces.service.customer.AddressServiceI;
import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.model.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.naming.InvalidNameException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


/**
 * The type Store controller test.
 */
@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {

    private Store store;

    @Mock
    private ServiceI<Store> storeService;

    @Mock
    private AddressServiceI addressService;

    @Captor
    private ArgumentCaptor<Store> captorStore;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private ControllerI<Store> storeController;

    private UUID storeId;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        storeId = UUID.randomUUID();

        storeController = new StoreController(storeService, addressService);

        store = new Store(
                "Cloud9",
                new Address()
        );

        store.setStoreUUID(storeId);
    }

    /**
     * Add store.
     *
     * @throws InventoryAlreadyExistsException the inventory already exists exception
     * @throws InvalidStoreException           the invalid store exception
     * @throws StoreAlreadyExistsException     the store already exists exception
     * @throws LoginAlreadyExistsException     the login already exists exception
     * @throws InvalidLoginException           the invalid login exception
     * @throws InvalidPostalCodeException      the invalid postal code exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemAlreadyExistsException      the item already exists exception
     */
    @Test
    void addStore() throws InventoryAlreadyExistsException, InvalidStoreException, StoreAlreadyExistsException, LoginAlreadyExistsException, InvalidLoginException, InvalidPostalCodeException, InvalidItemException, ItemAlreadyExistsException {

        given(storeService.existsById(storeId)).willReturn(false);
        given(addressService.isValidPostalCode(store.getAddress().getPostalCode())).willReturn(true);

        assertThat(storeController.add(store).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(storeService).should().add(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Add store throws invalid store exception.
     */
    @Test
    void addStoreThrowsInvalidStoreException() {

        assertThrows(InvalidStoreException.class, () -> storeController.add(null));
    }

    /**
     * Add store throws store already exists exception.
     */
    @Test
    void addStoreThrowsStoreAlreadyExistsException() {

        given(storeService.existsById(storeId)).willReturn(true);

        assertThrows(StoreAlreadyExistsException.class, () -> storeController.add(store));
    }

    /**
     * Add throws invalid postal code exception.
     */
    @Test
    void addThrowsInvalidPostalCodeException() {

        given(storeService.existsById(storeId)).willReturn(false);
        given(addressService.isValidPostalCode(store.getAddress().getPostalCode())).willReturn(false);

        assertThrows(InvalidPostalCodeException.class, () -> storeController.add(store));
    }

    /**
     * Update store.
     *
     * @throws InventoryAlreadyExistsException the inventory already exists exception
     * @throws InvalidStoreException           the invalid store exception
     * @throws StoreNotFoundException          the store not found exception
     * @throws InvalidLoginException           the invalid login exception
     * @throws LoginNotFoundException          the login not found exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemNotFoundException           the item not found exception
     */
    @Test
    void updateStore() throws InventoryAlreadyExistsException, InvalidStoreException, StoreNotFoundException, InvalidLoginException, LoginNotFoundException, InvalidItemException, ItemNotFoundException {

        given(storeService.existsById(storeId)).willReturn(true);
        given(storeService.getById(storeId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(storeController.update(store, storeId).getStatusCode());

        then(storeService).should().update(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Update store throws invalid store exception.
     */
    @Test
    void updateStoreThrowsInvalidStoreException() {

        assertThrows(InvalidStoreException.class, () -> storeController.add(null));
    }

    /**
     * Update store throws store not found exception.
     */
    @Test
    void updateStoreThrowsStoreNotFoundException() {

        given(storeService.existsById(storeId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> storeController.update(store, storeId));
    }

    /**
     * Delete store.
     *
     * @throws StoreNotFoundException the store not found exception
     * @throws LoginNotFoundException the login not found exception
     * @throws ItemNotFoundException  the item not found exception
     */
    @Test
    void deleteStore() throws StoreNotFoundException, LoginNotFoundException, ItemNotFoundException {

        given(storeService.existsById(storeId)).willReturn(true);
        given(storeService.getById(storeId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(storeController.delete(storeId).getStatusCode());

        then(storeService).should().remove(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Delete store throws store not found exception.
     */
    @Test
    void deleteStoreThrowsStoreNotFoundException() {

        given(storeService.existsById(storeId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> storeController.delete(storeId));
    }

    /**
     * List.
     */
    @Test
    void list() {

    }

    /**
     * Get.
     *
     * @throws InvalidNameException   the invalid name exception
     * @throws InvalidStoreException  the invalid store exception
     * @throws StoreNotFoundException the store not found exception
     * @throws InvalidLoginException  the invalid login exception
     * @throws LoginNotFoundException the login not found exception
     * @throws ItemNotFoundException  the item not found exception
     * @throws InvalidItemException   the invalid item exception
     */
    @Test
    void get() throws InvalidNameException, InvalidStoreException, StoreNotFoundException, InvalidLoginException, LoginNotFoundException, ItemNotFoundException, InvalidItemException {

        given(storeService.existsById(storeId)).willReturn(true);
        given(storeService.getById(storeId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(storeController.get(storeId).getStatusCode());
        assertThat(store).isEqualTo(storeController.get(storeId).getBody());
    }

    /**
     * Gets store throws store not found exception.
     */
    @Test
    void getStoreThrowsStoreNotFoundException() {

        given(storeService.existsById(storeId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> storeController.get(storeId));
    }

    /**
     * Gets store throws invalid store exception.
     */
    @Test
    void getStoreThrowsInvalidStoreException() {

        assertThrows(InvalidStoreException.class, () -> storeController.get(null));
    }
}
