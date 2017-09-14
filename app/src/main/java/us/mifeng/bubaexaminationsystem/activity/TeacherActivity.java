package us.mifeng.bubaexaminationsystem.activity;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.CollegeT;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TeacherActivity extends Activity {

	private TextView tv_lsname,tv_xy,tv_lscql;
	private ListView lv_banji;
	private ArrayList<CollegeT> aList=new ArrayList<CollegeT>();
	private String id;
	private String last_name;
	private String path;
	private String depart_name;
	private String teacher_name;
	private double cql;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher);
		
		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		last_name = intent.getStringExtra("last_name");
		
		initView();
		initHttp();

		
		
		
	}
	//联网获取数据
	private void initHttp() {
		path = JieKou.ZHUJI+JieKou.ATTENDENCE_RATE_COLLEGE+"?cycle=M&date1=2016-06-29&date2=2016-06-29&teacher_id="+id+"&token=f31d58db21cc4026b3b45c3166075eba";
				//?cycle=M&date1=2016-06-29&date2=2016-06-29&teacher_id="+id+"&token=f31d58db21cc4026b3b45c3166075eba";
		new JanyTools() {
			
			@Override
			public void zhong() {
				try {
					String string = ConnectInternet.connect(path);
					
					Log.e("打印数据", string);
					//JSON解析 
					parseJSON(string);
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void qian() {
				
			}
			
			@Override
			public void hou() {
				tv_lsname.setText("老师姓名："+teacher_name);
				tv_xy.setText("所在学院："+depart_name);
				tv_lscql.setText("老师出勤率:"+cql+"%");
				lv_banji.setAdapter(new MyAdapter());
			}
		}.open();
		
	}
	
	//JSON解析
	protected void parseJSON(String string) {
		
		try {
			
			JSONObject jsonObject = new JSONObject(string);
			JSONObject data=(JSONObject) jsonObject.get("data");
			JSONArray list=(JSONArray) data.get("list");
			
			double pingjun=0; 
			
			for (int i = 0; i < list.length(); i++) {
				CollegeT collegeT = new CollegeT();
				JSONObject jo=(JSONObject) list.get(i);
				depart_name = (String) jo.get("depart_name");
				teacher_name = (String) jo.get("teacher_name");
				String class_name=(String) jo.get("class_name");
				String attendance_rate=(String) jo.get("attendance_rate");
				double d = Double.parseDouble(attendance_rate);
				pingjun=d+pingjun;
				collegeT.setClass_name(class_name);
				aList.add(collegeT);
			}
			cql = pingjun/list.length();
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//找id
	private void initView() {
		tv_lsname = (TextView) findViewById(R.id.tv_lsname);
		tv_lscql = (TextView) findViewById(R.id.tv_lscql);
		tv_xy = (TextView) findViewById(R.id.tv_xy);
		lv_banji = (ListView) findViewById(R.id.lv_banji);
	}
	
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return aList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			
			View v = View.inflate(TeacherActivity.this, R.layout.item_teacher, null);
			TextView tv_bj=(TextView) v.findViewById(R.id.tv_name);
			tv_bj.setText("所在班级"+aList.get(arg0).getClass_name());
			return v;
		}
		
	}
	
	public void fanhui(View v){
		startActivity(new Intent(TeacherActivity.this,CollegeTeachersActivity.class));
		
	}


	
}
