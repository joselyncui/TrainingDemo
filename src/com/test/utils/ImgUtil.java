package com.test.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import libcore.io.DiskLruCache;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.test.custom.AsyncDrawable;
import com.test.tasks.BitmapFromUrl;

public class ImgUtil {
	public static int calcuteInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight){
		final int height = options.outHeight;
		final int width = options.outWidth;
		
		
		int inSampleSize = 1;
		if(height>reqHeight || width > reqWidth) {
			
			final int halfHeight = height/2;
			final int hafWidth = width/2;
			
			while ((halfHeight/inSampleSize) > reqHeight 
					&& (hafWidth/inSampleSize) > reqWidth) {
				inSampleSize *=2;
			}
		}
		
		return inSampleSize;
	}
	
	public static Bitmap decodeSampleBitmapFromResource(Resources resources, 
			int resId, int reqWidth, int reqHeight){
		final BitmapFactory.Options  options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resources, resId,options);
		options.inSampleSize = calcuteInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(resources, resId,options);
	}
	
	public static Bitmap decodeFromBytes(byte[] bytes, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		options.inSampleSize = calcuteInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
		
	}
	
	public static Bitmap decodeFromStream(URL url, int reqWidth, int reqHeight) throws IOException {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(url.openStream(), null, options);
		options.inSampleSize = calcuteInSampleSize(options, reqWidth, reqHeight);
		
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeStream(url.openStream(), null, options);
		return bitmap;
		
	}
	

	public static BitmapFromUrl getBitmapWorkerTask(ImageView imageView) {
		if(imageView != null) {
			final Drawable drawable = imageView.getDrawable();
			if(drawable instanceof AsyncDrawable) {
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return (BitmapFromUrl) asyncDrawable.getTask();
			}
		}
		
		return null;
	}
	
	public static void addBitmapToMemoryCache(LruCache<String, Bitmap> mMemoryCache,String key, Bitmap bitmap) {
		if(getBitmapFromMemCache(mMemoryCache,key) == null){
			mMemoryCache.put(key, bitmap);
		}
	}
	
	public static Bitmap getBitmapFromMemCache(LruCache<String, Bitmap> mMemoryCache, String key) {
		return (Bitmap) mMemoryCache.get(key);
	}

	public static void addBitmapToDiskCache(
			DiskLruCache mDiskLruCache, Object mDiskCacheLock, String key, Bitmap bitmap) throws IOException {
		
		InputStream input = bitmap2InputStream(bitmap);
		if(mDiskCacheLock == null || mDiskLruCache == null) {
			return;
		}
		
		// Also add to disk cache
		synchronized (mDiskCacheLock) {
			if (mDiskLruCache != null && mDiskLruCache.get(key) == null) {
				
				// 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
				DiskLruCache.Editor editor = mDiskLruCache.edit(key);
				if (editor != null) {
					OutputStream outputStream = editor.newOutputStream(0);
					if (inputToOutput(input, outputStream)) {
						editor.commit();
					} else {
						editor.abort();
					}
				}
			}
		}
	}
	
	public static boolean inputToOutput(InputStream inputStream, OutputStream outputStream) {
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据传入的uniqueName获取硬盘缓存的路径地址。
	 */
	public static File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			System.out.println("exter path " + context.getExternalCacheDir());
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}
	
	public static InputStream bitmap2InputStream(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
		return inputStream;
	}
}
