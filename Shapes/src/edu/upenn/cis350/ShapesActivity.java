package edu.upenn.cis350;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ShapesActivity extends Activity {
    /** Called when the activity is first created. */
	public static final String DEBUG = "DEBUG";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onContinueClick(View view){
    	ShapesView v = (ShapesView)findViewById(R.id.shapeView);
    }
}