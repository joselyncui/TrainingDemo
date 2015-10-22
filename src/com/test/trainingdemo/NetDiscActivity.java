package com.test.trainingdemo;

import java.io.IOException;
import java.net.ServerSocket;

import android.app.Activity;
import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.DiscoveryListener;
import android.net.nsd.NsdManager.RegistrationListener;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class NetDiscActivity extends Activity {
	
	private final String TAG = "NetDiscActivity";
	
	ServerSocket mServerSocket;
	int mLocalPort;
	RegistrationListener mRegistrationListener;
	String mServiceName;
	NsdManager mNsdManager;
	DiscoveryListener mDiscoveryListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_net_disc);
	}

	
	public void registerService(int port) {
		
		//createthe NsdServiceInfo object, and populate it.
		NsdServiceInfo serviceInfo = new NsdServiceInfo();
		
		//The name is usbject to change based on conflicts
		//with other services advertised on the same network
		serviceInfo.setServiceName("NsdChat");
		serviceInfo.setServiceType("_http._tcp.");
		serviceInfo.setPort(port);
		
		mNsdManager = (NsdManager) this.getSystemService(Context.NSD_SERVICE);
		mNsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, mRegistrationListener);
		
		
	}
	
	public void initializeServerSocket() throws IOException{
		//Initialize a server socket on the next available port;
		mServerSocket = new ServerSocket(0);
		mLocalPort = mServerSocket.getLocalPort();
	}
	
	public void initializeRegistrationListener(){
		mRegistrationListener = new NsdManager.RegistrationListener() {
			
			@Override
			public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
				// Unregistration failed. Put debugging code here to determine why.
				
			}
			
			@Override
			public void onServiceUnregistered(NsdServiceInfo serviceInfo) {
				// Service has been unregistered. This only happens when you call
				// NsdManager.unregisterService() and pass in this listener.
				
			}
			
			@Override
			public void onServiceRegistered(NsdServiceInfo serviceInfo) {
				// Save the service name. Android may have changed it 
				//in order to resolve a conflict, so update the name
				//you initially requested with the name android actually used.
				mServiceName = serviceInfo.getServiceName();
				
			}
			
			@Override
			public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
				// Registration failed! Put debugging code here to determine why.
				
			}
		};
	}
	
	public void initializeDiscoveryListener(){
		// Instantiate a new DiscoveryListener
		mDiscoveryListener = new NsdManager.DiscoveryListener() {
			
			@Override
			public void onStopDiscoveryFailed(String serviceType, int errorCode) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartDiscoveryFailed(String serviceType, int errorCode) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceLost(NsdServiceInfo serviceInfo) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceFound(NsdServiceInfo serviceInfo) {
				
				//a service was found! do something with it
				Log.i(TAG, "Service discovery success" + serviceInfo);
//				if(!serviceInfo.getServiceType().equals(SERVICE_TYPE)){
//					
//				}
			}
			
			@Override
			public void onDiscoveryStopped(String serviceType) {
				// TODO Auto-generated method stub
				
			}
			
			// Called as soon as service discovery begins.
			@Override
			public void onDiscoveryStarted(String serviceType) {
				Log.d(TAG, "Service discovery started");
			}
		};
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.net_disc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
