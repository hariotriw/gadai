package com.nds.gadai.controllers;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nds.gadai.entities.FixedInstallmentEntiy;
import com.nds.gadai.entities.InstallmentEntity;
import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.models.FixedInstallmentModel;
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
            System.out.println(actorId);
            HashMap<String, Object> result = paymentService.doGetDetailTransaction(transactionNumber, actorId.toString());

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
        @RequestParam String transactionNumber,
        @RequestParam String customerId,
        @RequestParam String customerName,
        @RequestParam Timestamp installmentStartDate,
        @RequestParam Timestamp installmentEndDate,
        @RequestParam String actorId
    ) {
        try {
            // request
            System.out.println("test 1");
            // Object result = 
            List<FixedInstallmentEntiy> result = paymentService.doSearchPayFixedInstallment(transactionNumber, customerId, customerName, installmentStartDate, installmentEndDate, actorId);

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

    @PutMapping(value = "/pay-installment")
    public ResponseEntity<ResponseModel> doUpdatePembayaranController (
        @RequestParam String transactionNumber,
        @RequestParam String paymentMethod,
        @RequestParam BigDecimal discount,
        @RequestParam BigDecimal totalPayment,
        @RequestParam List<Integer> selectedInstallment,
        @RequestParam String actorId
    ) {
        try {
            // request
            System.out.println("test 1");
            // Object result = 
            HashMap<String, Object> result = paymentService.doUpdatePembayaran(transactionNumber, paymentMethod, discount, totalPayment, selectedInstallment, actorId);

            // transactionNumber.equals
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
    
    // --- Controller testing ---
    @GetMapping(value = "/test1")
    public ResponseEntity<ResponseModel> test1Controller() {
        try {
            // Online Java Compiler
            // Use this editor to write, compile and run your Java code online

            // public static void main(String[] args) {
            //     // String input = "1010";
            //     String input = "01011";
            //     String str = "00000";
            //     int counter = 0;
            //     char[] charArrst = input.toCharArray();
                
            //     System.out.println("input yang dimasukkan: " + input);
            //     while (!str.equals(input)) {
            //         System.out.println("while looping : " + counter);
            //         // System.out.println(input);
            //         // int minus = 1;
            //         charArrst = input.toCharArray();
            //         int n = getN(charArrst);
            //         input = doFlip(input, n);
            //         System.out.println("n = " + n);
            //         System.out.println(input);
                    
            //         counter++;
            //     }
                
            //     // Output: "1010"
            //     // System.out.println("output akhir: " + input);
            //     System.out.println("counter: " + counter);
            // }
            
            // public static Integer getN(char[] arr) {
            //     int countN = 0;
            //     for(int j = arr.length-1; j >= 0; j--) {
            //         // System.out.println(arr[j]);
            //         if(j == 0){
            //             return j+1;
            //         } else {
            //             if(arr[j] == '0'){
            //                 if(arr[j-1] == '1') {
            //                     return j+1;
            //                 } else {
            //                   continue;
            //                 } 
            //             } else {
            //                 continue;
            //             }
            //         }
                        
                    
                    
            //         // if(arr[j] == '0'){
            //         //     if(j == 0) {
            //         //         return j+1;
            //         //     } else {
            //         //         if(arr[j-1] == '1') {
            //         //             return j+1;
            //         //         } else {
            //         //             continue;
            //         //         }
            //         //     }
            //         // } else {
            //         //     continue;
            //         // }
            //     }
            //         return 0;
            // }
            
            // public static String doFlip(String str, int n) {
            //     int count = 0;
            //     char[] charArr = str.toCharArray();
            //     for(int i = n-1; i <= charArr.length-1; i++){
            //         if(charArr[i] == '0'){
            //             charArr[i] = '1';
            //         } else if(charArr[i] == '1'){
            //             charArr[i] = '0';
            //         }
            //     }
            //     // str = 
            //     // System.out.println(str);
            //     return String.valueOf(charArr);
            // }

            // response
            ResponseModel response = new ResponseModel();
            response.setCode(200);
            response.setMessage("OK");
            response.setDescription("Request successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setData(0);

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
}
