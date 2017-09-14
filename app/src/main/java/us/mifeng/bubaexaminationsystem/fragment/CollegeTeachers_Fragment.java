package us.mifeng.bubaexaminationsystem.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.CollegeT;
import us.mifeng.bubaexaminationsystem.bean.TeacherAttendanceRate;
import us.mifeng.bubaexaminationsystem.fragment.Teacher_Fragment.MyAdapter;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.TimeUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CollegeTeachers_Fragment extends Fragment {

	private ListView lv_college;
	private String path;
	private ArrayList<TeacherAttendanceRate> aList;
	private View v;
	private XueYuanHuiZong_Activity activity;
	private String collegeName;
	private boolean zhou;
	private String depart_name,prev1,prev2;
	private String teacher_name,next1,next2;
	private double cql;
	private String time , timezq;
	private TextView tv_status;
	private MyAdapter attendenceAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = View.inflate(getActivity(), R.layout.classoverall_activity, null);
		activity = (XueYuanHuiZong_Activity) getActivity();
		activity.xueyuan_id.setTextSize(CommonUtils.dip2px(activity, 10));
		collegeName = activity.xueyuan_id.getText().toString();
		tv_status = (TextView) v.findViewById(R.id.tv_status);
		if (!collegeName.contains("出勤率")) {
			activity.xueyuan_id.setText("月统计表");
		}
		initView();
		timezq="W";
		XueYuanHuiZong_Activity.time=3;
		initHttp2();
		jianTing();
		return v;
	}
	@Override
	public void onResume() {
		super.onResume();
		if (activity.progressDialog.isShowing()) {
			activity.progressDialog.dismiss();
		}
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
		zhou = false;
	}

	@Override
	public void onStart() {
		super.onStart();
		zhou = true;
	}
	private void initEvent() {
		lv_college.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			ToShow.show(getActivity(), aList.get(position).getTeacherName());
			FragmentTransaction bt = XueYuanHuiZong_Activity.fm.beginTransaction();
			bt.replace(R.id.fl_id, new Teacher_Fragment(aList.get(position).getTeacherId()+""));
			bt.commit();
			}
			
		});
	}
	//联网获取数据
		private void initHttp2() {
			new JanyTools() {
				@Override
				public void zhong() {
					try {
						//if ("M".equals(timezq)||"W".equals(timezq)) {
							String string = ConnectInternet.connect(path);
							
							Log.e("打印数据", string);
							//JSON解析 
							parseJSON(string);
						//}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void qian() {
					if(XueYuanHuiZong_Activity.time==2){
						timezq="W";
					}else if(XueYuanHuiZong_Activity.time==3){
						timezq="M";
					}else if(XueYuanHuiZong_Activity.time==4){
						timezq="T";
					}else if(XueYuanHuiZong_Activity.time==1){
					timezq="D";}
					
					
					path = JieKou.ATTENDENCE_RATE_COLLEGE
							+"?depart_id="+XueYuanHuiZong_Activity.id+"&token="+JieKou.getSp(getActivity())+"&cycle="+timezq+"&date1="+activity.date1.getText()+"&date2="+activity.date1.getText();
					System.out.println(path);
				
				}
					
					
				@Override
				public void hou() {
					attendenceAdapter=new MyAdapter(aList);
					lv_college.setAdapter(attendenceAdapter);
					
					
					//if ("M".equals(timezq)||"W".equals(timezq)) {
						if (aList==null||aList.size()==0) {
							ToShow.show(getActivity(), "暂无数据");
							lv_college.setVisibility(View.INVISIBLE);
							tv_status.setVisibility(View.VISIBLE);
						}else {
							tv_status.setVisibility(View.INVISIBLE);
							lv_college.setVisibility(View.VISIBLE);
							initEvent();
						}
						
					if (activity.progressDialog.isShowing()) {
						activity.progressDialog.dismiss();
					}
					
//					}else {
//						ToShow.show(getActivity(), "暂不支持此项查询，请切换到周或月");
//						lv_college.setVisibility(View.INVISIBLE);
//						tv_status.setVisibility(View.VISIBLE);
//				}
					}
			}.open();
		}
		private void jianTing(){
			XueYuanHuiZong_Activity.left.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (TimeUtils.isFastDoubleClick()) {  
				        return;  
				    } 
					if(!"1".equals(prev1)){
						if (zhou) {
							time = prev1;
							if ("D".equals(timezq)) {
								XueYuanHuiZong_Activity.date1.setText(prev1);
								XueYuanHuiZong_Activity.date2.setVisibility(View.GONE);
							}else {
								XueYuanHuiZong_Activity.date2.setVisibility(View.VISIBLE);
								XueYuanHuiZong_Activity.date1.setText(prev1);
								XueYuanHuiZong_Activity.date2.setText(" 至" + prev2);
							}
						}
					}else{
						ToShow.show(getActivity(), "超出时间范围");
					}
				}
			});
			XueYuanHuiZong_Activity.right.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					if (TimeUtils.isFastDoubleClick()) {  
				        return;  
				    } 
					if(!"1".equals(next1)){
						if (zhou) {
							time = next1;
							
							if ("D".equals(timezq)) {
								XueYuanHuiZong_Activity.date1.setText(next2);
								XueYuanHuiZong_Activity.date2.setVisibility(View.GONE);
							}else {
						    XueYuanHuiZong_Activity.date2.setVisibility(View.VISIBLE);
							XueYuanHuiZong_Activity.date1.setText(next1);
							XueYuanHuiZong_Activity.date2.setText(" 至" + next2);
							}
						}
					}else{
						ToShow.show(getActivity(), "超出时间范围");
					}
				}
			});
			XueYuanHuiZong_Activity.date1.addTextChangedListener(new TextWatcher() {// data1的变化监听
						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {
						}

						@Override
						public void beforeTextChanged(CharSequence s, int start,
								int count, int after) {
						}

						@Override
						public void afterTextChanged(Editable s) {
							if (zhou) {
								initHttp2();
							}
						}
					});
			XueYuanHuiZong_Activity.xueyuan_id.addTextChangedListener(new TextWatcher() {// data1的变化监听
				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					if (zhou) {
						initHttp2();
					}
				}
			});
		}
	
	
	
	
	
	
	
	
	
	
	
	
	//JSON解析
	protected void parseJSON(String json) {
		aList=new ArrayList<TeacherAttendanceRate>();
		try {
			JSONObject jo = new JSONObject(json);
			JSONObject data=(JSONObject) jo.get("data");
			JSONArray jsonList = data.getJSONArray("list");
			prev1 = (String) data.get("prev_date1");
			prev2 = (String) data.get("prev_date2");
			next1 = (String) data.get("next_date1");
			next2 = (String) data.get("next_date2");
			for (int i = 0; i < jsonList.length(); i++) {
				JSONObject joo=(JSONObject) jsonList.get(i);
				String attendance_rate = joo.getString("attendance_rate");
				int depart_id = joo.getInt("depart_id");
				String teacher_name = joo.getString("teacher_name");
				int teacher_id = joo.getInt("teacher_id");
				TeacherAttendanceRate tRate = new TeacherAttendanceRate();
				tRate.setAttendance_rate(attendance_rate);
				tRate.setTeacherName(teacher_name);
				tRate.setTeacherId(teacher_id);
				tRate.setDepartId(depart_id);
				aList.add(tRate);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//找ID
	private void initView() {
		lv_college = (ListView)v.findViewById(R.id.lv);
		RelativeLayout rl=(RelativeLayout) v.findViewById(R.id.rl_fanhui);
		rl.setVisibility(View.GONE);
		
		
		
		
		
	}
	public class MyAdapter extends BaseAdapter{
		public List list;
		public MyAdapter(List mList){
			list=mList;
		}
		@Override
		public int getCount() {
			return list.size();
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
			View v = View.inflate(getActivity(), R.layout.item_teacher, null);
			TextView tv_xyls=(TextView) v.findViewById(R.id.tv_name);
			TextView tv_rate=(TextView) v.findViewById(R.id.tv_rate);
			if (list.size()!=0) {
				TeacherAttendanceRate teacherAttendanceRate = (TeacherAttendanceRate) list.get(position);
				tv_xyls.setText(teacherAttendanceRate.getTeacherName());
				tv_rate.setText("出勤率："+teacherAttendanceRate.getAttendance_rate()+"%");
			}
			else {
				System.out.println(list.size()+"哇卡哇卡");
				tv_status.setVisibility(View.VISIBLE);
				tv_xyls.setText("暂无数据");
				tv_rate.setText("暂无数据");
			}
			return v;
		}
	}
	
}
