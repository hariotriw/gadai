package com.nds.gadai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.models.CustomerModel;
import com.nds.gadai.models.ResponseModel;
import com.nds.gadai.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> doInsertPelangganController(@Valid @RequestBody CustomerModel customerModel) {
        try {
            // Request
            CustomerEntity customer = customerService.doInsertPelanggan(customerModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New customer is successfully added");
            response.setData(customer);

            return ResponseEntity.ok(response);
            }
            
        catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server" + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ResponseModel> doSearchPelangganController(@RequestBody CustomerModel customerModel) {
        try {
            // Request
            List<CustomerEntity> customers = customerService.doSearchPelanggan(customerModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(customers);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> doGetDetailPelangganController(@RequestBody CustomerModel customerModel) {
        try {
            // Request
            CustomerEntity customer = customerService.doGetDetailPelanggan(customerModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(customer);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> doUpdatePelangganController(@RequestBody CustomerModel customerModel) {
        try {
            // Request
            CustomerEntity customer = customerService.doUpdatePelanggan(customerModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(customer);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> doDeletePelangganController(@RequestBody CustomerModel customerModel) {
        try {
            // Request
            CustomerEntity customer = customerService.doDeletePelanggan(customerModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(customer);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }
}
