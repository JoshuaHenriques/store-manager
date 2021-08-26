package org.jayhenri.cloud9.controller.inventory;

import java.util.List;
import java.util.UUID;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type OnlineInventory controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/inventory/online")
public class OnlineInventoryController implements InventoryControllerI<OnlineInventory> {

    private final InventoryServiceI<OnlineInventory> onlineInventoryService;

    /**
     * Instantiates a new OnlineInventory controller.
     *
     * @param onlineInventoryService the onlineInventory service
     */
    @Autowired
    public OnlineInventoryController(InventoryServiceI<OnlineInventory> onlineInventoryService) {

        this.onlineInventoryService = onlineInventoryService;
    }

    /**
     * Add item to onlineInventory response entity.
     *
     * @param itemId   the item id
     * @return the response entity
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody OnlineInventory onlineInventory, @PathVariable UUID itemId)
            throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(onlineInventory)) {
            if (!onlineInventoryService.existsById(onlineInventory.getInventoryUUID())) {
                onlineInventory.setInventoryUUID(itemId);
                onlineInventoryService.add(onlineInventory);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "add");
                return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemAlreadyExistsException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Update item response entity.
     *
     * @param onlineInventory the onlineInventory
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @PutMapping(value = "/update/{inventoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody OnlineInventory onlineInventory, @PathVariable UUID inventoryId)
            throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(onlineInventory)) {
            if (onlineInventoryService.existsById(onlineInventory.getInventoryUUID())) {
                onlineInventoryService.update(onlineInventory);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "update");
                return new ResponseEntity<>("Successfully Updated Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Gets by product name.
     *
     * @param itemName the item name
     * @return the by product name
     * @throws ItemNotFoundException the item not found exception
     */

    @GetMapping(value = "/get/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OnlineInventory> getByItemName(@PathVariable String itemName) throws ItemNotFoundException {
        if (onlineInventoryService.existsByItemName(itemName)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "getByItemName");
            OnlineInventory onlineInventory = onlineInventoryService.getByItemName(itemName);
            return new ResponseEntity<>(onlineInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    /**
     * Get response entity.
     *
     * @param itemId the item name
     * @return the response entity
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OnlineInventory> getById(@PathVariable UUID itemId) throws ItemNotFoundException {
        if (onlineInventoryService.existsById(itemId)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "getByItemId");
            OnlineInventory onlineInventory = onlineInventoryService.getById(itemId);
            return new ResponseEntity<>(onlineInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    /**
     * Remove item to onlineInventory response entity.
     *
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @DeleteMapping(value = "/delete/{itemId}")
    public ResponseEntity<String> delete(@PathVariable UUID itemId)
            throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(itemId)) {
            if (onlineInventoryService.existsById(itemId)) {
                onlineInventoryService.delete(onlineInventoryService.getById(itemId));

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "delete");
                return new ResponseEntity<>("Successfully Deleted Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping(value = "/list")
    public ResponseEntity<List<OnlineInventory>> findAll() {

        List<OnlineInventory> allInventories = onlineInventoryService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OnlineInventoryController", "list");
        return new ResponseEntity<>(allInventories, responseHeaders, HttpStatus.OK);
    }
}
