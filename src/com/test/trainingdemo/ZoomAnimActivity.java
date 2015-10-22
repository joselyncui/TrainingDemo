package com.test.trainingdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ZoomAnimActivity extends Activity {
	
	private ImageButton btnThumb;
	
	// ����һ����ǰanimator������,
	// �Ժ��Ա�����;ȡ������.
	private Animator mCurrentAnimator;
	//���ϵͳ�ڵġ��̡�����ʱ�����Ժ���Ϊ��λ�ġ�
	//���ʱ�����ھ�ȷ���ƵĶ�����Ƶ�������Ķ����Ƿǳ�����ġ�
	private int mShortAnimationDuration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zoom_anim);
		
		btnThumb = (ImageButton)findViewById(R.id.btn_thumb_1);
		
		btnThumb.setOnClickListener(new MClickListener());
		mShortAnimationDuration = getResources().getInteger(R.integer.config_shortAnimTime);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zoom_anim, menu);
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
	
	class MClickListener implements View.OnClickListener{
		
		Intent intent = null;
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_thumb_1:
				zoomImageFromThumb(btnThumb, R.drawable.img);
				break;

			default:
				break;
			}
			
		}
		
		private void zoomImageFromThumb(final View thumbView, int imageResId) {
			//���һ���������ڽ��й����У���ô��Ҫ����ȡ��֮ǰ�Ķ�����������һ����
			if(mCurrentAnimator != null) {
				mCurrentAnimator.cancel();
			}
			
			// ����һ���߷ֱ��ʵ���ν "�ѷŴ�" ��ͼƬ.
			final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
			expandedImageView.setImageResource(imageResId);
			
			// Ϊ�Ŵ��ͼƬ���㿪ʼ�����ͽ��������ľ��α߽�
			// �������ǣ�����˴�������ѧ���㣬YEAH!!�ӵ�����ѧ!!
			final Rect startBounds = new Rect();
			final Rect finalBounds = new Rect();
			final Point globalOffset = new Point();
			
			// ������ʼ�ı߽�������ͼ��ȫ�ֿɼ��ľ��Σ����յı߽����ⲿ�����Ĳ��ֿɼ����Ρ�
			// ���ﻹ�����˰�����ͼ��ƫ����Ϊԭ��ı߽�,��Ϊ����ԭ��Ϊ��λ�Ķ�������(X, Y)��
			thumbView.getGlobalVisibleRect(startBounds);
			
			findViewById(R.id.container).getGlobalVisibleRect(finalBounds,globalOffset);
			startBounds.offset(-globalOffset.x, -globalOffset.y);
			finalBounds.offset(-globalOffset.x, -globalOffset.y);
			
			// ������ʼ�߽�Ҫ��ʹ���ˡ�centerCrop�����������ձ߽籣����ͬ���ݺ�ȡ�
			// ������ڶ��������з�ֹ��ϣ�����ֵ��������󡣻������˿�ʼ��С������ϵ��
			// (������С��ϵ����һֱΪ1.0)
			
			float startScale;
			if((float) finalBounds.width()/finalBounds.height()
					> (float) startBounds.width()/startBounds.height()){
				// ˮƽ��չ��ʼ�߽�
				startScale = (float)startBounds.height()/finalBounds.height();
				float startWidth = startScale * finalBounds.width();
				float deltaWidth = (startWidth-startBounds.width())/2;
				startBounds.left -= deltaWidth;
				startBounds.right +=deltaWidth;
			} else {
				// ��ֱ��չ��ʼ�߽�
				startScale = (float)startBounds.width()/finalBounds.width();
				float startHeight = startScale * finalBounds.height();
				float deltaHeight = (startHeight-startBounds.height())/2;
				startBounds.top -= deltaHeight;
				startBounds.bottom += deltaHeight;
			}
			
			// ��������ͼ����ʾ�Ŵ���View����������ʼ����������ͼ��λ�ö�λ�Ŵ����ͼ
			thumbView.setAlpha(0f);
			expandedImageView.setVisibility(View.VISIBLE);
			
			// ����ê�㣬�ԷŴ���View���Ͻ�����Ϊ׼��׼�� SCALE_X �� SCALE_Y �任
			// (Ĭ��ΪView������)
			expandedImageView.setPivotX(0f);
			expandedImageView.setPivotY(0f);
			
			//���������л�����4��ƽ�ƶ�������������(X, Y, SCALE_X, and SCALE_Y)
			AnimatorSet set = new AnimatorSet();
			set.play(ObjectAnimator.ofFloat(expandedImageView, View.X,
					startBounds.left,finalBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView,View.Y,
					startBounds.top,finalBounds.top))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
					startScale, 1f))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y,
					startScale,1f));
			set.setDuration(mShortAnimationDuration);
			set.setInterpolator(new DecelerateInterpolator());
			set.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mCurrentAnimator = null;
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					mCurrentAnimator = null;
				}
			});
			
			set.start();mCurrentAnimator = set;
			
			// ����Ŵ���ͼƬ��Ӧ�������Ż�ԭ���ı߽粢��ʾ����ͼ
			// ��������ʾ�����ͼ
			final float startScaleFinal = startScale;
			expandedImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (mCurrentAnimator != null) {
						mCurrentAnimator.cancel();
					}
					
					// ��ʼ���ж������ĸ�λ��/��С���ԣ�ֱ������ԭʼֵ��
					AnimatorSet set = new AnimatorSet();
					set.play(ObjectAnimator.ofFloat(expandedImageView,
							View.X, startBounds.left))
						.with(ObjectAnimator.ofFloat(expandedImageView, 
							View.Y, startBounds.top))
						.with(ObjectAnimator.ofFloat(expandedImageView, 
							View.SCALE_X, startScaleFinal))
						.with(ObjectAnimator.ofFloat(expandedImageView, 
							View.SCALE_Y, startScaleFinal));
					set.setDuration(mShortAnimationDuration);
					set.setInterpolator(new DecelerateInterpolator());
					set.addListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							thumbView.setAlpha(1f);
							expandedImageView.setVisibility(View.GONE);
							mCurrentAnimator = null;
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
							thumbView.setAlpha(1f);
							expandedImageView.setVisibility(View.GONE);
							mCurrentAnimator = null;
						}
					});
					set.start();mCurrentAnimator = set;
				}
			});
		}
		
	}
}
