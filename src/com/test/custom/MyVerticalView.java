package com.test.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

public class MyVerticalView extends ViewGroup {
	private Scroller mScroller;
	private VelocityTracker mTracker;
	private int height;
	private int lastY;
	private int heightScrollerY;

	public MyVerticalView(Context context) {
		super(context);
		init(context);
	}

	public MyVerticalView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		init(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		height = getMeasuredHeight();

		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.layout(l, t, r, t + height);
			t += height;
		}
		heightScrollerY = height * (getChildCount() - 1);
	}

	public void init(Context context) {
		mScroller = new Scroller(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mTracker == null) {
			mTracker = VelocityTracker.obtain();
		}

		mTracker.addMovement(event);

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			lastY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int currentY = (int) event.getY();
			int dis = lastY - currentY;
			int scrollerY = getScrollY();
			// 向下移动
			if (dis > 0) {
				if (scrollerY + dis > heightScrollerY) {
					dis = heightScrollerY - scrollerY;
				}
			} else {
				if (dis + scrollerY < 0) {
					dis = 0 - scrollerY;
				}
			}
			scrollBy(0, dis);
			lastY = currentY;
			break;
		case MotionEvent.ACTION_UP:
			mTracker.computeCurrentVelocity(1000);
			int velocityY = (int) mTracker.getYVelocity();

			Toast.makeText(getContext(), "velocity " + velocityY,
					Toast.LENGTH_LONG).show();

			int disY = 0;
			if (mTracker != null) {
				mTracker.clear();
				mTracker.recycle();
				mTracker = null;
			}

			if (Math.abs(velocityY) > 500) {

				// 向下滚动
				disY = scollerYDis(velocityY);
				mScroller.startScroll(0, getScrollY(), 0, disY, 500);
			} else {
				disY = scrollerYSlowly();
				mScroller.startScroll(0, getScrollY(), 0, disY, 500);
			}

			break;

		default:
			break;
		}
		postInvalidate();
		return true;
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		//判断是否还在滚动，还在滚动为true
		if (mScroller.computeScrollOffset()) {
			scrollTo(0, mScroller.getCurrY());
			postInvalidate();
		}
		System.out.println("compute scroll");
	}

	public int scollerYDis(int velocityY) {
		int count = 0;
		if (velocityY < 0) {
			count = getScrollY() / height + 1;
			if (count > 2) {
				count = 2;
			}
		} else {
			count = getScrollY() / height;
		}

		int dis = count * height - getScrollY();
		return dis;
	}

	public int scrollerYSlowly() {
		int count = getScrollY() % height > height / 2 ? (getScrollY() / height + 1)
				: getScrollY() / height;
		int dis = count * height - getScrollY();
		return dis;
	}

}
