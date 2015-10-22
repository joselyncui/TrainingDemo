package com.test.trainingdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.test.fragment.ImageDetailFragment;
import com.test.transformer.RotationTransformer;

public class ImageDetailActivity extends FragmentActivity {
	public static final String EXTRA_IMAGE = "extra_image";
	private ImagePagerAdapter mAdapter;
	private ViewPager mPager;
	
	public final static Integer[] imageResIds = new Integer[]{
		R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,
		R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,
		R.drawable.img_7,R.drawable.img_8,R.drawable.img_9,
		R.drawable.img_10
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_detail);
		mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imageResIds.length);
		mPager = (ViewPager) findViewById(R.id.image_detail_viewpager);
		mPager.setAdapter(mAdapter);
		mPager.setPageTransformer(true, new RotationTransformer());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pager, menu);
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
	
	public static class ImagePagerAdapter extends FragmentPagerAdapter{
		private final int mSize;

		public ImagePagerAdapter(FragmentManager fm, int size) {
			super(fm);
			mSize = size;
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return ImageDetailFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mSize;
		}
		
	}
}
