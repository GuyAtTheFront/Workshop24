package com.example.Workshop24.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.Workshop24.model.Orders;

@Repository
public class OrderRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_INSERT_ORDER = 
    """
    INSERT INTO orders(order_date, customer_name, ship_address, notes, tax)
    VALUES (?, ?, ?, ?, ?);
    """;


    public Integer insertOrder(Orders orders) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                ps.setDate(1, new java.sql.Date(orders.getOrderDate().getTime()));
                ps.setString(2, orders.getCustomerName());
                ps.setString(3, orders.getShipAddress());
                ps.setString(4, orders.getNotes());
                ps.setNull(5, Types.DOUBLE);
                // ps.setDouble(5, orders.getTax());
                return ps;
            }
        };

        Integer inserted = jdbcTemplate.update(psc, keyHolder);

        return (inserted > 0) ? keyHolder.getKey().intValue() : -1;
    }

}
