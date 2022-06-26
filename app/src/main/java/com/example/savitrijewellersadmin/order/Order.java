package com.example.savitrijewellersadmin.order;

public class Order {

    private String method;
    private String paymentamt;
    private String date;
    private String orderno;
    private String orderstatus;
    private String transactionid;
    private String userreq;
    private Integer count;
    private String couriername;
    private String trackingid;
    private String itemid;

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getTrackingid() {
        return trackingid;
    }

    public void setTrackingid(String trackingid) {
        this.trackingid = trackingid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }



    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order(String method, String paymentamt, String date, String orderno, String orderstatus, String transactionid, String userreq, Integer count, String couriername, String trackingid) {
        this.method = method;
        this.paymentamt = paymentamt;
        this.date = date;
        this.orderno = orderno;
        this.orderstatus = orderstatus;
        this.transactionid = transactionid;
        this.userreq = userreq;
        this.count = count;
        this.couriername=couriername;
        this.trackingid=trackingid;
    }

    public String getPaymentamt() {
        return paymentamt;
    }

    public void setPaymentamt(String paymentamt) {
        this.paymentamt = paymentamt;
    }



    public Order() {
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getUserreq() {
        return userreq;
    }

    public void setUserreq(String userreq) {
        this.userreq = userreq;
    }
}
