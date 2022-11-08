package com.nds.gadai.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.entities.UserEntity;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.UserModel;
import com.nds.gadai.repos.UserRepo;
import com.nds.gadai.repos.specs.UserSpec;
import com.nds.gadai.validators.UserValidator;

@Service
public class UserService implements Serializable{

    @Autowired
    private UserRepo userRepo;

    UserValidator userValidator = new UserValidator();

    
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>(); 
        userRepo.findAll().forEach(users::add);

        return users;
    }

    public List<UserEntity> doSearchUser(UserModel userModel) throws ClientException, Exception {
        // authentikasi actor id
        userValidator.nullCheckActorId(userModel.getActorId());
        Integer authCount = userRepo.countById(userModel.getActorId());
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // validation
        // -- no need validation --

        // process
        
        List<UserEntity> users = new ArrayList<>(); 
        UserSpec specs = new UserSpec(userModel);
        userRepo.findAll(specs).forEach(users::add);

        return users;
    } 

    public UserEntity doGetDetailUser(UserModel userModel) throws NotFoundException, ClientException {
        // authentikasi actor id
        userValidator.nullCheckActorId(userModel.getActorId());
        Integer authCount = userRepo.countById(userModel.getActorId());
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // validation
        userValidator.nullCheckUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());

        Integer userCount = userRepo.countById(userModel.getId());
        if(userCount < 1){
            throw new NotFoundException("Id is not found");
        }

        // process
        // UserEntity user = userRepo.findById(id).orElse(null); 
        UserEntity user = userRepo.findByUserId(userModel.getActorId()); 
        // userValidator.nullCheckObject(user);
        
        return user;
    }

    public UserEntity doInsert(UserModel userModel) throws ClientException {
        // authentikasi actor id
        // userValidator.nullCheckActorId(userModel.getActorId());
        // Integer authCount = userRepo.countById(userModel.getActorId());
        // if(authCount < 1){
        //     throw new ClientException("Actor id is not found");
        // }

        // validation
        userValidator.nullCheckUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());
        userValidator.nullCheckName(userModel.getName());
        userValidator.validateName(userModel.getName());
        userValidator.validatePhoneNumber(userModel.getPhoneNumber());
        userValidator.validateMaxLimit(userModel.getMaxLimit());
        userValidator.validateRegDate(userModel.getRegDate());

        // process
        UserEntity user = new UserEntity();
        user.setId(userModel.getId());
        user.setName(userModel.getName());
        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setMaxLimit(userModel.getMaxLimit());
        user.setDescription(userModel.getDescription());
        if(userModel.getDescription() == null){
            throw new ClientException("Deskripsi masih null");
        }
        user.setRegDate(userModel.getRegDate());
        user.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setCreatorId(userModel.getActorId());
        return userRepo.save(user);

    }

    public UserEntity doUpdate(UserModel userModel) throws ClientException, NotFoundException {
        // authentikasi actor id
        userValidator.nullCheckActorId(userModel.getActorId());
        Integer authCount = userRepo.countById(userModel.getActorId());
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }
        
        // Validation
        userValidator.nullCheckUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());
        userValidator.nullCheckName(userModel.getName());
        userValidator.validateName(userModel.getName());
        userValidator.validatePhoneNumber(userModel.getPhoneNumber());
        userValidator.validateMaxLimit(userModel.getMaxLimit());
        userValidator.validateRegDate(userModel.getRegDate());

        Integer userCount = userRepo.countById(userModel.getId());
        if(userCount < 1){
            throw new NotFoundException("Id is not found");
        }

        // Process
        UserEntity user = new UserEntity();
        user = userRepo.findByUserId(userModel.getId());

        // if(userModel.getName() != null && userModel.getName().length() > 0 && userModel.getName() != "") {

        // name
        if(userModel.getName() != null) {
            user.setName(userModel.getName());
        }

        // phone number
        if(userModel.getPhoneNumber() != null ) {
            user.setPhoneNumber(userModel.getPhoneNumber());
        }

        // description
        if(userModel.getDescription() != null ) {
            user.setDescription(userModel.getDescription());
        }

        // max limit
        if(userModel.getMaxLimit() != null ) {
            System.out.println("ini tidak null");
            user.setMaxLimit(userModel.getMaxLimit());
        }

        // regDate 
        if(userModel.getRegDate() != null ) {
            user.setRegDate(userModel.getRegDate());
        }

        user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        user.setUpdaterId(userModel.getActorId());

        return userRepo.save(user);

    }

    public UserEntity doSoftDelete(UserModel userModel) throws NotFoundException, ClientException {
        // authentikasi actor id
        userValidator.nullCheckActorId(userModel.getActorId());
        Integer authCount = userRepo.countById(userModel.getActorId());
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // Validation
        userValidator.nullCheckUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());

        Integer userCount = userRepo.countById(userModel.getId());
        if(userCount < 1){
            throw new NotFoundException("Id is not found");
        }

        // process
        UserEntity user = new UserEntity();
        user = userRepo.findByUserId(userModel.getId());

        if(user.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User id (" + userModel.getId() + ") is already been deleted.");
        }

        user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        user.setDeleterId(userModel.getActorId());

        return userRepo.save(user);

    }

    
}
