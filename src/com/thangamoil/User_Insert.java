package com.thangamoil;

import java.util.ArrayList;
import java.util.List;

import com.thangamoil.Model.Agent;
import com.thangamoil.db.DatabaseHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

public class User_Insert extends Activity {
	
	private static final String LOG = "User_Insert";
	
	private EditText User_id;
	private EditText UserName;
	private EditText Password;
	
	private Button Btnupdate;

	private RadioGroup User_radio;
	private RadioButton Existing_User;
	private RadioButton New_User;
	private RadioButton Admin_User;
	
	private Spinner exeitingUser_id;
	
	private TableRow TableRow_listshow;
	private TableRow TableRow_save;
	private TableRow TableRow_update;
	private TableRow TableRow_UserId;
	private TableRow TableRow_username;
	private TableRow TableRow_Pword;
	
	private TextView Error_text;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
			super.onCreate(savedInstanceState);
			setContentView(R.layout.user_insert);
			
			User_id 		= 	(EditText) 		findViewById(R.id.User_Id); 
			UserName 		= 	(EditText) 		findViewById(R.id.username);
			Password 		= 	(EditText) 		findViewById(R.id.usepword);
			
			exeitingUser_id = 	(Spinner) 		findViewById(R.id.exeitingUser_id);
			
			User_radio		=	(RadioGroup) 	findViewById(R.id.User_radio);
			New_User		=	(RadioButton) 	findViewById(R.id.New_User);
			Existing_User	=	(RadioButton) 	findViewById(R.id.Existing_User);
			Admin_User		=	(RadioButton) 	findViewById(R.id.User_admin);
			
			TableRow_listshow 	= (TableRow) findViewById(R.id.TR_listshow);
			TableRow_save 		= (TableRow) findViewById(R.id.TR_save);
			TableRow_update 	= (TableRow) findViewById(R.id.TR_update);
			
			Btnupdate 	= (Button) findViewById(R.id.Update_User);
			
			TableRow_UserId 	= (TableRow) findViewById(R.id.TR_UserId);
			TableRow_username 	= (TableRow) findViewById(R.id.TR_username);
			TableRow_Pword 	= (TableRow) findViewById(R.id.TR_Pword);
			
			Error_text 	= (TextView) findViewById(R.id.Txt_error);
			
			
			New_User.setChecked(true);
			TableRow_listshow.setVisibility(View.GONE);
			TableRow_update.setVisibility(View.GONE);
			
			
			Btnupdate.setOnClickListener(new View.OnClickListener() { 
				@Override
		        public void onClick(View view) {
					
					Agent agt = new Agent();
					
					agt.setUser_Id(User_id.getText().toString());
					agt.setUsername(UserName.getText().toString());
					agt.setPassword(Password.getText().toString());
					DatabaseHelper DBH_update = new  DatabaseHelper(getApplicationContext());
					long re = DBH_update.UpdateAgent(agt);
					Toast toast;
					if(re != 0){
						toast = Toast.makeText(getApplicationContext(), "User Updated Successfully", Toast.LENGTH_SHORT);
				    	if(UserName.getText().length()>0){UserName.getText().clear();}
				    	if(Password.getText().length()>0){Password.getText().clear();}
					}else{
						toast = Toast.makeText(getApplicationContext(), "User Not Updated", Toast.LENGTH_SHORT);
					}
					toast.setGravity(Gravity.CENTER, 0, 0);
            		toast.show();
            		DBH_update.close();
				}
			});
			
