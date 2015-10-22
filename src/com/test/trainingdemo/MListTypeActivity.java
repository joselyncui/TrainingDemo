package com.test.trainingdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MListTypeActivity extends Activity {
	ListView mListView;
	MyAdapter mAdapter;
	public static final int ITEM_TITLE = 0;
	public static final int ITEM_CONTNET = ITEM_TITLE + 1;
	private List<ViewItem> mList;
	LayoutInflater mInflater;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mlist_type);
		mListView = (ListView) findViewById(R.id.listview);
		mList = new ArrayList<ViewItem>();
		setData();
		
		mAdapter = new MyAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}
	
	
	
	class MyAdapter extends BaseAdapter{
		private Context mContext;
		private List<ViewItem> mList;
		
		public MyAdapter(Context mContext, List<ViewItem> mList) {
			this.mContext = mContext;
			this.mList = mList;
			mInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getItemViewType(int position) {
			return mList.get(position).type;
		}
		
		@Override
		public int getViewTypeCount() {
			return 2;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int type = getItemViewType(position);
			TitleHolder titleHolder = null;
			ContentHolder contentHolder = null;
			System.out.println("converview " + convertView + "type " + type);
			if(convertView == null) {
				switch (type) {
				case ITEM_TITLE:
					convertView = mInflater.inflate(R.layout.list_type_1, parent, false);
					titleHolder = new TitleHolder(convertView);
					convertView.setTag(titleHolder);
					break;
				case ITEM_CONTNET:
					convertView = mInflater.inflate(R.layout.list_type_2, parent, false);
					contentHolder = new ContentHolder(convertView);
					convertView.setTag(contentHolder);
					break;
				}
			} else {
				switch (type) {
				case ITEM_TITLE:
					titleHolder = (TitleHolder) convertView.getTag();
					break;

				case ITEM_CONTNET:
					contentHolder = (ContentHolder) convertView.getTag();
					break;
				}
			}
			
			
			if(titleHolder != null) {
				titleHolder.mTitlte.setText(mList.get(position).title);
			}
			if(contentHolder != null) {
				System.out.println("mcontent " + contentHolder.mContent);
				contentHolder.mContent.setText(mList.get(position).content);
			}
			
			return convertView;
		}
		
	}
	
	class TitleHolder{
		public TextView mTitlte;
		public TitleHolder(View v) {
			this.mTitlte = (TextView)v.findViewById(R.id.title);
		}
	}
	
	class ContentHolder{
		public TextView mContent;
		public ImageView icon;
		
		public ContentHolder(View v) {
			this.mContent = (TextView) v.findViewById(R.id.content);
			this.icon = (ImageView)v.findViewById(R.id.img_icon);
			System.out.println("this.mContent " + this.mContent);
		}
	}
	
	private void setData(){
		for(int i = 3; i < 16; i++) {
			
			ViewItem item = new ViewItem();
			
			if(i%3 == 0) {
				item.title = "title " + i;
				item.type = ITEM_TITLE;
			} else {
				item.content = "content " + i;
				item.type = ITEM_CONTNET;
			}
			mList.add(item);
			
		}
	}
}

class ViewItem{
	public String title;
	public String content;
	public int type;
	
	
}
