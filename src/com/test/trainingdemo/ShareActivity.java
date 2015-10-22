package com.test.trainingdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;

public class ShareActivity extends Activity {
	private Button mTextBtn;
	private Button mBinaryBtn;
	private Button mMultipleBtn;
	
	private final int IMAGE_REQUEST_CODE=0;
	private final int MULTIPLE_REQUEST_CODE = 1;
	private final String IMAGE_TYPE = "image/*";
	private ArrayList<Uri> imageList = new ArrayList<Uri>();
	
	private NfcAdapter mNfcAdapter;
	private FileUriCallback mFileUriCallback;
	
	private ShareActionProvider mShareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		
		mTextBtn = (Button) findViewById(R.id.btn_share_text);
		mBinaryBtn = (Button) findViewById(R.id.btn_share_binary);
		mMultipleBtn = (Button) findViewById(R.id.btn_share_multiple);
		
		mTextBtn.setOnClickListener(new MClickListener());
		mBinaryBtn.setOnClickListener(new MClickListener());
		mMultipleBtn.setOnClickListener(new MClickListener());
		
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		mFileUriCallback = new FileUriCallback();
		mNfcAdapter.setBeamPushUrisCallback(mFileUriCallback, this);
		
	}
	
	private class MClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_share_text:
				shareText();
//				startActivity(Intent.createChooser(sendIntent, "please chose"));
				break;
			case R.id.btn_share_binary:
				Intent photoIntnet = new Intent(Intent.ACTION_GET_CONTENT);
				photoIntnet.setType(IMAGE_TYPE);
				startActivityForResult(photoIntnet, IMAGE_REQUEST_CODE);
				break;
			case R.id.btn_share_multiple:
				Intent photoIntnet2 = new Intent(Intent.ACTION_GET_CONTENT);
				photoIntnet2.setType(IMAGE_TYPE);
				startActivityForResult(photoIntnet2, MULTIPLE_REQUEST_CODE);
				break;
			default:
				break;
			}
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.share, menu);
		MenuItem item = menu.findItem(R.id.menu_item_share);
		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		if(!setShareIntent()){
			menu.removeItem(R.id.menu_item_share); 
		}
		return true;
	}
	
	
	private boolean setShareIntent(){
		 if (mShareActionProvider != null) {  
	            Intent shareIntent = new Intent(Intent.ACTION_SEND);  
	            shareIntent.setType("image/*");  
	            shareIntent.putExtra(Intent.EXTRA_TEXT, "这是要发送的文本");  
	              
	            PackageManager pm = getPackageManager();              
	            //检查手机上是否存在可以处理这个动作的应用  
	            List<ResolveInfo> infolist = pm.queryIntentActivities(shareIntent, 0);  
	            if (!infolist.isEmpty()) {  
	                mShareActionProvider.setShareIntent(shareIntent);  
	                return true;  
	            }  
	            return false;  
	        } 
		 return false;
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode != RESULT_OK) {
			return;
		}
		if (IMAGE_REQUEST_CODE == requestCode) {
			 Uri originalUri = data.getData();
			 System.out.println("origin uri " + originalUri.toString());
			 shareBinary(originalUri);
		} else if(MULTIPLE_REQUEST_CODE == requestCode){
			imageList.add(data.getData());
			shareMultiple(imageList);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void shareText(){
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, "this is my text to send");
		sendIntent.setType("text/plain");
		startActivity(sendIntent);
	}
	
	private void shareBinary(Uri uri){
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
		shareIntent.setType(IMAGE_TYPE);
		startActivity(shareIntent);
	}
	
	private void shareMultiple(ArrayList<Uri> imageList){
		Intent multiIntent = new Intent();
		multiIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
		multiIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageList);
		multiIntent.setType(IMAGE_TYPE);
		startActivity(multiIntent);
		
	}
	
	private class FileUriCallback implements NfcAdapter.CreateBeamUrisCallback{

		@Override
		public Uri[] createBeamUris(NfcEvent event) {
			// TODO Auto-generated method stub
			return (Uri[]) imageList.toArray();
		}

	}

}
