package org.jayhenri.cloud9.store;

import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.model.store.Store;
import org.jayhenri.store_manager.repository.store.StoreRepository;
import org.jayhenri.store_manager.service.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Store service test.
 */
@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    private Store store;

    private ServiceI<Store> storeService;

    @Mock
    private StoreRepository storeRepository;

    @Captor
    private ArgumentCaptor<Store> captorStore;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        storeService = new StoreService(storeRepository);

        store = new Store(
                "Cloud9 Superstore",
                new Address()
        );
    }

    /**
     * Add.
     */
    @Test
    void add() {

        storeService.add(store);

        then(storeRepository).should().save(captorStore.capture());

        assertThat(store).isEqualTo(captorStore.getValue());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        storeService.update(store);

        then(storeRepository).should().save(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        storeService.remove(store);

        then(storeRepository).should().delete(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Find all stores.
     */
    @Test
    void findAllStores() {
        storeService.findAll();

        then(storeRepository).should().findAll();
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        given(storeRepository.existsById(uuid)).willReturn(true);

        boolean exists = storeService.existsById(uuid);

        then(storeRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        given(storeRepository.existsById(uuid)).willReturn(false);

        boolean exists = storeService.existsById(uuid);

        then(storeRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        given(storeRepository.getById(uuid)).willReturn(store);

        Store _store = storeService.getById(uuid);

        then(storeRepository).should().getById(captorUUID.capture());

        assertThat(_store).isEqualTo(store);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

}
