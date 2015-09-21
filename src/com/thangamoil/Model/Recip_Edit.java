/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : Receipt Edite Pojo calss
 */
package com.thangamoil.Model;

public class Recip_Edit {
	
	private  int balance;
	
	private  int Row_Id;
	private  String ReceiptNo;
	private  String Cust_Id;
	private  String Bill_No;
	private  int Amount;
	private  String ChequeNo;
	private  String ChequeDate;
	private  String BankBranch;
	private  String Date;
	
	public int getBalance() {
		return balance;
	}
	public int getRow_Id() {
		return Row_Id;
	}
	public String getReceiptNo() {
		return ReceiptNo;
	}
	public String getCust_Id() {
		return Cust_Id;
	}
	public String getBill_No() {
		return Bill_No;
	}
	public int getAmount() {
		return Amount;
	}
	public String getChequeNo() {
		return ChequeNo;
	}
	public String getChequeDate() {
		return ChequeDate;
	}
	public String getBankBranch() {
		return BankBranch;
	}
	public String getDate() {
		return Date;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setRow_Id(int row_Id) {
		Row_Id = row_Id;
	}
	public void setReceiptNo(String receiptNo) {
		ReceiptNo = receiptNo;
	}
	public void setCust_Id(String cust_Id) {
		Cust_Id = cust_Id;
	}
	public void setBill_No(String bill_No) {
		Bill_No = bill_No;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public void setChequeNo(String chequeNo) {
		ChequeNo = chequeNo;
	}
	public void setChequeDate(String chequeDate) {
		ChequeDate = chequeDate;
	}
	public void setBankBranch(String bankBranch) {
		BankBranch = bankBranch;
	}
	public void setDate(String date) {
		Date = date;
	} 
}
