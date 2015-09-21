/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : Bill Pojo calss
 */
package com.thangamoil.Model;

public class Bills {

	private String Cust_Id;
	private String Bill_No;
	private int balance;
	private int received;
	private String Date;
	
	public Bills() {

	}

	public Bills(String cust_Id, String bill_No, int balance, int received,String date) {
		this.Cust_Id = cust_Id;
		this.Bill_No = bill_No;
		this.balance = balance;
		this.received = received;
		this.Date = date;
	}
	
	public String getCust_Id() {
		return Cust_Id;
	}
	public String getBill_No() {
		return Bill_No;
	}
	public int getBalance() {
		return balance;
	}
	public int getReceived() {
		return received;
	}
	public String getDate() {
		return Date;
	}
	public void setCust_Id(String cust_Id) {
		Cust_Id = cust_Id;
	}
	public void setBill_No(String bill_No) {
		Bill_No = bill_No;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setReceived(int received) {
		this.received = received;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
}
