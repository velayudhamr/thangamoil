package com.thangamoil;

import java.util.ArrayList;
import java.util.List;

import com.thangamoil.Model.Order_Details;
import com.thangamoil.db.DatabaseHelper;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

public class Gen_Report extends Activity {
	
		// Logcat tag
		//private static final String LOG = "Gen_Report";
		//final Context context = this;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.product_report);
			
			Get_Report();
		}
		public void Get_Report() {
			
			DatabaseHelper dbhelper = new  DatabaseHelper(getApplicationContext());
			
			List<Order_Details> Product_OrderList = new ArrayList<Order_Details>();
			Product_OrderList = dbhelper.Gen_Report();
			TableLayout stk = (TableLayout) findViewById(R.id.Product_table);
			stk.setPadding(6,6,6,6);
			stk.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
			if(Product_OrderList.size() >0){
				
				 stk.removeAllViews();
				 TableRow.LayoutParams tlparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
				 TableRow tbrow = new TableRow(this);
				 tbrow.setLayoutParams(tlparams);
				
				 TextView t1v = new TextView(this);
                 t1v.setText("Customer Id");
                 t1v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t1v.setTextColor(Color.WHITE);
                 t1v.setTextSize(15);
                 t1v.setTypeface(null,Typeface.BOLD);
                 t1v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 tbrow.addView(t1v);
                 
                 TextView t2v = new TextView(this);
                 t2v.setText("1000 ml");
                 t2v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t2v.setTextColor(Color.WHITE);
                 t2v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t2v.setTextSize(15);
                 t2v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t2v);
                 
                 TextView t3v = new TextView(this);
                 t3v.setText("500 ml");
                 t3v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t3v.setTextColor(Color.WHITE);
                 t3v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t3v.setTextSize(15);
                 t3v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t3v);
                 
                 TextView t4v = new TextView(this);
                 t4v.setText("200 ml");
                 t4v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t4v.setTextColor(Color.WHITE);
                 t4v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t4v.setTextSize(15);
                 t4v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t4v);
                 
                 TextView t5v = new TextView(this);
                 t5v.setText("100 ml");
                 t5v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t5v.setTextColor(Color.WHITE);
                 t5v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t5v.setTextSize(15);
                 t5v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t5v);
                 
                 TextView t6v = new TextView(this);
                 t6v.setText("50 ml");
                 t6v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t6v.setTextColor(Color.WHITE);
                 t6v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t6v.setTextSize(15);
                 t6v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t6v);
                 
                 TextView t7v =  new TextView(this);
                 t7v.setText("15kg Tin");
                 t7v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t7v.setTextColor(Color.WHITE);
                 t7v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t7v.setTextSize(15);
                 t7v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t7v);
                 
                 TextView t8v = new TextView(this);
                 t8v.setText("Date");
                 t8v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t8v.setTextColor(Color.WHITE);
                 t8v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                 t8v.setTextSize(15);
                 t8v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t8v);
                 
                 stk.addView(tbrow);

                 for(int i = 0; i<Product_OrderList.size();i++) {
                	 
    				 TableRow tbrow_dyna = new TableRow(this);
    				 tbrow_dyna.setLayoutParams(tlparams);
    				 TextView t1v_dyna = new TextView(this);
                     t1v_dyna.setText(Product_OrderList.get(i).getCust_Id());
                     t1v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t1v_dyna.setTextColor(Color.WHITE);
                     t1v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t1v_dyna.setTextSize(15);
                     t1v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t1v_dyna);
                     
                     TextView t2v_dyna = new TextView(this);
                     t2v_dyna.setText(String.valueOf(Product_OrderList.get(i).getItem_192()));
                     t2v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t2v_dyna.setTextColor(Color.WHITE);
                     t2v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t2v_dyna.setTextSize(15);
                     t2v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t2v_dyna);
                     
                     TextView t3v_dyna =  new TextView(this);
                     t3v_dyna.setText(String.valueOf(Product_OrderList.get(i).getItem_193()));
                     t3v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t3v_dyna.setTextColor(Color.WHITE);
                     t3v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t3v_dyna.setTextSize(15);
                     t3v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t3v_dyna);
                     
                     TextView t4v_dyna = new TextView(this);
                     t4v_dyna.setText(String.valueOf(Product_OrderList.get(i).getItem_194()));
                     t4v_dyna.setTextColor(Color.WHITE);
                     t4v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t4v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t4v_dyna.setTextSize(15);
                     t4v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t4v_dyna);
                     
                     TextView t5v_dyna =  new TextView(this);
                     t5v_dyna.setText(String.valueOf(Product_OrderList.get(i).getItem_195()));
                     t5v_dyna.setTextColor(Color.WHITE);
                     t5v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t5v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t5v_dyna.setTextSize(15);
                     t5v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t5v_dyna);
                     
                     TextView t6v_dyna = new TextView(this);
                     t6v_dyna.setText(String.valueOf(Product_OrderList.get(i).getItem_196()));
                     t6v_dyna.setTextColor(Color.WHITE);
                     t6v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t6v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t6v_dyna.setTextSize(15);
                     t6v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t6v_dyna);
                     
                     TextView t7v_dyna = new TextView(this);
                     t7v_dyna.setText(String.valueOf(Product_OrderList.get(i).getItem_197()));
                     t7v_dyna.setTextColor(Color.WHITE);
                     t7v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t7v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t7v_dyna.setTextSize(15);
                     t7v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t7v_dyna);
                     
                     TextView t8v_dyna = new TextView(this);
                     t8v_dyna.setText(String.valueOf(Product_OrderList.get(i).getDate()));
                     t8v_dyna.setTextColor(Color.WHITE);
                     t8v_dyna.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                     t8v_dyna.setBackgroundDrawable(getResources().getDrawable(R.drawable.tb_border));
                     t8v_dyna.setTextSize(15);
                     t8v_dyna.setTypeface(null,Typeface.BOLD);
                     tbrow_dyna.addView(t8v_dyna);
                     
                     stk.addView(tbrow_dyna);
               	}
			 }else{
				 stk.removeAllViews();
				 TableRow.LayoutParams tlparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
				 TableRow tbrow = new TableRow(this);
				 tbrow.setLayoutParams(tlparams);
				 
				 TextView t1v = new TextView(this);
                 t1v.setText("There is No data found");
                 t1v.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                 t1v.setTextColor(Color.RED);
                 t1v.setTextSize(24);
                 t1v.setTypeface(null,Typeface.BOLD);
                 tbrow.addView(t1v);
                 
                 stk.addView(tbrow);
			 }
		}
} 
