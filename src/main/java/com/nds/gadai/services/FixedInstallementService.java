package com.nds.gadai.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nds.gadai.models.CustomerModel;
import com.nds.gadai.models.PawnedGoods;
import com.nds.gadai.repos.CustomerRepo;
import com.nds.gadai.repos.FixedInstallmentRepo;
import com.nds.gadai.repos.InstallmentRepo;
import com.nds.gadai.repos.ProductRepo;
import com.nds.gadai.repos.interfaces.CustomerInfo;
import com.nds.gadai.repos.interfaces.ProductInfo;
import com.nds.gadai.repos.specs.CustomerSpec;

@Service
public class FixedInstallementService {
    @Autowired
    private FixedInstallmentRepo fixedInstallmentRepo;

    @Autowired
    private PawnedGoods pawnedGoodsRepo;

    @Autowired
    private InstallmentRepo installmentRepo;

    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private ProductRepo productRepo;

    public List<ProductInfo> doGetListProduk() {
        List<ProductInfo> products = new ArrayList<>();
        productRepo.findAllProductInfo().forEach(products::add);
        return products;
    }

    public List<CustomerInfo> doSearchPelanggan(CustomerModel customerModel) {
        List<CustomerInfo> customers = new ArrayList<>();
        CustomerSpec specs = new CustomerSpec(customerModel);
        customerRepo.findAllCustomerInfo(specs).forEach(customers::add);
        return customers;
    }
}
