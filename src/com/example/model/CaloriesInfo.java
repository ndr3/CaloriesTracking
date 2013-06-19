package com.example.model;

import com.example.view.R;

public class CaloriesInfo {
	private static CaloriesInfo instance =  null;
	
	private int todayCalories;
	private int caloricNeeds;
	private int remainingCalories;
	
	public CaloriesInfo() {
		todayCalories = 0;
		caloricNeeds = 0;
		remainingCalories = 0;
	}
	
	public static CaloriesInfo getInstance() {
		if (instance == null) {
			instance = new CaloriesInfo();
		}
		
		return instance;
	}

	public void setTodayCalories(int todayCalories) {
		this.todayCalories = todayCalories;
		this.remainingCalories = this.caloricNeeds - this.todayCalories;
	}

	public void setCaloricNeeds(int caloricNeeds) {
		this.caloricNeeds = caloricNeeds;
		this.remainingCalories = this.caloricNeeds - this.todayCalories;
	}

	public int getTodayCalories() {
		return todayCalories;
	}

	public int getCaloricNeeds() {
		return caloricNeeds;
	}

	public int getRemainingCalories() {
		return remainingCalories;
	}

	public void setRemainingCalories(int remainingCalories) {
		this.remainingCalories = remainingCalories;
	}

	public int computeDailyCaloricNeeds(int weight, int height, int age, int genderID, int activityID) throws Exception {
		//convert to pounds and inches
		weight *= 2.2046;
		height /= 2.54;
		
		int bmr;
		if (genderID == R.id.radio_female) {
			bmr = (int) (655 + 4.3*weight + 4.7*height - 4.7*age);
		} else {
			bmr = (int) (66 + 6.3*weight + 12.9*height - 6.8*age);
		}
		
		int caloricNeeds;
		switch (activityID) {
			case R.id.radio_sedentary:
				caloricNeeds = bmr + bmr/5;
				break;
			case R.id.radio_lightly_active:
				caloricNeeds = bmr + 3*bmr/10;
				break;
			case R.id.radio_moderately_active:
				caloricNeeds = bmr + 2*bmr/5;
				break;
			case R.id.radio_very_active:
				caloricNeeds = bmr + bmr/2;
				break;
			case R.id.radio_extra_active:
				caloricNeeds = bmr + 3*bmr/5;
				break;
			default: caloricNeeds = 0;				
		}
		
		if (caloricNeeds <= 0 ) {
			throw new Exception();
		}
		
		return caloricNeeds;
	}
	
	public void addCalories(int calories) {
		setTodayCalories(getTodayCalories() + calories);
	}
}
