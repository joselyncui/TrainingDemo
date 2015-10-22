package com.test.custom;

import android.annotation.SuppressLint;
import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MLinearLayout extends LinearLayout {

	public MLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MLinearLayout dispatch  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MLinearLayout dispatch ACTION_MOVE" );
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MLinearLayout dispatch ACTION_UP" );
			break;
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MLinearLayout Intercept  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MLinearLayout Intercept ACTION_MOVE" );
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MLinearLayout Intercept ACTION_UP" );
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MLinearLayout onTouchEvent  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MLinearLayout onTouchEvent ACTION_MOVE" );
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MLinearLayout onTouchEvent ACTION_UP" );
			break;
		default:
			break;
		}
		System.out.println("mLinearLayout onTouchEvent " + super.onTouchEvent(event));
		return super.onTouchEvent(event);
	}

}
