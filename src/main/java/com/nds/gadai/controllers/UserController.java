package com.nds.gadai.controllers;

import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nds.gadai.entities.UserEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.models.ResponseModel;
import com.nds.gadai.models.UserModel;
import com.nds.gadai.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel> doInsertUserController (
        @RequestBody UserModel userModel
    ) {
        try {
            // request
            System.out.println("test 1");
            UserEntity user = userService.doInsert(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(201);
            response.setMessage("Created");
            response.setDescription("New User is successfully added");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(user);

            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setCode(400);
            response.setMessage("Bad Request");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(response);
            
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setDescription("Sorry, there is a failure on our server.");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getAllUsersController() {
        try {
            // request
            List<UserEntity> users = userService.findAll();

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("Request successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(users);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setDescription("Sorry, there is a failure on our server.");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value= "/get/search")
    public ResponseEntity<ResponseModel> doSearchUserController(@RequestBody UserModel userModel) {
        try {
            // request
            List<UserEntity> users = userService.doSearchUser(userModel);
    
            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("Request successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(users);

            return ResponseEntity.ok(response);
            
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setCode(400);
            response.setMessage("Bad Request");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());

            return ResponseEntity.badRequest().body(response);
            
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setDescription("Sorry, there is a failure on our server.");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<ResponseModel> doGetDetailUserController(@RequestBody UserModel userModel) {
        try {
            // request
            UserEntity user = userService.doGetDetailUser(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("Request successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(user);

            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setCode(400);
            response.setMessage("Bad Request");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());
            
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e){
            ResponseModel response = new ResponseModel();
            response.setCode(404);
            response.setMessage("Not Found");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setDescription("Sorry, there is a failure on our server.");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> doUpdateUserController(@RequestBody UserModel userModel) {
        try {
            // request
            UserEntity user = userService.doUpdate(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("User is successfully updated");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(user);

            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setCode(400);
            response.setMessage("Bad Request");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());
            
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e){
            ResponseModel response = new ResponseModel();
            response.setCode(404);
            response.setMessage("Not Found");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setDescription("Sorry, there is a failure on our server.");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> doDeleteUserController(@RequestBody UserModel userModel) {
        try {
            // request
            UserEntity user = userService.doSoftDelete(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("User is successfully deleted");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(user);

            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setCode(400);
            response.setMessage("Bad Request");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());
            
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e){
            ResponseModel response = new ResponseModel();
            response.setCode(404);
            response.setMessage("Not Found");
            response.setDescription(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg(e.getMessage());
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setDescription("Sorry, there is a failure on our server.");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(null);
            // response.setMsg("Sorry, there is a failure on our server.");
            e.printStackTrace();

            return ResponseEntity.internalServerError().body(response);
        }
    }
    
}
