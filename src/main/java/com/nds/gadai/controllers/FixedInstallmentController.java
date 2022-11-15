package com.nds.gadai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nds.gadai.entities.FixedInstallmentEntity;
import com.nds.gadai.models.ContractModel;
import com.nds.gadai.models.CustomerModel;
import com.nds.gadai.models.FixedInstallmentModel;
import com.nds.gadai.models.PawnedGoods;
import com.nds.gadai.models.ResponseModel;
import com.nds.gadai.repos.interfaces.CustomerInfo;
import com.nds.gadai.repos.interfaces.ProductInfo;
import com.nds.gadai.services.FixedInstallmentService;

@RestController
@RequestMapping("/fixed-installment")
public class FixedInstallmentController {
    @Autowired
    private FixedInstallmentService fixedInstallmentService;

    @GetMapping(value = "/products")
    public ResponseEntity<ResponseModel> doGetListProdukController() {
        try {
            // Request
            List<ProductInfo> products = fixedInstallmentService.doGetListProduk();

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(products);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server. " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<ResponseModel> doSearchPelangganController(@Valid @RequestBody CustomerModel customerModel) {
        try {
            // Request
            List<CustomerInfo> customers = fixedInstallmentService.doSearchPelanggan(customerModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(customers);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server. " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ResponseModel> doSearchTransCicTetapController(@Valid @RequestBody FixedInstallmentModel fixedInstallmentModel) {
        try {
            // Request
            List<FixedInstallmentEntity> fixedInstallments = fixedInstallmentService.doSearchTransCicTetap(fixedInstallmentModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(fixedInstallments);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server. " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> doGetDetailCicTetapController(@RequestParam String id) {
        try {
            // Request
            FixedInstallmentEntity fixedInstallment = fixedInstallmentService.doGetDetailCicTetap(id);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(fixedInstallment);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server. " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/hitung")
    public ResponseEntity<ResponseModel> doHitungTrxCicTetapController(@RequestBody ContractModel contract) {
        try {
            // Request
            contract = fixedInstallmentService.doHitungTrxCicTetap(contract);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(contract);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server. " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/save")
    public ResponseEntity<ResponseModel> doSaveCicTetapController(@RequestBody ContractModel contract, @RequestBody List<PawnedGoods> pawnedGoods) {
        try {
            // Request
            FixedInstallmentEntity fixedInstallment = fixedInstallmentService.doSaveTrxCicTetap(contract, pawnedGoods);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(fixedInstallment);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server. " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }
}
