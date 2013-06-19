package com.example.view;

import com.example.view.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.view.MESSAGE"; 
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addCalories(View view) {
		Intent intent = new Intent(this, com.example.view.DisplayCaloriesActivity.class);
		EditText editText = (EditText) findViewById(R.id.add_message_edittext);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	public void setDailyCaloricNeed(View view) {
		Intent intent = new Intent(this, com.example.view.SetDailyCaloricNeedsActivity.class);
		startActivity(intent);
	}
	
	public void viewHistoricData(View view) {
		Intent intent = new Intent(this, com.example.view.DisplayHistoricDataActivity.class);
		startActivity(intent);
	}
}
