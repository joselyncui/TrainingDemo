package com.test.transformer;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer{
	
	private static final float MIN_SCALE = 0.65F;
	private static final float MIN_APPHA = 0.5f;

	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		int pageHeight = view.getHeight();
		
		if(position < -1) {//(~,-1)
			view.setAlpha(0);
		} else if (position <=1) {//[-1,1]
			System.out.println("position " + position);
			float scaleFactor =  Math.max(MIN_SCALE, 1-Math.abs(position));
			float vertMargin = pageHeight * (1-scaleFactor)/2;
			float horzMargin = pageWidth * (1-scaleFactor)/2;
			
			//µ÷ÕûXÎ»ÖÃ
			if(position < 0) {//[-1,0)
				System.out.println("translation " + view.getTranslationX());
				view.setTranslationX(horzMargin-vertMargin/2);
			} else {//[0,1]
				view.setTranslationX(-horzMargin + vertMargin/2);
			}
			
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
			
			view.setAlpha(MIN_APPHA + (scaleFactor - MIN_SCALE)/
					(1-MIN_SCALE) * (1-MIN_APPHA));
		} else {//(1,~)
			view.setAlpha(0);
		}
		
	}

}
