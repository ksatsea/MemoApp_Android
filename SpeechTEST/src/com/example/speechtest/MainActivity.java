package com.example.speechtest;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



//http://www.adakoda.com/android/000164.html


public class MainActivity extends Activity {
	private static final int REQUEST_CODE = 0;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button)findViewById(R.id.buttonA);

        button.setOnClickListener(new View.OnClickListener() {
            ToggleButton tgb_jpen = (ToggleButton)findViewById(R.id.toggleButton1);
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
								RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

					if (tgb_jpen.isChecked()) {
						intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
					}
					else
					{
						intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja-JP");
					}

					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"âπê∫ì¸óÕÉeÉXÉg");
					
					startActivityForResult(intent, REQUEST_CODE);
					
					
					
				} catch(Exception e) {
						//ActivityNotFoundException e){
					Toast.makeText(MainActivity.this,
									"ActivityNotFoundException",
									Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE && resultCode == RESULT_OK ){
    		String resultsString ="";
    		ArrayList<String>results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
/*
    		for(int i=0;i<results.size();i++){
    			resultsString+=results.get(i);
    		}
    		Toast.makeText(this,resultsString,Toast.LENGTH_LONG).show();
    		*/
    		
    		TextView t_tview = (TextView)findViewById(R.id.text_view);
			t_tview.setText("");
    		for(int i=0;i<results.size();i++){
    			t_tview.append(i+"  ::  "+results.get(i)+"\n");
    		}

    	}
    	super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
