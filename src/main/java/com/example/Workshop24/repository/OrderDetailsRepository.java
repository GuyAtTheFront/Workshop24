package com.example.Workshop24.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Workshop24.model.Orders;

@Repository
public class OrderDetailsRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_INSERT_DETAILS = 
    """
    INSERT INTO order_details (product, unit_price, discount, quantity, order_id)
    VALUES (?, ?, ?, ?, ?);
    """;

    public boolean insertOrderDetails(Orders orders) {

        List<Object[]> params = orders.getOrders().stream()
                                .map(x -> new Object[] {
                                    x.getProduct(),
                                    x.getUnitPrice(),
                                    x.getDiscount(),
                                    x.getQuantity(),
                                    x.getOrderId()
                                    })
                                .toList();
        // jdbcTemplate.batchUpdate(SQL_INSERT_DETAILS, params);
        int inserted[] = jdbcTemplate.batchUpdate(SQL_INSERT_DETAILS, params);
        
        for (int i : inserted) {
            System.out.println("hello -->" + i);
        }

        return !Arrays.asList(inserted).contains(0);
    }

}
