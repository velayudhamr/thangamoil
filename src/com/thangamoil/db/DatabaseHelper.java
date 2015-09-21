/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : this class contain the all Database Method
 this is add to test the file in github
 */
package com.thangamoil.db;

import java.util.ArrayList;
import java.util.List;

import com.thangamoil.Model.Agent;
import com.thangamoil.Model.Bills;
import com.thangamoil.Model.Customer;
import com.thangamoil.Model.Items;
import com.thangamoil.Model.Receipt;
import com.thangamoil.Model.Recip_Edit;
import com.thangamoil.Model.Order_Details;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements Databaseneed{

		
		public DatabaseHelper(Context context) {
			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		// Logcat tag
		private static final String LOG = "DatabaseHelper";

		// Table Create Statements
		// Receipt table create statement
		private static final String CREATE_Receipt = "CREATE TABLE "
				+ TABLE_RECEIPT +"("
				+ KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_RECEIPTINO + " TEXT,"
				+ KEY_CUST_ID + " TEXT," 
				+ KEY_BILLNO + " TEXT," 
				+ KEY_AMOUNT + " INTEGER,"
				+ KEY_CHEQUENO + " TEXT,"
				+ KEY_CHEQUEDATE + " TEXT,"
				+ KEY_BANKNAME + " TEXT," 
				+ KEY_DATE + " TEXT"+")";
		
		// SALES_DETAILS table create statement
		private static final String CREATE_Order_Details = "CREATE TABLE "
				+ TABLE_ORDER_DETAILS + "("
				+ KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ KEY_CUST_ID + " TEXT,Item_192 INTEGER,Item_193 INTEGER,Item_194 INTEGER,Item_195 INTEGER,Item_196 INTEGER,Item_197 INTEGER,"
				+ KEY_DATE + " TEXT "+ ")";

		// CUSTOMER table create statement
		private static final String CREATE_CUSTOMER = "CREATE TABLE "
				+ TABLE_CUSTOMER + "(" 
				+ KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ KEY_NAME + " TEXT," 
				+ KEY_ADDRESS + " TEXT,"
				+ KEY_CELL + " TEXT,"
				+ KEY_CUST_ID + " TEXT" + ")";
		
		// User table create statement
		private static final String CREATE_USER = "CREATE TABLE "
				+ TABLE_USER + "("
				+ KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_USERNAME	+ " TEXT ,"
				+ KEY_PASSWORD + " TEXT,"
				+ KEY_ROLE + " TEXT,"
				+ KEY_USER_ID + " TEXT"+")";
		
		// BILLS table create statement
		private static final String CREATE_BILLS = "CREATE TABLE "
				+ TABLE_BILLS + "("
				+ KEY_CUST_ID + " TEXT,"
				+ KEY_BILLNO + " TEXT,"
				+ KEY_BALANCE + " INTEGER,"
				+ KEY_RECEIVED	+ " INTEGER ,"
				+ KEY_DATE + " TEXT"+")";
		
		// ------------------------ Creating the table ----------------//
		@Override
		public void onCreate(SQLiteDatabase db) {

			// creating required tables
			db.execSQL(CREATE_Receipt);
			db.execSQL(CREATE_Order_Details);
			db.execSQL(CREATE_CUSTOMER);
			db.execSQL(CREATE_BILLS);
			db.execSQL(CREATE_USER);
		}
		
		// ------------------------ UpGrade the table ----------------//
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			// on upgrade drop older tables
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_Receipt);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_Order_Details);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_CUSTOMER);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_BILLS);
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_USER);
			
			// create new tables
			onCreate(db);
		}
		
		// ------------------------ insert Agent into User table ----------------//
		public long insertAgent(Agent agt) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_USERNAME,agt.getUsername());
			values.put(Databaseneed.KEY_PASSWORD,agt.getPassword());
			values.put(Databaseneed.KEY_USER_ID,agt.getUser_Id());
			values.put(Databaseneed.KEY_ROLE,agt.getRole());
			long rt = db.insert(TABLE_USER, null, values);
			db.close();
			return rt;
		}
		
		// ------------------------ insert Agent into User table ----------------//
		public long UpdateAgent(Agent agt) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_USERNAME,agt.getUsername());
			values.put(Databaseneed.KEY_PASSWORD,agt.getPassword());
			//values.put(Databaseneed.KEY_USER_ID,agt.getUser_Id());
			//values.put(Databaseneed.KEY_ROLE,agt.getRole());
			long rt =db.update(TABLE_USER,values ,KEY_USER_ID + "= ?",new String[] { String.valueOf(agt.getUser_Id()) });
			db.close();			
			return rt;
		}
		
		public Agent CheckLogin_Agent(String User,String Pass) {
			
			Agent Agt = new Agent();
			SQLiteDatabase db = this.getReadableDatabase();
			String selectQuery = "SELECT * FROM " + TABLE_USER + " where Username = '"+ User+"' AND Password = '"+ Pass+"'";
			Log.e(LOG, selectQuery);
			Cursor c = db.rawQuery(selectQuery,null);
			if(c.moveToFirst()){
				
				Agt.setUsername(c.getString(c.getColumnIndex("Username")));
				Agt.setRole(c.getString(c.getColumnIndex("Role")));
				Agt.setUser_Id(c.getString(c.getColumnIndex("User_Id")));
				db.close();
				return Agt;
				
			}else{
				Agt.setUser_Id("0");
				Agt.setRole("0");
				db.close();
				return Agt;
			}
			
		}
		
		public Agent Selected_UserDetail(String UserId) {
				
				Agent Agt = new Agent();
				SQLiteDatabase db = this.getReadableDatabase();
				String selectQuery = "SELECT * FROM " + TABLE_USER + " where User_Id = '"+ UserId+"'";
				Log.e(LOG, selectQuery);
				Cursor c = db.rawQuery(selectQuery,null);
				if(c.moveToFirst()){
					
					Agt.setUsername(c.getString(c.getColumnIndex("Username")));
					Agt.setPassword(c.getString(c.getColumnIndex("Password")));
					
					Log.e(LOG,"User Name from user table "+String.valueOf(c.getString(c.getColumnIndex("Username"))));
					Log.e(LOG,"Password from user table "+String.valueOf(c.getString(c.getColumnIndex("Password"))));
				}
				db.close();
				return Agt;
			}
		
		public List<Agent>  getUserList() {
			
			List<Agent> ar = new ArrayList<Agent>();
			SQLiteDatabase db = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM " + TABLE_USER ;
			
			Log.e(LOG, selectQuery);
			Cursor c = db.rawQuery(selectQuery,null);
			
			if (c.moveToFirst()) {
				do {
					
					Agent td = new Agent();
					
					td.setUser_Id(c.getString(c.getColumnIndex("User_Id")));
					td.setRole(c.getString(c.getColumnIndex("Role")));
					
					Log.e(LOG,"User Id from user table "+String.valueOf(c.getInt(c.getColumnIndex("User_Id"))));
					Log.e(LOG,"Role for above user ID from user table "+String.valueOf(c.getInt(c.getColumnIndex("Role"))));
					// adding to list
					ar.add(td);
				} while (c.moveToNext());
			}
			db.close();
			return ar;
		}
		
		// ------------------------ insert Customer into Customer table ----------------//
		public void insertcustomer(Customer cus) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			//values.put(Databaseneed.KEY_Row_Id,cus.getRow_Id());
			values.put(Databaseneed.KEY_NAME,cus.getName());
			//values.put(Databaseneed.KEY_COMPANY,cus.getCompany());
			values.put(Databaseneed.KEY_ADDRESS,cus.getAddress());
			values.put(Databaseneed.KEY_CELL,cus.getCell());
			values.put(Databaseneed.KEY_CUST_ID,cus.getCust_Id());
			
			// insert row
			db.insert(TABLE_CUSTOMER, null, values);
			db.close();
		}
		
		// ------------------------ insert bills into Bills table ----------------//
		public void insertnBills(Bills bill) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_CUST_ID,bill.getCust_Id());
			values.put(Databaseneed.KEY_DATE,bill.getDate());
			values.put(Databaseneed.KEY_BILLNO,bill.getBill_No());
			values.put(Databaseneed.KEY_BALANCE,bill.getBalance());
			
			// insert row
			db.insert(TABLE_BILLS, null, values);
			db.close();
		}
		
		// ------------------------ Get Bill in Bills table using Customer Id ----------------//
		public List<Bills>  getBills(String  Cus_id) {
			
			List<Bills> ar = new ArrayList<Bills>();
			SQLiteDatabase db = this.getReadableDatabase();
			String selectQuery = "SELECT * FROM " + TABLE_BILLS + " where Cust_Id = '"+Cus_id+"'";
			
			Log.e(LOG, selectQuery);
			Cursor c = db.rawQuery(selectQuery,null);
			
			if (c.moveToFirst()) {
				do {
					Bills td = new Bills();
					Log.e(LOG,"Bill NO Form Get Bills Method"+String.valueOf(c.getInt(c.getColumnIndex("Bill_No"))));
					td.setCust_Id(c.getString(c.getColumnIndex("Cust_Id")));
					Log.e(LOG,"Bill NO Form Get Bills Method"+4);
					td.setBill_No(c.getString(c.getColumnIndex("Bill_No")));
					Log.e(LOG,"Bill NO Form Get Bills Method"+3);
					td.setBalance(c.getInt(c.getColumnIndex("balance")));
					Log.e(LOG,"Bill NO Form Get Bills Method"+2);
					td.setReceived(c.getInt(c.getColumnIndex("received")));
					Log.e(LOG,"Bill NO Form Get Bills Method"+1);
					td.setDate(c.getString(c.getColumnIndex("Date")));
					// adding to list
					ar.add(td);
				} while (c.moveToNext());
			}
			db.close();
			return ar;
		}
		
		// ------------------------ Get Amount ----------------//
		public Recip_Edit  getAmount(String bill_id) {
			
			Recip_Edit Re = new Recip_Edit();
			
			String Cus_Id;
			SQLiteDatabase db = this.getReadableDatabase();
			String selectQuery = "SELECT * FROM " + TABLE_BILLS + " where Bill_No = '"+bill_id+"'";
			Log.e(LOG, selectQuery);
			Cursor c = db.rawQuery(selectQuery,null);
			Log.e(LOG,"Bill table number of row"+ c.getCount());
			if (c.moveToFirst())
					Re.setBalance(c.getInt(c.getColumnIndex("balance")));
					Cus_Id = c.getString(c.getColumnIndex("Cust_Id"));
					//Log.e(LOG,String.valueOf(c.getInt(c.getColumnIndex("balance"))));
					if(Cus_Id != null){
						String selectQuery_Rect = "SELECT * FROM " + TABLE_RECEIPT + " where Cust_Id = '"+Cus_Id+"' AND Bill_No ='"+bill_id+"'";
						Log.e(LOG, selectQuery_Rect);
						Cursor c_Recp = db.rawQuery(selectQuery_Rect,null);
						if (c_Recp.moveToFirst() ){
							Re.setBill_No(bill_id);
							Re.setRow_Id(c_Recp.getInt(c_Recp.getColumnIndex("Row_Id")));
							Re.setReceiptNo(c_Recp.getString(c_Recp.getColumnIndex("ReceiptNo")));
							Re.setCust_Id(c_Recp.getString(c_Recp.getColumnIndex("Cust_Id")));
							Re.setAmount(c_Recp.getInt(c_Recp.getColumnIndex("Amount")));
							Re.setChequeNo(c_Recp.getString(c_Recp.getColumnIndex("ChequeNo")));
							Re.setChequeDate(c_Recp.getString(c_Recp.getColumnIndex("ChequeDate")));
							Re.setBankBranch(c_Recp.getString(c_Recp.getColumnIndex("BankName")));
							Re.setDate(c_Recp.getString(c_Recp.getColumnIndex("Date")));
							
						}
					}
			db.close();
			return Re;
		}
		
		// ------------------------ insert data into Reception table ----------------//
		public long insertReception(Receipt rec) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			//values.put(Databaseneed.KEY_RECEIPTINO,rec.getReceiptNo());
			values.put(Databaseneed.KEY_CUST_ID,rec.getCust_Id());
			values.put(Databaseneed.KEY_BILLNO,rec.getBill_No());
			values.put(Databaseneed.KEY_AMOUNT,rec.getAmount());
			values.put(Databaseneed.KEY_CHEQUENO,rec.getChequeNo());
			values.put(Databaseneed.KEY_CHEQUEDATE,rec.getChequeDate());
			values.put(Databaseneed.KEY_BANKNAME,rec.getBankBranch());
			values.put(Databaseneed.KEY_DATE,rec.getDate());
			// insert row
			long out = db.insert(TABLE_RECEIPT, null, values);
			db.close();
			return out;
		}
		
		// ------------------------ Get data into Reception table ----------------//
		public void getReception(){

			
			SQLiteDatabase db = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM " + TABLE_RECEIPT ;
			Log.e(LOG, selectQuery);
			Cursor c = db.rawQuery(selectQuery,null);
			if (c.moveToFirst()) {
				do {

					Log.i(LOG,c.toString());
					Log.e(LOG,String.valueOf(c.getInt(c.getColumnIndex("ReceiptNo"))));
					Log.e(LOG,c.getString(c.getColumnIndex("Bill_No")));
					Log.e(LOG,String.valueOf(c.getFloat(c.getColumnIndex("Amount"))));
					
				} while (c.moveToNext());
			}
			db.close();
		}
	
		// ------------------------ insert data into Sales_Details table ----------------//
		public long  insertOrder(Order_Details ord) {
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_CUST_ID,ord.getCust_Id());
			values.put("Item_192",ord.getItem_192());
			values.put("Item_193",ord.getItem_193());
			values.put("Item_194",ord.getItem_194());
			values.put("Item_195",ord.getItem_195());
			values.put("Item_196",ord.getItem_196());
			values.put("Item_197",ord.getItem_197());
			values.put(Databaseneed.KEY_DATE,ord.getDate());
			
			// insert row
			long out = db.insert(TABLE_ORDER_DETAILS, null, values);
			Log.d(LOG,"Order Inserted with this Id "+out);
			Log.d(LOG,"Order Inserted with this Id "+ord.getCust_Id());
			db.close();
			return out;
		}
		
		// ------------------------ Delete all bill in bills table ----------------//
		public void DeleteBills(){
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_BILLS,null,null);
			db.close();
		}
		
		// ------------------------ Delete all Customer in Customer table ----------------//
		public void DeleteCustomers(){
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CUSTOMER,null,null);
			db.close();
		}
		
		// ------------------------ Delete all Collection in Collection table ----------------//
		public void DeleteReceipt(){
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_RECEIPT,null,null);
			db.close();
		}
		
		// ------------------------ Delete all Sales Details in Sales Details table ----------------//
		public void DeleteOrder_DETAILS(){
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_ORDER_DETAILS,null,null);
			db.close();
		}

		// ------------------------ Get Customer in Customer table ----------------//
		public Customer getCustomerById(String  Cus_id){
			Customer cus = new Customer();
			
			SQLiteDatabase db = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER + "  where Cust_Id = '"+Cus_id+"'";
	
			Log.e(LOG, selectQuery);
			Cursor cr = db.rawQuery(selectQuery,null);
			
			if (cr.moveToFirst()){
				cus.setName(cr.getString(cr.getColumnIndex("name")));
				//cus.setCompany(cr.getString(cr.getColumnIndex("Company")));
				cus.setAddress(cr.getString(cr.getColumnIndex("Address")));
				cus.setCell(cr.getString(cr.getColumnIndex("Cell")));
				cus.setCust_Id(cr.getString(cr.getColumnIndex("Cust_Id")));
				Log.e(LOG,String.valueOf(cr.getInt(cr.getColumnIndex("Cust_Id"))));
			}
					// adding to todo list
			db.close();
			return cus;
		}

		// ------------------------ Up Reciption in Collection table ----------------//
		public long UpdateReception(Receipt rec) {
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_CUST_ID,rec.getCust_Id());
			values.put(Databaseneed.KEY_BILLNO,rec.getBill_No());
			values.put(Databaseneed.KEY_AMOUNT,rec.getAmount());
			values.put(Databaseneed.KEY_CHEQUENO,rec.getChequeNo());
			values.put(Databaseneed.KEY_CHEQUEDATE,rec.getChequeDate());
			values.put(Databaseneed.KEY_BANKNAME,rec.getBankBranch());
			values.put(Databaseneed.KEY_DATE,rec.getDate());
			
			// insert row
			long rt =  db.update(TABLE_RECEIPT,values ,KEY_ROW_ID + "= ?",new String[] { String.valueOf(rec.getRow_Id()) });
			db.close();
			return rt;
		}

		// ------------------------ Get ordered item in order_detail Table using cus id ----------------//
		public Order_Details getItems(String Cus_id){
			
			Order_Details itm = new Order_Details();
			SQLiteDatabase db = this.getReadableDatabase();
			
			String selectQuery = "SELECT * FROM " + TABLE_ORDER_DETAILS + "  where Cust_Id = '"+Cus_id+"'";
			Log.e(LOG, selectQuery);
			Cursor cr = db.rawQuery(selectQuery,null);
			
			if (cr.moveToFirst()){
				
				itm.setRow_Id(cr.getInt(cr.getColumnIndex("Row_Id")));
				itm.setCust_Id(cr.getString(cr.getColumnIndex("Cust_Id")));
				itm.setItem_192(cr.getInt(cr.getColumnIndex("Item_192")));
				itm.setItem_193(cr.getInt(cr.getColumnIndex("Item_193")));
				itm.setItem_194(cr.getInt(cr.getColumnIndex("Item_194")));
				itm.setItem_195(cr.getInt(cr.getColumnIndex("Item_195")));
				itm.setItem_196(cr.getInt(cr.getColumnIndex("Item_196")));
				itm.setItem_197(cr.getInt(cr.getColumnIndex("Item_197")));
				itm.setDate(cr.getString(cr.getColumnIndex("Date")));
				
				Log.e(LOG,"Cust_Id "+cr.getString(cr.getColumnIndex("Cust_Id")));
				Log.e(LOG,"item_192 "+cr.getInt(cr.getColumnIndex("Item_192")));
				Log.e(LOG,"item_193 "+cr.getInt(cr.getColumnIndex("Item_193")));
				Log.e(LOG,"item_194 "+cr.getInt(cr.getColumnIndex("Item_194")));
				Log.e(LOG,"item_195 "+cr.getInt(cr.getColumnIndex("Item_195")));
				Log.e(LOG,"item_196 "+cr.getInt(cr.getColumnIndex("Item_196")));
				Log.e(LOG,"item_197 "+cr.getInt(cr.getColumnIndex("Item_197")));
				
			}
			db.close();
			return itm;
		}
		
		// ------------------------update order  in order_detail Table ----------------//
		public long  UpdateOrder(Order_Details ord) {
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_CUST_ID,ord.getCust_Id());
			values.put("Item_192",ord.getItem_192());
			values.put("Item_193",ord.getItem_193());
			values.put("Item_194",ord.getItem_194());
			values.put("Item_195",ord.getItem_195());
			values.put("Item_196",ord.getItem_196());
			values.put("Item_197",ord.getItem_197());
			values.put(Databaseneed.KEY_DATE,ord.getDate());

			long rt = db.update(TABLE_ORDER_DETAILS,values ,KEY_ROW_ID + "= ?",new String[] { String.valueOf(ord.getRow_Id()) });
			db.close();
			return rt;
			
		}

		// ------------------------this methos are used to insert reciption in into recipt Table ----------------//
		public long insertReciptionId(long id,String prefix){
			
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(Databaseneed.KEY_RECEIPTINO,prefix);
			Log.i(LOG,"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+prefix);
			long rt =  db.update(TABLE_RECEIPT,values ,KEY_ROW_ID + "= ?",new String[] { String.valueOf(id) });
			db.close();
			return rt;
			
		}
		
		// ------------------------Get all order in order_detail Table displaying orders ----------------//		
		public List<Order_Details> Gen_Report(){
			 
			List<Order_Details> ar = new ArrayList<Order_Details>();
			SQLiteDatabase db = this.getReadableDatabase();
			String selectQuery = "SELECT * FROM " + TABLE_ORDER_DETAILS ;
			
			Log.e(LOG, selectQuery);
			Cursor c = db.rawQuery(selectQuery,null);
			
			if (c.moveToFirst()) {
				do {
					Order_Details td = new Order_Details();
					
					td.setCust_Id(c.getString(c.getColumnIndex("Cust_Id")));
					td.setItem_192(c.getInt(c.getColumnIndex("Item_192")));
					td.setItem_193(c.getInt(c.getColumnIndex("Item_193")));
					td.setItem_194(c.getInt(c.getColumnIndex("Item_194")));
					td.setItem_195(c.getInt(c.getColumnIndex("Item_195")));
					td.setItem_196(c.getInt(c.getColumnIndex("Item_196")));
					td.setItem_197(c.getInt(c.getColumnIndex("Item_197")));
					td.setDate(c.getString(c.getColumnIndex("Date")));

					Log.e(LOG,"Bill NO Form Get Bills Method "+String.valueOf(c.getString(c.getColumnIndex("Cust_Id"))));
					
					// adding to list
					ar.add(td);
				} while (c.moveToNext());
			}
			db.close();
			return ar;
		}
		
		// ------------------------Get all order in order_detail Table displaying orders ----------------//		
		public long insetRecived(int amount,String billId){
			 
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(Databaseneed.KEY_RECEIVED,amount);
			Log.i(LOG,"iiiiiiiiiiiii   amount"+amount);
			
			long rt = db.update(TABLE_BILLS,values ,KEY_BILLNO + "= ?",new String[] { billId });
			db.close();
			return rt;
		}

		public int checkcustomer(String Cus_id){
			
			SQLiteDatabase db = this.getReadableDatabase();

			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER + "  where Cust_Id = '"+Cus_id+"'";
			Log.e(LOG, selectQuery);
			Cursor cr = db.rawQuery(selectQuery,null);
			
			if (cr.moveToFirst()){
				db.close();
				return 1;
			}else{
				db.close();
				return 0;
			}
		}
		
		public int checkbillNo(String Bill_No){
			
			SQLiteDatabase db = this.getReadableDatabase();

			String selectQuery = "SELECT * FROM " + TABLE_BILLS + "  where Bill_No = '"+Bill_No+"'";
			Log.e(LOG, selectQuery);
			Cursor cr = db.rawQuery(selectQuery,null);
			
			if (cr.moveToFirst()){
				db.close();
				return 1;
			}else{
				db.close();
				return 0;
			}
		}

}
