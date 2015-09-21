/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : It is the Main Activity to Get receipt  and Order so on
 */
package com.thangamoil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.thangamoil.Model.Bills;
import com.thangamoil.Model.Customer;
import com.thangamoil.Model.Receipt;
import com.thangamoil.Model.Recip_Edit;
import com.thangamoil.Model.Order_Details;
import com.thangamoil.db.DatabaseHelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract.Colors;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Logcat tag
	private static final String LOG = "MainActivity";
	
	AutoCompleteTextView text;
	MultiAutoCompleteTextView text1;
	String[] banks={"State Bank India","State Bank of Bikaner & Jaipur","State Bank of Hyderabad",
			"State Bank of Patiala","State Bank of Mysore","State Bank of Travancore",
			"Allahabad Bank","Andhra Bank","Bank of Baroda",
			"Bank of India","Bank of Maharashtra","Canara Bank","Central Bank of India",
			"Corporation Bank","Dena Bank","Indian Bank",
			"Indian Overseas Bank","Oriental Bank of Commerce","Punjab & Sind Bank",
			"Punjab National Bank","Syndicate Bank","UCO Bank",
			"Union Bank of India","United Bank of India","Vijaya Bank",
			"Bharatiya Mahila Bank","IDBI Bank","Post Bank of India",
			"Catholic Syrian Bank","City Union Bank","Dhanlaxmi Bank",
			"Federal Bank","ING Vysya Bank ","Jammu and Kashmir Bank",
			"Karnataka Bank","Karur Vysya Bank","Lakshmi Vilas Bank",
			"Nainital Bank","Ratnakar Bank","South Indian Bank",
			"Tamilnad Mercantile Bank","ICICI Bank","HDFC Bank","Axis Bank",
			"Kotak Mahindra Bank","IndusInd Bank","Development Credit Bank",
			"Yes Bank","Bandhan Bank","IDFC","Puduvai Bharathiar Grama Bank",
			"Pandyan Grama Bank","Pallavan Grama Bank"};
	
	private EditText shop_id;
	private EditText amount_id;
	private EditText checkno_id;
	private EditText checkdate_id;
	private AutoCompleteTextView Bankname_id;
	private EditText EDT_1;
	private EditText EDT_2;
	private EditText EDT_3;
	private EditText EDT_4;
	private EditText EDT_5;
	private EditText EDT_6;
	
	private TextView balance;
	private TextView Address;
	
	private Spinner selected_bill;
	
	private RadioButton ByCash;
	private RadioButton bycheck;
	private RadioButton Reciptpage_radio;
	private RadioButton orderpage_radio;
	private RadioGroup page_radio;
	
	private TableRow address_order_row;
	private TableRow balance_row;
	private TableRow payment_row;
	private TableRow amount_row;
	private TableRow check_no_row;
	private TableRow check_date_row;
	private TableRow check_Bankname_row;
	private TableRow save_btn_row;
	private TableRow  Error_row;
	private TableLayout table_layout;
	
	private TableLayout Recipt_table;
	private TableLayout  Order_table;
	Calendar myCalendar = Calendar.getInstance();
	public int Rep_rowId = 0;
	public String mobile_no = null;
	public int order_id = 0;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
			
		shop_id 		= 	(EditText) 		findViewById(R.id.shop_id_id);
		
		Address			=	(TextView) 		findViewById(R.id.address_text);
		balance 		=	(TextView) 		findViewById(R.id.balance_id);
		
		selected_bill 	=	(Spinner) 		findViewById(R.id.bull_no_id);

		
		ByCash 			=	(RadioButton) 	findViewById(R.id.cash_id);
		bycheck 		=	(RadioButton) 	findViewById(R.id.check_id);
		
		page_radio		=	(RadioGroup) 	findViewById(R.id.page_id);
		Reciptpage_radio=	(RadioButton) 	findViewById(R.id.Reciptpage);
		orderpage_radio =	(RadioButton) 	findViewById(R.id.orderpage);
		
		amount_id 		=	(EditText) 		findViewById(R.id.amount_id);

		checkno_id 		=	(EditText) 		findViewById(R.id.checkno_id);
		checkdate_id 	=	(EditText) 		findViewById(R.id.checkdate_id);
		Bankname_id = (AutoCompleteTextView) findViewById(R.id.Bankname_id);
		
		EDT_1 		= (EditText) findViewById(R.id.id_1000);
		EDT_2 		= (EditText) findViewById(R.id.id_500);
		EDT_3 		= (EditText) findViewById(R.id.id_200);
		EDT_4 		= (EditText) findViewById(R.id.id_100);
		EDT_5 		= (EditText) findViewById(R.id.id_50);
		EDT_6 		= (EditText) findViewById(R.id.tin_id);
		
    	Recipt_table 		= (TableLayout) findViewById(R.id.Recipt_table); 
    	Order_table		 	= (TableLayout) findViewById(R.id.Order_table);
    	address_order_row 	= (TableRow) findViewById(R.id.address_order_row);
    	
    	Error_row			= (TableRow) findViewById(R.id.Error_row);
		balance_row 		= (TableRow) findViewById(R.id.balance_row);
		payment_row 		= (TableRow) findViewById(R.id.payment_row);
		amount_row 			= (TableRow) findViewById(R.id.amount_row);
		check_no_row 		= (TableRow) findViewById(R.id.check_no_row);
		check_date_row 		= (TableRow) findViewById(R.id.check_date_row);
		check_Bankname_row 	= (TableRow) findViewById(R.id.check_Bankname_row);
		save_btn_row 		= (TableRow) findViewById(R.id.save_btn_row);
	
		
		address_order_row.setVisibility(View.GONE);
		
		Recipt_table.setVisibility(View.GONE);
		Order_table.setVisibility(View.GONE);
		
		Error_row.setVisibility(View.GONE);
		
		ByCash.setChecked(true);
		check_no_row.setVisibility(View.GONE);
		check_date_row.setVisibility(View.GONE);
		check_Bankname_row.setVisibility(View.GONE);
		
		getActionBar().setTitle(" Welcome "+ globalVariable.getUsername());
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, banks);
		Bankname_id.setAdapter(adapter);
		
		
		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
		        
		    	// TODO Auto-generated method stub
		        myCalendar.set(Calendar.YEAR, year);
		        myCalendar.set(Calendar.MONTH, monthOfYear);
		        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        
		        String myFormat = "dd/MM/yyyy"; 
		        
		        //In which you need put here
		        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
		        checkdate_id.setText(sdf.format(myCalendar.getTime()));
		    }
		};

		checkdate_id.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {

	        	// TODO Auto-generated method stub
	        	DatePickerDialog dialog =   new DatePickerDialog(MainActivity.this, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
	        	
	        	Date dt = new Date();
	        	Calendar c = Calendar.getInstance(); 
	        	c.setTime(dt); 
	        	
	        	c.add(Calendar.DATE,30);
	        	dt = c.getTime();
	        	dialog.getDatePicker().setMaxDate(dt.getTime());
	        	Log.e(LOG,"After millis "+ dt.getTime());
	        	
	        	c.add(Calendar.DATE,-90);
	        	dt = c.getTime();
	        	dialog.getDatePicker().setMinDate(dt.getTime());
	        	Log.e(LOG,"Before millis "+ dt.getTime());
	        	
	        	dialog.show();	        	
	        }
	    });

		selected_bill.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				
		    	amount_id.getText().clear();
		    	checkno_id.getText().clear();
	        	checkdate_id.getText().clear();
	        	Bankname_id.getText().clear();
	        	
	        	ByCash.setChecked(true);
	        	check_no_row.setVisibility(View.GONE);
        		check_date_row.setVisibility(View.GONE);
        		check_Bankname_row.setVisibility(View.GONE);
        		
		    	DatabaseHelper dbhelper = new  DatabaseHelper(getApplicationContext());
		    	
		    	Recip_Edit Rept = new Recip_Edit();
		    	String billno = selected_bill.getSelectedItem().toString();
		    	Log.e(LOG,"selected String is  "+billno);
		    	Rept = dbhelper.getAmount(billno);
		    	
		    	balance.setText(String.valueOf(Rept.getBalance()));
		    	
		    	if(Rept.getRow_Id() > 0){
		    		
		    		Log.e(LOG,Rept.getCust_Id());
			    	Log.e(LOG,String.valueOf(Rept.getReceiptNo()));
			    	
			    	Rep_rowId = Rept.getRow_Id();
		    		
		    		amount_id.setText(String.valueOf(Rept.getAmount()));
		    		
		    		if(Rept.getChequeDate() != null){
		    			checkdate_id.setText(Rept.getChequeDate());	
		    		}
		    		
		    		if(Rept.getChequeNo() != null){
		    			checkno_id.setText(Rept.getChequeNo());
		    			
		    			bycheck.setChecked(true);
			        	
		    			check_no_row.setVisibility(View.VISIBLE);
		        		check_date_row.setVisibility(View.VISIBLE); 
		        		check_Bankname_row.setVisibility(View.VISIBLE);
		    		}
		    		
		    		if(Rept.getBankBranch() != null){
		    			Bankname_id.setText(Rept.getBankBranch());	
		    		}
		    	}else { Rep_rowId = 0;}
		    	dbhelper.close();
		    }
			
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    	
		    }
		});
		
		Button btnGet = (Button) findViewById(R.id.go_btn);
		btnGet.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View view) {
	        	 
	        	 Log.i(LOG,"I am in Go button onClick event");
	        	 
	        	page_radio.clearCheck();
	        	amount_id.getText().clear();
	        	checkno_id.getText().clear();
	        	checkdate_id.getText().clear();
	        	Bankname_id.getText().clear();
	        	EDT_1.getText().clear();
	        	EDT_2.getText().clear();
	        	EDT_3.getText().clear();
	        	EDT_4.getText().clear();
	        	EDT_5.getText().clear();
	        	EDT_6.getText().clear();
	        	
	        	address_order_row.setVisibility(View.GONE);
	    		
	    		Recipt_table.setVisibility(View.GONE);
	    		Order_table.setVisibility(View.GONE);
	    		
	    		Error_row.setVisibility(View.GONE);
	    		
	        	DatabaseHelper dbhelper1 = new  DatabaseHelper(getApplicationContext());
	        	Customer custom = new Customer();
	        	custom = dbhelper1.getCustomerById(shop_id.getText().toString());
	        	
	        	if(custom.getCust_Id() != null)
	        	{
	        		
	        		Address.setText(" Name : "+custom.getName()
	        				+System.getProperty ("line.separator")
	        				+" Address :"+custom.getAddress()
	        				+System.getProperty ("line.separator")
	        				+" Cell : "+ custom.getCell());
	        		
	        		address_order_row.setVisibility(View.VISIBLE);
	        		
	        		Address.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
	        		Address.setTextColor(Color.WHITE);
	        		Address.setTextSize(15);
	        		Address.setTypeface(null,Typeface.BOLD);
	        		
	        		mobile_no = custom.getCell();
	        		
	        		List<Bills> bill_list = new ArrayList<Bills>();
		        	bill_list = dbhelper1.getBills(shop_id.getText().toString());
		        	ArrayList<String> arrayList1 = new ArrayList<String>();
		        	 if(bill_list.size() >0){
		        		 	int total_balance = 0;
		        		 	int total_receiv = 0;
		        		 	
			            	for(int i = 0; i<bill_list.size();i++) {
				        		arrayList1.add(bill_list.get(i).getBill_No());
			            		Log.e(LOG,"hi i am in bille generate method");
			            		 total_balance += bill_list.get(i).getBalance();
			            		 total_receiv +=  bill_list.get(i).getReceived();
			            	}
			            	total_balance = total_balance - total_receiv;
			            	TextView CA=(TextView) findViewById(R.id.totalbalance_id);
			            	if(total_balance > 0){
			            		CA.setText(String.valueOf(total_balance));	
			            	}else{
			            		CA.setText("NILL");
			            	}
			            	selected_bill.setAdapter(null);
				        	ArrayAdapter<String> adp = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_dropdown_item,arrayList1);
				        	selected_bill.setAdapter(adp);
				        	selected_bill.setVisibility(View.VISIBLE);
				        	adp.notifyDataSetChanged();
			            }else{
			            	Error_row.setVisibility(View.VISIBLE);
				        	balance_row.setVisibility(View.GONE);
				        	payment_row.setVisibility(View.GONE);
				        	amount_row.setVisibility(View.GONE);
				        	check_no_row.setVisibility(View.GONE);
			        		check_date_row.setVisibility(View.GONE);
			        		check_Bankname_row.setVisibility(View.GONE);
				        	save_btn_row.setVisibility(View.GONE);
			            }
		            Order_Details get_SD =new Order_Details();
		    		get_SD = dbhelper1.getItems(custom.getCust_Id());
		    		if(get_SD.getRow_Id() > 0){
		    			for(int g = 1; g <= 6; g++) {
		    				switch(g){
		            			case 1:
		            				if(get_SD.getItem_192() != 0){
		            					EDT_1.setText(String.valueOf(get_SD.getItem_192()));
		            				}
		            				break;
		            			case 2:
		            				if(get_SD.getItem_193() != 0){
		            					EDT_2.setText(String.valueOf(get_SD.getItem_193()));
		            				}
		            				break;
		            			case 3:
		            				if(get_SD.getItem_194() != 0){
		            					EDT_3.setText(String.valueOf(get_SD.getItem_194()));
		            				}
		            				break;
		            			case 4:
		            				if(get_SD.getItem_195() != 0){
		            					EDT_4.setText(String.valueOf(get_SD.getItem_195()));
		            				}
		            				break;
		            			case 5:
		            				if(get_SD.getItem_196() != 0){
		            					EDT_5.setText(String.valueOf(get_SD.getItem_196()));
		            				}
		    	        			break;
		            			case 6:
		            				if(get_SD.getItem_197() != 0){
		            					EDT_6.setText(String.valueOf(get_SD.getItem_197()));
		            				}
		    	        			break;
		            		}
		    			}
		    			order_id = get_SD.getRow_Id();
		    		}else{ order_id = 0; }
	        	}else{
	        		Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Correct Customer ID", Toast.LENGTH_SHORT);
	        		toast.setGravity(Gravity.CENTER, 0, 0);
            		toast.show();
	        	}
	         }
		});
		
        Button btnToSave = (Button) findViewById(R.id.receipt_btn);
	    btnToSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String date = new SimpleDateFormat("dd/mm/yyyy").format(new Date());
				if(!amount_id.getText().toString().isEmpty() && amount_id.getText().toString() != null){
					int current_balance = Integer.parseInt(balance.getText().toString());
					final int current_amount = Integer.parseInt(amount_id.getText().toString());
	            	final int balance = (current_balance - current_amount);
	            	if(current_balance < current_amount)
	            	{
	            		amount_id.setText("");
	            		amount_id.requestFocus(1);
	            		Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Correct Value", Toast.LENGTH_SHORT);
    	        		toast.setGravity(Gravity.CENTER, 0, 0);
                		toast.show();
	            	}else{
	            		DatabaseHelper dbhelper4 = new  DatabaseHelper(getApplicationContext());
	    				Receipt rpt = new Receipt();
	    	        	if(bycheck.isChecked()){
	    	        		String ch_date = checkdate_id.getText().toString();
	        	        	String ch_no = checkno_id.getText().toString();
	        	        	String BankName = Bankname_id.getText().toString();
	        	        	if(!ch_date.isEmpty() && !ch_no.isEmpty() && !BankName.isEmpty()){
	        	        		String bill_id=selected_bill.getSelectedItem().toString();
	        	        		rpt.setCust_Id(shop_id.getText().toString());
	            				rpt.setBill_No(bill_id);	        	
	            	        	rpt.setAmount(Integer.valueOf(amount_id.getText().toString()));
	            	        	rpt.setDate(date);
	            	        	rpt.setChequeDate(ch_date);
	        	        		rpt.setChequeNo(ch_no);
	        	        		rpt.setBankBranch(BankName);
	        	        		//rpt.setReceiptNo(globalVariable.getUsername());
	        	        		
	        	        		long row_id = 0 ;
	        	        		if(Rep_rowId != 0){
	        	        			rpt.setRow_Id(Rep_rowId);
	        	        			row_id = dbhelper4.UpdateReception(rpt);
	        	        			Rep_rowId = 0;
	        	        		}else{
	        	        			row_id = dbhelper4.insertReception(rpt);
	        	        			String rid = globalVariable.getUsername()+row_id;
	        	        			dbhelper4.insertReciptionId(row_id,rid);
	        	        		}
	        	        		if(row_id>0){
        	        				dbhelper4.insetRecived(Integer.valueOf(amount_id.getText().toString()),bill_id);
	        	        			final String message = "Dear Customer,Receipt Number: Receipt Amount: "+ current_amount +"Balance Amount: "+ balance +" Thank You, -THANGAM";
	        	        				new Thread(new Runnable(){
	        	        				    @Override
	        	        				    public void run() {
	        	        				    	try{
	        	        				    		HttpClient Client = new DefaultHttpClient();
	        	        				    		String SetServerString = "";
	    	            	        				String msg =   URLEncoder.encode(message,"UTF-8");
	    	            	        				String  strPostData = "http://www.smsintegra.com/smsweb/desktop_sms/desktopsms.asp?uid=SREESAKTHIOIL&pwd=4295985&mobile="+mobile_no+"&msg="+msg+"&sid=SMSIntegra&dtNow="+date+"";
	    	            	        				HttpGet request = new HttpGet(strPostData);
	    	            	        				ResponseHandler<String> responseHandler = new BasicResponseHandler();
	    	                                        SetServerString = Client.execute(request, responseHandler);
	    	            	        				Log.i(LOG,request.getAllHeaders().toString());
	    	            	        				Log.i(LOG,"SetServerString"+SetServerString);
	    	        	        				} catch(Exception ex){
	    	        	        					Log.i(LOG,ex.toString());
	    	        	                       }
	        	        				    }
	        	        				}).start();
	        	        		}
	        	        		Toast toast = Toast.makeText(getApplicationContext(), " Receipt Successfull inserted", Toast.LENGTH_SHORT);
		    	        		toast.setGravity(Gravity.CENTER, 0, 0);
		                		toast.show();
		                		amount_id.getText().clear();
		                		checkdate_id.getText().clear();
		        	        	checkno_id.getText().clear();
		        	        	Bankname_id.getText().clear();
	        	        	}else{
	        					Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Bank Detail's", Toast.LENGTH_SHORT);
		    	        		toast.setGravity(Gravity.CENTER, 0, 0);
		                		toast.show();
	        				}
	     	           	}else{
	     	           		String bill_id=selected_bill.getSelectedItem().toString();
			        		rpt.setCust_Id(shop_id.getText().toString());
	        				rpt.setBill_No(bill_id);	        	
	        	        	rpt.setAmount(Integer.valueOf(amount_id.getText().toString()));
	        	        	rpt.setDate(date);
	        	        	long row_id;
	        	        	if(Rep_rowId != 0){
        	        			rpt.setRow_Id(Rep_rowId);
        	        			row_id = dbhelper4.UpdateReception(rpt);
        	        			Rep_rowId = 0;
        	        		}else{
        	        			row_id = dbhelper4.insertReception(rpt);
        	        			String rid = globalVariable.getUsername()+row_id;
        	        			dbhelper4.insertReciptionId(row_id,rid);
        	        		}
	        	        	if(row_id>0){
	        	        		dbhelper4.insetRecived(Integer.valueOf(amount_id.getText().toString()),bill_id);
        	        			final String message = "Dear Customer,Receipt Number:   Receipt Amount: "+ current_amount +"Balance Amount: "+ balance +" Thank You, -THANGAM";
        	        				new Thread(new Runnable(){
        	        				    @Override
        	        				    public void run() {
        	        				    	try{
        	        				    		HttpClient Client = new DefaultHttpClient();
        	        				    		String SetServerString = "";
    	            	        				String msg =   URLEncoder.encode(message,"UTF-8");
    	            	        				String  strPostData = "http://www.smsintegra.com/smsweb/desktop_sms/desktopsms.asp?uid=SREESAKTHIOIL&pwd=4295985&mobile="+mobile_no+"&msg="+msg+"&sid=SMSIntegra&dtNow="+date+"";
    	            	        				HttpGet request = new HttpGet(strPostData);
    	            	        				ResponseHandler<String> responseHandler = new BasicResponseHandler();
    	                                        SetServerString = Client.execute(request, responseHandler);
    	            	        				Log.i(LOG,request.getAllHeaders().toString());
    	            	        				Log.i(LOG,"SetServerString"+SetServerString);
    	            	        				
    	        	        				} catch(Exception ex){
    	        	        					Log.i(LOG,ex.toString());
    	        	                       }
        	        				    }
        	        				}).start();
        	        			}
	    	        		Toast toast = Toast.makeText(getApplicationContext(), " Receipt Successfull inserted", Toast.LENGTH_SHORT);
	    	        		toast.setGravity(Gravity.CENTER, 0, 0);
	                		toast.show();
	                		amount_id.getText().clear();
	     	           	}
	            	}
            	}else {
            		Toast toast = Toast.makeText(getApplicationContext(), " Please Ender the Amount ", Toast.LENGTH_SHORT);
            		toast.setGravity(Gravity.CENTER, 0, 0);
            		toast.show();
            	}
			}
		});
	    
	    Button btn_Order = (Button) findViewById(R.id.placeOrder);
		btn_Order.setOnClickListener(new View.OnClickListener() {
			@Override
	        public void onClick(View view) {
				Order_Details SD =new Order_Details();
	        	DatabaseHelper dbh = new  DatabaseHelper(getApplicationContext());
	        	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        	 	SD.setCust_Id(shop_id.getText().toString());
	        	 	SD.setDate(date);
	 	     		if(!EDT_1.getText().toString().isEmpty() && EDT_1.getText().toString() != null){
	 	     			Log.e(LOG,"i am in edite 1");
	 					SD.setItem_192(Integer.parseInt(EDT_1.getText().toString()));
	 				}else{
	 					SD.setItem_192(0);
	 				}
	 	     		if(!EDT_2.getText().toString().isEmpty() && EDT_2.getText().toString() != null){
	 	     			Log.e(LOG,"i am in edite 2");
	 					SD.setItem_193(Integer.parseInt(EDT_2.getText().toString()));
	 				}else{
	 					SD.setItem_193(0);
	 				}
	 	     		if(!EDT_3.getText().toString().isEmpty() && EDT_3.getText().toString() != null){
	 	     			Log.e(LOG,"i am in edite 3");
	 					SD.setItem_194(Integer.parseInt(EDT_3.getText().toString()));
	 				}else{
	 					SD.setItem_194(0);
	 				}
	 	     		if(!EDT_4.getText().toString().isEmpty() && EDT_4.getText().toString() != null){
	 	     			Log.e(LOG,"i am in edite 4");
	 					SD.setItem_195(Integer.parseInt(EDT_4.getText().toString()));
	 				}else{
	 					SD.setItem_195(0);
	 				}
	 	     		if(!EDT_5.getText().toString().isEmpty() && EDT_5.getText().toString() != null){
	 	     			Log.e(LOG,"i am in edite 5");
	 					SD.setItem_196(Integer.parseInt(EDT_5.getText().toString()));
	 				}else{
	 					SD.setItem_196(0);
	 				}
	 	     		if(!EDT_6.getText().toString().isEmpty() && EDT_6.getText().toString() != null){
	 	     			Log.e(LOG,"i am in edite 6");
	 					SD.setItem_197(Integer.parseInt(EDT_6.getText().toString()));
	 				}else{
	 					SD.setItem_197(0);
	 				}
	 			if(order_id > 0 ){
	 					SD.setRow_Id(order_id);
		      			long out = dbh.UpdateOrder(SD);
		      			if(out >0){
		      				Toast toast = Toast.makeText(getApplicationContext(), "Data Updated successfully", Toast.LENGTH_SHORT);
		      				toast.setGravity(Gravity.CENTER, 0, 0);
		      				toast.show();
		      				EDT_1.getText().clear();
		      				EDT_2.getText().clear();
		      				EDT_3.getText().clear();
		      				EDT_4.getText().clear();
		      				EDT_5.getText().clear();
		      				EDT_6.getText().clear();
		      				order_id=0;
		      			}else{
		      				Toast toast = Toast.makeText(getApplicationContext(), "Data Not Updated", Toast.LENGTH_SHORT);
		      				toast.setGravity(Gravity.CENTER, 0, 0);
		      				toast.show();
		      				order_id=0;
		      			}
	        	 }else{
	      			long out = dbh.insertOrder(SD);
	      			if(out >0){
	      				Toast toast = Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT);
	      				toast.setGravity(Gravity.CENTER, 0, 0);
	      				toast.show();
	      				EDT_1.getText().clear();
	      				EDT_2.getText().clear();
	      				EDT_3.getText().clear();
	      				EDT_4.getText().clear();
	      				EDT_5.getText().clear();
	      				EDT_6.getText().clear();
	      				order_id=0;
	      			}else{
	      				Toast toast = Toast.makeText(getApplicationContext(), "Data Not inserted", Toast.LENGTH_SHORT);
	      				toast.setGravity(Gravity.CENTER, 0, 0);
	      				toast.show();
	      				order_id=0;
	      			}
	        	 }
	         }
   		});
	}
	
	public void onRadioButtonClicked(View view) {
	    switch (view.getId()) {
		    case R.id.cash_id:
				check_no_row.setVisibility(View.GONE);
				check_date_row.setVisibility(View.GONE);
				check_Bankname_row.setVisibility(View.GONE);
		        break;
		    case R.id.check_id:
		    	check_no_row.setVisibility(View.VISIBLE);
				check_date_row.setVisibility(View.VISIBLE);
				check_Bankname_row.setVisibility(View.VISIBLE);
		        break;
			case R.id.Reciptpage:
				Order_table.setVisibility(View.GONE);
				Recipt_table.setVisibility(View.VISIBLE);
				break;
			case R.id.orderpage:
				Recipt_table.setVisibility(View.GONE);
				Order_table.setVisibility(View.VISIBLE);
			    break;
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		if(!globalVariable.getRole().equalsIgnoreCase("admin")){
			MenuItem item = menu.findItem(R.id.add_user);
			if (item != null) {
			  item.setVisible(false); 
			}
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		DatabaseHelper dbhelper = new  DatabaseHelper(getApplicationContext());
		
		//Saving file in external storage
		File sdCard = Environment.getExternalStorageDirectory();	
		File directory = new File(sdCard.getAbsolutePath() + "/thangam");
		Log.e(LOG,directory.toString());
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		int id = item.getItemId();
		
		switch (id) {
		
		case R.id.action_Import_bills:
			
			Toast toast = Toast.makeText(getApplicationContext(), "Import Bills is Selected....", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			try{
				
				Toast toast1 =  Toast.makeText(getApplicationContext(), "Import Bills is Working....", Toast.LENGTH_SHORT);
				toast1.setGravity(Gravity.CENTER, 0, 0);
  				toast1.show();
				
  				final String fileName = "thangam_Bills.xls";
				
  				//file path
				File file = new File(directory, fileName);
				FileInputStream fileInputStream = new FileInputStream(file);
				HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
				
				int n = workbook.getNumberOfSheets();
				Log.e(LOG,n+" ");
				for(int i=0;i<n;i++)
				{
					String worksheet_name =  workbook.getSheetName(i);
					Log.e(LOG,worksheet_name);
					if(worksheet_name.equalsIgnoreCase("bills"))
					{
						Bills bill = new Bills();
						HSSFSheet worksheet = workbook.getSheet("bills");
						Log.e(LOG,worksheet.getLastRowNum()+"Total row");
						String myFormat = "dd/MM/yyyy";
					    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

						for (int k = 0; k <= worksheet.getLastRowNum(); k++) 
						{
							if ((worksheet.getRow(k)) != null ) 
							{
						    	Log.e(LOG,"work sheet name "+worksheet_name);
						    	
								HSSFRow row1 = worksheet.getRow(k);
								
								HSSFCell cellA = row1.getCell(0);
								Log.e(LOG,cellA.getStringCellValue());
								bill.setCust_Id(cellA.getStringCellValue());
								
								HSSFCell cellb = row1.getCell(1);
								Log.e(LOG,cellb.getStringCellValue());
								bill.setBill_No(cellb.getStringCellValue());
								
								HSSFCell cellc = row1.getCell(2);
								Log.e(LOG,cellc.getNumericCellValue()+" ");
								bill.setBalance((int)cellc.getNumericCellValue());
								
								bill.setDate(sdf.format(myCalendar.getTime()));;
								
								int ck = dbhelper.checkbillNo(cellb.getStringCellValue());
								if(ck == 0){
									dbhelper.insertnBills(bill);	
								}
						    }
						}
					}
				}
				fileInputStream.close();
				Log.e(LOG,"finished exporting");
				//file.delete();
				Toast toast12 = Toast.makeText(getApplicationContext(), "Import is Finished", Toast.LENGTH_LONG);
				toast12.setGravity(Gravity.CENTER, 0, 0);
				toast12.show();
				return true;
			}
			catch (FileNotFoundException e){	
				Toast toast11 = Toast.makeText(getApplicationContext(), "File Not Founted", Toast.LENGTH_LONG);
				toast11.setGravity(Gravity.CENTER, 0, 0);
				toast11.show();
				} 
			catch (IOException e){	
				Toast toast10 = Toast.makeText(getApplicationContext(), "IO Exception", Toast.LENGTH_SHORT);
				toast10.setGravity(Gravity.CENTER, 0, 0);
				toast10.show();
				}
			break;
		
		case R.id.action_Import_customer:
			
			Toast toast9 = Toast.makeText(getApplicationContext(), "import Customer is Selected", Toast.LENGTH_SHORT);
			toast9.setGravity(Gravity.CENTER, 0, 0);
			toast9.show();
			
			try{
				
				Toast toast8 =  Toast.makeText(getApplicationContext(), "import Customer is Working....", Toast.LENGTH_LONG);
				toast8.setGravity(Gravity.CENTER, 0, 0);
				toast8.show();
				
				final String fileName = "thangam_Customer.xls";
				
				//file path
				File file = new File(directory, fileName);
				FileInputStream fileInputStream = new FileInputStream(file);
				HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
				
				int n = workbook.getNumberOfSheets();
				Log.e(LOG,n+" ");
				for(int i=0;i<n;i++)
				{
					String worksheet_name =  workbook.getSheetName(i);
					Log.e(LOG,worksheet_name);
					if(worksheet_name.equalsIgnoreCase("Customer"))
					{
						Customer cus = new Customer();
						HSSFSheet worksheet = workbook.getSheet("Customer");
						Log.e(LOG,worksheet.getLastRowNum()+" ");
						for (int k = 0; k <= worksheet.getLastRowNum(); k++) 
						{
							if ((worksheet.getRow(k)) != null ) 
							{
						    	HSSFRow row1 = worksheet.getRow(k);
								
									HSSFCell cellb = row1.getCell(0);
									cus.setName(cellb.getStringCellValue());
									Log.e(LOG,cellb.getStringCellValue());
									
									HSSFCell celld = row1.getCell(1);
									cus.setAddress(celld.getStringCellValue());
									Log.e(LOG,celld.getStringCellValue());
									
									
									HSSFCell celle = row1.getCell(2);
									Log.e(LOG,celle.getStringCellValue());
									cus.setCell("+"+(celle.getStringCellValue()));
									
									HSSFCell cellf = row1.getCell(3);
									cus.setCust_Id(cellf.getStringCellValue());
									Log.e(LOG,cellf.getStringCellValue()+" ");
									
									int ck = dbhelper.checkcustomer(cellf.getStringCellValue());
									if(ck == 0){
										dbhelper.insertcustomer(cus);	
									}
						    }
						}
					}
				}
				fileInputStream.close();
				//file.delete();
				Log.e(LOG,"finished exporting");
				Toast toast6 = Toast.makeText(getApplicationContext(), "Customer Import is Finished", Toast.LENGTH_LONG);
				toast6.setGravity(Gravity.CENTER, 0, 0);
				toast6.show();
				return true;
				
			}
			catch (FileNotFoundException e){	
				Toast toast7 = Toast.makeText(getApplicationContext(), "File Not Founted", Toast.LENGTH_LONG);	
				toast7.setGravity(Gravity.CENTER, 0, 0);
				toast7.show();
				} 
			catch (IOException e){	
				Toast toast5 = Toast.makeText(getApplicationContext(), "IO Exception", Toast.LENGTH_LONG);
				toast5.setGravity(Gravity.CENTER, 0, 0);
				toast5.show();
				}
			break;
		case R.id.action_Export:
			try{
				
				Toast toast5 = Toast.makeText(getApplicationContext(), "Start Exporting Data", Toast.LENGTH_SHORT);
				toast5.setGravity(Gravity.CENTER, 0, 0);
				toast5.show();
				final String fileName = "thangam_out.xls";

				//create directory if not exist
				if(!directory.isDirectory()){
					directory.mkdirs();	
				}
				
				//file path
				File file = new File(directory, fileName);
				
				FileOutputStream fileOut = new FileOutputStream(file);
				HSSFWorkbook workbook = new HSSFWorkbook();
				
				String tableQuery = "SELECT name FROM sqlite_master WHERE type='table'";
				Log.e(LOG, tableQuery);

				Cursor c = db.rawQuery(tableQuery,null);
				if (c.moveToFirst()) {
					do {
						int i = 0;
						Log.e(LOG,c.getString(i)+"table Name");
						String table_Name =c.getString(i);

						if(table_Name.equalsIgnoreCase("Receipt")){
								HSSFSheet worksheet = workbook.createSheet("Receipt");
								String tableQuery1 = "SELECT * FROM  Receipt";
								Log.e(LOG,tableQuery1);
								Cursor cll_cur = db.rawQuery(tableQuery1,null);
								
								if (cll_cur.moveToFirst()) {
									int j=1;
									do{
										
										Row row1  = worksheet.createRow(j);

										Cell cellA0 = row1.createCell(0);
										cellA0.setCellValue(cll_cur.getInt(cll_cur.getColumnIndex("Row_Id")));
										
										Cell cellA1 = row1.createCell(1);
										cellA1.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("ReceiptNo")));
										Log.e(LOG,cll_cur.getString(cll_cur.getColumnIndex("ReceiptNo")));
										
										Cell cellA2 = row1.createCell(2);
										cellA2.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("Cust_Id")));
										
										Cell cellA3 = row1.createCell(3);
										cellA3.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("Bill_No")));
										
										Cell cellA4 = row1.createCell(4);
										cellA4.setCellValue(cll_cur.getInt(cll_cur.getColumnIndex("Amount")));
										
										Cell cellA5 = row1.createCell(5);
										cellA5.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("ChequeNo")));
										
										Cell cellA6 = row1.createCell(6);
										cellA6.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("ChequeDate")));
										
										Cell cellA7 = row1.createCell(7);
										cellA7.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("BankName")));
										
										Cell cellA8 = row1.createCell(8);
										cellA8.setCellValue(cll_cur.getString(cll_cur.getColumnIndex("Date")));
										
										j++;
									} while (cll_cur.moveToNext());
								}
						
						} else if(table_Name.equalsIgnoreCase("Order_Details")){
								
								HSSFSheet worksheet = workbook.createSheet("Order_Details");
								String tableQuery_sd = "SELECT * FROM  Order_Details";
								Log.e(LOG,tableQuery_sd);
								Cursor cur_sd = db.rawQuery(tableQuery_sd,null);
								
								if (cur_sd.moveToFirst()) {
									int j=1;
									do{
										Row row1  = worksheet.createRow(j);
										
										Cell cellA1 = row1.createCell(0);
										cellA1.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Row_Id")));
										
										Cell cellA2 = row1.createCell(1);
										cellA2.setCellValue(cur_sd.getString(cur_sd.getColumnIndex("Cust_Id")));
										
										Cell cellA3 = row1.createCell(2);
										cellA3.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Item_192")));
										
										Cell cellA4 = row1.createCell(3);
										cellA4.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Item_193")));
										
										Cell cellA5 = row1.createCell(4);
										cellA5.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Item_194")));
										
										Cell cellA6 = row1.createCell(5);
										cellA6.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Item_195")));
										
										Cell cellA7 = row1.createCell(6);
										cellA7.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Item_196")));
										
										Cell cellA8 = row1.createCell(7);
										cellA8.setCellValue(cur_sd.getInt(cur_sd.getColumnIndex("Item_197")));
										
										Cell cellA9 = row1.createCell(8);
										cellA9.setCellValue(cur_sd.getString(cur_sd.getColumnIndex("Date")));

										j++;
									} while (cur_sd.moveToNext());
								}

						}else if(table_Name.equalsIgnoreCase("bills")){
								
								HSSFSheet worksheet = workbook.createSheet("bills");
								String tableQuery_bills = "SELECT * FROM  bills";
								Log.e(LOG,tableQuery_bills);
								Cursor cur_bill = db.rawQuery(tableQuery_bills,null);
								
								if (cur_bill.moveToFirst()) {
									int j=1;
									do{
										Row row1  = worksheet.createRow(j);
										Log.e(LOG,j+"row");
										
										Cell cellA1 = row1.createCell(0);
										cellA1.setCellValue(cur_bill.getString(cur_bill.getColumnIndex("Cust_Id")));
										
										Cell cellA2 = row1.createCell(1);
										cellA2.setCellValue(cur_bill.getString(cur_bill.getColumnIndex("Bill_No")));
										
										Cell cellA3 = row1.createCell(2);
										cellA3.setCellValue(cur_bill.getString(cur_bill.getColumnIndex("balance")));
										
										Cell cellA4 = row1.createCell(3);
										cellA4.setCellValue(cur_bill.getString(cur_bill.getColumnIndex("Date")));
										
										j++;
									} while (cur_bill.moveToNext());
								}
						}
						i++;
					} while (c.moveToNext());
				}
			
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				
				Log.e(LOG,"finished exporting");
				Toast toast4 = Toast.makeText(getApplicationContext(), "Export is Finished", Toast.LENGTH_SHORT);
				toast4.setGravity(Gravity.CENTER, 0, 0);
				toast4.show();
				
				dbhelper.DeleteBills();
				dbhelper.DeleteReceipt();
				dbhelper.DeleteOrder_DETAILS();
				
				Toast toast2 =   Toast.makeText(getApplicationContext(), "Bills,Receipt and Order is Deleted successfully", Toast.LENGTH_SHORT);
				toast2.setGravity(Gravity.CENTER, 0, 0);
				toast2.show();
				return true;
			}catch (FileNotFoundException e){	
				Toast toast3 = Toast.makeText(getApplicationContext(), "File Not Founted", Toast.LENGTH_LONG);	
				toast3.setGravity(Gravity.CENTER, 0, 0);
				toast3.show();
				}
			catch (IOException e){	
				Toast toast3 = Toast.makeText(getApplicationContext(), "IO Exception", Toast.LENGTH_SHORT);
				toast3.setGravity(Gravity.CENTER, 0, 0);
				toast3.show();
				}
			break;
		case R.id.add_user:
			
			Intent intent_User = new Intent(this,User_Insert.class);
			startActivity(intent_User);
			break;
			
		case R.id.Gen_report:
			
			Intent intent_report = new Intent(this,Gen_Report.class);
			startActivity(intent_report);
			break;
		}
		dbhelper.close();
		db.close();
		return super.onOptionsItemSelected(item);
	}
}
