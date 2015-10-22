package com.test.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class RotationTransformer implements ViewPager.PageTransformer{
	private final int MAX_ROTATION = 20;

	@Override
	public void transformPage(View view, float position) {
		
		if(position< -1) {
			System.out.println("position " + position +" " + view.getRotation());
			view.setRotation(0);
			
		} else if(position <= 1) {
			view.setPivotX(view.getMeasuredWidth()/2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(MAX_ROTATION * position);
		} else {
			view.setRotation(0);
		}
	}

}
