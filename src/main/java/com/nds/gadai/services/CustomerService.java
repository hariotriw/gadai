package com.nds.gadai.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.CustomerModel;
import com.nds.gadai.repos.CustomerRepo;
import com.nds.gadai.repos.specs.CustomerSpec;
import com.nds.gadai.validators.CustomerValidator;

@Service
public class CustomerService implements Serializable{
    @Autowired
    private CustomerRepo customerRepo;

    CustomerValidator customerValidator = new CustomerValidator();

    public String businessType(String s) {
        return GlobalConstant.BUSINESS_TYPE[Integer.valueOf(s)-1];
    }

    public CustomerEntity doInsertPelanggan(CustomerModel customerModel) throws ClientException {
        Long count = customerRepo.countByPhoneNumber(customerModel.getPhoneNumber());
        if (count> 0) {
            throw new ClientException("Customer Phone number already existed");
        }

        count = customerRepo.countByIdNumber(customerModel.getIdNumber());
        if (count > 0) {
            throw new ClientException("Customer ID number already existed");
        }
        
        // Process

        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerModel.getName());
        customer.setIdNumber(customerModel.getIdNumber());
        customer.setPhoneNumber(customerModel.getPhoneNumber());
        customer.setGender(customerModel.getGender());
        customer.setBusinessType(businessType(customerModel.getBusinessType()));
        customer.setMaxLimit(customerModel.getMaxLimit().doubleValue());
        customer.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setCreatorId(customerModel.getActorId() == null ? "0" : customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public List<CustomerEntity> doSearchPelanggan(CustomerModel customerModel) {
        List<CustomerEntity> customers = new ArrayList<>();
        CustomerSpec specs = new CustomerSpec(customerModel);
        customerRepo.findAll(specs).forEach(customers::add);

        return customers;
    }

    public CustomerEntity doGetDetailPelanggan (CustomerModel customerModel) throws NotFoundException, ClientException {
        customerValidator.nullCheckId(customerModel.getId());
        customerValidator.validateId(customerModel.getId());

        CustomerEntity customer = customerRepo.findById(customerModel.getId()).orElse(null);
        customerValidator.nullCheckObject(customer);
        
        return customer;
    }

    public CustomerEntity doUpdatePelanggan(CustomerModel customerModel) throws ClientException, NotFoundException {
        customerValidator.nullCheckId(customerModel.getId());
        customerValidator.validateId(customerModel.getId());

        Long count = customerRepo.countById(customerModel.getId());
        if (count == 0) {
            throw new ClientException("Cannot find customer with id: " + customerModel.getId());
        }

        CustomerEntity customer = new CustomerEntity();
        customer = doGetDetailPelanggan(customerModel);

        if (customerModel.getName() != null) {
            customer.setName(customerModel.getName());
        }

        if (customerModel.getIdNumber() != null) {
            customer.setIdNumber(customerModel.getIdNumber());
        }

        if (customerModel.getPhoneNumber() != null) {
            customer.setPhoneNumber(customerModel.getPhoneNumber());
        }

        if (customerModel.getGender() != null) {
            customer.setGender(customerModel.getGender());
        }

        customer.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setUpdaterId(customerModel.getActorId() == null ? "0" : customerModel.getActorId());
        
        return customerRepo.save(customer);
    }

    public CustomerEntity doDeletePelanggan(CustomerModel customerModel) throws ClientException, NotFoundException {
        customerValidator.nullCheckId(customerModel.getId());
        customerValidator.validateId(customerModel.getId());

        Long count = customerRepo.countById(customerModel.getId());
        if (count == 0) {
            throw new ClientException("Cannot find customer with id: " + customerModel.getId());
        }

        CustomerEntity customer = new CustomerEntity();
        customer = doGetDetailPelanggan(customerModel);

        if (customer.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Customer id (" + customerModel.getId() + ") is already deleted");
        }

        customer.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        customer.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        customer.setDeleterId(customerModel.getActorId() == null ? "0" : customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public String[] doGetListJenisUsaha() {
        return GlobalConstant.BUSINESS_TYPE;
    }

}
