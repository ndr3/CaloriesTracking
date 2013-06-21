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

		//Advanced graph

		GraphView graphView;
		ArrayList<Integer> thisWeekCalories = caloriesCtrl.getThisWeekCalories();
		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;		
		
		int num = 5;
		GraphViewData[] data = new GraphViewData[num];
		double v=0;
		for (int i=0; i<num; i++) {
			v += 0.2;
			System.out.println("Math ==== " + Math.sin(v));
			data[i] = new GraphViewData(i, 1000); //thisWeekCalories.get(dayOfTheWeek - i - 1));
		}
		// graph with dynamically genereated horizontal and vertical labels
			
	    graphView = new LineGraphView(this, "example"); //{  
//	        @Override  
//	        protected String formatLabel(double value, boolean isValueX) {  
//	           if (!isValueX) {  
//	              // convert unix time to human time  
//	              return String.valueOf(value/1000.0);  
//	           } else return super.formatLabel(value, isValueX); // let the y-value be normal-formatted  
//	        }  
//	     };  
//		
//		Calendar calendar = Calendar.getInstance();
//		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;		
//		
//		GraphViewData[] data = new GraphViewData[dayOfTheWeek];			
//		ArrayList<Integer> thisWeekCalories = caloriesCtrl.getThisWeekCalories();
//
//		for (int i = 0; i < thisWeekCalories.size(); i++ ) {
//			System.out.println("-- new point " + thisWeekCalories.get(dayOfTheWeek - i - 1) + " " + i);
//			data[i] = new GraphViewData(thisWeekCalories.get(dayOfTheWeek - i - 1), i);
//		}
		
		//graphView.setHorizontalLabels( new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
		
		// add data
		graphView.addSeries(new GraphViewSeries(data));
		// set view port, start=2, size=40
		graphView.setViewPort(0, 4);
		graphView.setScrollable(true);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
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
