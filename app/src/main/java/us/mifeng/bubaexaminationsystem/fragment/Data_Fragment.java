package us.mifeng.bubaexaminationsystem.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.WeiJiOfWeek;
import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.adapter.BaseExListView_Zhou;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.app.PublicApplication;
import us.mifeng.bubaexaminationsystem.bean.CollageWeiJi;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.bean.ZhouXueyuan;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.TimeUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import us.mifeng.bubaexaminationsystem.utils.Volley_LianWang;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

public class Data_Fragment extends Fragment {
	private ArrayList<View> list;
	public static TextView tv_riqi;
	private ExpandableListView elv_id;
	private ArrayList<ZhouBanji> list_BanJi;
	private ArrayList<ZhouXueyuan> list_XueYuan;
	private BaseExListView_Zhou myBase;
	private String curr_date1, curr_date2;
	private String prev, prev2;
	private String next, next2, Time = "W";
	private boolean zhou;
	private Handler ha = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				new Thread(new Runnable() {
					@Override
					public void run() {
						erLianWang();
						ha.sendEmptyMessage(1);
					}
				}).start();
				break;
			case 1:
				myBase = new BaseExListView_Zhou(getActivity(), list_XueYuan,
						list_BanJi);
				elv_id.setAdapter(myBase);
				dismissDialog();
				
