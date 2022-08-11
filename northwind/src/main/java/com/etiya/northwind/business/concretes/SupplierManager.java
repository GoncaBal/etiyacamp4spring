package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.requests.suppliers.CreateSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.DeleteSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.ReadSupplierResponse;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.HashMap;
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
    public Result add(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier=this.modelMapperService.forRequest().map(createSupplierRequest,Supplier.class);
        this.supplierRepository.save(supplier);
        return new SuccessResult("SUPPLIER.ADDED");
    }

    @Override
    public Result update(UpdateSupplierRequest updateSupplierRequest) {
        Supplier supplierToUpdate=this.modelMapperService.forRequest().map(updateSupplierRequest,Supplier.class);
        this.supplierRepository.save(supplierToUpdate);
        return new SuccessResult("SUPPLIER.UPDATED");
    }

    @Override
    public Result delete(DeleteSupplierRequest deleteSupplierRequest) {
        this.supplierRepository.deleteById(deleteSupplierRequest.getSupplierId());
        return new SuccessResult("PRODUCT.DELETED");
    }

    @Override
    public DataResult<ReadSupplierResponse> getById(int id) {
        Supplier supplier=this.supplierRepository.findById(id).get();
        ReadSupplierResponse readSupplierResponse= this.modelMapperService.forResponse().map(supplier,ReadSupplierResponse.class);
        return new SuccessDataResult<>(readSupplierResponse);

    }

    @Override
    public DataResult<List<SupplierListResponse>> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = result.stream().map(supplier -> this.modelMapperService.forResponse()
                .map(supplier,SupplierListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }

    public DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber-1,pageSize);
        return new SuccessDataResult<>( pageableMap(pageable));
    }

    @Override
    public DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        Pageable pageable= PageRequest.of(pageNumber-1,pageSize, SortingEntities.sortType(entity,type));
        return new SuccessDataResult<>(  pageableMap(pageable));
    }
    private Map<String, Object> pageableMap(Pageable pageable){
        Map<String,Object> response = new HashMap<>();
        Page<Supplier> suppliers = supplierRepository.findAll(pageable);
        response.put("Total Elements",suppliers.getTotalElements());
        response.put("Total Pages",suppliers.getTotalPages());
        response.put("Current Page",suppliers.getNumber()+1);
        response.put("Suppliers",suppliers.getContent().stream()
                .map(supplier -> this.modelMapperService.forResponse()
                        .map(supplier, SupplierListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }
}
