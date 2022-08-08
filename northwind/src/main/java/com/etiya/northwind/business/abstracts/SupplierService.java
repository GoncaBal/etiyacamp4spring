package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.SupplierListResponse;

import java.util.List;

public interface SupplierService {
    List<SupplierListResponse> getAll();
}
