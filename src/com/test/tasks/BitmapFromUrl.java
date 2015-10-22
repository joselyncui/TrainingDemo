package com.test.tasks;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import libcore.io.DiskLruCache;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.test.trainingdemo.DiskCacheActivity;
import com.test.utils.ImgUtil;
import com.test.utils.MD5Utils;

public class BitmapFromUrl extends AsyncTask<String, Integer, Bitmap>{
	
	private WeakReference<ImageView> mWeakReference;
	public String path;
	private LruCache<String, Bitmap> cache;
	private DiskLruCache mDiskLruCache;
	private Object lock;
	
	public BitmapFromUrl(Activity activity,LruCache<String, Bitmap> cache, ImageView imageView){
		this.cache = cache;
		if(activity instanceof DiskCacheActivity) {
			this.mDiskLruCache = ((DiskCacheActivity)activity).mDiskLruCache;
			this.lock = ((DiskCacheActivity)activity).mDiskCacheLock;
		}
		
		mWeakReference = new WeakReference<ImageView>(imageView);
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		path = params[0];
		System.out.println("path " + path);
		try {
			URL url = new URL(path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10*1000);
			con.setConnectTimeout(10*1000);
			con.setRequestMethod("GET");
			
			if(con.getResponseCode()==HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				Bitmap bitmap = ImgUtil.decodeFromStream(url, 100, 100);
				
				String key = MD5Utils.string2MD5(path);
				
				//加入内存缓存
				if(cache != null) {
					System.out.println("add to memory cache");
					ImgUtil.addBitmapToMemoryCache(cache, key, bitmap);
				}
				
				//加入磁盘缓存
				if(mDiskLruCache != null || cache != null) {
					System.out.println("add to disk cache");
					ImgUtil.addBitmapToDiskCache(mDiskLruCache, lock, key, bitmap);
				}
				
				return bitmap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		
		if (isCancelled()) {
			result = null;
		}
		
		ImageView img = mWeakReference.get();
		if(result != null && mWeakReference != null &&  img!= null) {
			if (this == ImgUtil.getBitmapWorkerTask(img)) {
				img.setImageBitmap(result);
			}
		}
	}

}
