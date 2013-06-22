package com.example.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayProductsActivity extends ListActivity {
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_products);
		
//		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_display_products, FRUITS));
//		 
//		ListView listView = getListView();
//		listView.setTextFilterEnabled(true);
// 
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//			    // When clicked, show a toast with the TextView text
//			    Toast.makeText(getApplicationContext(),
//				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//			}
//		});
		
		ListView caloricNeedsTextView = (ListView) findViewById(R.id.historic_caloric_needs_textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_products, menu);
		return true;
	}

	public void addProduct(View view) {
		Intent intent = new Intent(this, com.example.view.AddProductActivity.class);
		startActivity(intent);
	}
}
