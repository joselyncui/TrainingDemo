package com.test.trainingdemo;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class FileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		System.out.println("getFilesDir " + getFilesDir());
		System.out.println("getCacheDir " + getCacheDir());
		
		try {
			File file = File.createTempFile("abc", ".temp");
			System.out.println("temp abc " + file.getAbsolutePath());
			File file2 = File.createTempFile("iop", ".catch", getFilesDir());
			System.out.println("temp io " + file2.getAbsolutePath());
			
			File publicExDir = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES);
			System.out.println("external public " + publicExDir.getAbsolutePath());
			
			File privateExDir = new File(this.getExternalFilesDir(
					null), "exTemp");
			System.out.println("external private " + privateExDir.getAbsolutePath());
			
			System.out.println("free space public " + publicExDir.getFreeSpace());
			System.out.println("free space private " + privateExDir.getFreeSpace());
			
			String path = Uri.parse("http://www.baodu.com").getLastPathSegment();
			System.out.println("last path sec " + path);
			
			
			System.out.println("context external cache " + getExternalCacheDir().getPath());
			System.out.println("context catch " + getCacheDir().getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
