package us.mifeng.bubaexaminationsystem.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class ConnectInternet {
	private static HttpURLConnection connect;
	private static StringBuffer sb;

	public static String connect(String path) throws IOException{
		URL url = new URL(path);
		connect = (HttpURLConnection) url.openConnection();
		connect.setRequestMethod("GET");
		connect.setConnectTimeout(5000);
		connect.setReadTimeout(5000);
		int code = connect.getResponseCode();
		if (200==code) {
			if (connect==null) {
				return "";
			}
			InputStream in = connect.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			sb = new StringBuffer();
			String str = "";
			while((str=br.readLine())!=null){
				sb.append(str);
			}
			in.close();
			isr.close();
			br.close();
			return sb.toString();
		}
		else {
			return "";
		}
	}
	public static boolean isNetWork(Context context){//返回true代表可以联网
		//网络的管理者
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if(info!=null){
			return true;
		}else{
			return false;
		}
	}
}

