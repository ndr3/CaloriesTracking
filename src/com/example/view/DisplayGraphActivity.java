package com.example.view;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.ctrl.CaloriesCtrl;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.LinearLayout;

public class DisplayGraphActivity extends Activity {
	CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_graph);
		
		caloriesCtrl = CaloriesCtrl.getInstance();

		//Week calories graph

		GraphView graphView;
		ArrayList<Integer> thisWeekCalories = caloriesCtrl.getThisWeekCalories();
		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;		
		
		GraphViewData[] data = new GraphViewData[dayOfTheWeek];
		for (int i=0; i<dayOfTheWeek; i++) {
			data[i] = new GraphViewData(i, thisWeekCalories.get(dayOfTheWeek - i - 1));
		}
		
	    String[] weekDays = new  String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	    String[] currentWeekDays = new String[dayOfTheWeek];
	    for (int i = 0; i < dayOfTheWeek; i++) {
	    	currentWeekDays[i] = weekDays[i];
	    }
	    
	    if (getIntent().getStringExtra("type").equals("bar")) {
			graphView = new BarGraphView(
					this
					, "This week"
			);
		} else {
			graphView = new LineGraphView(
					this
					,  "This week"
			);
		}    
	    graphView.setHorizontalLabels(currentWeekDays);
		graphView.addSeries(new GraphViewSeries(data));
		graphView.setViewPort(0, dayOfTheWeek - 1);
		graphView.setScrollable(true);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.setBackgroundColor(Color.BLACK);
		layout.addView(graphView);
		
		
		//Month calories graph
		ArrayList<Integer> thisMonthCalories = caloriesCtrl.getThisMonthCalories();
		int dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);	
		
		data = new GraphViewData[dayOfTheMonth];
		for (int i=0; i<dayOfTheMonth; i++) {
			data[i] = new GraphViewData(i, thisMonthCalories.get(dayOfTheMonth - i - 1));
		}				
		
		String[] currentMonthDays = new String[dayOfTheMonth];
	    for (int i = 0; i < dayOfTheMonth; i++) {
	    	currentMonthDays[i] = String.valueOf(i + 1);
	    }
		
	    if (getIntent().getStringExtra("type").equals("bar")) {
			graphView = new BarGraphView(
					this
					, "This month"
			);
		} else {
			graphView = new LineGraphView(
					this
					, "This month"
			);
			((LineGraphView) graphView).setDrawBackground(true);
		}

		graphView.setHorizontalLabels(currentMonthDays);
		graphView.addSeries(new GraphViewSeries(data));
		graphView.setViewPort(0, dayOfTheMonth - 1);
		graphView.setScalable(true);
		layout = (LinearLayout) findViewById(R.id.graph2);
		layout.setBackgroundColor(Color.BLACK);
		layout.addView(graphView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_graph, menu);
		return true;
	}

}
