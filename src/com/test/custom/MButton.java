package com.test.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class MButton extends Button {

	public MButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MButton dispatch  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MButton dispatch ACTION_MOVE" );
//			return true;
//			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MButton dispatch ACTION_UP" );
//			return true;
//			break;
		default:
			break;
		}
		System.out.println(" MButton dispatch " + super.dispatchTouchEvent(ev));
		return super.dispatchTouchEvent(ev);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MButton onTouchEvent  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MButton onTouchEvent ACTION_MOVE" );
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MButton onTouchEvent ACTION_UP" );
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
//		return false;
	}
	
	

}
