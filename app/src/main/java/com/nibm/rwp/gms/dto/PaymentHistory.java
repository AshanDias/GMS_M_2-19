package com.nibm.rwp.gms.dto;

public class PaymentHistory {
    private String first_name;

    public PaymentHistory(String first_name, String total_payment, String date) {
        this.first_name = first_name;
        this.total_payment = total_payment;
        this.date = date;
    }

    private String total_payment;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

}
