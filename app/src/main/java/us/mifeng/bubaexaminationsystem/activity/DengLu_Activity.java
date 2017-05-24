package us.mifeng.bubaexaminationsystem.activity;

import org.apache.http.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.DataAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DengLu_Activity extends Activity {

    private EditText et_name;
	private EditText et_passward;
	private String name;
	private String passward;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        initView();
    }
	
	public void click(View v){
		name = et_name.getText().toString().trim();
		passward = et_passward.getText().toString().trim();
		if(TextUtils.isEmpty(passward)){
			Toast.makeText(this, "密码不能等于空", 0).show();
		}else  if(TextUtils.isEmpty(passward)){
			Toast.makeText(this, "用户名不能等于空", 0).show();
		}else{
			startActivity(new Intent(DengLu_Activity.this,InputQuery_Activity.class));
			finish();
		}
		
//		if(!(TextUtils.isEmpty(passward)&&TextUtils.isEmpty(passward))){
//			//获取数据请求
//			getDataRequests();
//					
//		}
		
	}
	//获取数据请求
	private void getDataRequests() {
		AsyncHttpClient async = new AsyncHttpClient() ;
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("passward", passward);
		async.post("", params, new DataAsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				
				
				Toast.makeText(getApplicationContext(), "登陆成功", 0).show();
				//Intent intent=new Intent();
				//intent.setClass(MainActivity.this ,ChaXunActivite.class);
				//startActivity(intent);
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				
				
				if("11".equals(name)&&"11".equals(passward)){
					Toast.makeText(DengLu_Activity.this, "正在登陆.....", 0).show();
					Intent intent=new Intent();
					intent.setClass(DengLu_Activity.this ,InputQuery_Activity.class);
					startActivity(intent);
				}
				Toast.makeText(DengLu_Activity.this, "用户名或密码错误", 0).show();
				
			}
		});
				
	}

	//找id
	private void initView() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_passward = (EditText) findViewById(R.id.et_passward);
		
	}


}
