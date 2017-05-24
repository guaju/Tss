package us.mifeng.bubaexaminationsystem.activity;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.bean.CollegeT;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CollegeTeachersActivity extends Activity {

	private ListView lv_college;
	private String path;
	private ArrayList<CollegeT> aList=new ArrayList<CollegeT>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classoverall_activity);
		
		initView();
		initHttp();
		initEvent();
		
		
	}
	private void initEvent() {
		lv_college.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent=new Intent();
				intent.putExtra("id", aList.get(position).getTeacher_id());
				intent.putExtra("last_name", aList.get(position).getLast_name());
				intent.setClass(CollegeTeachersActivity.this, TeacherActivity.class);
				startActivity(intent);
			
				
				
				
			}
		});
		
		
	}
	//
	private void initHttp() {
	
		path = "http://192.168.8.125/TSS/app/teacherList.json?depart_id=17&token=f31d58db21cc4026b3b45c3166075eba";
	
		//异步加载
		new JanyTools() {
		
			@Override
			public void zhong() {
			
				try {
					//联网操作
					String string = ConnectInternet.connect(path);
					
					Log.e("数据", string);
					//JSON解析
					parseJSON(string);
				
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		
			@Override
			public void qian() {
				// TODO Auto-generated method stub
			
			}
		
			@Override
			public void hou() {
				lv_college.setAdapter(new MyAdapter());
			
			}
		}.open();
	
	
	
	
	
		
	}
	//JSON解析
	protected void parseJSON(String json) {
		
		
		
		try {
			JSONObject jo = new JSONObject(json);
			JSONArray data=(JSONArray) jo.get("data");
			
			for (int i = 0; i < data.length(); i++) {
				CollegeT collegeT = new CollegeT();
				JSONObject joo=(JSONObject) data.get(i);
				String id=(String) joo.get("id");
				String last_name=(String) joo.get("last_name");
				collegeT.setLast_name(last_name);
				collegeT.setTeacher_id(id);
				aList.add(collegeT);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	
	}
	//找ID
	private void initView() {
		lv_college = (ListView) findViewById(R.id.lv);
		
	}
	
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return aList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View v = View.inflate(CollegeTeachersActivity.this, R.layout.item_teacher, null);
			TextView tv_xyls=(TextView) v.findViewById(R.id.tv_name);
			
			tv_xyls.setText(aList.get(position).getLast_name());
			return v;
		}
		
	}

	

}
