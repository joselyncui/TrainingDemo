package com.test.fragment;

import com.test.trainingdemo.ImageDetailActivity;
import com.test.trainingdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageDetailFragment extends Fragment{
	
	private static final String IMAGE_DATA_EXTRA = "resid";
	private int mImageNum;
	private ImageView mImageView;
	
	public static ImageDetailFragment newInstance(int imageNum) {
		final ImageDetailFragment fragment = new ImageDetailFragment();
		final Bundle args = new Bundle();
		args.putInt(IMAGE_DATA_EXTRA,imageNum);
		
		fragment.setArguments(args);
		return fragment;
	}

	public ImageDetailFragment(){}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mImageNum = getArguments() != null?getArguments().getInt(IMAGE_DATA_EXTRA):-1;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment, container,false);
		mImageView = (ImageView) v.findViewById(R.id.imageView);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		final int resId = ImageDetailActivity.imageResIds[mImageNum];
		mImageView.setImageResource(resId);
	}
}
