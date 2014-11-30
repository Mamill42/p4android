package com.cs314.p4;

import java.util.ArrayList;
import java.util.List;

import com.cs314.questioneer.Questioneer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends Activity {


	private static Questioneer questioneer;
	private Integer iteration;
	private Integer selectedAnswer = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//set up stuff
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);

		//receiving intent from previous activity
		Intent incomingIntent = getIntent();
		iteration = incomingIntent.getIntExtra("iteration",1); // 1 is default, if no iteration key/value was put into the intent
		
		
		//set question number
		TextView questionNumber = (TextView) findViewById(R.id.question_number_text_view);
		questionNumber.setText("Question number " + (iteration.toString()));
							
		//get question from questioneer and set text as such
		TextView questionTextView = (TextView) findViewById(R.id.question_content_text_view);
		questionTextView.setText(questioneer.getQuestion(iteration));
		
		//get answers from questioneer
		ArrayList<String> answers = new ArrayList<String>(questioneer.getAnswers(iteration));
		
		//set buttons' text to possible answers
		RadioButton firstQuestion = (RadioButton) findViewById(R.id.radio_first);
		RadioButton secondQuestion = (RadioButton) findViewById(R.id.radio_second);
		RadioButton thirdQuestion = (RadioButton) findViewById(R.id.radio_third);
		RadioButton fourthQuestion = (RadioButton) findViewById(R.id.radio_fourth);
		RadioButton fifthQuestion = (RadioButton) findViewById(R.id.radio_fifth);
		
		firstQuestion.setText(answers.get(0));
		secondQuestion.setText(answers.get(1));
		thirdQuestion.setText(answers.get(2));
		fourthQuestion.setText(answers.get(3));
		fifthQuestion.setText(answers.get(4));
	}

	public void onRadioButtonClicked(View view) {
    	boolean checked = ((RadioButton) view).isChecked();
    	switch(view.getId()) {
    	case R.id.radio_first:
    		if(checked)
    			selectedAnswer = 1;
    			break;
    	case R.id.radio_second:
    		if(checked)
    			selectedAnswer = 2;    			
    		break;
    	case R.id.radio_third:
    		if(checked)
    			selectedAnswer = 3;    			
    		break;
    	case R.id.radio_fourth:
    		if(checked)
    			selectedAnswer = 4;
    		break;
    	case R.id.radio_fifth:
    		if(checked)
    			selectedAnswer = 5;
    		break;
    	
    	}
    }
	
	public void onButtonClickedBack(View view) {
		//if first question, go back to main activity and discard any answer
		if(iteration.equals(1)) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else { //decrement iterator and send intent to question activity, and save answer
			Intent intent = new Intent(this, QuestionActivity.class);
			intent.putExtra("iteration", --iteration);
			//get selected answer from selectedAnswer and save it in questioneer object
			startActivity(intent);
		}
		
	}

	public void onButtonClickedNext(View view) {
		//if last question, go to stats activity
		if(iteration.equals(10)) {
			Intent intent = new Intent(this, StatisticsActivity.class);
			startActivity(intent);
		}
		else { //increment iterator and save answer
			Intent intent = new Intent(this, QuestionActivity.class);
			intent.putExtra("iteration", ++iteration);
			//get selected answer from selectedAnswer and save it in questioneer object
			startActivity(intent);
		}
	}

	public void onButtonClickedAnswer(View view) { // only this button will save answers
		//if last question, go to stats activity
		
	}

	public void onButtonClickedDone(View view) {
		//from anywhere, go to stats activity
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}
}