				jianTing();
				break;
			case 2:
				new Thread(new Runnable() {
					@Override
					public void run() {
						erLianWang();
						ha.sendEmptyMessage(3);
					}
				}).start();
				break;
			case 3:
				shuaXin();
				break;
			default:
				break;
			}
		}

		
	};
	private TextView id_tv;
	private XueYuanHuiZong_Activity activity;

	
	private void dismissDialog() {
		if (activity.progressDialog.isShowing()) {
			activity.progressDialog.dismiss();
		}
		if (myBase.getGroupCount()==0) {
			id_tv.setVisibility(View.VISIBLE);
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View vie = View.inflate(getActivity(), R.layout.fragmetn_zhou, null);
		elv_id = (ExpandableListView) vie.findViewById(R.id.elv_id);
		activity = (XueYuanHuiZong_Activity) getActivity();
		id_tv = (TextView) vie.findViewById(R.id.id_tv);
		
		
		initTime();
		// XueYuanHuiZong_Activity.date1.setText(data());
		// XueYuanHuiZong_Activity.date2.setText("");
		String time = XueYuanHuiZong_Activity.date1.getText().toString().trim();
		initEvent();
		initStartData(time,XueYuanHuiZong_Activity.id);
		return vie;
	}
	private void initStartData(String time,String id) {
		if (zhou) {
			id_tv.setVisibility(View.INVISIBLE);
			activity.progressDialog.show();
			Volley_LianWang.volley_Get(
					JieKou.QUERY_XUEYUAN + Time + "&date1=" + time + "&date2="
							+ time + "&depart_id=" + id
							+ "&token=" + JieKou.getSp(getActivity()),
					new WangLuo_HuiDiao() {
						@Override
						public void getData(String data) {
							try {
								Log.e("教学周期", data);
								JieXiXueYuan(data);
							} catch (JSONException e) {
								e.printStackTrace();
							}
							ha.sendEmptyMessage(0);
						}
					}, getActivity());
			XueYuanHuiZong_Activity.id=id;
		}
	}

	private void initEvent() {
		elv_id.setOnGroupClickListener(new OnGroupClickListener() {
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if ("".equals(XueYuanHuiZong_Activity.id)) {
					//TODO
					//
					//如果是全校，那么就点击让他
					 ZhouXueyuan zhouXueyuan = list_XueYuan.get(groupPosition);
					 System.out.println(zhouXueyuan.toString());
					 
					if (zhouXueyuan.getid()!=0) {
						int collegeId = zhouXueyuan.getid();
						System.out.println("collegeId="+collegeId);
						initStartData(XueYuanHuiZong_Activity.date1
								.getText().toString().trim(), collegeId+"");
						
						//TODO
						//20160819
						activity.IS_IN_TOTAL=true;
						
					}
					else {
						ToShow.show(getActivity(), "非法操作");
					}
					
				}
				return false;
			}
		});
	}
	private void initTime() {
		XueYuanHuiZong_Activity.date2.setText("");
		zhou = true;
		if (XueYuanHuiZong_Activity.time == 2) {
			Time = "W";
		} else if (XueYuanHuiZong_Activity.time == 3) {
			Time = "M";
		} else if (XueYuanHuiZong_Activity.time == 4) {
			Time = "T";
		}
	}

	private void shuaXin() {// 刷新界面
		myBase = new BaseExListView_Zhou(getActivity(), list_XueYuan,
				list_BanJi);
		elv_id.setAdapter(myBase);
		if (activity.progressDialog.isShowing()) {
			activity.progressDialog.dismiss();
		}
		if (myBase.getGroupCount()==0) {
			id_tv.setVisibility(View.VISIBLE);
		}
		// ZheXian();
		// ZhuXing();
	};

	private void erLianWang() {// 班级联网提取
		if (!"".equals(XueYuanHuiZong_Activity.id)) {
			lianWangBanji();
		} else {
			list_BanJi = new ArrayList<ZhouBanji>();
		}
	}

	private void jianTing() {// 设置监听事件
		XueYuanHuiZong_Activity.left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (TimeUtils.isFastDoubleClick()) {  
			        return;  
			    } 
				if(!"1".equals(prev2)){
					if (zhou) {
						XueYuanHuiZong_Activity.date1.setText(prev);
						XueYuanHuiZong_Activity.date2.setText(" 至 " + prev2);
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
				
				
				if(!"1".equals(next2)){
					if (zhou) {
						XueYuanHuiZong_Activity.date1.setText(next);
						XueYuanHuiZong_Activity.date2.setText(" 至 " + next2);
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
							Log.e("我是周页面", "时间发生变化了");
							String time = XueYuanHuiZong_Activity.date1
									.getText().toString().trim();
							id_tv.setVisibility(View.INVISIBLE);
							if (!activity.progressDialog.isShowing()) {
								activity.progressDialog.show();
							}
							Volley_LianWang.volley_Get(JieKou.QUERY_XUEYUAN
									+ Time + "&date1=" + time + "&date2="
									+ time + "&depart_id="
									+ XueYuanHuiZong_Activity.id + "&token="
									+ JieKou.getSp(getActivity()),
									new WangLuo_HuiDiao() {
										@Override
										public void getData(String data) {
											Log.e("学院信息", data);
											try {
												JieXiXueYuan(data);
											} catch (JSONException e) {
												e.printStackTrace();
											}
											ha.sendEmptyMessage(2);
										}
									}, getActivity());
						}
					}
				});
		elv_id.setOnChildClickListener(new OnChildClickListener() {//listView的点击事件
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent inten = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("curr1", curr_date1);
				bundle.putString("curr2", curr_date2);
				bundle.putString("id", list_BanJi.get(childPosition).getId());
				String xy_id = XueYuanHuiZong_Activity.id;
				bundle.putString("xy_id", xy_id);
				if (XueYuanHuiZong_Activity.time == 2) {
					bundle.putString("Time", "D");
				} else if (XueYuanHuiZong_Activity.time == 3) {
					bundle.putString("Time", "W");
				} else if (XueYuanHuiZong_Activity.time == 4) {
					bundle.putString("Time", "W");
				}
				inten.putExtras(bundle);
				inten.setClass(getActivity(), WeiJiOfWeek.class);
				startActivity(inten);
				return false;
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
	private void jieXiBanJI(String connect) throws JSONException {// 解析班级信息
		list_BanJi = new ArrayList<ZhouBanji>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		curr_date1 =  data.get("curr_date1")+"";
		curr_date2 =  data.get("curr_date2")+"";
		JSONArray jslist = data.getJSONArray("list");
		for (int i = 0; i < jslist.length(); i++) {
			ZhouBanji zhouBanji = new ZhouBanji();
			JSONObject suoyin = jslist.getJSONObject(i);
			String name =  suoyin.get("class_name")+"";
			String jishu =  suoyin.get("supposed_attendance")+"";
			String chuqinlv =  suoyin.get("attendance_rate")+"";
			String weijilv =  suoyin.get("discipline_rate")+"";
			int class_id =  Integer.parseInt(suoyin.get("class_id")+"");
			zhouBanji.setBase(jishu);
			String depart_name = (String) suoyin.get("depart_name");
			zhouBanji.setDepart_name(depart_name);
			zhouBanji.setAttendence(chuqinlv);
			zhouBanji.setDisciplinerate(weijilv);
			zhouBanji.setId(class_id + "");
			zhouBanji.setName(name);
			list_BanJi.add(zhouBanji);
			Log.e(name, list_BanJi.size() + "");
		}
	}
	private void JieXiXueYuan(String connect) throws JSONException {// 解析学院
		list_XueYuan = new ArrayList<ZhouXueyuan>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		prev = (String) data.get("prev_date1");
		prev2 = (String) data.get("prev_date2");
		if(!data.isNull("")){
			curr_date1 = (String) data.get("curr_date1");
			curr_date2 = (String) data.get("curr_date2");
		}else{
			curr_date1 = "2105-06-01"; 
			curr_date1 = "2105-06-01";
		}
		next = (String) data.get("next_date1");
		next2 = (String) data.get("next_date2");
		
//		XueYuanHuiZong_Activity.date1.setText(curr_date1);
//		XueYuanHuiZong_Activity.date2.setText(" 至 " + curr_date2);
		
		JSONArray jslist = data.getJSONArray("list");
		for (int i = 0; i < jslist.length(); i++) {
			JSONObject suoyin = jslist.getJSONObject(i);
			ZhouXueyuan zhouXueyuan = new ZhouXueyuan();
			String jishu = suoyin.get("supposed_attendance")+"";
			String chuqinlv =  suoyin.get("attendance_rate")+"";
			String weijilv =  suoyin.get("discipline_rate")+"";
			if (suoyin.isNull("depart_id")&&jslist.length()>2) {
				zhouXueyuan.setid(0);
				zhouXueyuan.setXueyuan_name("全校");
				list_XueYuan.add(zhouXueyuan);
				zhouXueyuan.setAttendance(chuqinlv);
				zhouXueyuan.setDisciplinerate(weijilv);
				zhouXueyuan.setJiShu(jishu);
			}else {
				if ((!suoyin.isNull("depart_name"))&&(!suoyin.isNull("depart_id"))) {
					String xueyuan_name =  suoyin.get("depart_name")+"";
					String id=suoyin.get("depart_id")+"";
					if ("工商学院".equals(xueyuan_name.trim())) {
						xueyuan_name="工商管理学院";
					}
					zhouXueyuan.setXueyuan_name(xueyuan_name);
					zhouXueyuan.setid(Integer.parseInt(id.trim()));
					list_XueYuan.add(zhouXueyuan);
					zhouXueyuan.setAttendance(chuqinlv);
					zhouXueyuan.setDisciplinerate(weijilv);
					zhouXueyuan.setJiShu(jishu);
				}
				
			}
			
		}
	}

	public void lianWangBanji() {// 班级的联网
		try {
			String time = XueYuanHuiZong_Activity.date1.getText().toString()
					.trim();
			String connect = ConnectInternet.connect(JieKou.QUERY_BANJI + Time
					+ "&date1=" + time + "&date2=" + time + "&depart_id="
					+ XueYuanHuiZong_Activity.id + "&token="
					+ JieKou.getSp(getActivity()));
			Log.e("班级信息", connect);
			jieXiBanJI(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private class MyPagerAd extends PagerAdapter {// ViewPager的Adapter
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));
		}
	}
	
	
	
}
