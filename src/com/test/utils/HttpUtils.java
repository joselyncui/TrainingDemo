package com.test.utils;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import com.cy.entity.User;

import android.R.menu;
import android.net.Uri;

public class HttpUtils {
	public static final String BASE_PATH="http://192.168.1.61:8081/TestRest/rest/user/";
	
	public static String conGet(String uri, JSONObject object){
		String result = null;
		URL url;
		
		try {
			url = new URL(uri);
			HttpURLConnection connection= (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");//设置请求方式
			connection.setReadTimeout(5000);//设置超时时间
			connection.setConnectTimeout(5000);//设置链接超时时间
			
			//获得相应的状态吗
			if(connection.getResponseCode() == 200) {
				
				//获取响应的输入流对象
				InputStream is = connection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String str = null;
				StringBuffer sb = new StringBuffer();
				while ((str = br.readLine()) != null) {
					sb.append(str);
				}
				
				result = sb.toString();
			} else {
				result = "error";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		
		
		return result;
	}
	
	public static String conPost(String uri, String jsonString) {
		System.out.println("post");
		String result = null;
		URL url;
		try {
			url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(jsonString);
			writer.flush();
			writer.close();
			
			System.out.println(connection.getResponseCode());
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				
				String str = "";
				StringBuffer sb = new StringBuffer();
				while ((str = reader.readLine()) != null) {
					sb.append(str);
				}
				
				result = sb.toString();
			} else {
				result = "error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		
		return result;
	}
	
	public static String conPost(String uri, Map<String, String> params) throws Exception {
		System.out.println("post");
		String result = null;
		URL url;
		try {
			url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			
            // 设置请求的头  
			StringBuilder content = new StringBuilder();
			for (String key : params.keySet()) {
				content.append(key);
				content.append("=");
				content.append(params.get(key));
				content.append("&");
			}
			String dataStr = (String) content.subSequence(0, content.length()-1);
			
//			Uri.Builder builder = new Uri.Builder();
//			builder.appendQueryParameter("email", "hahah");
//			builder.appendQueryParameter("pwd", "123");
//			String dataStr = builder.build().getEncodedQuery();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", "hahah");
			jsonObject.put("password", "123");
			
			connection.connect();
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
//			output.writeBytes(jsonObject.toString());
			output.writeBytes(dataStr);
//			output.writeBytes("haha");
//			output.writeBytes("123");
			output.flush();
			output.close();
			
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("ok");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				
				String str = "";
				StringBuffer sb = new StringBuffer();
				while ((str = reader.readLine()) != null) {
					sb.append(str);
				}
				
				result = sb.toString();
			} else {
				System.out.println("code " + connection.getResponseCode());
				result = "error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		
		return result;
	}
	
	public static String conPut(String uri, JSONObject object) {
		return "";
	}
	
	public static String conDelete(String uri, JSONObject object) {
		return "";
	}
	
	public static String clientGet(String uri, JSONObject object) {
		return "";
	}
	
	public static String clientPost(String uri, JSONObject object) {
		return "";
	}
	
	public static String clientPut(String uri, JSONObject object) {
		return "";
	}
	
	public static String clientDelete(String uri, JSONObject object) {
		return "";
	}

}
