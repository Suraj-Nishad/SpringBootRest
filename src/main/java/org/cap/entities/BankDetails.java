package org.cap.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_details")
public class BankDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(length=15)
	private Integer accountNo;
	@Column(length=15)
	private String firstName;
	@Column(length=15)
	private String lastName;
	@Column(length=15)
	private Long mobileNo;
	@Column(length=15)
	private String address;
	@Column(length=15)
	private Integer accBal;
	
	public Integer getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
	public Integer getAccBal() {
		return accBal;
	}
	public void setAccBal(Integer accBal) {
		this.accBal = accBal;
	}
	 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	
}
