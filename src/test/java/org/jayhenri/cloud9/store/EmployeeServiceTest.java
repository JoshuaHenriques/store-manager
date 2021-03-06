package org.jayhenri.cloud9.store;

import org.jayhenri.store_manager.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.store_manager.model.store.Employee;
import org.jayhenri.store_manager.repository.store.EmployeeRepository;
import org.jayhenri.store_manager.service.store.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
 * The type Employee service test.
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    private EmployeeServiceI employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Captor
    private ArgumentCaptor<Employee> captorEmployee;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private Employee employee;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        employee = new Employee();

        employeeService = new EmployeeService(employeeRepository);
    }

    /**
     * Test add.
     */
    @Test
    void testAdd() {
        employeeService.add(this.employee);

        then(employeeRepository).should().save(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Exists by phone number.
     */
    @Test
    void existsByPhoneNumber() {
        given(employeeRepository.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = employeeService.existsByPhoneNumber("1234567890");
        then(employeeRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    /**
     * Does not exists by phone number.
     */
    @Test
    void doesNotExistsByPhoneNumber() {
        given(employeeRepository.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = employeeService.existsByPhoneNumber("1234567890");
        then(employeeRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    /**
     * Add.
     */
    @Test
    void add() {
        employeeService.add(this.employee);

        then(employeeRepository).should().save(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        employeeService.remove(this.employee);

        then(employeeRepository).should().delete(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Update.
     */
    @Test
    void update() {
        employeeService.update(this.employee);

        then(employeeRepository).should().save(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Find all employees.
     */
    @Test
    @Disabled
    void findAllEmployees() {
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
        String email = "employeeService@gmail.com";
        given(employeeRepository.existsByEmail(email)).willReturn(true);

        boolean bool = employeeService.existsByEmail(email);

        then(employeeRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isTrue();
    }

    /**
     * Does not exists by email.
     */
    @Test
    void doesNotExistsByEmail() {
        String email = "employeeService@gmail.com";
        given(employeeRepository.existsByEmail(email)).willReturn(false);

        boolean bool = employeeService.existsByEmail(email);

        then(employeeRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isFalse();
    }

    /**
     * Gets by email.
     */
    @Test
    void getByEmail() {
        String email = "employeeService@gmail.com";
        given(employeeRepository.getByEmail(email)).willReturn(employee);
        Employee _employee = employeeService.getByEmail(email);

        then(employeeRepository).should().getByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(employee).isEqualTo(_employee);
    }
}