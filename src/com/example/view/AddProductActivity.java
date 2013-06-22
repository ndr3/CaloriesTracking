package com.example.view;

import com.example.ctrl.CaloriesCtrl;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddProductActivity extends Activity {
	private CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		
		caloriesCtrl = CaloriesCtrl.getInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_productctivity, menu);
		return true;
	}
	
	public void addProduct(View view) {
		EditText editText = (EditText) findViewById(R.id.insert_name);
		String name = editText.getText().toString();
		
		editText = (EditText) findViewById(R.id.insert_calories_no);
		int calories = Integer.parseInt(editText.getText().toString());
		
		if (caloriesCtrl.addProduct(name, calories, null) == -1) {
			System.out.println("Failure when adding new product!");
		}
	}

}
