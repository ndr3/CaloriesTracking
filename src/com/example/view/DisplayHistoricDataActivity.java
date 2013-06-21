package com.example.view;

import com.example.ctrl.CaloriesCtrl;
import com.example.utils.CaloriesDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DisplayHistoricDataActivity extends Activity {
	CaloriesCtrl caloriesCtrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_historic_data);
					
		caloriesCtrl = CaloriesCtrl.getInstance();
		@SuppressWarnings("unused")
		Intent intent = getIntent();
	}

	public void viewTodayData(View view) { 
		Intent intent = new Intent(this, com.example.view.DisplayTodayDataActivity.class);
		startActivity(intent);
	}
	
	public void viewWeekData(View view) {
		Intent intent = new Intent(this, com.example.view.DisplayWeekDataActivity.class);
		startActivity(intent);
	}
	
	public void viewMonthData(View view) {
		Intent intent = new Intent(this, com.example.view.DisplayMonthDataActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_historic_data, menu);
		return true;
	}

}
