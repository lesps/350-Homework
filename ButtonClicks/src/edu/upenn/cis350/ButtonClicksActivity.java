package edu.upenn.cis350;

import android.app.Activity;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.TextView;

public class ButtonClicksActivity extends Activity {
	private int count;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        count = 0;
    }
    
    public void onButtonClick(View view){
    	TextView text = (TextView) findViewById(R.id.count);
    	
    	text.setText(new Integer(++count).toString());
    	
    }
}