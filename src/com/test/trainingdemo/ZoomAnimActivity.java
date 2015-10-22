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
	
	// 持有一个当前animator的引用,
	// 以后以便于中途取消动画.
	private Animator mCurrentAnimator;
	//这个系统内的“短”动画时长是以毫秒为单位的。
	//这个时长对于精确控制的动画或频繁激发的动画是非常理想的。
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
			//如果一个动画正在进行过程中，那么就要立即取消之前的动画并进行这一个。
			if(mCurrentAnimator != null) {
				mCurrentAnimator.cancel();
			}
			
			// 载入一个高分辨率的所谓 "已放大" 的图片.
			final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
			expandedImageView.setImageResource(imageResId);
			
			// 为放大的图片计算开始动画和结束动画的矩形边界
			// 这个步骤牵扯到了大量的数学计算，YEAH!!坑爹的数学!!
			final Rect startBounds = new Rect();
			final Rect finalBounds = new Rect();
			final Point globalOffset = new Point();
			
			// 动画开始的边界是缩略图对全局可见的矩形，最终的边界是外部包裹的布局可见矩形。
			// 这里还设置了包裹视图的偏移量为原点的边界,因为这是原点为定位的动画属性(X, Y)。
			thumbView.getGlobalVisibleRect(startBounds);
			
			findViewById(R.id.container).getGlobalVisibleRect(finalBounds,globalOffset);
			startBounds.offset(-globalOffset.x, -globalOffset.y);
			finalBounds.offset(-globalOffset.x, -globalOffset.y);
			
			// 调整开始边界要和使用了“centerCrop”技术的最终边界保持相同的纵横比。
			// 这可以在动画过程中防止不希望出现的拉伸现象。还计算了开始大小的缩放系数
			// (结束大小的系数则一直为1.0)
			
			float startScale;
			if((float) finalBounds.width()/finalBounds.height()
					> (float) startBounds.width()/startBounds.height()){
				// 水平扩展开始边界
				startScale = (float)startBounds.height()/finalBounds.height();
				float startWidth = startScale * finalBounds.width();
				float deltaWidth = (startWidth-startBounds.width())/2;
				startBounds.left -= deltaWidth;
				startBounds.right +=deltaWidth;
			} else {
				// 竖直扩展开始边界
				startScale = (float)startBounds.width()/finalBounds.width();
				float startHeight = startScale * finalBounds.height();
				float deltaHeight = (startHeight-startBounds.height())/2;
				startBounds.top -= deltaHeight;
				startBounds.bottom += deltaHeight;
			}
			
			// 隐藏缩略图并显示放大后的View。当动画开始，将在缩略图的位置定位放大的视图
			thumbView.setAlpha(0f);
			expandedImageView.setVisibility(View.VISIBLE);
			
			// 设置锚点，以放大后的View左上角坐标为准来准备 SCALE_X 和 SCALE_Y 变换
			// (默认为View的中心)
			expandedImageView.setPivotX(0f);
			expandedImageView.setPivotY(0f);
			
			//构建并并行化运行4个平移动画和缩放属性(X, Y, SCALE_X, and SCALE_Y)
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
			
			// 点击放大后的图片，应该是缩放回原来的边界并显示缩略图
			// 而不是显示扩大的图
			final float startScaleFinal = startScale;
			expandedImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (mCurrentAnimator != null) {
						mCurrentAnimator.cancel();
					}
					
					// 开始并行动画这四个位置/大小属性，直到归至原始值。
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
