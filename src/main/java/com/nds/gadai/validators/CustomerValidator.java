package com.nds.gadai.validators;


import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.globals.GlobalConstant;

public class CustomerValidator {
    public void nullCheckId(String id) throws ClientException {
        if (id == null) {
            throw new ClientException("User id is required");
        }
    }

    public void notNullCheckUserId(String id) throws ClientException {
        if (id != null) {
            throw new ClientException("User id is autogenerated, do not input id");
        }
    }

    public void nullCheckObject(Object o) throws ClientException {
        if (o == null) {
            throw new ClientException("User not found");
        }
    }

    public void validateId(String categoryId) throws ClientException {
        if (categoryId.length() != 10 || categoryId.chars().allMatch(Character::isDigit)){
            throw new ClientException("Customer id contains 10 digits and is all numeric");
        }
    }

    public void validateRecStatus(String id, String recStatus) throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User with id - " + id + " has already been deleted");
        }
    }
    
}
