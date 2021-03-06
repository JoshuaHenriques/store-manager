package org.jayhenri.store_manager.service.store;

import org.jayhenri.store_manager.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.store_manager.model.store.Employee;
import org.jayhenri.store_manager.repository.store.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Employee service.
 */
@Service
public class EmployeeService implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee service.
     *
     * @param employeeRepository the employee repository
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
        // this.orderDBService = orderDBService;
    }

    public boolean existsByPhoneNumber(String phoneNumber) {

        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    public void add(Employee employee) {

        employeeRepository.save(employee);
    }

    public void remove(Employee employee) {

        employeeRepository.delete(employee);
    }

    public void update(Employee employee) {

        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    public boolean existsById(UUID uuid) {

        return employeeRepository.existsById(uuid);
    }

    public Employee getById(UUID uuid) {

        return employeeRepository.getById(uuid);
    }

    public boolean existsByEmail(String email) {

        return employeeRepository.existsByEmail(email);
    }

    public Employee getByEmail(String email) {

        return employeeRepository.getByEmail(email);
    }
}