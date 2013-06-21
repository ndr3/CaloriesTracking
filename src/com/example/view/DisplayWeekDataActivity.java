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

public class DisplayWeekDataActivity extends Activity {
	private CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_week_data);
		caloriesCtrl = CaloriesCtrl.getInstance();
		@SuppressWarnings("unused")
		Intent intent = getIntent();
		
		TextView weekDataTextView = (TextView) findViewById(R.id.week_data_textView);
		weekDataTextView.setTextSize(20);
		weekDataTextView.setTextColor(Color.rgb(45, 100, 180));
		weekDataTextView.setText("This week data");
		
		String weekData= "";		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String[] days = new String[] {"Monday: ", "Tuesday: ", "Wednesday: ", "Thursday: ", "Friday: ", "Saturday: ", "Sunday: "};
		
		ArrayList<Integer> weekCalories = caloriesCtrl.getThisWeekCalories();
		int totalConsumedCalories = 0;
		
		for (int i=0; i<dayOfTheWeek; i++) {
			weekData = weekData + days[i] + weekCalories.get(dayOfTheWeek - i - 1) + " calories" + "\n";
			totalConsumedCalories += weekCalories.get(dayOfTheWeek - i - 1);
		}
		
		weekData += "\nTotal consumed calories: " + totalConsumedCalories + " calories\n";
		weekData += "From total caloric needs: " + caloriesCtrl.getCaloricNeeds()*dayOfTheWeek + " calories\n";
		weekData += "Average calories intake per day: " + totalConsumedCalories/dayOfTheWeek + " calories\n";
		
		TextView daysDataTextView = (TextView) findViewById(R.id.daysDataTextView);
		daysDataTextView.setTextSize(18);
		daysDataTextView.setTextColor(Color.rgb(45, 100, 180));
		daysDataTextView.setText(weekData);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_week_data, menu);
		return true;
	}

}
