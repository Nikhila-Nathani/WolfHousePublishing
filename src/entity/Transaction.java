package entity;

import java.sql.Date;

public class Transaction {
    private Integer trasactionId;
    private Date dateOfTransaction;
    private Float amount;

    public Integer getTrasactionId() {
        return trasactionId;
    }

    public void setTrasactionId(Integer trasactionId) {
        this.trasactionId = trasactionId;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Transaction(Integer trasactionId, Date dateOfTransaction, Float amount) {
        this.trasactionId = trasactionId;
        this.dateOfTransaction = dateOfTransaction;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Id : \t"+trasactionId+"\tDate of Transaction : \t"+dateOfTransaction+"\tAmount : \t"+amount;
    }
}
