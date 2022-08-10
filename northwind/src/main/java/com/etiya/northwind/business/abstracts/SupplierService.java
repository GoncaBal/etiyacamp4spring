package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.suppliers.CreateSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.DeleteSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.ReadSupplierResponse;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    void add(CreateSupplierRequest createSupplierRequest);
    void update(UpdateSupplierRequest updateSupplierRequest);
    void delete(DeleteSupplierRequest deleteSupplierRequest);
    ReadSupplierResponse getById(int id);
    List<SupplierListResponse> getAll();
    Map<String,Object> getAllPages(int pageNumber, int pageSize);
    Map<String,Object> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
