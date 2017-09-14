package us.mifeng.bubaexaminationsystem.utils;

import java.util.HashMap;
import java.util.Map;

import us.mifeng.bubaexaminationsystem.app.PublicApplication;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Volley_LianWang {
	
	public static void volley_Get(String url,final WangLuo_HuiDiao huiDiao,Context context){//GET请求
		StringRequest stringReque=new StringRequest(url,new Response.Listener<String>() {

			@Override
			public void onResponse(String data) {
				// 联网成功
				huiDiao.getData(data);
				//使用回掉接口
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// 联网失败
			}
		});
		stringReque.setTag("get");
		PublicApplication.getRequestQueue(context).add(stringReque);
	}
	public static void volley_Post (String url,final WangLuo_HuiDiao huiDiao,final HashMap<String, String> myMap,Context context){
		StringRequest stringRe=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String data) {
				// 联网成功
				huiDiao.getData(data);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// 联网失败
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				//给服务器一个HasMap
				return myMap;
			}
		};
		stringRe.setTag("post");
		PublicApplication.getRequestQueue(context).add(stringRe);
	}

}
