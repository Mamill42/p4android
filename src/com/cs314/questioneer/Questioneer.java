package com.cs314.questioneer;

import java.util.ArrayList;
import java.util.List;

public class Questioneer {
	List<String> questions;
	
	
	public Questioneer() {
		
	}

	public CharSequence getQuestion(int iteration) {
		
		return "This is a question from iteration " + ((Integer)iteration).toString();
	}
	
	public ArrayList<String> getAnswers(int iteration) {
		return new ArrayList<String>();
	}
}
