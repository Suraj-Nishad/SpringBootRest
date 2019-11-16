package org.cap.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="trans_details")
public class Transaction {
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
private int tid;
@Column(length=30)
private String type;


@Column(length=15)
private int amountTransferred;
@Column(length=15)

private int accountNo;



public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getAmountTransferred() {
	return amountTransferred;
}
public void setAmountTransferred(int amountTransferred) {
	this.amountTransferred = amountTransferred;
}
public int getAccountNo() {
	return accountNo;
}
public void setAccountNo(int accountNo) {
	this.accountNo = accountNo;
}
public String toString() {
	return "History [trans_id=" + tid + ", operation=" + type + ", acc_id=" + accountNo  + ", amount="
			+ amountTransferred + "]";
}
}
