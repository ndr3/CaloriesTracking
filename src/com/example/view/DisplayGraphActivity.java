package com.example.view;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.ctrl.CaloriesCtrl;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.os.Bundle;
import android.app.Activity;
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
		// draw sin curve
//		int num = 150;
//		GraphViewData[] data = new GraphViewData[num];
//		double v=0;
//		for (int i=0; i<num; i++) {
//			v += 0.2;
//			data[i] = new GraphViewData(i, Math.sin(v));
//		}
		// graph with dynamically genereated horizontal and vertical labels
		GraphView graphView;

			graphView = new LineGraphView(
					this
					, "GraphView"
			);
			
		
//		final String[] xLabels = new String[] {
//				  "foo", "bar", "third", "bla", "more", "more2", "more3", "more4", "more5"
//				};
//				graphView = new LineGraphView(this, "example") {
//				   @Override
//				   protected String formatLabel(double value, boolean isValueX) {
//				      if (isValueX) {
//				         return xLabels[(int) value];
//				      } else {
//				         // return y label as number
//				         return super.formatLabel(value, isValueX); // let the y-value be normal-formatted
//				      }
//				   }
//				};
		
		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		
		
		GraphViewData[] data = new GraphViewData[dayOfTheWeek];
//		data[0] = new GraphViewData(caloriesCtrl.getTodayCalories());
			
		ArrayList<Integer> thisWeekCalories = caloriesCtrl.getThisWeekCalories();

		for (int i = 0; i < thisWeekCalories.size(); i++ ) {
			data[i] = new GraphViewData(i, thisWeekCalories.get(dayOfTheWeek - i - 1));
		}
		
			
		//}
		// add data
		graphView.addSeries(new GraphViewSeries(data));
		// set view port, start=2, size=40
		graphView.setViewPort(2, 40);
		graphView.setScrollable(true);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_graph, menu);
		return true;
	}

}
