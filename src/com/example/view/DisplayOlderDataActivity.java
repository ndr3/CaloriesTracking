package com.example.view;

import com.example.ctrl.CaloriesCtrl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DisplayOlderDataActivity extends Activity {
	private CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_older_data);
		
		caloriesCtrl = CaloriesCtrl.getInstance();
		@SuppressWarnings("unused")
		Intent intent = getIntent();
		
		TextView weekDataTextView = (TextView) findViewById(R.id.week_data_textView);
		weekDataTextView.setTextSize(20);
		weekDataTextView.setTextColor(Color.rgb(45, 100, 180));
		weekDataTextView.setText("This week data");
		
		TextView daysDataTextView = (TextView) findViewById(R.id.daysDataTextView);
		daysDataTextView.setTextSize(18);
		daysDataTextView.setTextColor(Color.rgb(45, 100, 180));
		daysDataTextView.setText(caloriesCtrl.getWeekData());
	}
	
	public void viewMonthData(View view) {
		Intent intent = new Intent(this, com.example.view.DisplayMonthlyDataActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_older_data, menu);
		return true;
	}

}
