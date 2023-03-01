package com.example.Workshop24.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Workshop24.exception.TransactionFailedException;
import com.example.Workshop24.model.OrderDetails;
import com.example.Workshop24.model.Orders;
import com.example.Workshop24.repository.OrderDetailsRepository;
import com.example.Workshop24.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailsRepository detailsRepo;

    @Transactional
    public void commitOrder (Orders orders) {
        
        
        orders.setOrderDate(new java.util.Date());
        Integer key = orderRepo.insertOrder(orders);

        // throw exception if inserted key is weird
        if (key <= 0) {
            throw new TransactionFailedException("Invalid generated key - %d".formatted(key));
        }
        
        if(orders.getCustomerName() != null)
        throw new TransactionFailedException("Invalid generated key - %d".formatted(key));

        orders.getOrders().forEach(x -> x.setOrderId(key));
        Boolean inserted = detailsRepo.insertOrderDetails(orders);

        // details = true throw exception
        if(!inserted) {
            throw new TransactionFailedException("One or more items failed to save");
        }


    }

}
