/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : this interface contain all static variable
 */
package com.thangamoil.db;

public interface Databaseneed {

			// Database Version
			public static final int DATABASE_VERSION = 2;

			// Database Name
			public static final String DATABASE_NAME = "Thangam_Oil.db";

			// Table Names
			public static final String TABLE_RECEIPT = "Receipt";
			public static final String TABLE_ORDER_DETAILS = "Order_Details";
			public static final String TABLE_CUSTOMER = "Customer";
			public static final String TABLE_BILLS = "bills";
			public static final String TABLE_USER = "User";
	
			// User Table - column names
			public static final String KEY_USERNAME	=	"Username";
			public static final String KEY_PASSWORD	=	"Password";
			public static final String KEY_USER_ID	=	"User_Id";
			public static final String KEY_ROLE		=	"Role";
			
			
			// Common column names
			public static final String KEY_ROW_ID 	= 	"Row_Id";
			public static final String KEY_CUST_ID	= 	"Cust_Id";
			public static final String KEY_ITEM_ID 	= 	"Item_Id";
			public static final String KEY_BILLNO 	=	"Bill_No";
			public static final String KEY_DATE 	= 	"Date";
			
			
			// Collection Table - column nmaes
			public static final String KEY_RECEIPTINO	=	"ReceiptNo";
			public static final String KEY_AMOUNT		=	"Amount";
			public static final String KEY_CHEQUENO		=	"ChequeNo";
			public static final String KEY_CHEQUEDATE	=	"ChequeDate";
			public static final String KEY_BANKNAME	=	"BankName";

			
			// Order_Details Table - column names
			public static final String KEY_QTY = "Qty";
		
			// Customer Table - column names
			public static final String KEY_NAME = "name";
			public static final String KEY_COMPANY = "Company";
			public static final String KEY_ADDRESS = "Address";
			public static final String KEY_CELL = "Cell";
			
			// bills Table - column name
			public static final String KEY_BALANCE = "balance";
			public static final String KEY_RECEIVED = "received";
}
