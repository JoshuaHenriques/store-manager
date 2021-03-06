package org.jayhenri.store_manager.exception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Employee not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Employee not found exception.
     */
    public EmployeeNotFoundException() {
    }

    /**
     * Instantiates a new Employee not found exception.
     *
     * @param msg the msg
     */
    public EmployeeNotFoundException(String msg) {

        super(msg);
    }

    /**
     * Gets error messages.
     *
     * @return the error messages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets error messages.
     *
     * @param errorMessages the error messages
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Add error message.
     *
     * @param msg the msg
     */
    public void addErrorMessage(String msg) {
        this.errorMessages.add(msg);
    }
}
