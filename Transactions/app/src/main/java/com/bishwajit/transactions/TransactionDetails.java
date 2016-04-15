package com.bishwajit.transactions;

/**
 * Created by bishwajit on 4/14/2016.
 */
public class TransactionDetails {

    // parameters for the transaction details
    long id, amount;
    String type, status,title;

    // getters and setters for the parameters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Boolean isOriginator;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getOriginator() {
        return isOriginator;
    }

    public void setOriginator(Boolean originator) {
        isOriginator = originator;
    }
}
