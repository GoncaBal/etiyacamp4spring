package com.etiya.northwind.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailId implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private int productId;
}
