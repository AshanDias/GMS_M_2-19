package com.nibm.rwp.gms.dto;

public class RequestHistory {
   private String customer_name;
   private String request_date;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestHistory(String customer_name, String request_date, String status) {
        this.customer_name = customer_name;
        this.request_date = request_date;
        this.status = status;
    }

    private String status;



}
