package com.test.trainingdemo;

import com.test.renderer.MyGLRenderer;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OpenGLActivity extends Activity {
	private GLSurfaceView mGLView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_open_gl);
		mGLView = new MyGLSurfaceView(this);
		setContentView(mGLView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_gl, menu);
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
	
	class MyGLSurfaceView extends GLSurfaceView{
		
		private final MyGLRenderer mRenderer;
		
		public MyGLSurfaceView(Context context) {
			super(context);
			setEGLContextClientVersion(2);
			mRenderer = new MyGLRenderer();
			
			// Set the Renderer for drawing on the GLSurfaceView
			setRenderer(mRenderer);
			setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		}

		
	}
}
