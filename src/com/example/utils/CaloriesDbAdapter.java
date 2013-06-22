package com.example.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class CaloriesDbAdapter extends Activity {
	private static CaloriesDbAdapter instance = null;
	
	private static final String KEY_ROWID = "_id";
	private static final String KEY_CALORIES = "calories_no";
	private static final String KEY_CALORIC_NEED = "caloric_need";
	private static final String KEY_EATEN_DATE = "eaten_date";
	private static final String KEY_NAME = "name";
	private static final String KEY_BARCODE = "barcode";
			
	
	private static final String TAG = "CaloriesDbAdapter";
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	private static final String CREATE_CALORIES_TABLE = "CREATE TABLE IF NOT EXISTS calories (_id integer primary key autoincrement, calories_no integer not null, eaten_date long )";
	private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (_id integer primary key autoincrement, caloric_need integer)";
	private static final String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS products (_id integer primary key autoincrement, name string not null, calories_no integer not null, barcode string)";
	
	private static final String DATABASE_NAME = "CaloriesDB2";
	private static final String DATABASE_CALORIES_TABLE = "calories";
	private static final String DATABASE_USERS_TABLE = "users";
	private static final String DATABASE_PRODUCTS_TABLE = "products";
	private static final int DATABASE_VERSION = 2;
	
	private final Context ctx;
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		private static DatabaseHelper mInstance = null;
		
		public static DatabaseHelper getInstance(Context ctx) {
			if (mInstance == null) {
				mInstance = new DatabaseHelper(ctx.getApplicationContext());
			}		
			return mInstance;
		}
		
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.beginTransaction();
			try {
				db.execSQL(CREATE_CALORIES_TABLE);
				db.execSQL(CREATE_USERS_TABLE);
				db.execSQL(CREATE_PRODUCTS_TABLE);
				
				db.setTransactionSuccessful();
			}finally {
				db.endTransaction();
			}
		}

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS calories");
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS products");
            onCreate(db);
        }
	}
	
	public static CaloriesDbAdapter getInstance(Context ctx) {
		if ((instance == null) && (ctx != null)) {
			instance = new CaloriesDbAdapter(ctx);
			instance.open();
		}
		
		return instance;
	}
	
	public CaloriesDbAdapter(Context ctx) {
		this.ctx = ctx;
	}
	
	
	public CaloriesDbAdapter open() throws SQLException {
		try {
			dbHelper = DatabaseHelper.getInstance(ctx);					
			db = dbHelper.getWritableDatabase();
			
//			addProduct("Product1", 350, "codebar1");
//			addProduct("Product2", 100, null);
//			addProduct("Product1", 350, "codebar1");
//			addProduct("Product3", 100, null);
//			addProduct("Product4", 350, "codebar1");
//			addProduct("Product5", 100, null);
			
//			ContentValues values = new ContentValues();
//			Calendar rightNow = Calendar.getInstance();
//			long timestamp = rightNow.getTimeInMillis();
//			values.put(KEY_EATEN_DATE, timestamp - 56*60*60*1000);
//			values.put(KEY_CALORIES, 500);
//			
//			db.insert(DATABASE_CALORIES_TABLE, null, values);
			
			
			
			return this;
		} catch (NullPointerException e) {
			System.out.println("CaloriesDbAdapter/Open null pointer exception");
			return null;
		} catch (Exception ex) {
			System.out.println("CaloriesDbAdapter/Open exception");
			return null;
		} 
	}
	
	public long addCalories(int calories) {
		ContentValues values = new ContentValues();
		Calendar rightNow = Calendar.getInstance();
		long timestamp = rightNow.getTimeInMillis();
		values.put(KEY_EATEN_DATE, timestamp);
		values.put(KEY_CALORIES, calories);
		
		return db.insert(DATABASE_CALORIES_TABLE, null, values);
	}
	
	public long addProduct(String name, int calories, String barcode) {
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
		values.put(KEY_CALORIES, calories);
		values.put(KEY_BARCODE, barcode);
		
		return db.insert(DATABASE_PRODUCTS_TABLE, null, values);
	}

	public long setCaloricNeeds(int caloricNeeds) {
		String strFilter = "_id=1";
		ContentValues values = new ContentValues();
		values.put(KEY_CALORIC_NEED, caloricNeeds);
		
		Cursor c = db.rawQuery("SELECT COUNT(*) FROM users", null);
		if (c != null) {
			c.moveToFirst();
			if (c.getInt(0) == 0) {
				//table is empty -> insert values
				return db.insert(DATABASE_USERS_TABLE, null, values);
			} else { //update values
				return db.update(DATABASE_USERS_TABLE, values, strFilter, null);
			}			
		}
		
		return 0;
	}
	
	public Cursor fetchAllCalories() {
		return db.query(DATABASE_CALORIES_TABLE, new String[] {KEY_ROWID, KEY_CALORIES}, null, null, null, null, null);
	}

	@SuppressWarnings("deprecation")
	public int fetchTodayCalories() {	
		//compute the miliseconds that passed since yesterday
		Date d = new Date();
		int hours = d.getHours() + 3;
		int minutes = d.getMinutes();
		int seconds = d.getSeconds();
		
		long todayPassedMilisecs = hours*60*60*1000 + minutes*60*1000 + seconds*1000;
		
		Calendar rightNow = Calendar.getInstance();
		long timestamp = rightNow.getTimeInMillis();
		long yesterday = timestamp - todayPassedMilisecs; 		
		
		//select only calories eaten today
		int consumedCalories = 0;
		Cursor todayCaloriesCursor = db.rawQuery("SELECT * FROM calories WHERE eaten_date > ?", new String[] {String.valueOf(yesterday)});
		for (todayCaloriesCursor.moveToFirst(); !todayCaloriesCursor.isAfterLast(); todayCaloriesCursor.moveToNext())
			consumedCalories += todayCaloriesCursor.getInt(1);
		
		return consumedCalories;
			
	}
	
	public int fetchCaloricNeeds() {
		try {
		Cursor caloricNeedsCursor = db.query(DATABASE_USERS_TABLE, new String[] {KEY_CALORIC_NEED}, null, null, null, null, null);
		caloricNeedsCursor.moveToFirst();
		return caloricNeedsCursor.getInt(0);
		} catch (Exception e) {
			System.out.println("DbAdapter/fetchCaloricNeeds exception");
			e.printStackTrace();
			return 2000;
		}
		
	}
	
	public int fetchDailyCalories(long begin, long end) {
		int consumedCalories = 0;
		try {
			Cursor todayCaloriesCursor = db.rawQuery("SELECT * FROM calories WHERE eaten_date > ? AND eaten_date < ?", new String[] {String.valueOf(begin), String.valueOf(end)});
			for (todayCaloriesCursor.moveToFirst(); !todayCaloriesCursor.isAfterLast(); todayCaloriesCursor.moveToNext())
				consumedCalories += todayCaloriesCursor.getInt(1);
			
			return consumedCalories;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	//retrieve calories in reverse order of days
	public ArrayList<Integer> fetchThisWeekCalories() {
		ArrayList<Integer> caloriesList = new ArrayList<Integer>();
		
		caloriesList.add(fetchTodayCalories());
		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		Date d = new Date();
		int hours = d.getHours() + 3;
		int minutes = d.getMinutes();
		int seconds = d.getSeconds();
		
		long todayPassedMilisecs = hours*60*60*1000 + minutes*60*1000 + seconds*1000;
		long rightNow = calendar.getTimeInMillis();		
		long begginingOfToday = rightNow - todayPassedMilisecs;
		long wholeDayInMilisecs = 24*60*60*1000;
		
		int passedDays = 1;		
		int dailyCalories = 0;
		
		for (int i = dayOfTheWeek - 1; i > 0; i--) {
			dailyCalories = fetchDailyCalories(begginingOfToday - wholeDayInMilisecs*passedDays, begginingOfToday - wholeDayInMilisecs*(passedDays - 1));
			caloriesList.add(dailyCalories);
			passedDays++;
		}		
		
		return caloriesList;
	}
	
	@SuppressWarnings("deprecation")
	//retrieve calories in reverse order of days
	public ArrayList<Integer> fetchThisMonthCalories() {
		ArrayList<Integer> caloriesList = new ArrayList<Integer>();
		
		caloriesList.add(fetchTodayCalories());
		
		Calendar calendar = Calendar.getInstance();
		int dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		Date d = new Date();
		int hours = d.getHours() + 3;
		int minutes = d.getMinutes();
		int seconds = d.getSeconds();
		
		long todayPassedMilisecs = hours*60*60*1000 + minutes*60*1000 + seconds*1000;
		long rightNow = calendar.getTimeInMillis();		
		long begginingOfToday = rightNow - todayPassedMilisecs;
		long wholeDayInMilisecs = 24*60*60*1000;
		
		int passedDays = 1;		
		int dailyCalories = 0;
		
		for (int i = dayOfTheMonth - 1; i > 0; i--) {
			dailyCalories = fetchDailyCalories(begginingOfToday - wholeDayInMilisecs*passedDays, begginingOfToday - wholeDayInMilisecs*(passedDays - 1));
			caloriesList.add(dailyCalories);
			passedDays++;
		}		
		
		return caloriesList;
	}
	
	public ArrayList<String> fetchAllProductsWithCalories() {
		ArrayList<String> allProductsWithCalories = new ArrayList<String>();
		
		Cursor allProductsWithCaloriesCursor = db.rawQuery("SELECT * FROM products", new String[] {});
		for (allProductsWithCaloriesCursor.moveToFirst(); !allProductsWithCaloriesCursor.isAfterLast(); allProductsWithCaloriesCursor.moveToNext())
			allProductsWithCalories.add(allProductsWithCaloriesCursor.getString(1) + " : " +  allProductsWithCaloriesCursor.getInt(2));//+  String.valueOf(allProductsWithCaloriesCursor.getInt(3)));
		
		return allProductsWithCalories;
	}

	public void deleteCaloriesTable() {
		db.execSQL("drop table calories");
	}
	
	public void close() {
		dbHelper.close();
	}
}

