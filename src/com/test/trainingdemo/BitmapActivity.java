package com.test.trainingdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.test.tasks.BitmapWorkerTask;
import com.test.utils.ImgUtil;

public class BitmapActivity extends Activity {
	ImageView mImg1;
	ImageView mImg2;
	ImageView mImg3;
	ImageView mImg4;
	
	Button mBtnListImg;
	Button mBtnDiskCache;
	Button mBtnViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bitmap);
		mImg1 = (ImageView) findViewById(R.id.img_1);
		mImg2 = (ImageView) findViewById(R.id.img_2);
		mImg3 = (ImageView) findViewById(R.id.img_3);
		mImg4 = (ImageView) findViewById(R.id.img_4);
		mBtnListImg = (Button) findViewById(R.id.btn_list_img);
		mBtnDiskCache = (Button) findViewById(R.id.btn_disk_cache);
		mBtnViewPager = (Button) findViewById(R.id.btn_viewpager);
		
		Bitmap img1 = ImgUtil.decodeSampleBitmapFromResource(getResources(), R.drawable.img, 100, 100);
		Bitmap img2 = ImgUtil.decodeSampleBitmapFromResource(getResources(), R.drawable.img, 200, 200);
		mImg1.setImageBitmap(img1);
		mImg2.setImageBitmap(img2);
		System.out.println("bitmap 1 " + img1.getWidth() +"  " + img1.getHeight());
		System.out.println("bitmap 2 " + img2.getWidth() +" " + img2.getHeight());
		mImg3.setImageResource(R.drawable.img);
		
		new BitmapWorkerTask(getResources(),mImg4).execute(R.drawable.img);
		
		mBtnListImg.setOnClickListener(new MClickListener());
		mBtnDiskCache.setOnClickListener(new MClickListener());
		mBtnViewPager.setOnClickListener(new MClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bitmap, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private class MClickListener implements View.OnClickListener{

		Intent intent = null;
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_list_img:
				intent = new Intent(BitmapActivity.this, ListImgActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_disk_cache:
				intent = new Intent(BitmapActivity.this, DiskCacheActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_viewpager:
				intent = new Intent(BitmapActivity.this, ImageDetailActivity.class);
				startActivity(intent);
			default:
				break;
			}
			
		}
		
	}
	
}
