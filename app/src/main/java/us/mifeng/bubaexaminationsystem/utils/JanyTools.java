package us.mifeng.bubaexaminationsystem.utils;

import android.os.Handler;

public abstract class JanyTools {
	public abstract void qian();
	public abstract void zhong();
	public abstract void hou();
	Handler ha=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==0){
				hou();
			}
		};
	};
	public void open(){
		qian();
		new Thread(new Runnable() {
			@Override
			public void run() {
				zhong();
				ha.sendEmptyMessage(0);
			} 
		}).start();
	}
}
