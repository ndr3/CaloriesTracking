package com.example.view;

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

		//Advanced graph
		// draw sin curve
		int num = 150;
		GraphViewData[] data = new GraphViewData[num];
		double v=0;
		for (int i=0; i<num; i++) {
			v += 0.2;
			data[i] = new GraphViewData(i, Math.sin(v));
		}
		// graph with dynamically genereated horizontal and vertical labels
		GraphView graphView;

			graphView = new LineGraphView(
					this
					, "GraphViewDemo"
			);
			
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
