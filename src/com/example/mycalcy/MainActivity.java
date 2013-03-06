package com.example.mycalcy;


import java.util.ArrayList;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnInitListener {

	public String arithmExp = "";
	public String result = "";
	private TextToSpeech tts;
    public int SPEECH_REQUEST_CODE = 1234;
    public int length;
	public Button speak; 
	public EditText display;
	public Evaluator eval;
	public Button button1;
	public Button button2;
	public Button button3;
	public Button button4;
	public Button button5;
	public Button button6;
	public Button button7;
	public Button button8;
	public Button button9;
	public Button button0;
	public Button buttondiv;
	public Button buttonmul;
	public Button buttonsub;
	public Button buttonadd;
	public Button buttonclear;
	public Button buttondel;
	public Button buttondot;
	public Button buttoneql;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		speak = (Button)findViewById(R.id.buttonspeak);
		display =(EditText) findViewById(R.id.editText1);
		eval  = new Evaluator();
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
		button0 = (Button) findViewById(R.id.button0);
		buttondiv = (Button) findViewById(R.id.buttondiv);
		buttonmul = (Button) findViewById(R.id.buttonmul);
		buttonsub = (Button) findViewById(R.id.buttonsub);
		buttonadd = (Button) findViewById(R.id.buttonadd);
		buttonclear = (Button) findViewById(R.id.buttonclear);
		buttondel = (Button) findViewById(R.id.buttondel);
		buttondot = (Button) findViewById(R.id.buttondot);
		buttoneql = (Button) findViewById(R.id.buttoneql);
		
		display.setSelection(0);
			
		
		display.setSingleLine();
		tts = new TextToSpeech(this, this);
		
		speak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendRecognizeIntent();
            }
        });
		
		speak.setEnabled(false);
		button1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton1();
			}
		});
		
		button2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton2();
			}
		});
		
		button3.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton3();
			}
		});
		
		button4.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton4();
			}
		});
		
		button5.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton5();
			}
		});
		
		button6.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton6();
			}
		});
		
		button7.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton7();
			}
		});
		
		button8.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton8();
			}
		});
		
		button9.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton9();
			}
		});
		
		button0.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbutton0();
			}
		});

		buttonadd.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttonadd();
			}
		});
		
		buttondiv.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttondiv();
			}
		});
		
		buttonmul.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttonmul();
			}
		});
		
		buttonsub.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttonsub();
			}
		});
		
		buttonclear.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttonclear();
			}
		});
		
		buttondel.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttondel();
			}
		});
		
		buttondot.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttondot();
			}
		});
		
		buttoneql.setOnClickListener(new OnClickListener(){
			public void onClick(View arg){
				pressbuttoneql();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onInit(int status)
    {
		
        if (status == TextToSpeech.SUCCESS)
        {
            speak.setEnabled(true);
        }
        else
        {
            //failed to init
            finish();
        }
        
    }
	private void sendRecognizeIntent()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }
	
	@Override
    protected void
            onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SPEECH_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                
                if (matches.size() == 0)
                {
                    tts.speak("Heard nothing", TextToSpeech.QUEUE_FLUSH, null);
                }
                else
                {
                	String mostLikelyThingHeard = null;
                	for(int i=0;i <10;i++)
                	{
                		String strmatch = matches.get(i);
                		if(Integer.parseInt(strmatch) < 20) mostLikelyThingHeard = strmatch;
                		else if(strmatch.contains("+") || strmatch.contains("add") ||strmatch.contains("addition"))
                		{
                			mostLikelyThingHeard = "Plus";
                		}
                		else if(strmatch.contains("-") || strmatch.contains("subtract") ||strmatch.contains("minus"))
                		{
                			mostLikelyThingHeard = "Minus";
                		}
                		else if(strmatch.contains("/") || strmatch.contains("divide") ||strmatch.contains("by"))
                		{
                			mostLikelyThingHeard = "Divide";
                		}
                		else if(strmatch.contains("*") || strmatch.contains("times") ||strmatch.contains("multiply"))
                		{
                			mostLikelyThingHeard = "Multiply";
                		}
                		else if(strmatch.contains("clear") || strmatch.contains("clean"))
                		{
                			mostLikelyThingHeard = "Clear";
                		}
                		else if(strmatch.contains(".") || strmatch.contains("dot") ||strmatch.contains("point"))
                		{
                			mostLikelyThingHeard = "Dot";
                		}
                		else if(strmatch.contains("=") || strmatch.contains("equal") ||strmatch.contains("answer"))
                		{
                			mostLikelyThingHeard = "Equals";
                		}
                		else
                		{
                			//
                		}
                		if(mostLikelyThingHeard.length() > 0) break;
                	}
                    
                    if(mostLikelyThingHeard.contains("0"))
                    {
                    	pressbutton0();
                    }
                    else if(mostLikelyThingHeard.contains("1"))
                    {
                    	pressbutton1();
                    }
                    else if(mostLikelyThingHeard.contains("2"))
                    {
                    	pressbutton2();
                    }
                    else if(mostLikelyThingHeard.contains("3"))
                    {
                    	pressbutton3();
                    }
                    else if(mostLikelyThingHeard.contains("4"))
                    {
                    	pressbutton4();
                    }
                    else if(mostLikelyThingHeard.contains("5"))
                    {
                    	pressbutton5();
                    }
                    else if(mostLikelyThingHeard.contains("6"))
                    {
                    	pressbutton6();
                    }
                    else if(mostLikelyThingHeard.contains("7"))
                    {
                    	pressbutton7();
                    }
                    else if(mostLikelyThingHeard.contains("8"))
                    {
                    	pressbutton8();
                    }
                    else if(mostLikelyThingHeard.contains("9"))
                    {
                    	pressbutton9();
                    }
                    else if(mostLikelyThingHeard.contains("Plus"))
                    {
                    	pressbuttonadd();
                    }
                    else if(mostLikelyThingHeard.contains("Minus"))
                    {
                    	pressbuttonsub();
                    }
                    else if(mostLikelyThingHeard.contains("Divide"))
                    {
                    	pressbuttondiv();
                    }
                    else if(mostLikelyThingHeard.contains("Multiply"))
                    {
                    	pressbuttonmul();
                    }
                    else if(mostLikelyThingHeard.contains("Equals"))
                    {
                    	pressbuttoneql();
                    }
                    else if(mostLikelyThingHeard.contains("Dot"))
                    {
                    	pressbuttondot();
                    }
                    
                    else
                    {
                    	//
                    }
                    tts.speak(" ", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
            else
            {
                Log.d("TAG", "result NOT ok");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

	public void pressbutton0()
	{
		Log.i("Button", "Button0");
		arithmExp = arithmExp + "0";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	
	public void pressbutton1()
	{
		Log.i("Button", "Button1");
		arithmExp = arithmExp + "1";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	
	public void pressbutton2()
	{
		Log.i("Button", "Button2");
		arithmExp = arithmExp + "2";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	
	public void pressbutton3()
	{
		Log.i("Button", "Button3");
		arithmExp = arithmExp + "3";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	
	public void pressbutton4()
	{
		Log.i("Button", "Button4");
		arithmExp = arithmExp + "4";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbutton5()
	{
		Log.i("Button", "Button5");
		arithmExp = arithmExp + "5";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbutton6()
	{
		Log.i("Button", "Button6");
		arithmExp = arithmExp + "6";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbutton7()
	{
		Log.i("Button", "Button7");
		result = "";
		arithmExp = arithmExp + "7"; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbutton8()
	{
		Log.i("Button", "Button8");
		result = "";
		arithmExp = arithmExp + "8"; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbutton9()
	{
		Log.i("Button", "Button9");
		arithmExp = arithmExp + "9";
		result = "";
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttoneql()
	{
		Log.i("Button", "Buttoneql");
		try
		{
			result = eval.evaluate(arithmExp);
			display.setText(result);
			display.setSelection(display.getText().length());
		}
		catch(EvaluationException e)
		{
			display.setText("INVALID");
			display.setSelection(display.getText().length());
		}
		arithmExp = "";
	}
	public void pressbuttonadd()
	{
		Log.i("Button", "Buttonadd");
		if(arithmExp.length() == 0 && result.length() > 0) arithmExp = result;
		arithmExp = arithmExp + "+"; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttonsub()
	{
		Log.i("Button", "Buttonsub");
		Log.i("arithm", arithmExp);
		Log.i("result", result);
		if(arithmExp.length() == 0 && result.length() > 0) arithmExp = result;
		arithmExp = arithmExp + "-"; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttondiv()
	{
		Log.i("Button", "Buttondiv");
		if(arithmExp.length() == 0 && result.length() > 0) arithmExp = result;
		arithmExp = arithmExp + "/"; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttonmul()
	{
		Log.i("Button", "Buttonmul");
		Log.i("arithm", String.valueOf(arithmExp.length()));
		Log.i("result", result);
		if(arithmExp.length() == 0 && result.length() > 0) arithmExp = result;
		arithmExp = arithmExp + "*"; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttondot()
	{
		Log.i("Button", "Buttondot");
		arithmExp = arithmExp + "."; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttondel()
	{
		Log.i("Button", "Buttondel");
		if(arithmExp.length() == 0 && result.length() > 0) arithmExp = result;
		result = "";
		length = arithmExp.length();
		if(length != 0)
		{
			arithmExp = arithmExp.substring(0, length-1);
		}
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
	public void pressbuttonclear()
	{
		Log.i("Button", "Buttonclear");
		arithmExp = ""; 
		display.setText(arithmExp);
		display.setSelection(display.getText().length());
	}
}
