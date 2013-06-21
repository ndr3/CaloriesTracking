package com.example.view;

import com.example.ctrl.CaloriesCtrl;
import com.example.utils.CaloriesDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class DisplayTodayDataActivity extends Activity {
	CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_today_data);
		
		caloriesCtrl = CaloriesCtrl.getInstance();
		@SuppressWarnings("unused")
		Intent intent = getIntent();
		
		TextView caloricNeedsTextView = (TextView) findViewById(R.id.historic_caloric_needs_textView);
		caloricNeedsTextView.setTextSize(18);
		caloricNeedsTextView.setTextColor(Color.rgb(45, 100, 180));
		caloricNeedsTextView.setText("Your daily caloric needs are: " + caloriesCtrl.getCaloricNeeds() + " kcal");
		
		TextView totalCaloriesTextView = (TextView) findViewById(R.id.historic_total_calories_textView);
		totalCaloriesTextView.setTextSize(18);
		totalCaloriesTextView.setTextColor(Color.rgb(45, 100, 180));
		totalCaloriesTextView.setText("Today consumed calories: " + caloriesCtrl.getTodayCalories() + " kcal");
		
		TextView remainingCaloriesTextView = (TextView) findViewById(R.id.historic_remaining_calories_textView);
		remainingCaloriesTextView.setTextSize(18);
		remainingCaloriesTextView.setTextColor(Color.rgb(45, 100, 180));
		remainingCaloriesTextView.setText("Remaining calories: " + caloriesCtrl.getRemainingCalories() + " kcal");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_today_data, menu);
		return true;
	}

}
