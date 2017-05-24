package us.mifeng.bubaexaminationsystem.activity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.activity.R.id;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.app.PublicApplication;
import us.mifeng.bubaexaminationsystem.utils.ToShow;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.DataAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText et_login_name,et_login_password;
	private String name;
	private String password;
	private SharedPreferences sp;
	private Editor edit;
	private String name2;
	private CheckBox id_cb;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        sp = getSharedPreferences("config", MODE_PRIVATE);
        edit = sp.edit();
        
        initView();
    }
	
	public void login(View v){
		name = et_login_name.getText().toString().trim();
		password = et_login_password.getText().toString().trim();
		//直接保存上一次输入的用户名
		sp = getSharedPreferences("config", MODE_PRIVATE);
		edit = sp.edit();
		edit.putString("username", name);
		edit.commit();
		//通过判断来存储密码
		if (id_cb.isChecked()) {
			edit = sp.edit();
			edit.putString("password", password);
			edit.commit();
		}
		
		
		if(TextUtils.isEmpty(password)){
			ToShow.show(this, "密码不能等于空");
		}else  if(TextUtils.isEmpty(password)){
			ToShow.show(this, "用户名不能等于空");
		}
			else{
			getDataRequests();
		}
		
		
	}
	//获取数据请求
	private void getDataRequests() {
		String PATH = JieKou.DENGLU;
		AsyncHttpClient async = new AsyncHttpClient() ;
		RequestParams params = new RequestParams();
		params.put("username", name);
		params.put("password", password);
		async.post(PATH, params, new DataAsyncHttpResponseHandler() {
			
			

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				
				String json = new String(arg2);
				try {
					
					Log.e("",json );
					Log.e("",arg2 +"");
					JSONObject object = new JSONObject(json);
					String status =(String) object.get("status");
					if("200".equals(status)){
						
						String token =(String) object.get("token");
						Log.e("----------", token);
						edit.putString("token", token);
						edit.commit();
						String str= new String(arg2);
						System.out.println(str);
						JSONObject jo;
						int type = 0;
						try {
							jo = new JSONObject(str);
							JSONObject jo2 = jo.getJSONObject("data");
							type = (Integer) jo2.get("type");
							name2 = (String) jo2.get("name");
							edit.putInt("type", type);
							edit.putString("name", name2);
							edit.commit();
							System.out.println(type);
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						if (type==1100||type==1200) {
							Intent intent = new Intent(LoginActivity.this,InputQuery_Activity.class);
							intent.putExtra("权限", "查询");
							
							startActivity(intent);
							
						}
						
						else  {
							Intent intent=new Intent();
							intent.putExtra("权限", "");
							intent.setClass(LoginActivity.this ,InputQuery_Activity.class);
							startActivity(intent);
						}
						
						ToShow.show(getApplicationContext(), "登陆成功");
					/*	Intent intent=new Intent();
						intent.setClass(LoginActivity.this ,InputQuery_Activity.class);
						startActivity(intent);*/
						finish();
					}else  {
						ToShow.show(getApplicationContext(), "登陆失败，用户或密码错误");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				Toast.makeText(getApplicationContext(), "登陆成功", 0).show();
//				Intent intent=new Intent();
//				intent.setClass(LoginActivity.this ,InputQuery_Activity.class);
//				startActivity(intent);
//				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				
				ToShow.show(LoginActivity.this, "网络异常,请稍后重试");
				
			}
		});
	}

	//找id
	private void initView() {
		et_login_name = (EditText) findViewById(R.id.et_login_name);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		
		sp=getSharedPreferences("config", MODE_PRIVATE);
		String username = sp.getString("username", "");
		String password = sp.getString("password", "");
		System.out.println("哈哈哈"+username+"---"+password);
		et_login_name.setText(username);
		et_login_name.setSelection(username.length());
		et_login_password.setText(password);
		et_login_password.setSelection(password.length());
		
		id_cb = (CheckBox) findViewById(R.id.id_cb);
		if (sp.getBoolean("rememberPassword", false)) {
			id_cb.setChecked(true);
		}else {
			id_cb.setChecked(false);
		}
	}
	//点击记住用户名的逻辑
	public void rememberusername(View v){
		if (id_cb.isChecked()) {
			id_cb.setChecked(false);
			sp = getSharedPreferences("config", MODE_PRIVATE);
			edit = sp.edit();
			edit.putString("password", "");
			edit.putBoolean("rememberPassword", false);
			edit.commit();
		}
		else {
			id_cb.setChecked(true);
			sp = getSharedPreferences("config", MODE_PRIVATE);
			edit = sp.edit();
			edit.putBoolean("rememberPassword", true);
			edit.putString("password", et_login_name.getText().toString().trim());
			edit.commit();
		}
	}
}