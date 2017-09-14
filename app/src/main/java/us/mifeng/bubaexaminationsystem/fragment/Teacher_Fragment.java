package us.mifeng.bubaexaminationsystem.fragment;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.github.mikephil.charting.utils.Utils;
import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.CollegeT;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.TimeUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class Teacher_Fragment extends Fragment {

	private TextView tv_lsname,tv_xy,tv_lscql;
	private ListView lv_banji;
	private boolean zhou;
	private ArrayList<CollegeT> aList;
	private String id;
	private String path;
	private String depart_name,prev1,prev2;
	private String teacher_name,next1,next2;
	private double cql;
	private String time , timezq;
	private View v;
	private XueYuanHuiZong_Activity activity;
	private String formatCql;
	
	
	public Teacher_Fragment (String id){
		this.id=id;
		this.time=XueYuanHuiZong_Activity.date1.getText().toString();
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = View.inflate(getActivity(), R.layout.activity_teacher, null);
		activity = (XueYuanHuiZong_Activity) getActivity();
		activity.fanhuiIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activity.IS_IN_TEACHERATTENDENCEDETAIL) {
					FragmentTransaction bt = XueYuanHuiZong_Activity.fm.beginTransaction();
					bt.replace(R.id.fl_id, new CollegeTeachers_Fragment());
					bt.commit();
					activity.IS_IN_TEACHERATTENDENCEDETAIL=false;
				}
				else {
					activity.finish();
				}
				
			}
		});
		initHttp();
		return v;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		XueYuanHuiZong_Activity.IS_IN_TEACHERATTENDENCEDETAIL=true;
		if (activity.progressDialog.isShowing()) {
			activity.progressDialog.dismiss();
		}
	}
	//联网获取数据
	private void initHttp() {
		new JanyTools() {
			@Override
			public void zhong() {
				try {
					String string = ConnectInternet.connect(path);
					Log.e("打印数据老师详细信息", string);
					//JSON解析 
					parseJSON(string);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void qian() {
				initView();
				path = JieKou.ATTENDENCE_RATE_PER+"?cycle="+timezq+"&date1="+time+"&date2="+time+"&teacher_id="+id+"&token="+JieKou.getSp(getActivity());
				System.out.println(path);
			}
			@Override
			public void hou() {
				if (Double.isNaN(cql)) {
					tv_lscql.setText("老师出勤率:"+"暂无记录");
				}
				tv_lsname.setText("老师姓名："+teacher_name);
				tv_xy.setText("所在学院："+depart_name);
				tv_lscql.setText("老师出勤率:"+formatCql+"%");
				lv_banji.setAdapter(new MyAdapter());
				jianTing();
			}
		}.open();
	}
	private void initHttp2() {
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
				if(XueYuanHuiZong_Activity.time==2){
					timezq="W";
				}else if(XueYuanHuiZong_Activity.time==3){
					timezq="M";
				}else if(XueYuanHuiZong_Activity.time==4){
					timezq="T";
				}else if(XueYuanHuiZong_Activity.time==1){
				timezq="D";}
				path = JieKou.ATTENDENCE_RATE_PER+"?cycle="+timezq+"&date1="+time+"&date2="+time+"&teacher_id="+id+"&token="+JieKou.getSp(getActivity());
				System.out.println(path);}
			@Override
			public void hou() {
				
				tv_lsname.setText("老师姓名："+teacher_name);
				tv_xy.setText("所在学院："+depart_name);
				
				if (Double.isNaN(cql)) {
					tv_lscql.setText("老师出勤率:"+"暂无记录");
				}
				else {
					tv_lscql.setText("老师出勤率:"+formatCql+"%");
				}
				lv_banji.setAdapter(new MyAdapter());
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
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
					}}
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
	//JSON解析
	protected void parseJSON(String string) {
		aList=new ArrayList<CollegeT>();
		try {
			JSONObject jsonObject = new JSONObject(string);
			JSONObject data=(JSONObject) jsonObject.get("data");
			JSONArray list=(JSONArray) data.get("list");
			
			
			
			prev1 = (String) data.get("prev_date1");
			prev2 = (String) data.get("prev_date2");
			next1 = (String) data.get("next_date1");
			next2 = (String) data.get("next_date2");
			double pingjun=0; 
			for (int i = 0; i < list.length(); i++) {
				CollegeT collegeT = new CollegeT();
				JSONObject jo=(JSONObject) list.get(i);
				depart_name = (String) jo.get("depart_name");
				teacher_name = (String) jo.get("teacher_name");
				String class_name=(String) jo.get("class_name");
				double attendance_rate=jo.getDouble("attendance_rate");
				DecimalFormat df=new DecimalFormat(".##");
				String format = df.format(attendance_rate);
				pingjun=attendance_rate+pingjun;
				collegeT.setClass_name(class_name);
				collegeT.setAttendance_rate(format);
				aList.add(collegeT);
			}
			
			cql = (pingjun/list.length())*1.00d;
			DecimalFormat df=new DecimalFormat(".##");
			formatCql = df.format(cql);
			System.out.println(formatCql);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	//找id
	private void initView() {
		if(XueYuanHuiZong_Activity.time==2){
			timezq="W";
		}else if(XueYuanHuiZong_Activity.time==3){
			timezq="M";
		}else if(XueYuanHuiZong_Activity.time==4){
			timezq="T";
		}else if(XueYuanHuiZong_Activity.time==1){
		timezq="D";
		}
		tv_lsname = (TextView) v.findViewById(R.id.tv_lsname);
		tv_lscql = (TextView) v.findViewById(R.id.tv_lscql);
		tv_xy = (TextView) v.findViewById(R.id.tv_xy);
		lv_banji = (ListView) v.findViewById(R.id.lv_banji);
		RelativeLayout rl=(RelativeLayout) v.findViewById(R.id.rl_fanhui);
		rl.setVisibility(View.GONE);
	}
	
	public class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return aList.size();
		}
		@Override
		public Object getItem(int arg0) {
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View v = View.inflate(getActivity(), R.layout.item_teacher, null);
			TextView tv_bj=(TextView) v.findViewById(R.id.tv_name);
			TextView tv_rate=(TextView) v.findViewById(R.id.tv_rate);
			CollegeT collegeT = aList.get(arg0);
			String class_name = collegeT.getClass_name();
			String attendance_rate = collegeT.getAttendance_rate();
			tv_bj.setText("所在班级"+class_name);
			System.out.println(attendance_rate);
			tv_rate.setText("出勤率"+attendance_rate+"%");
			return v;
		}
	}
	
	
}
