package com.test.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.test.figure.Square;
import com.test.figure.Triangle;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class MyGLRenderer implements GLSurfaceView.Renderer{
	private Triangle mTriangle;
	private Square mSquare;

	//call once£¬ used to config the OpenGL ES environment of the view¡£
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		mTriangle = new Triangle();
		mSquare = new Square();
		
	}

	//called when the geometric figure of the view has been changed, for example, then the orgientation of the screen changed.
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
		
	}

	// callled when redraw the view 
	@Override
	public void onDrawFrame(GL10 gl) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		mTriangle.draw();
		
	}
	
	public static int loadShader(int type, String shaderCode) {
		// create a vertex shader type (GLES20.GL_VERTEX_SHADER)
		// or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
		int shader = GLES20.glCreateShader(type);
		
		// add the source code to the shader and compile it
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		return shader;
	}

}
