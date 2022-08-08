package com.etiya.northwind.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(OrderDetailId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_details")
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="unit_price")
    private double unitPrice;
    @Column(name="quantity")
    private int quantity;
    @Column(name="discount")
    private double discount;
}
