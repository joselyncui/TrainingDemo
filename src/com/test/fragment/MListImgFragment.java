package com.test.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;

public class MListImgFragment extends Fragment{

	private static final String TAG = "ListImgFragment";
	public LruCache<String, Bitmap> mRetainedCache;
	
	public MListImgFragment(){
		System.out.println(TAG +" constructor " + this.toString());
	}
	
	public static MListImgFragment findOrCreateRetainFragment(FragmentManager fm) {
		MListImgFragment fragment = (MListImgFragment) fm.findFragmentByTag(TAG);
		if(fragment == null) {
			fragment = new MListImgFragment();
			fm.beginTransaction().add(fragment, TAG).commit();
		}
		
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		System.out.println(TAG +  " create " + this.toString());
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		System.out.println("fragment " + this.toString());
	}
	
}
