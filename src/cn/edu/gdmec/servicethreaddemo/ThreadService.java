package cn.edu.gdmec.servicethreaddemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ThreadService extends Service {
	private Thread workThread;
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "Service has created", 1000).show();
		workThread = new Thread(null,backgroundWork,"Workthread");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "Service has Destoried", 1000).show();
		workThread.interrupt();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Service has started", 1000).show();
		if(!workThread.isAlive()){
			workThread.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	private Runnable backgroundWork = new Runnable() {
		
		@Override
		public void run() {
			try{
			while(!Thread.interrupted()){
				double randomDouble = Math.random();
				MainActivity.UpdateGUI(randomDouble);
				Thread.sleep(1000);
			}
			}catch(InterruptedException e){
				e.printStackTrace();
				
			}
			
		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
