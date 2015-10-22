package com.test.trainingdemo;

import com.test.fragment.CardBackFragment;
import com.test.fragment.CardFrontFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CardAnimActivity extends Activity {
	private boolean mShowingBack = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_anim);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new CardFrontFragment()).commit();
		}
		flipCard();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_anim, menu);
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
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		flipCard();
	}

	private void flipCard() {
		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}
		// 向后翻转.
		mShowingBack = true;

		// 创建并提交一个新的Fragment事务用于在卡片后面添加Fragment，使用自定义动画，并且加入
		// Fragment管理器回退栈
		getFragmentManager().beginTransaction()
				// 用动画器资源呈现卡片自前向后的旋转效果替换默认的Fragment动画，
				// 当翻转到前面的时候动画器资源也可以呈现自后向前的旋转效果（例如按下系统返回键时）
				.setCustomAnimations(R.anim.card_flip_right_in,
						R.anim.card_flip_right_out, R.anim.card_flip_left_in,
						R.anim.card_flip_left_out)
		
		// 用一个Fragment替换任何当前在容器布局内的Fragment来呈现下一页
		//（通过仅自增的变量currentPage来表示）
					.replace(R.id.container, new CardBackFragment())
					.addToBackStack(null)
					.commit();
	}
}
