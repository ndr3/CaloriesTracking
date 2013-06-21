package com.example.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
		ArrayList<Integer> weekCalories = caloriesCtrl.getThisWeekCalories();
		
		
		TextView daysDataTextView = (TextView) findViewById(R.id.daysDataTextView);
		daysDataTextView.setTextSize(18);
		daysDataTextView.setTextColor(Color.rgb(45, 100, 180));
		daysDataTextView.setText(caloriesCtrl.getWeekData());
		//daysDataTextView.setText(weekData);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_week_data, menu);
		return true;
	}
	
	public void viewGraph(View view) {
		Intent intent = new Intent(this, com.example.view.DisplayGraphActivity.class);
		startActivity(intent);
	}

}
