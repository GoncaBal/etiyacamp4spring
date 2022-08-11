package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.suppliers.CreateSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.DeleteSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.ReadSupplierResponse;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    Result add(CreateSupplierRequest createSupplierRequest);
    Result update(UpdateSupplierRequest updateSupplierRequest);
    Result delete(DeleteSupplierRequest deleteSupplierRequest);
    DataResult<ReadSupplierResponse> getById(int id);
    DataResult<List<SupplierListResponse>> getAll();
    DataResult<Map<String,Object>> getAllPages(int pageNumber, int pageSize);
    DataResult<Map<String,Object>> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
