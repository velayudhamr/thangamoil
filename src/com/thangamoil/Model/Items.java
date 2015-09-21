/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : Item Pojo calss
 */
package com.thangamoil.Model;

public class Items {

	private  int Item_Id;
	private  String Item;
	private  int Rate;
	
	// constructors
	public Items() {
		}

	public Items(int Item_Id, String Item,int Rate) {
			
			this.Item_Id = Item_Id;
			this.Item = Item;
			this.Rate = Rate;
	}
		
	/**
	 * @return the item_Id
	 */
	public  int getItem_Id() {
		return Item_Id;
	}
	/**
	 * @return the item
	 */
	public  String getItem() {
		return Item;
	}
	/**
	 * @return the rate
	 */
	public  int getRate() {
		return Rate;
	}
	/**
	 * @param item_Id the item_Id to set
	 */
	public  void setItem_Id(int item_Id) {
		this.Item_Id = item_Id;
	}
	/**
	 * @param item the item to set
	 */
	public  void setItem(String item) {
		this.Item = item;
	}
	/**
	 * @param rate the rate to set
	 */
	public  void setRate(int rate) {
		this.Rate = rate;
	}
	
	
	
}
