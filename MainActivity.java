package com.example.sharedpreferences;

import java.nio.channels.SelectableChannel;

import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	SharedPreferences prefs;
	public static String prefName = "Reports";
	TextView dataResults;
	EditText ename;
	String strname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupVariable();
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
	}		
	private void setupVariable()
	{
		
		ename = (EditText)findViewById(R.id.editText2);
		dataResults =(TextView)findViewById(R.id.textView1);
					
		Button save =(Button)findViewById(R.id.btSave);		
		Button select = (Button)findViewById(R.id.btSelect);
		Button update = (Button)findViewById(R.id.btUpdate);
		Button clearcontent = (Button)findViewById(R.id.btClearContent);
		Button cleartxt =(Button)findViewById(R.id.btClearText);	
		
			
		save.setOnClickListener(this);
		select.setOnClickListener(this);
		update.setOnClickListener(this);
		clearcontent.setOnClickListener(this);
		cleartxt.setOnClickListener(this);		
			
		}	
		public void onClick(View v) 
		{
						
				switch(v.getId())
				{			
				case R.id.btSave:
					strname = ename.getText().toString();					
					SharedPreferences.Editor editor = prefs.edit();				
							// SAVE THE VALUES IN THE EDIT TEXT TO PREFERENCES ----
					editor.putString("Name", strname);				
								// SAVE THE VALUES
					editor.commit();				
					Toast.makeText(getBaseContext(), "Saved", 
								Toast.LENGTH_LONG).show();
					break;						
				case R.id.btSelect :						
					//prefs = getSharedPreferences(prefName, MODE_PRIVATE);						
					String storedData = prefs.getString("Name","Could nt");
					dataResults.setText(storedData);	
					break;
					
				case R.id.btUpdate:	
					strname = ename.getText().toString();					
					SharedPreferences.Editor editor1 = prefs.edit();
					String prevstr = prefs.getString("name", "");
					//String prevstr = editor1.putString("name", "");
					editor1.putString("name", prevstr.concat(ename.getText().toString()));
					editor1.commit();
					Toast.makeText(getBaseContext(), "Updated Successfully",
							Toast.LENGTH_SHORT).show();	
					break;
				case R.id.btClearContent:
					SharedPreferences.Editor editor2 = prefs.edit();
					editor2.clear();
					editor2.commit();
					Toast.makeText(getBaseContext(), "File cleared",
							Toast.LENGTH_SHORT).show();	
				case R.id.btClearText:
					dataResults.clearComposingText();	
					
				}
			}		
	}


