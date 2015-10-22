package com.test.trainingdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;

public class ThreadPoolActivity extends Activity {
	private ThreadPoolExecutor threadPoolExecutor;
	private ExecutorService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_pool);
		threadPoolExecutor = new ThreadPoolExecutor(5, 10, 3, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(4),
				new ThreadPoolExecutor.DiscardOldestPolicy());

		service = Executors.newCachedThreadPool();
		
			for (int i = 0; i < 10; i++) {
				service.execute(new MyThread());
			}
		service.shutdown();

	}

}

class MyThread implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ÕýÔÚÖ´ÐÐ");
	}
}
