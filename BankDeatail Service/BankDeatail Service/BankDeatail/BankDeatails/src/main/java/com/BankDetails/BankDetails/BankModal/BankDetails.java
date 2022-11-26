package com.BankDetails.BankDetails.BankModal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BankDetails")
public class BankDetails {
	
	private String bankname;
	
	@Id
	private String accountNo;
	
	private String accountHolderName;
	
	private String ifscCode;
	
	private String debitCardNo;
	
	private String cvv;
	
	private String expiry;

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getDebitCardNo() {
		return debitCardNo;
	}

	public void setDebitCardNo(String debitCardNo) {
		this.debitCardNo = debitCardNo;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public BankDetails(String bankname, String accountNo, String accountHolderName, String ifscCode, String debitCardNo,
			String cvv, String expiry) {
		super();
		this.bankname = bankname;
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.ifscCode = ifscCode;
		this.debitCardNo = debitCardNo;
		this.cvv = cvv;
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return "BankDetails [bankname=" + bankname + ", accountNo=" + accountNo + ", accountHolderName="
				+ accountHolderName + ", ifscCode=" + ifscCode + ", debitCardNo=" + debitCardNo + ", cvv=" + cvv
				+ ", expiry=" + expiry + ", getBankname()=" + getBankname() + ", getAccountNo()=" + getAccountNo()
				+ ", getAccountHolderName()=" + getAccountHolderName() + ", getIfscCode()=" + getIfscCode()
				+ ", getDebitCardNo()=" + getDebitCardNo() + ", getCvv()=" + getCvv() + ", getExpiry()=" + getExpiry()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public BankDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
