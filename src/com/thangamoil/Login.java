/**
 * @author  R.velayudham
 * @version 2.0
 * @since   JDK1.7
 * @Platform Android
 * @Description : This is Login Screen
 */
package com.thangamoil;

import com.thangamoil.Model.Agent;
import com.thangamoil.db.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class Login  extends Activity {
	
	
	
	private static final String LOG = "Login_Screen";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
			super.onCreate(savedInstanceState);
			setContentView(R.layout.login);
			
			/** Admin insert area **/
			DatabaseHelper DBH_1 = new DatabaseHelper(getApplicationContext());			
			Agent Agt_admin = new Agent();			
			Agt_admin.setUsername("admin");
			Agt_admin.setPassword("admin");
			Agt_admin.setRole("admin");
			Agt_admin.setUser_Id("145");
			DBH_1.insertAgent(Agt_admin);
			Log.e(LOG,"Data inserted successfully");
			
			Button btnlog = (Button) findViewById(R.id.login1);
			btnlog.setOnClickListener(new View.OnClickListener() { 
		         
				@Override
		         public void onClick(View view) {
		    
					DatabaseHelper DBH = new DatabaseHelper(getApplicationContext());
					
		 			EditText uname = (EditText) findViewById(R.id.username);
		 			EditText pword = (EditText) findViewById(R.id.password);
		 			String Name = String.valueOf(uname.getText());
		 			String Pword = String.valueOf(pword.getText());
		 			
		 			Agent Agt = new Agent();
		 			Agt = DBH.CheckLogin_Agent(Name,Pword);
		 			
		 			Log.e(LOG,"User ID"+Agt.getUser_Id());
		 			Log.e(LOG,"Role "+Agt.getRole());
		 			
		 			if(Agt.getUser_Id() != "0" && !Agt.getUser_Id().isEmpty()){
		 				
		 				globalVariable.setRole(Agt.getRole());
		 				globalVariable.setUsername(Agt.getUsername());
		 				globalVariable.setUser_Id(Agt.getUser_Id());
		 				Intent intent = new Intent(Login.this,MainActivity.class);
					    startActivity(intent);
					    
					}else{
						
						//UserName and Password are incorrect
						Toast.makeText(getApplicationContext(), "Please Ender currect username and Password", 2000).show();
						
					}	
		         }
			});
	}
}
