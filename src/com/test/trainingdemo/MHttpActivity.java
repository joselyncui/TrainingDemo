package com.test.trainingdemo;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.cy.entity.Response;
import com.cy.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.utils.HttpUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MHttpActivity extends Activity implements OnClickListener {
	private Button btnConGet, btnConPost, btnConPut, btnConDelete;
	private Button btnClientGet, btnClientPost, btnClientPut, btnClientDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mhttp);

		btnConGet = (Button) findViewById(R.id.btn_con_get);
		btnConPost = (Button) findViewById(R.id.btn_con_post);
		btnConPut = (Button) findViewById(R.id.btn_con_put);
		btnConDelete = (Button) findViewById(R.id.btn_con_delete);

		btnClientGet = (Button) findViewById(R.id.btn_client_get);
		btnClientPost = (Button) findViewById(R.id.btn_client_post);
		btnClientPut = (Button) findViewById(R.id.btn_client_put);
		btnClientDelete = (Button) findViewById(R.id.btn_client_delete);

		btnConGet.setOnClickListener(this);
		btnConPost.setOnClickListener(this);
		btnConPut.setOnClickListener(this);
		btnConDelete.setOnClickListener(this);

		btnClientGet.setOnClickListener(this);
		btnClientPost.setOnClickListener(this);
		btnClientDelete.setOnClickListener(this);
		btnClientPut.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_con_get:
			new Thread(new MConGetThread()).start();
			break;
		case R.id.btn_con_post:
			new Thread(new MConPostThread()).start();
			break;
		case R.id.btn_con_put:

			break;
		case R.id.btn_con_delete:

			break;
		case R.id.btn_client_get:

			break;
		case R.id.btn_client_post:

			break;
		case R.id.btn_client_put:

			break;
		case R.id.btn_client_delete:

			break;
		
		}

	}
	
	class MConGetThread implements Runnable{

		@Override
		public void run() {
			String path = HttpUtils.BASE_PATH+"testGet?id=1&token="+URLEncoder.encode("Добў");
			String result = HttpUtils.conGet(path, null);
			System.out.println(result);
		}
	}
	
	class MConPostThread implements Runnable{
		@Override
		public void run() {
			String path = HttpUtils.BASE_PATH+"testPost";
			User user = new User();
			user.id = 1;
			user.name = "cuiyao";
			Gson gson = new Gson();
			String json = gson.toJson(user);
			
//			JSONObject jsonObject = new JSONObject();
//			try {
//				jsonObject.put("id", 1);
//				jsonObject.put("user", json);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
			
			String result = HttpUtils.conPost(path, json);
			System.out.println(result);
			
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("email", "hahah");
				jsonObject.put("pwd", "123");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
//			String path2 = "http://192.168.1.61:8081/TestRest/TestPostServlet";
			String path2 = "http://192.168.1.61:8081/TestRest/rest/user/login";
			Map<String, String> params = new HashMap<String, String>();
			params.put("email", "email");
			params.put("pwd", "123");
			String result2="";
			try {
				result2 = HttpUtils.conPost(path2, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("result2 " + result2);
		}
	}

}
