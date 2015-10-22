package com.test.trainingdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class ActionBarTest extends Activity {
	private ShareActionProvider mShareActionProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar_layout);
		
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// If your minSdkVersion is 11 or higher, instead use:
	    // getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbarmenu, menu);
		
//		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//		
//		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//		searchView.setIconifiedByDefault(false);
//		searchView.setSubmitButtonEnabled(true);
//		searchView.setQueryRefinementEnabled(true);
//		searchView.setIconified(true);
//		
//		MenuItem item = menu.findItem(R.id.menu_item_share);
		//mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void openSearch(){
		Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
	}
	
	private void openSettings(){
		Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_LONG).show();
	}
	
}
