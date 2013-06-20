package com.example.view;

import com.example.ctrl.CaloriesCtrl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Menu;
import android.view.View;

public class DisplayGraphActivity extends Activity {
	CaloriesCtrl caloriesCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_graph);
		//setContentView(new MyView(this));
		
		caloriesCtrl = CaloriesCtrl.getInstance();
		@SuppressWarnings("unused")
		Intent intent = getIntent();
		
		
		
		GraphView graphView = new GraphView(
				  this // context
				  , new GraphViewData[] {
				    new GraphViewData(1, 2.0d)
				    , new GraphViewData(2, 1.5d)
				    , new GraphViewData(2.5, 3.0d) // another frequency
				    , new GraphViewData(3, 2.5d)
				    , new GraphViewData(4, 1.0d)
				    , new GraphViewData(5, 3.0d)
				  } // data
				  , "GraphViewDemo" // heading
				  , null // dynamic labels
				  , null // dynamic labels
				);
	}

	public class MyView extends View {
		class Pt{

	  		float x, y;

	  		

	  		Pt(float _x, float _y){

	  			x = _x;

	  			y = _y;

	  		}

	  	}
		Pt[] myPath = { new Pt(100, 100),

					new Pt(200, 200),

					new Pt(200, 500),

					new Pt(400, 500),

					new Pt(400, 200)

					};



	public MyView(Context context) {

		super(context);

		// TODO Auto-generated constructor stub

	}



	@Override

	protected void onDraw(Canvas canvas) {

		// TODO Auto-generated method stub

		super.onDraw(canvas);



		

		Paint paint = new Paint();

		paint.setColor(Color.WHITE);

		paint.setStrokeWidth(3);

		paint.setStyle(Paint.Style.STROKE);

		Path path = new Path();

		

		path.moveTo(myPath[0].x, myPath[0].y);

		for (int i = 1; i < myPath.length; i++){

			path.lineTo(myPath[i].x, myPath[i].y);

		}

		canvas.drawPath(path, paint);

		

	}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_graph, menu);
		return true;
	}

}
