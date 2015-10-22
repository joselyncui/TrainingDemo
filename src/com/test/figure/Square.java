package com.test.figure;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square {
	
	private FloatBuffer vertexBuffer;
	private ShortBuffer drawListBuffer;
	
	static final int COORDS_PER_VETEX = 3;
	static float squareCoords[] = {
		-0.5f, 0.5f, 0.0f, // top left
		-0.5f, -0.5f, 0.0f, // bottom left
		0.5f, -0.5f, 0.0f, // bottom right
		0.5f, 0.5f, 0.0f
	};
	
	private short drawOrder[] = {0,1,2,0,2,3};//order to draw vertices
	
	public Square(){
		//initiallize vertex byte buffer for shape coordinates
		
		// (# of coordinate values * 4 bytes per float)
		ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length*4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(squareCoords);
		vertexBuffer.position(0);
		
		//initialize byte buffer for the draw list
		
		// (# of coordinate values * 2 bytes per short)
		ByteBuffer dlb = ByteBuffer.allocate(drawOrder.length*2);
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);
		
	}

}
