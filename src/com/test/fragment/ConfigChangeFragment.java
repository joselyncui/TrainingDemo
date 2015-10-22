package com.test.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

public class ConfigChangeFragment extends Fragment{
	
	public static interface ConfigCallbacks{
		void onPreExecute();
		void onProgressUpdate(int percent);
		void onCancelled();
		void onPostExecute();
	}
	
	private ConfigCallbacks mCallbacks;
	private DummyTask mTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("config change oncreate ");
		
		setRetainInstance(true);
		mTask = new DummyTask();
		mTask.execute();
		
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		System.out.println("config change onAttach ");
		super.onAttach(activity);
		mCallbacks = (ConfigCallbacks) activity;
	}
	
	@Override
	public void onDetach() {
		System.out.println("config change onDetach ");
		super.onDetach();
		mCallbacks = null;
	}
	private class DummyTask extends AsyncTask<Void, Integer, Void> {
		
		@Override
		protected void onPreExecute() {
			if(mCallbacks != null) {
				mCallbacks.onPreExecute();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			for(int i = 0; !isCancelled() && i < 100; i++) {
				SystemClock.sleep(100);
				publishProgress(i);
			}
			
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			if(mCallbacks != null) {
				mCallbacks.onProgressUpdate(values[0]);
			}
		}
		
		@Override
		protected void onCancelled() {
			if (mCallbacks != null) {
				mCallbacks.onCancelled();
			}
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if (mCallbacks != null) {
				mCallbacks.onPostExecute();
			}
		}
	}

}
