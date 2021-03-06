package org.jayhenri.store_manager.interfaces.controller.other;

import org.jayhenri.store_manager.exception.alreadyexists.EmployeeAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidEmployeeException;
import org.jayhenri.store_manager.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.store_manager.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.store_manager.model.store.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The interface Employee controller i.
 */
public interface EmployeeControllerI {

    /**
     * Add response entity.
     *
     * @param employee the employee
     * @return the response entity
     * @throws EmployeeAlreadyExistsException the employee already exists exception
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws InvalidEmployeeException       the invalid employee exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Employee employee)
            throws EmployeeAlreadyExistsException, InvalidPostalCodeException, InvalidEmployeeException;

    /**
     * Update response entity.
     *
     * @param employee   the employee
     * @param employeeId the employee id
     * @return the response entity
     * @throws InvalidEmployeeException  the invalid employee exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @PutMapping(value = "/update/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Employee employee, @PathVariable UUID employeeId)
            throws InvalidEmployeeException, EmployeeNotFoundException;

    /**
     * Delete response entity.
     *
     * @param employeeId the employee id
     * @return the response entity
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @DeleteMapping(value = "/delete/{employeeId}")
    ResponseEntity<String> delete(@PathVariable UUID employeeId)
            throws EmployeeNotFoundException;

    /**
     * List response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Employee>> list();

    /**
     * Get response entity.
     *
     * @param employeeId the employee id
     * @return the response entity
     * @throws InvalidNameException      the invalid name exception
     * @throws EmployeeNotFoundException the employee not found exception
     * @throws InvalidEmployeeException  the invalid employee exception
     */
    @GetMapping(value = "/get/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> get(@PathVariable UUID employeeId)
            throws InvalidNameException, EmployeeNotFoundException, InvalidEmployeeException;
}