			Button btnsave = (Button) findViewById(R.id.Save_New);
			btnsave.setOnClickListener(new View.OnClickListener() {
				@Override
		         public void onClick(View view) {
					
					Agent agt = new Agent();
					
					agt.setUser_Id(User_id.getText().toString());
					agt.setUsername(UserName.getText().toString());
					agt.setRole("user");
					agt.setPassword(Password.getText().toString());
					DatabaseHelper DBH_insert = new  DatabaseHelper(getApplicationContext());
					long re = DBH_insert.insertAgent(agt);
					Toast toast;
					if(re != 0){
						toast = Toast.makeText(getApplicationContext(), "User inserted Successfully", Toast.LENGTH_SHORT);
				    	if(User_id.getText().length()>0){User_id.getText().clear();}
				    	if(UserName.getText().length()>0){UserName.getText().clear();}
				    	if(Password.getText().length()>0){Password.getText().clear();}
					}else{
						toast = Toast.makeText(getApplicationContext(), "User Not Inserted", Toast.LENGTH_SHORT);
					}
					toast.setGravity(Gravity.CENTER, 0, 0);
            		toast.show();
            		DBH_insert.close();
				}
			});
			

			
			exeitingUser_id.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
			    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
					Agent agt = new Agent();
					String Selected_userId=exeitingUser_id.getSelectedItem().toString();
					DatabaseHelper DBH_user = new  DatabaseHelper(getApplicationContext());
					agt = DBH_user.Selected_UserDetail(Selected_userId);
					UserName.setText(agt.getUsername().toString());
					Password.setText(agt.getPassword().toString());
					DBH_user.close();
			    }
			    @Override
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    	
			    }
			});
			    
	}
	
	public void onRadioButtonClicked(View view) {
		DatabaseHelper DBH1 = new  DatabaseHelper(getApplicationContext());
		//GlobalClass globalVariable = (GlobalClass) getApplicationContext();
    	switch (view.getId()) {
		    case R.id.New_User:
		    	
		    	if(User_id.getText().length()>0){User_id.getText().clear();}
		    	if(UserName.getText().length()>0){UserName.getText().clear();}
		    	if(Password.getText().length()>0){Password.getText().clear();}
		    	
		    	TableRow_UserId.setVisibility(View.VISIBLE);
            	TableRow_username.setVisibility(View.VISIBLE);
            	TableRow_Pword.setVisibility(View.VISIBLE);
            	TableRow_save.setVisibility(View.VISIBLE);
            	
            	
		    	TableRow_listshow.setVisibility(View.GONE);
				TableRow_update.setVisibility(View.GONE);
				break;
				
		    case R.id.User_admin:
		    	
		    	if(User_id.getText().length()>0){User_id.getText().clear();}
		    	if(UserName.getText().length()>0){UserName.getText().clear();}
		    	if(Password.getText().length()>0){Password.getText().clear();}
		    	
		    	TableRow_UserId.setVisibility(View.GONE);
				TableRow_save.setVisibility(View.GONE);
				
				List<Agent> Admin_list = new ArrayList<Agent>();
				Admin_list = DBH1.getUserList();
				ArrayList<String> admin_arrayList = new ArrayList<String>();
				
				if(Admin_list.size() >0){
	            	for(int i = 0; i<Admin_list.size();i++) {
	            		Log.e(LOG,Admin_list.get(i).getUser_Id()+"Role is ="+Admin_list.get(i).getRole());
	            		if(Admin_list.get(i).getRole().equalsIgnoreCase("admin")){
	            			admin_arrayList.add(Admin_list.get(i).getUser_Id());
	            		}
		        	 }
	            	exeitingUser_id.setAdapter(null);
		        	ArrayAdapter<String> adp = new ArrayAdapter<String> (User_Insert.this,android.R.layout.simple_spinner_dropdown_item,admin_arrayList);
		        	exeitingUser_id.setAdapter(adp);
		        	exeitingUser_id.setVisibility(View.VISIBLE);
		        	adp.notifyDataSetChanged();
		        	Error_text.setVisibility(View.GONE);
		        	Btnupdate.setVisibility(View.VISIBLE);
	            }else{
	            	exeitingUser_id.setVisibility(View.GONE);
	            	Btnupdate.setVisibility(View.GONE);
	            	Error_text.setVisibility(View.VISIBLE);
	            	TableRow_listshow.setVisibility(View.GONE);
	            }
				
				TableRow_update.setVisibility(View.VISIBLE);
		    	break;
		    	
		    case R.id.Existing_User:
				
		    	if(User_id.getText().length()>0){User_id.getText().clear();}
		    	if(UserName.getText().length()>0){UserName.getText().clear();}
		    	if(Password.getText().length()>0){Password.getText().clear();}
		    	
		    	TableRow_UserId.setVisibility(View.GONE);
				TableRow_save.setVisibility(View.GONE);
				
				
				
				
				List<Agent> Agent_list = new ArrayList<Agent>();
				Agent_list = DBH1.getUserList();
				ArrayList<String> User_arrayList = new ArrayList<String>();
				
				if(Agent_list.size() >0){
	            	for(int i = 0; i<Agent_list.size();i++) {
	            		Log.e(LOG,Agent_list.get(i).getUser_Id()+"Role is ="+Agent_list.get(i).getRole());
	            		if(Agent_list.get(i).getRole().equalsIgnoreCase("user")){
	            			User_arrayList.add(Agent_list.get(i).getUser_Id());
	            		//}else if(globalVariable.getRole().equalsIgnoreCase("user") && (globalVariable.getUser_Id() == Agent_list.get(i).getUser_Id())){
	            		//	User_arrayList.add(Agent_list.get(i).getUser_Id());
	            		}
		        	 }
	            	exeitingUser_id.setAdapter(null);
		        	ArrayAdapter<String> adp = new ArrayAdapter<String> (User_Insert.this,android.R.layout.simple_spinner_dropdown_item,User_arrayList);
		        	exeitingUser_id.setAdapter(adp);
		        	exeitingUser_id.setVisibility(View.VISIBLE);
		        	adp.notifyDataSetChanged();
		        	Error_text.setVisibility(View.GONE);
		        	Btnupdate.setVisibility(View.VISIBLE);
		        	TableRow_listshow.setVisibility(View.VISIBLE);
	            }else{
	            	
	            	exeitingUser_id.setVisibility(View.GONE);
	            	Btnupdate.setVisibility(View.GONE);
	            	Error_text.setVisibility(View.VISIBLE);
	            	TableRow_listshow.setVisibility(View.GONE);
	            }
				
				TableRow_update.setVisibility(View.VISIBLE);
				break;
	    }
	}
}
