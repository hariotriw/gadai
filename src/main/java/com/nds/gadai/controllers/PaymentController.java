package com.nds.gadai.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nds.gadai.entities.FixedInstallmentEntiy;
import com.nds.gadai.entities.InstallmentEntity;
import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.models.ResponseModel;
import com.nds.gadai.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // --- Controller testing ---
    @GetMapping(value = "/test")
    public ResponseEntity<ResponseModel> testController() {
        try {
            // request
            List<FixedInstallmentEntiy> tests = paymentService.testFind();

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("Request successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(tests);

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

    // 
    @GetMapping(value = "/transaction/{transactionNumber}")
    public ResponseEntity<ResponseModel> doGetDetailTransactionController(@PathVariable String transactionNumber, @RequestParam String actorId) {
        try {
            // request
            FixedInstallmentEntiy result = paymentService.doGetDetailTransaction(transactionNumber, actorId);

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("Request successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(result);

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

    @PostMapping(value = "/search-transaction")
    public ResponseEntity<ResponseModel> doSearchPayFixedInstallmentController (
        @RequestParam Map<String,String> requestParams
    ) {
        try {
            // request
            System.out.println("test 1");
            Object result = paymentService.doSearchPayFixedInstallment(requestParams);

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(201);
            response.setMessage("Created");
            response.setDescription("New User is successfully added");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(result);

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
    
}
