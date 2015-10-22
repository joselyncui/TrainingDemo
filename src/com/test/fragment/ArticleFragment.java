package com.test.fragment;

import com.test.trainingdemo.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArticleFragment extends Fragment{
	
	public static final String ARG_POSITION = "arg_position";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("fragemnt oncreate view");
		
		int arg = this.getArguments().getInt(ARG_POSITION);
		System.out.println(arg);
		return inflater.inflate(R.layout.fragment_article, container,false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("fragment oncreate");

		int arg = this.getArguments().getInt(ARG_POSITION);
		System.out.println(arg);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		System.out.println("fragemnt onattach");

		int arg = this.getArguments().getInt(ARG_POSITION);
		System.out.println(arg);
		super.onAttach(activity);
	}

}
