package com.example.view;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.ctrl.CaloriesCtrl;

public class DisplayMonthDataActivity extends Activity {
	CaloriesCtrl caloriesCtrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_monthly_data);
		
		caloriesCtrl = CaloriesCtrl.getInstance();
		@SuppressWarnings("unused")
		Intent intent = getIntent();
		
		String monthData= "";		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		ArrayList<Integer> monthCalories = caloriesCtrl.getThisMonthCalories();
		int totalConsumedCalories = 0;
		
		for (int i=0; i<dayOfTheMonth; i++) {
			monthData = monthData + "Day " + i + ": " + monthCalories.get(dayOfTheMonth - i - 1) + " calories" + "\n";
			totalConsumedCalories += monthCalories.get(dayOfTheMonth - i - 1);
		}
		
		monthData += "\nTotal consumed calories: " + totalConsumedCalories + " calories\n";
		monthData += "From total caloric needs: " + caloriesCtrl.getCaloricNeeds()*dayOfTheMonth + " calories\n";
		monthData += "Average calories intake per day: " + totalConsumedCalories/dayOfTheMonth + " calories\n";
		
		TextView weekDataTextView = (TextView) findViewById(R.id.month_data_textview);
		weekDataTextView.setTextSize(20);
		weekDataTextView.setTextColor(Color.rgb(45, 100, 180));
		weekDataTextView.setText("This month data");
		
		TextView daysDataTextView = (TextView) findViewById(R.id.month_days_textview);
		daysDataTextView.setTextSize(11);
		daysDataTextView.setTextColor(Color.rgb(45, 100, 180));
		daysDataTextView.setText(monthData);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_monthly_data, menu);
		return true;
	}

}
