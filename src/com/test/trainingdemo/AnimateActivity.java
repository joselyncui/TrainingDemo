package com.test.trainingdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AnimateActivity extends Activity {
	
	private View mContentView;
	private View mLoadingView;
	private int mShortAnimationDuration;
	
	private Button mbtnCardAnim;
	private Button mbtnZoomAnim;
	private Button mBtnChangeAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animate);
		
		mContentView = findViewById(R.id.content);
		mLoadingView = findViewById(R.id.loading_spinner);
		
		mContentView.setVisibility(View.GONE);
		mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
		crossfade();
		
		mbtnCardAnim = (Button) findViewById(R.id.btn_card_anim);
		mbtnZoomAnim = (Button) findViewById(R.id.btn_zoom_anim);
		mBtnChangeAnim = (Button) findViewById(R.id.btn_change_anim);
		
		mbtnCardAnim.setOnClickListener(new MClickListener());
		mbtnZoomAnim.setOnClickListener(new MClickListener());
		mBtnChangeAnim.setOnClickListener(new MClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animate, menu);
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
	
	private void crossfade(){
		// ��������ViewΪ0%�Ĳ�͸���ȣ�����״̬Ϊ���ɼ�����
		// ����ڶ�����������һֱ�ɼ��ģ�����Ϊȫ͸������
		mContentView.setAlpha(0);
		mContentView.setVisibility(View.VISIBLE);
		
		// ��ʼ��������View��100%�Ĳ�͸���ȣ�Ȼ���������������View�ϵĶ�����������
		mContentView.animate()
			.alpha(1f)
			.setDuration(mShortAnimationDuration)
			.setListener(null);
		
		// ����View��ʼ�����𽥱�Ϊ0%�Ĳ�͸���ȣ�
		// �������������ÿɼ���ΪGONE����ʧ����Ϊһ���Ż�����
		//���������ٲ��벼�ֵĴ��ݵȹ��̣�
		mLoadingView.animate()
			.alpha(0)
			.setDuration(mShortAnimationDuration)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoadingView.setVisibility(View.GONE);
				}
			});
	}
	
	class MClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.btn_card_anim:
				intent = new Intent(AnimateActivity.this, CardAnimActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_zoom_anim:
				intent = new Intent(AnimateActivity.this, ZoomAnimActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_change_anim:
				intent = new Intent(AnimateActivity.this, ChangeLayoutAnimActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
			
		}
		
	}
}
