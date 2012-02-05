package edu.upenn.cis350;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TypingGameActivity extends Activity {
	
	private static final int READY_DIALOG = 1;
	private static final int CORRECT_DIALOG = 2;
	private static final int INCORRECT_DIALOG = 3;
	private long startTime;
	private double totalTime;
	private double bestTime;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showDialog(READY_DIALOG);
    }
    
    protected Dialog onCreateDialog(int id) {
    	if (id == READY_DIALOG) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // this is the message to display
	    	builder.setMessage(R.string.ready); 
                // this is the button to display
	    	builder.setPositiveButton(R.string.yes,
	    		new DialogInterface.OnClickListener() {
                           // this is the method to call when the button is clicked 
	    	           public void onClick(DialogInterface dialog, int id) {
                                   // this will hide the dialog
	    	        	   dialog.cancel();
	    	        	   startTime = System.currentTimeMillis();
	    	           }
	    	         });
    		return builder.create();
    	}
    	else if (id == CORRECT_DIALOG) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // this is the message to display
    	builder.setMessage("Super good job!" + " That took you " + totalTime + " seconds!" + 
    			" Your best time is " + bestTime + " seconds."); 
            // this is the button to display
    	builder.setPositiveButton(R.string.again,
    		new DialogInterface.OnClickListener() {
                       // this is the method to call when the button is clicked 
    	           public void onClick(DialogInterface dialog, int id) {
                               // this will hide the dialog
    	        	   dialog.cancel();
    	        	   startTime = System.currentTimeMillis();
    	           }
    	         });
		return builder.create();
	}
    	else if (id == INCORRECT_DIALOG) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // this is the message to display
    	builder.setMessage(R.string.incorrect); 
            // this is the button to display
    	builder.setPositiveButton(R.string.again,
    		new DialogInterface.OnClickListener() {
                       // this is the method to call when the button is clicked 
    	           public void onClick(DialogInterface dialog, int id) {
                               // this will hide the dialog
    	        	   dialog.cancel();
    	        	   startTime = System.currentTimeMillis();
    	           }
    	         });
		return builder.create();
	}
    	else return null;
    }
    
    public void onSubmitClick(View view){
    	EditText box = (EditText) findViewById(R.id.input);
    	TextView text = (TextView) findViewById(R.id.typingText);
    	if(box.getText().toString().equals(text.getText().toString())){
    		totalTime = (System.currentTimeMillis() - startTime)/1000;
    		if(bestTime==0||totalTime<bestTime)
    			bestTime=totalTime;
    		removeDialog(CORRECT_DIALOG);
    		showDialog(CORRECT_DIALOG);
    	}
    	else{
    		removeDialog(INCORRECT_DIALOG);
    		showDialog(INCORRECT_DIALOG);
    	}
    }
    
    public void onQuit(View view){
    	finish();
    }
}