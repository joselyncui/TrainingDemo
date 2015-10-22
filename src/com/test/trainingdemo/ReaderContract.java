package com.test.trainingdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class ReaderContract {
	
	private static final String TEXT_TYPE="TEXT";
	private static final String COMMA_SEP =",";
	private static final String SQL_CREATE_USERENTRYS =
			"CREATE TABLE" + UserEntry.TABLE_NAME +"("+
					UserEntry.COLUMN_NAME_ENTRY_ID +" INTEGER PRIMARY KEY,"+
					UserEntry.COLUMN_NAME_NAME + TEXT_TYPE +COMMA_SEP+
					UserEntry.COLUMN_NAME_AGE + TEXT_TYPE + COMMA_SEP +")";
	
	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ UserEntry.TABLE_NAME;
	
	private Context context;
	public ReaderContract(Context context){
		this.context = context;
	}
	
	public static abstract class UserEntry implements BaseColumns{
		public static final String TABLE_NAME="entry";
		public static final String COLUMN_NAME_ENTRY_ID = "entryId";
		public static final String COLUMN_NAME_NAME ="name";
		public static final String COLUMN_NAME_AGE = "age";
	}
	
	public class ReaderDBHelper extends SQLiteOpenHelper{
		
		public static final int DATABASE_VERSION=1;
		public static final String DATABASE_NAME="user.db";
		
		public ReaderDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_USERENTRYS);
			
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(SQL_DELETE_ENTRIES);
		}
		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}

}
