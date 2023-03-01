package com.example.Workshop24.model;

import java.math.BigDecimal;

public class OrderDetails {

    private Integer id;
    private String product;
    private BigDecimal unitPrice;
    private Double discount;
    private Integer quantity;
    private Integer orderId;

    @Override
    public String toString() {
        return "OrderDetails [id=" + id + ", product=" + product + ", unitPrice=" + unitPrice + ", discount=" + discount
                + ", quantity=" + quantity + ", orderId=" + orderId + "]";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    
    
    
}
