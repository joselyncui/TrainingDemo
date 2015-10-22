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
		// ���ת.
		mShowingBack = true;

		// �������ύһ���µ�Fragment���������ڿ�Ƭ�������Fragment��ʹ���Զ��嶯�������Ҽ���
		// Fragment����������ջ
		getFragmentManager().beginTransaction()
				// �ö�������Դ���ֿ�Ƭ��ǰ������תЧ���滻Ĭ�ϵ�Fragment������
				// ����ת��ǰ���ʱ�򶯻�����ԴҲ���Գ����Ժ���ǰ����תЧ�������簴��ϵͳ���ؼ�ʱ��
				.setCustomAnimations(R.anim.card_flip_right_in,
						R.anim.card_flip_right_out, R.anim.card_flip_left_in,
						R.anim.card_flip_left_out)
		
		// ��һ��Fragment�滻�κε�ǰ�����������ڵ�Fragment��������һҳ
		//��ͨ���������ı���currentPage����ʾ��
					.replace(R.id.container, new CardBackFragment())
					.addToBackStack(null)
					.commit();
	}
}
