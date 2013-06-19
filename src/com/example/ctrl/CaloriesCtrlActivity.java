package com.example.ctrl;

import com.example.model.CaloriesInfo;
import com.example.utils.CaloriesDbAdapter;

public class CaloriesCtrlActivity {
	private CaloriesInfo caloriesInfo;
	private CaloriesDbAdapter caloriesDbAdapter = null;
	
	public CaloriesCtrlActivity() {
		caloriesInfo = CaloriesInfo.getInstance(); 
		caloriesDbAdapter = CaloriesDbAdapter.getInstance(null);
		
		//fill the model		
		
		int caloricNeeds = 0;
		int consumedCalories = 0;
		
		try {								
			caloricNeeds = caloriesDbAdapter.fetchCaloricNeeds(); 
		} catch (Exception e) {			
			int defaultCaloricNeeds = 2000;			
			caloricNeeds = defaultCaloricNeeds;
			caloriesDbAdapter.setCaloricNeeds(defaultCaloricNeeds);
			
			System.out.println("Ctrl/Constructor exception");
			e.printStackTrace();
		}
		
		try {			
			consumedCalories = caloriesDbAdapter.fetchTodayCalories();
		} catch (Exception e) {		
			int defaultTotalCalories = 0;
			consumedCalories = defaultTotalCalories;
			caloriesDbAdapter.addCalories(defaultTotalCalories);
			
			System.out.println("Ctrl/Constructor exception");
			e.printStackTrace();
		}

		caloriesInfo.setCaloricNeeds(caloricNeeds);
		caloriesInfo.setTodayCalories(consumedCalories);		
		caloriesInfo.setRemainingCalories(caloricNeeds - consumedCalories);
		
	}
	
	public void addCalories(int calories) {		
		try {
			caloriesInfo.addCalories(calories);
			caloriesDbAdapter.addCalories(calories);		
		} catch (Exception e) {
			System.out.println("Ctrl/getCalories exception");
			e.printStackTrace();
		}
	}
	
	public int getTodayCalories() {
		return caloriesInfo.getTodayCalories();
	}
	
	public int getCaloricNeeds() {
		try {
			return caloriesDbAdapter.fetchCaloricNeeds();
		} catch (Exception e) {
			System.out.println("Ctrl/getCaloricNeeds exception");
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getRemainingCalories() {
		try {
			return caloriesInfo.getRemainingCalories();
		} catch (Exception e) {
			System.out.println("Ctrl/getRemainingCalories exception");
			e.printStackTrace();
			return 0;
		}
	}
	
	public void setCaloricNeeds(int weight, int height, int age, int genderID, int activityID) {
		try {
			int caloricNeeds = caloriesInfo.computeDailyCaloricNeeds(weight, height, age, genderID, activityID);
			caloriesInfo.setCaloricNeeds(caloricNeeds);
			caloriesDbAdapter.setCaloricNeeds(caloricNeeds);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getWeekData() {
		return caloriesDbAdapter.getWeekData();
	}
}
