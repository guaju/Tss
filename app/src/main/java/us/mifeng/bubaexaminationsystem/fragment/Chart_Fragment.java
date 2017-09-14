package us.mifeng.bubaexaminationsystem.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.adapter.Chart_PagerAdapter;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.utils.TimeUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import us.mifeng.bubaexaminationsystem.utils.Volley_LianWang;
import us.mifeng.bubaexaminationsystem.view.PieChartActivity;
import us.mifeng.bubaexaminationsystem.view.PieChart_BingTu;
import us.mifeng.bubaexaminationsystem.view.ZheXianTu;
import us.mifeng.bubaexaminationsystem.view.ZhuXingTu;
import us.mifeng.bubaexaminationsystem.view.ZhuXingTuWeiJi;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Chart_Fragment extends Fragment {
	private String time1, time2, prev1, prev2, next1, next2,curr_date1,curr_date2;
	private View v;
	private boolean zhou;
	private ViewPager vp;
	private ArrayList<View> list;
	private RadioGroup rg_id;
	private RadioButton rb_bt1, rb_zx, rb_bt2, rb_zhux;
	private String Time,TimeO;
	private ZhuXingTu v3;
	private int youBiao;
	private ArrayList<ZhouBanji> list_BanJi, list_XueYuan,list_ZheXian,list_BingTu;
	private Handler ha = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				lianWangZheXian(1);
				break;
			case 1:
				lianWangBingTu(2);
				break;
			case 2:
				addView(0);
				jianTing();
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				break;
			case 3:
				lianWangZheXian(4);
				break;
			case 4:
				lianWangBingTu(5);
				break;
			case 5:
				addView(youBiao);
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				
				break;
			default:
				break;
			}

		}
	};
	private XueYuanHuiZong_Activity activity;
	private String cycle;
	private View v1;
	private String xueyuan_name;
	private String depart_name;
	private String chuqinlv;
	private String jishu;
	private String weijilv;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = View.inflate(getActivity(), R.layout.fragment_data, null);
		activity = (XueYuanHuiZong_Activity) getActivity();
		initView();
		banJiLianWang(0);
		return v;
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
	private void lianWangZheXian(final int i) {//折线图联网
		String path ;
		if("".equals(XueYuanHuiZong_Activity.id)){
			path = JieKou.QUANYUAN_CHUQINWEIJI + TimeO + "&date1=" + curr_date1 + "&date2=" + curr_date2
					+ "&token=" + JieKou.getSp(getActivity());
		}else{
			path =JieKou.QUERY_XUEYUAN + TimeO + "&date1=" + curr_date1 + "&date2=" + curr_date2
					+ "&depart_id=" + XueYuanHuiZong_Activity.id
					+ "&token=" + JieKou.getSp(getActivity());
		}
		Volley_LianWang.volley_Get(
				path,new WangLuo_HuiDiao() {
					@Override
					public void getData(String data) {
						Log.e("折线图", data);
						try {
							jieXiZheXian(data);
							ha.sendEmptyMessage(i);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, getActivity());
	};
	private void lianWangBingTu(final int i) {//饼图违纪情况联网
		String path ;
		if("".equals(XueYuanHuiZong_Activity.id)){
			path = JieKou.QUANYUAN_WEIJILEIXING + Time + "&date1=" + curr_date1 + "&date2=" + curr_date2
					+ "&token=" + JieKou.getSp(getActivity());
			Log.e("饼图", "我走的是全院");
		}else{
		path =JieKou.XUEYUAN_WEIJILEIXING + Time + "&date1=" + curr_date1 + "&date2=" + curr_date2
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
		list_BingTu = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		JSONArray list = data.getJSONArray("list");
		for (int i = 0; i < list.length(); i++) {
			JSONObject suoyin = list.getJSONObject(i);
			if(!suoyin.isNull("discipline_name")){
				ZhouBanji zhouXueyuan = new ZhouBanji();
				String attendance_rate =  suoyin.get("discipline_name")+"";
				zhouXueyuan.setAttendence(attendance_rate);//违纪名字
				if(!"".equals(XueYuanHuiZong_Activity.id)){
					String depart_name =  suoyin.get("depart_name")+"";
					zhouXueyuan.setName(depart_name);//学院名称
				}else{
					zhouXueyuan.setName("全校");//学院名称
				}
				Integer discipline_rate = (Integer) suoyin.get("discipline_number");
				zhouXueyuan.setDisciplinerate(discipline_rate+"");//违纪数量
				list_BingTu.add(zhouXueyuan);
			}
		}
	}
	private void jieXiZheXian(String connect)throws JSONException{//解析折线图
		list_ZheXian = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		JSONArray list = data.getJSONArray("list");
		for (int i = 0; i < list.length(); i++) {
			ZhouBanji zhouXueyuan = new ZhouBanji();
			JSONObject suoyin = list.getJSONObject(i);
			if(!"".equals(XueYuanHuiZong_Activity.id)&&suoyin.has("depart_name")){
				String depart_name =  suoyin.get("depart_name")+"";
				zhouXueyuan.setName(depart_name);//学院名称
				cycle = suoyin.get("cycle")+"";
			}else{
				zhouXueyuan.setName("全校");//学院名称
			}
			String attendance_rate =  suoyin.get("attendance_rate")+"";
			String discipline_rate = suoyin.get("discipline_rate")+"";
			zhouXueyuan.setAttendence(attendance_rate);//出勤率
			zhouXueyuan.setDisciplinerate(discipline_rate);//违纪率
			zhouXueyuan.setBase(cycle);//查课时间
			list_ZheXian.add(zhouXueyuan);
		}
	}
	private void JieXiXueYuan(String connect) throws JSONException {// 解析学院
		list_XueYuan = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		prev1 = (String) data.get("prev_date1");
		prev2 = (String) data.get("prev_date2");
		if(!data.isNull("curr_date1")){
			curr_date1 = (String) data.get("curr_date1");
			curr_date2 = (String) data.get("curr_date2");
		}else{
			curr_date1 = "2105-06-01";
			curr_date2 ="2105-06-01";
		}
		next1 = (String) data.get("next_date1");
		next2 = (String) data.get("next_date2");
		JSONArray jslist = data.getJSONArray("list");
		for (int i = 0; i < jslist.length(); i++) {
			JSONObject suoyin = jslist.getJSONObject(i);
			ZhouBanji zhouXueyuan = new ZhouBanji();
			if (suoyin.has("depart_name")) {
				jishu = suoyin.get("supposed_attendance")+"";
				weijilv = suoyin.get("discipline_rate")+"";
				xueyuan_name = suoyin.get("depart_name")+"";
				chuqinlv = suoyin.get("attendance_rate")+"";
			}else {
				xueyuan_name="全校";
			}
			
			zhouXueyuan.setName(xueyuan_name);
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
		prev1 = (String) data.get("prev_date1");
		prev2 = (String) data.get("prev_date2");
		if(!data.isNull("curr_date1")){
			curr_date1 = (String) data.get("curr_date1");
			curr_date2 = (String) data.get("curr_date2");
		}else{
			curr_date1 = "2105-06-01";
			curr_date2 ="2105-06-01";
		}
		next1 = (String) data.get("next_date1");
		next2 = (String) data.get("next_date2");
		JSONArray jslist = data.getJSONArray("list");
		for (int i = 0; i < jslist.length(); i++) {
			ZhouBanji zhouBanji = new ZhouBanji();
			JSONObject suoyin = jslist.getJSONObject(i);
			String name = suoyin.get("class_name")+"";
			String jishu =suoyin.get("supposed_attendance")+"";
			String chuqinlv =  suoyin.get("attendance_rate")+"";
			String weijilv =  suoyin.get("discipline_rate")+"";
			int class_id =  suoyin.getInt("class_id");
			if (suoyin.has("depart_name")) {
				depart_name = suoyin.get("depart_name")+"";
				
			}
			else {
				depart_name="全校";
			}
			zhouBanji.setDepart_name(depart_name);
			zhouBanji.setBase(jishu);
			zhouBanji.setAttendence(chuqinlv);
			zhouBanji.setDisciplinerate(weijilv);
			zhouBanji.setId(class_id + "");
			zhouBanji.setName(name);
			list_BanJi.add(zhouBanji);
		}
	}

	private void banJiLianWang(final int a) {
		String jiekou;
		time1 = XueYuanHuiZong_Activity.date1.getText().toString().trim();
		time2 = XueYuanHuiZong_Activity.date1.getText().toString().trim();
		if ("".equals(XueYuanHuiZong_Activity.id)) {
			jiekou = JieKou.QUERY_XUEYUAN;
		} else {
			jiekou = JieKou.QUERY_BANJI;
		}
		Volley_LianWang.volley_Get(
				jiekou + Time + "&date1=" + time1 + "&date2=" + time2
						+ "&depart_id=" + XueYuanHuiZong_Activity.id
						+ "&token=" + JieKou.getSp(getActivity()),
				new WangLuo_HuiDiao() {
					@Override
					public void getData(String data) {
						Log.e("班级联网", data);
						try {
							if ("".equals(XueYuanHuiZong_Activity.id)) {
								JieXiXueYuan(data);
							} else {
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
				Log.e("一", arg0 + "");
				switch (arg0) {
				case 0:
					rb_bt1.setChecked(true);
					break;
				case 1:
					rb_zx.setChecked(true);
					break;
				case 2:
					rb_zhux.setChecked(true);
					break;
				case 3:
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
				case R.id.rb_zx:
					vp.setCurrentItem(1);
					break;
				case R.id.rb_zhux:
					vp.setCurrentItem(2);
					break;
				case R.id.rb_bt2:
					vp.setCurrentItem(3);
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
				if(!"1".equals(prev1)){
					if (zhou) {
						time1 = prev1;
						time2 = prev2;
						XueYuanHuiZong_Activity.date1.setText(prev1);
						XueYuanHuiZong_Activity.date2.setText(" 至" + prev2);
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
						time1 = next1;
						time2 = next2;
						XueYuanHuiZong_Activity.date1.setText(next1);
						XueYuanHuiZong_Activity.date2.setText(" 至" + next2);
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
							banJiLianWang(3);
						}
					}
				});
	}

	private void addView(int a) {// 往ViewPager里面添加View
		list = new ArrayList<View>();
		LayoutInflater inform = LayoutInflater.from(getActivity());
//		View v1 = View.inflate(getActivity(), R.layout.piechart, null);
		//PieChart pc = (PieChart) v1.findViewById(R.id.pc_id);
		if ("".equals(XueYuanHuiZong_Activity.id)) {
			if (list_XueYuan.size()>0) {
			list_XueYuan.remove(list_XueYuan.size()-1);
			v1 = new ZhuXingTuWeiJi(getActivity(), list_XueYuan);
			v3 = new ZhuXingTu(getActivity(), list_XueYuan);
			}
			else {
				v1=new TextView(getActivity());
				TextView v1t1=(TextView)v1;
				v1t1.setText("暂无记录");
				v1t1.setGravity(Gravity.CENTER);
				v3 = new ZhuXingTu(getActivity());
			}
			} else {
//			new PieChartActivity(
//					list_BanJi, pc);
			v1 = new ZhuXingTuWeiJi(getActivity(), list_BanJi);
			v3 = new ZhuXingTu(getActivity(), list_BanJi);
		}
		View v2 = inform.inflate(R.layout.zhexian_tu, null,false);
		LineChart lin=(LineChart) v2.findViewById(R.id.line);
		ZheXianTu lineTu = new ZheXianTu(list_ZheXian);
		lineTu.showChart(lin, lineTu.getLineData(2, 3), Color.rgb(114, 188, 223));
		View v4 = inform.inflate(R.layout.piechart, null, false);
		PieChart bt = (PieChart) v4.findViewById(R.id.pc_id);
		new PieChart_BingTu(list_BingTu, bt);
		list.add(v1);
		list.add(v2);
		list.add(v3);
		list.add(v4);
		vp.setAdapter(new Chart_PagerAdapter(list));
		vp.setCurrentItem(a);
		switch (a) {
		case 0:
			rb_bt1.setChecked(true);
			break;
		case 1:
			rb_zx.setChecked(true);
			break;
		case 2:
			rb_zhux.setChecked(true);
			break;
		case 3:
			rb_bt2.setChecked(true);
			break;
		default:
			break;
		}
	}

	private void initView() {
		zhou = true;
		if (XueYuanHuiZong_Activity.time == 2) {
			Time = "W";
			TimeO ="D";
		} else if (XueYuanHuiZong_Activity.time == 3) {
			Time = "M";
			TimeO = "W";
		} else if (XueYuanHuiZong_Activity.time == 4) {
			Time = "T";
			TimeO = "W";
		}
		XueYuanHuiZong_Activity.date2.setText("");
		vp = (ViewPager) v.findViewById(R.id.data_ri_vp);
		vp.setOffscreenPageLimit(0);
		rg_id = (RadioGroup) v.findViewById(R.id.rg_id);
		rb_bt1 = (RadioButton) v.findViewById(R.id.rb_bt1);
		rb_zx = (RadioButton) v.findViewById(R.id.rb_zx);
		rb_zhux = (RadioButton) v.findViewById(R.id.rb_zhux);
		rb_bt2 = (RadioButton) v.findViewById(R.id.rb_bt2);

	}
}