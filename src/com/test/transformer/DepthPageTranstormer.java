package com.test.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;
public class DepthPageTranstormer implements ViewPager.PageTransformer{
	private static final float MIN_SCALE = 0.75f;

	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		if(position < -1) {// [-�� ,-1)
			
			// ��һҳ�Ѿ�������ߵ���Ļҳ
			view.setAlpha(0);
		} else if (position <= 0) {// [-1,0]
			
			// �����滬��ʹ��Ĭ�ϵĹ��ɶ���
			view.setAlpha(1);
			view.setTranslationX(0);
			view.setScaleX(1);
			view.setScaleY(1);
			
		} else if (position <= 1){// (0,1]
			// ����ҳ��
			view.setAlpha(1-position);
			
			// ����Ĭ�ϵ���ҳ����
			view.setTranslationX(pageWidth * -position);
			
			// ��������ϵ���仯 (�� MIN_SCALE �� 1 ֮��仯)
			float scaleFactor = MIN_SCALE +  (1-MIN_SCALE) * (1-Math.abs(position));
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
		} else {// (1,+��]
			// ��һҳ�Ѿ������ұߵ���Ļҳ
			view.setAlpha(0);
		}
		
	}

}
