package us.mifeng.bubaexaminationsystem.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.mikephil.charting.charts.PieChart;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.adapter.Chart_PagerAdapter;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.utils.TimeUtils;
import us.mifeng.bubaexaminationsystem.utils.Volley_LianWang;
import us.mifeng.bubaexaminationsystem.view.PieChartActivity;
import us.mifeng.bubaexaminationsystem.view.PieChart_BingTu;
import us.mifeng.bubaexaminationsystem.view.ZhuXingTu;
import us.mifeng.bubaexaminationsystem.view.ZhuXingTuWeiJi;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Chart__FragmentRi extends Fragment {
	private static final View TextView = null;
	private View v;
	private boolean zhou;
	private ViewPager vp;
	private ArrayList<View> list;
	private RadioGroup rg_id;
	private RadioButton rb_bt1, rb_zx, rb_bt2, rb_zhux;
	private String prev,next;
	private ArrayList<ZhouBanji> list_BanJi,list_XueYuan,list_BingTu;
	private String time;
	private int youBiao;
	private ZhuXingTu v2;
	private Handler ha = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				lianWangBingTu(1);
				break;
			case 1:
				addView(0);
				jianTing();
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				break;
			case 2:
				lianWangBingTu(3);
				break;
			case 3:
				addView(youBiao);
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				break;

			default:
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				break;
			}

		};
	};
	private XueYuanHuiZong_Activity activity;
	private View v1;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = View.inflate(getActivity(), R.layout.fragment_data, null);
		activity = (XueYuanHuiZong_Activity) getActivity();
		initView();
		activity.progressDialog.show();
		banJiLianWang(0);
		return v;
	}
	private void lianWangBingTu(final int i) {//饼图违纪情况联网
		String path ;
		if("".equals(XueYuanHuiZong_Activity.id)){
			path = JieKou.ZHUJI+"/app/wholeDiscipline.json?cycle=D" +  "&date1=" + time + "&date2=" + time
					+ "&token=" + JieKou.getSp(getActivity());
			Log.e("饼图", "我走的是全员");
		}else{
		path =JieKou.ZHUJI+"/app/disciplineInfo.json?cycle=D"+ "&date1=" + time + "&date2=" + time
				+ "&depart_id=" + XueYuanHuiZong_Activity.id
				+ "&token=" + JieKou.getSp(getActivity());
		}
		Volley_LianWang.volley_Get(
				path,new WangLuo_HuiDiao() {
					@Override
					public void getData(String data) {
						Log.e("饼图", data);
						try {
							jieXiBingTu(data);
							ha.sendEmptyMessage(i);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, getActivity());
	};
	private void jieXiBingTu(String connect)throws JSONException{//解析饼图
		System.out.println(connect);
		list_BingTu = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		JSONArray list = data.getJSONArray("list");
		for (int i = 0; i < list.length(); i++) {
			ZhouBanji zhouXueyuan = new ZhouBanji();
			JSONObject suoyin = list.getJSONObject(i);
			if(!suoyin.isNull("discipline_name")){
				String attendance_rate =  suoyin.get("discipline_name")+"";
				zhouXueyuan.setAttendence(attendance_rate);//违纪名字
				if(!"".equals(XueYuanHuiZong_Activity.id)){
					String depart_name =  suoyin.get("depart_name")+"";
					zhouXueyuan.setName(depart_name);//学院名称
				}else{
					zhouXueyuan.setName("全校信息");//学院名称
				}
			Integer discipline_rate = (Integer) suoyin.get("discipline_number");
			zhouXueyuan.setDisciplinerate(discipline_rate+"");//违纪数量
			list_BingTu.add(zhouXueyuan);
			}
		}
	}
	private void banJiLianWang(final int a) {
		time = XueYuanHuiZong_Activity.date1.getText().toString().trim();
		String jiekou;
		if("".equals(XueYuanHuiZong_Activity.id)){
			jiekou= JieKou.QUERY_XUEYUAN;
		}else{
			jiekou=JieKou.QUERY_BANJI;
		}
		Volley_LianWang.volley_Get(jiekou + "D&date1=" + time
				+ "&date2=" + time + "&depart_id=" + XueYuanHuiZong_Activity.id
				+ "&token=" + JieKou.getSp(getActivity()),
				new WangLuo_HuiDiao() {
					@Override
					public void getData(String data) {
						Log.e("", data);
						try {
							if("".equals(XueYuanHuiZong_Activity.id)){
								JieXiXueYuan(data);
							}else{
								jieXiBanJI(data);
							}
							ha.sendEmptyMessage(a);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, getActivity());
		
	}

	private void jianTing() {
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {// 用这个数据
				youBiao = arg0;
				switch (arg0) {
				case 0:
					rb_bt1.setChecked(true);
					break;
				case 1:
					rb_zhux.setChecked(true);
					break;
				case 2:
					rb_bt2.setChecked(true);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		rg_id.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_bt1:
					vp.setCurrentItem(0);
					break;
				case R.id.rb_zhux:
					vp.setCurrentItem(1);
					break;
				case R.id.rb_bt2:
					vp.setCurrentItem(2);
					break;
				default:
					break;
				}
			}
		});
		XueYuanHuiZong_Activity.left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TimeUtils.isFastDoubleClick()) {  
				        return;  
				    } 
				if(zhou){
						XueYuanHuiZong_Activity.date1.setText(prev);
				}
			}
		});
		XueYuanHuiZong_Activity.right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TimeUtils.isFastDoubleClick()) {  
			        return;  
			    } 
				if(zhou){
						XueYuanHuiZong_Activity.date1.setText(next);
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
						if(zhou){
							banJiLianWang(2);
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
	private void JieXiXueYuan(String connect) throws JSONException {// 解析学院
		
		System.out.println("now get the data "+connect);
		list_XueYuan = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		prev = (String) data.get("prev_date1");
		next = (String) data.get("next_date1");
		Log.e(prev, next);
		JSONArray jslist = data.getJSONArray("list");
		if (jslist.length()==1) {
			return;
		}
		for (int i = 0; i < jslist.length(); i++) {
			JSONObject suoyin = jslist.getJSONObject(i);
//			if (!suoyin.has("circle")) {
//				return;
//			}
			
			ZhouBanji zhouXueyuan = new ZhouBanji();
			String jishu = suoyin.get("supposed_attendance")+"";
			String chuqinlv = suoyin.get("attendance_rate")+"";
			String weijilv =  suoyin.get("discipline_rate")+"";
			if (suoyin.has("depart_name")) {
				String xueyuan_name =  suoyin.get("depart_name")+"";
				zhouXueyuan.setName(xueyuan_name);
			}
			zhouXueyuan.setAttendence(chuqinlv);
			zhouXueyuan.setDisciplinerate(weijilv);
			zhouXueyuan.setBase(jishu);
			list_XueYuan.add(zhouXueyuan);
		}
	}
	private void jieXiBanJI(String connect) throws JSONException {// 解析班级信息
		list_BanJi = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		prev = (String) data.get("prev_date1");
		next = (String) data.get("next_date1");
		JSONArray jslist = data.getJSONArray("list");
		for (int i = 0; i < jslist.length(); i++) {
			ZhouBanji zhouBanji = new ZhouBanji();
			JSONObject suoyin = jslist.getJSONObject(i);
			String name = suoyin.get("class_name")+"";
			String jishu = suoyin.get("supposed_attendance")+"";
			String chuqinlv = suoyin.get("attendance_rate")+"";
			String weijilv =  suoyin.get("discipline_rate")+"";
			int class_id =  suoyin.getInt("class_id");
			String depart_name =  suoyin.get("depart_name")+"";
			zhouBanji.setDepart_name(depart_name);
			zhouBanji.setBase(jishu);
			zhouBanji.setAttendence(chuqinlv);
			zhouBanji.setDisciplinerate(weijilv);
			zhouBanji.setId(class_id + "");
			zhouBanji.setName(name);
			list_BanJi.add(zhouBanji);
			Log.e(name, list_BanJi.size() + "");
		}
	}

	private void addView(int a) {//往ViewPager里面添加View
		list = new ArrayList<View>();
		LayoutInflater inform = LayoutInflater.from(getActivity());

		//		View v1 = View.inflate(getActivity(), R.layout.piechart, null);
//		PieChart pc=(PieChart) v1.findViewById(R.id.pc_id);
		if("".equals(XueYuanHuiZong_Activity.id)){
			if (list_XueYuan.size()>0) {
				
				list_XueYuan.remove(list_XueYuan.size()-1);
				v1 = new ZhuXingTuWeiJi(getActivity(), list_XueYuan);
				
//			new PieChartActivity(list_XueYuan,pc);
				v2 = new ZhuXingTu(getActivity(), list_XueYuan);
			}
			else {
				v1=new TextView(getActivity());
				TextView v1t1=(TextView)v1;
				v1t1.setText("暂无记录");
				v1t1.setGravity(Gravity.CENTER);
				v2 = new ZhuXingTu(getActivity());
			}
		}else{
			v1 = new ZhuXingTuWeiJi(getActivity(), list_BanJi);
//			new PieChartActivity(list_BanJi,pc);
			v2 = new ZhuXingTu(getActivity(), list_BanJi);
		}
		View v3 = inform.inflate(R.layout.piechart, null, false);
		PieChart bt = (PieChart) v3.findViewById(R.id.pc_id);
		new PieChart_BingTu(list_BingTu, bt);
		list.add(v1);
		list.add(v2);
		list.add(v3);
		vp.setAdapter(new Chart_PagerAdapter(list));
		vp.setCurrentItem(a);
		switch (a) {
		case 0:
			rb_bt1.setChecked(true);
			break;
		case 1:
			rb_zhux.setChecked(true);
			break;
		case 2:
			rb_bt2.setChecked(true);
			break;
		default:
			break;
		}
	}

	private void initView() {//找id
		zhou=true;
		XueYuanHuiZong_Activity.date2.setText("");
		vp = (ViewPager) v.findViewById(R.id.data_ri_vp);
		rg_id = (RadioGroup) v.findViewById(R.id.rg_id);
		rb_bt1 = (RadioButton) v.findViewById(R.id.rb_bt1);
		rb_zx = (RadioButton) v.findViewById(R.id.rb_zx);
		rb_zhux = (RadioButton) v.findViewById(R.id.rb_zhux);
		rb_bt2 = (RadioButton) v.findViewById(R.id.rb_bt2);
		rb_zx.setVisibility(View.GONE);
	}
}
