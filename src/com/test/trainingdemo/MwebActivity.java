package com.test.trainingdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("JavascriptInterface")
public class MwebActivity extends Activity {
	/** Called when the activity is first created. */
	private WebView myWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meb_view);

		myWebView = (WebView) findViewById(R.id.myWebView);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new JavaScriptinterface(this),
				"android");
		String htmlText = getFromAssets("test.html");
		myWebView.loadData(htmlText, "text/html", "utf-8");
		myWebView.setWebViewClient(new myWebViewClient());

	}


	public String getFromAssets(String fileName) {
		try {
			InputStreamReader inputReader = new InputStreamReader(
					getResources().getAssets().open(fileName));

			BufferedReader bufReader = new BufferedReader(inputReader);

			String line = "";
			String Result = "";

			while ((line = bufReader.readLine()) != null)
				Result += line;
			if (bufReader != null)
				bufReader.close();
			if (inputReader != null)
				inputReader.close();
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	class myWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}

	}
}