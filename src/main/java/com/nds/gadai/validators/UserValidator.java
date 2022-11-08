package com.nds.gadai.validators;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.globals.GlobalConstant;

public class UserValidator {
    public void nullCheckUserId(String id) throws ClientException{
        if(id == null){
            throw new ClientException("User id is Required");
        }
    }

    public void notNullCheckUserId(String id) throws ClientException{
        if(id != null){
            throw new ClientException("User id is auto generated, do not input id");
        }
    }

    public void nullCheckName(String name) throws ClientException{
        if(name == null){
            throw new ClientException("name is Required");
        }
    }

    public void nullCheckActorId(String actorId) throws ClientException{
        if(actorId == null){
            throw new ClientException("Actor id is Required");
        }
    }
    
    public void nullCheckObject(Object o) throws ClientException{
        if(o == null){
            throw new ClientException("User is not found");
        }
    }

    public void validateUserId(String id) throws ClientException{
        if(id.length() > 15){
            throw new ClientException("User id must contain below than 15 character");
        }
    }

    public void validateName(String name) throws ClientException{
        if(name.trim().equalsIgnoreCase("")){
            throw new ClientException("User name is Required");
        }
    }

    // public void validateRoleId(String roleId) throws ClientException{
    //     if(roleId.length() != 5 || !roleId.startsWith("R") ){
    //         throw new ClientException("User role id contains six digits and starts with 'PC'");
    //     }
    // }

    public void validatePhoneNumber(String phoneNumber) throws ClientException {
        if(phoneNumber.length() > 12 || !phoneNumber.startsWith("0")  || phoneNumber.length() < 9){
            throw new ClientException("Phone Number must contains between 9 - 12 digits and starts with '0'");
        }
    }
    
    public void validateRecStatus(String id, String recStatus) throws ClientException{
        if(recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)){
            throw new ClientException("Id = " + id + " is already been deleted");
        }
    }
    
    public void validateMaxLimit(BigDecimal maxLimit) throws ClientException {
        if(maxLimit.compareTo(new BigDecimal(500000)) == -1 || maxLimit.compareTo(new BigDecimal(1000000)) == 1){
            throw new ClientException("Max Limit must between 500.000,00 and 1.000.000,00");
        }
    }

    public void validateRegDate(Timestamp regDate) throws ClientException {
        if(regDate != null ){
            throw new ClientException("Registration date have invalid format. format: 'YYYYMMDD'");
        }
    }
    
}
