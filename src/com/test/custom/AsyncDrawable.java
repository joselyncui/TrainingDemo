package com.test.custom;

import java.lang.ref.WeakReference;

import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;

public class AsyncDrawable extends BitmapDrawable{
	private final WeakReference bitmapReference;
	
	public AsyncDrawable(AsyncTask task) {
		bitmapReference = new WeakReference(task);
	}
	
	public AsyncTask getTask(){
		return (AsyncTask) bitmapReference.get();
	}
	
}
