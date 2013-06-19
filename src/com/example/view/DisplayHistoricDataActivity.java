package com.example.view;

import com.example.ctrl.CaloriesCtrlActivity;
import com.example.utils.CaloriesDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class DisplayHistoricDataActivity extends Activity {
	CaloriesCtrlActivity caloriesCtrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_historic_data);
		
		CaloriesDbAdapter.getInstance(this);			
		caloriesCtrl = new CaloriesCtrlActivity();
		
		Intent intent = getIntent();
		
		TextView caloricNeedsTextView = (TextView) findViewById(R.id.historic_caloric_needs_textView);
		caloricNeedsTextView.setTextSize(22);
		caloricNeedsTextView.setTextColor(Color.rgb(45, 100, 180));
		caloricNeedsTextView.setText("Your daily caloric needs are: " + caloriesCtrl.getCaloricNeeds() + " kcal");
		
		TextView totalCaloriesTextView = (TextView) findViewById(R.id.historic_total_calories_textView);
		totalCaloriesTextView.setTextSize(22);
		totalCaloriesTextView.setTextColor(Color.rgb(45, 100, 180));
		totalCaloriesTextView.setText("Today consumed calories: " + caloriesCtrl.getTodayCalories() + " kcal");
	}

	public void viewOlderData() { 
		Intent intent = new Intent(this, com.example.view.DisplayOlderDataActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_historic_data, menu);
		return true;
	}

}
