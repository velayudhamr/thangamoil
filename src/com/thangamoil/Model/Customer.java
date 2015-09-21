/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : Customer Pojo calss
 */
package com.thangamoil.Model;

public class Customer {
	private  int Row_Id;
	private  String name;
	private  String Address;
	private  String Cell;
	private  String Cust_Id;
	
	public Customer() {
		super();
	}

	public Customer(String name, String address,String cell, String cust_Id) {
		super();
		this.name = name;
		Address = address;
		Cell = cell;
		Cust_Id = cust_Id;
	}

	/**
	 * @return the row_Id
	 */
	public int getRow_Id() {
		return Row_Id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * @return the cell
	 */
	public String getCell() {
		return Cell;
	}

	/**
	 * @return the cust_Id
	 */
	public String getCust_Id() {
		return Cust_Id;
	}

	/**
	 * @param row_Id the row_Id to set
	 */
	public void setRow_Id(int row_Id) {
		Row_Id = row_Id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		Cell = cell;
	}

	/**
	 * @param cust_Id the cust_Id to set
	 */
	public void setCust_Id(String cust_Id) {
		Cust_Id = cust_Id;
	}

	
	

}
