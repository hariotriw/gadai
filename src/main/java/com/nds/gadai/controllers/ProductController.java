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

import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.models.ProductModel;
import com.nds.gadai.models.ResponseModel;
import com.nds.gadai.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> doInsertProdukController(@Valid @RequestBody ProductModel productModel) {
        try {
            // Request
            ProductEntity product = productService.doInsertProduk(productModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New product is successfully added");
            response.setData(product);

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
    public ResponseEntity<ResponseModel> doSearchProdukController(@Valid @RequestBody ProductModel productModel) {
        try {
            // Request
            List<ProductEntity> products = productService.doSearchProduk(productModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(products);

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
    public ResponseEntity<ResponseModel> doGetDetailProdukController(@Valid @RequestBody ProductModel productModel) {
        try {
            // Request
            ProductEntity product = productService.doGetDetailProduk(productModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(product);

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
    public ResponseEntity<ResponseModel> doUpdateProdukController(@Valid @RequestBody ProductModel productModel) {
        try {
            // Request
            ProductEntity product = productService.doUpdateProduk(productModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(product);

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
    public ResponseEntity<ResponseModel> doDeleteProdukController(@Valid @RequestBody ProductModel productModel) {
        try {
            // Request
            ProductEntity product = productService.doDeleteProduk(productModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(product);

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
