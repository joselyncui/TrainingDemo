package com.test.trainingdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.test.fragment.ArticleFragment;
import com.test.fragment.NewsFragment;

/**
 * @author MGC01
 * 
 */
public class MFragmentActivity extends Activity {

	private FrameLayout mFragmentContainer;
	private Button mBtnPrev;
	private Button mBtnNext;
	ArticleFragment mAarticleFragment;
	NewsFragment mNewsFragment;
	FragmentTransaction transaction;
	int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mfragment);

		mBtnPrev = (Button) findViewById(R.id.btn_previous);
		mBtnNext = (Button) findViewById(R.id.btn_next);

		mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
		if (mFragmentContainer != null) {
			if (savedInstanceState != null) {
				return;
			}

			mAarticleFragment = new ArticleFragment();
			mNewsFragment = new NewsFragment();

			Bundle articleArgs = new Bundle();
			articleArgs.putInt(ArticleFragment.ARG_POSITION, position);
			mAarticleFragment.setArguments(articleArgs);
			transaction = getFragmentManager().beginTransaction();
			transaction = transaction.add(R.id.fragment_container,
					mAarticleFragment);
			transaction.addToBackStack(null);
			transaction.commit();
		}

		mBtnPrev.setOnClickListener(new MClickListener());
		mBtnNext.setOnClickListener(new MClickListener());
	}

	class MClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_next:
				transaction = getFragmentManager().beginTransaction();

				switchFragment(mAarticleFragment, mNewsFragment);

				break;
			case R.id.btn_previous:

				position++;

				// Bundle articleArgs = new Bundle();
				// articleArgs.putInt(ArticleFragment.ARG_POSITION, position);
				// mAarticleFragment.setArguments(articleArgs);

				switchFragment(mNewsFragment, mAarticleFragment);
				break;
			default:
				break;
			}

		}

	}

	private void switchFragment(Fragment from, Fragment to) {
		transaction = getFragmentManager().beginTransaction();
		// .setCustomAnimations(android.R.anim.fade_in,
		// android.R.anim.fade_out);
		if (!to.isAdded()) {
			transaction
					.setCustomAnimations(R.anim.card_flip_right_in,
							R.anim.card_flip_right_out,
							R.anim.card_flip_left_in, 
							R.anim.card_flip_left_out)
					.hide(from).add(R.id.fragment_container, to).commit();
		} else {
			transaction
			.setCustomAnimations(R.anim.card_flip_right_in,
					R.anim.card_flip_right_out,
					R.anim.card_flip_left_in, 
					R.anim.card_flip_left_out)
			.hide(from).show(to).commit();
		}
	}

}
