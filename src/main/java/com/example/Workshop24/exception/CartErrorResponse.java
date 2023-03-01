package com.example.Workshop24.exception;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class CartErrorResponse {
    private Integer status;
    private String message;
    private Long timeStamp;

    public CartErrorResponse(){
    }
    
    public CartErrorResponse(Integer status, String message, Long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("status", this.getStatus())
                .add("message", this.getMessage())
                .add("timestamp", this.getTimeStamp())
                .build();
    }

    @Override
    public String toString() {
        return "CartErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }



}
