package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.requests.suppliers.CreateSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.DeleteSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.ReadSupplierResponse;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapperService modelMapperService;
    @Autowired
    public SupplierManager(SupplierRepository supplierRepository,ModelMapperService modelMapperService) {
        this.supplierRepository = supplierRepository;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public void add(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier=this.modelMapperService.forRequest().map(createSupplierRequest,Supplier.class);
        this.supplierRepository.save(supplier);
    }

    @Override
    public void update(UpdateSupplierRequest updateSupplierRequest) {
        Supplier supplierToUpdate=this.modelMapperService.forRequest().map(updateSupplierRequest,Supplier.class);
        this.supplierRepository.save(supplierToUpdate);
    }

    @Override
    public void delete(DeleteSupplierRequest deleteSupplierRequest) {
        this.supplierRepository.deleteById(deleteSupplierRequest.getSupplierId());
    }

    @Override
    public ReadSupplierResponse getById(int id) {
        Supplier supplier=this.supplierRepository.findById(id);
        return this.modelMapperService.forResponse().map(supplier,ReadSupplierResponse.class);
    }

    @Override
    public List<SupplierListResponse> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = result.stream().map(supplier -> this.modelMapperService.forResponse()
                .map(supplier,SupplierListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public Map<String, Object> getAllPages(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        return null;
    }
}
