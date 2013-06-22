package com.example.view;

import java.util.ArrayList;

import com.example.ctrl.CaloriesCtrl;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayProductListActivity extends ListActivity {
	public CaloriesCtrl caloriesCtrl;
	
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// no more this
		// setContentView(R.layout.list_fruit);
		caloriesCtrl = CaloriesCtrl.getInstance();
		ArrayList<String> allProductsWithCalories = new ArrayList<String>();
		allProductsWithCalories = caloriesCtrl.getAllProductsWithCalories();
		System.out.println(allProductsWithCalories);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_display_product_list, allProductsWithCalories));
	
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
	
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	
	
	}
}