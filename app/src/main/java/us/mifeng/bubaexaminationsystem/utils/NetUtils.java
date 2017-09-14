package us.mifeng.bubaexaminationsystem.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

import us.mifeng.bubaexaminationsystem.app.PublicApplication;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Looper;

public class NetUtils {
	static String result = "";
	public static byte[] getByteArray(Context context,String url) throws IOException{
			ByteArrayOutputStream bos = getByteArrayStream(context,url);
	return  bos.toByteArray();
	}

	private static ByteArrayOutputStream getByteArrayStream(Context context,String url)
			throws MalformedURLException, IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		URL url2 = new URL(url);
		System.out.println(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setConnectTimeout(5000);
		int code = conn.getResponseCode();
		if (code==200) {
			InputStream inputStream = conn.getInputStream();
			byte[] buf=new byte[1024];
			int temp=0;
			while ((temp=inputStream.read(buf))!=-1) {
				bos.write(buf,0,temp);
			}
		}
		return bos;
	}
	
	public  static String  getJson(Context context,String url) throws IOException{
		return getByteArrayStream(context,url).toString();
	}
}
