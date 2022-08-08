package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.responses.SupplierListResponse;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierManager implements SupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierManager(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierListResponse> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = new ArrayList<>();
        for (Supplier supplier : result) {
            SupplierListResponse supplierListResponse = new SupplierListResponse();
        supplierListResponse.setSupplierId(supplier.getSupplierId());
        supplierListResponse.setCompanyName(supplier.getCompanyName());
        supplierListResponse.setContactName(supplier.getContactName());
        supplierListResponse.setContactTitle(supplier.getContactTitle());
        response.add(supplierListResponse);
        }
        return response;
    }
}
