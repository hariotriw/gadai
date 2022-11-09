package com.nds.gadai.validators;


import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.globals.GlobalConstant;

public class CustomerValidator {
    public void nullCheckId(String id) throws ClientException {
        if (id == null) {
            throw new ClientException("Customer id is required");
        }
    }

    public void notNullCheckId(String id) throws ClientException {
        if (id != null) {
            throw new ClientException("Customer id is autogenerated, do not input id");
        }
    }

    public void nullCheckObject(Object o) throws ClientException {
        if (o == null) {
            throw new ClientException("Customer not found");
        }
    }

    public void validateId(String categoryId) throws ClientException {
        if (categoryId.length() != 10) {
            throw new ClientException("Customer id contains 10 digits");
        }
    }

    public void validateRecStatus(String id, String recStatus) throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Customer with id - " + id + " has already been deleted");
        }
    }
    
}
