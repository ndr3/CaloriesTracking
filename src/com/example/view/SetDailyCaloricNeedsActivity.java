package com.example.view;

import com.example.utils.CaloriesDbAdapter;
import com.example.view.R;
import com.example.ctrl.CaloriesCtrl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SetDailyCaloricNeedsActivity extends Activity {	
	private CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_daily_caloric_needs);
	
		CaloriesDbAdapter.getInstance(this);		
		caloriesCtrl = CaloriesCtrl.getInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_daily_caloric_needs, menu);
		return true;
	}
	
	public void computeDailyCaloricNeeds(View view) {
		EditText editText = (EditText) findViewById(R.id.insert_weight_edittext);
		int weight = Integer.parseInt(editText.getText().toString());
		
		editText = (EditText) findViewById(R.id.insert_height_edittext);
		int height = Integer.parseInt(editText.getText().toString());
		
		editText = (EditText) findViewById(R.id.insert_age_edittext);
		int age = Integer.parseInt(editText.getText().toString());
		
		
		RadioGroup radioGenderGroup = (RadioGroup) findViewById(R.id.gender_group);
		int genderID = radioGenderGroup.getCheckedRadioButtonId();
		
		RadioGroup radioActivityGroup = (RadioGroup) findViewById(R.id.activity_group);
		int activityID = radioActivityGroup.getCheckedRadioButtonId();
		
		caloriesCtrl.setCaloricNeeds(weight, height, age, genderID, activityID);
		
		Intent intent = new Intent(this, DisplayCaloricNeedsActivity.class);
		startActivity(intent);
	}
}
