package com.test.tasks;

import java.lang.ref.WeakReference;

import com.test.utils.ImgUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapWorkerTask extends AsyncTask<Integer, Integer, Bitmap>{
	
	private final WeakReference<ImageView> imageViewReference;
	public int data = 0;
	private Resources resources;
	
	public BitmapWorkerTask(Resources resources,ImageView imageView) {
		this.imageViewReference = new WeakReference<ImageView>(imageView);
		this.resources = resources;
	}
	
	@Override
	protected Bitmap doInBackground(Integer... params) {
		data = params[0];
		return ImgUtil.decodeSampleBitmapFromResource(resources, data, 200, 200);
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(imageViewReference != null && result != null) {
			final ImageView imageView = (ImageView) imageViewReference.get();
			if(imageView != null) {
				imageView.setImageBitmap(result);
			}
		}
	}
	

}
