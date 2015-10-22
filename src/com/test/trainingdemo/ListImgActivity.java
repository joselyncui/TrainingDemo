package com.test.trainingdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.test.custom.AsyncDrawable;
import com.test.tasks.BitmapFromUrl;
import com.test.utils.ImgUtil;
import com.test.fragment.*;

public class ListImgActivity extends Activity {
	
	private ListView mListView;
	private List<String> mImgpathList;
	private BaseAdapter adapter;
	private LruCache<String, Bitmap> mMemoryCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_img);
		mListView = (ListView) findViewById(R.id.list_img);
		initPath();
		adapter = new MListAdapter(this, mImgpathList);
		mListView.setAdapter(adapter);
		
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
		final int cacheSize = maxMemory/8;// Use 1/8th of the available memory for this memory cache.
		
		System.out.println("max memory " + maxMemory);
		System.out.println("cache size " + cacheSize);
		
		MListImgFragment listImgFragment = MListImgFragment.findOrCreateRetainFragment(getFragmentManager());
		mMemoryCache = listImgFragment.mRetainedCache;
		if (mMemoryCache == null) {
			mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
				
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getByteCount()/1024;
				}
			};
			
			listImgFragment.mRetainedCache = mMemoryCache;
		}
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("click");
				
			}
		});
		
		
	}
	
	private void initPath(){
		mImgpathList = new ArrayList<String>();
		mImgpathList.add("http://upload.cbg.cn/2015/0422/1429671503235.jpg");
		mImgpathList.add("http://www.people.com.cn/mediafile/pic/20150430/35/6101432160867715427.jpg");
		mImgpathList.add("http://www.people.com.cn/mediafile/pic/20150324/48/13050001171019761260.jpg");
		mImgpathList.add("http://www.71lady.net/d/file/yule/neidiyule/neidizixun/2015-04-15/12fe48fd433fed4e2d03574465d67bd3.jpg");
		mImgpathList.add("http://stimgcn1.s-msn.com/msnportal/ent/2015/04/27/2dec2604-5884-48bb-b650-eaeebe8f15ce.jpg");
		mImgpathList.add("http://images.xiu.com/upload/goods20120104/A6000001/20120111094529_823.jpg");
		mImgpathList.add("http://lz.dx0771.com/upload/d9112931189.jpg");
		mImgpathList.add("http://newsxml.cnool.net/newspic2011/2011/2011-8/2011-8-12/634487391580937500.jpg");
		mImgpathList.add("http://h.hiphotos.baidu.com/image/pic/item/359b033b5bb5c9ea62e8eb9ed639b6003af3b3b0.jpg");
		mImgpathList.add("http://d.hiphotos.baidu.com/image/pic/item/b812c8fcc3cec3fdb7a5ef4ad588d43f87942771.jpg");
		mImgpathList.add("http://e.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b504b65a86d8635e5dde7116e72.jpg");
		mImgpathList.add("http://c.hiphotos.baidu.com/image/pic/item/3c6d55fbb2fb4316d70b354523a4462309f7d3fd.jpg");
		mImgpathList.add("http://b.hiphotos.baidu.com/image/pic/item/b999a9014c086e06949e9e4200087bf40ad1cb81.jpg");
		mImgpathList.add("http://imgt9.bdstatic.com/it/u=2,675717199&fm=25&gp=0.jpg");
		mImgpathList.add("http://imgt8.bdstatic.com/it/u=2,773887425&fm=25&gp=0.jpg");
		mImgpathList.add("http://g.hiphotos.baidu.com/image/w%3D230/sign=02f0161a95dda144da096bb182b7d009/95eef01f3a292df5eed91e1cbe315c6034a873a4.jpg");
		mImgpathList.add("http://e.hiphotos.baidu.com/image/w%3D230/sign=b6f2f4270b24ab18e016e63405fbe69a/a8773912b31bb0511c8da229347adab44aede002.jpg");
		mImgpathList.add("http://imgt9.bdstatic.com/it/u=2,965160591&fm=25&gp=0.jpg");
		mImgpathList.add("http://f.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3dcd2a5b57b8a1cd11738b2989.jpg");
		mImgpathList.add("http://d.hiphotos.baidu.com/image/w%3D230/sign=8081daffd762853592e0d522a0ee76f2/32fa828ba61ea8d3e6ced409950a304e241f58ca.jpg");
		mImgpathList.add("http://imgt7.bdstatic.com/it/u=2,962161864&fm=25&gp=0.jpg");
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_img, menu);
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
	
	class MListAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		private List<String> mImgPathList;
		
		public MListAdapter(Context mContext, List<String> mImgPathList){
			this.mInflater = LayoutInflater.from(mContext);
			this.mImgPathList = mImgPathList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImgPathList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MViewHolder mViewHolder;
			if(convertView == null) {
				mViewHolder = new MViewHolder();
				convertView = mInflater.inflate(R.layout.list_img_item, parent,false);
				mViewHolder.img = (ImageView) convertView.findViewById(R.id.list_img_item_icon);
				convertView.setTag(mViewHolder);
			} else {
				mViewHolder = (MViewHolder) convertView.getTag();
				mViewHolder.img.setImageBitmap(null);
			}
			
			//new BitmapFromUrl( mViewHolder.img).execute(mImgPathList.get(position));
			loadBitmap(mImgpathList.get(position), mViewHolder.img);
			return convertView;
		}
		
	}
	
	final class MViewHolder{
		public ImageView img;
	}
	
	
	
	private void loadBitmap(String path, ImageView imageView) {
		
		if(cancelPotentialWork(path, imageView)) {
			
			final Bitmap bitmapCache = ImgUtil.getBitmapFromMemCache(mMemoryCache,path);
			if(bitmapCache != null){
				imageView.setImageBitmap(bitmapCache);
			} else {
				final BitmapFromUrl task = new BitmapFromUrl(ListImgActivity.this,mMemoryCache,imageView);
				final AsyncDrawable asyncDrawable = new AsyncDrawable(task);
				imageView.setImageDrawable(asyncDrawable);
				task.execute(path);
			}
			
		}
		
	}
	
	private boolean cancelPotentialWork(String path,ImageView imageView) {
		final BitmapFromUrl task = ImgUtil.getBitmapWorkerTask(imageView);
		if(task != null) {
			final String imgPath = task.path;
			if(imgPath == null || !TextUtils.equals(path, imgPath)) {
				task.cancel(true);
			} else {
				return false;
			}
		}
		return true;
	}
	
	

}
