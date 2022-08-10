package com.etiya.northwind.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @Column(name="order_id")
    private int orderId;

    @Column(name="order_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

//    @OneToMany(mappedBy = "orderId")
//    private List<OrderDetail> orderDetails;
}
