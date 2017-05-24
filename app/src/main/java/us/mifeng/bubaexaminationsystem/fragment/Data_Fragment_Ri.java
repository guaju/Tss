package us.mifeng.bubaexaminationsystem.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.activity.ClassOverall_Activity;
import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.adapter.BaseExListView_Ri;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.Classs;
import us.mifeng.bubaexaminationsystem.bean.CollageWeiJi;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.utils.TimeUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import us.mifeng.bubaexaminationsystem.utils.Volley_LianWang;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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

public class Data_Fragment_Ri extends Fragment {
	private View v;
	public static TextView tv_riqi;
	private ExpandableListView elv_id;
	private ArrayList<CollageWeiJi> myCollage;
	private CollageWeiJi collageWeiJi;
	private ArrayList<Classs> myClass;
	private Classs classs;
	private BaseExListView_Ri adapter;
	private String token;
	private String cycle;
	private String id1;
	private String date;
	private String searchdate;
	private String dateup;
	private String dateback;
	private boolean rilock;
	private TextView id_tv;
	private XueYuanHuiZong_Activity activity;
	private ProgressDialog progressDialog;
	private String xUEYUAN;
	private TextView curr_date1;
	// private TextView curr_date2;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				parseCollage(1,searchdate, searchdate, token, id1);
				break;
			case 1:
				adapter = new BaseExListView_Ri(getActivity(), myCollage,myClass);
				elv_id.setAdapter(adapter);
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				if (adapter.getGroupCount()==0) {
					id_tv.setVisibility(View.VISIBLE);
				}
				initEvent();
				break;
			case 2 :
				parseCollage(3,searchdate, searchdate, token, id1);
				break;
			case 3 :
				adapter.setList(myCollage, myClass);
				adapter.notifyDataSetChanged();
				
				if (activity.progressDialog.isShowing()) {
					activity.progressDialog.dismiss();
				}
				if (adapter.getGroupCount()==0) {
					id_tv.setVisibility(View.VISIBLE);
				}
				break;
			default:
				break;
			}
		}
	};


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = View.inflate(getActivity(), R.layout.fragmetn_ri, null);
		id_tv = (TextView) v.findViewById(R.id.id_tv);
		activity=(XueYuanHuiZong_Activity) getActivity();
		System.out.println("标记标记");
		initView();
		// 初始化数据
		initData(activity.id);
		return v;
	}

	private void initEvent() {
		elv_id.setOnGroupClickListener(new OnGroupClickListener() {
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if ("".equals(id1)) {
					//TODO
					//
					//如果是全校，那么就点击让他
					CollageWeiJi school = myCollage.get(groupPosition);
					if (school.getid()!=0) {
						int collegeId = school.getid();
						System.out.println("collegeId="+collegeId);
						activity.id=collegeId+"";
						initData(collegeId+"");
						
						
						//TODO
						//20160819
						activity.IS_IN_TOTAL=true;
						//id1="";
					}
					else {
						ToShow.show(getActivity(), "非法操作");
					}
					
				}
				return false;
			}
		});
		activity.left.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (TimeUtils.isFastDoubleClick()) {  
			        return;  
			    } 
				if (rilock) {
					searchdate = dateback;
					curr_date1.setText(dateback);
				}
				// curr_date2.setText("");
			}
		});
		activity.right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (TimeUtils.isFastDoubleClick()) {  
			        return;  
			    } 
				if (rilock) {
					searchdate = dateup;
					// curr_date2.setText("");
					curr_date1.setText(dateup);
//					myCollage.clear();
//					myClass.clear();
				}
			}
		});
		// bt_date.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// SetDateDialog sdd = new SetDateDialog();
		// FragmentManager fm = activity.fm;
		// sdd.show(fm, "hah");
		// }
		// });

		curr_date1.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {// 当时间变化后
				
				id_tv.setVisibility(View.INVISIBLE);
				
				if (!activity.progressDialog.isShowing()) {
					activity.progressDialog.show();
				}
				if ("".equals(id1)) {
					if(rilock){
						String string = curr_date1.getText().toString();
						Log.e("设置日期的时间是", string);
						searchdate = string;
						myClass = new ArrayList<Classs>();//班级不用联网了
						handler.sendEmptyMessage(2);
					}
				} else {
					if (rilock) {
						// curr_date2.setText("");
						String string = curr_date1.getText().toString();
						Log.e("设置日期的时间是", string);
						searchdate = string;
						parseClass(2,searchdate, searchdate, id1, token);//班级联网
					}
				}

			}
		});
		elv_id.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				String id2 = myClass.get(childPosition).getId();
				String class_name = myClass.get(childPosition).getClass_name();
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("id", id2);
				bundle.putString("class_name", class_name);
				bundle.putString("date1", searchdate);
				bundle.putString("xy_id", myCollage.get(groupPosition).getid()
						+ "");
				intent.putExtras(bundle);
				intent.setClass(getActivity(), ClassOverall_Activity.class);
				Log.e("传递的时间是", searchdate);
				startActivity(intent);
				return false;
			}
		});

	}

	private void parseClass(final int a,String date1, String date2, String id, String token) {
		String BANJI = JieKou.QUERY_BANJI;
		System.out.println("depart_id"+id);
		final String path = BANJI + cycle + "&date1=" + date1 + "&date2="
				+ date2 + "&depart_id=" + id + "&token=" + token;
		
				Volley_LianWang.volley_Get(path, new WangLuo_HuiDiao() {
					@Override
					public void getData(String data) {
						parse(data);
						handler.sendEmptyMessage(a);
					}
				}, getActivity());
	}

	private void parse(String str) {//解析班级
		try {
			System.out.println(str+"====测试中======");
			myClass = new ArrayList<Classs>();
			JSONObject object = new JSONObject(str);
			JSONObject jb = (JSONObject) object.get("data");
			JSONArray array = (JSONArray) jb.get("list");
			for (int i = 0; i < array.length(); i++) {
				classs = new Classs();
				JSONObject info = (JSONObject) array.get(i);
				String shiDaoBackUp = info.get("actual_attendance")+"";
				String yingDaoBackUp =  info.get("supposed_attendance")+"";
				String yingdao="";
				//20160811
				if (info.isNull("base_number")) {
					yingdao="--";
				}
				else {
					yingdao =info.get("base_number")+"";
				}
				int queQin=Integer.parseInt(yingDaoBackUp.trim())-Integer.parseInt(shiDaoBackUp.trim());
				String chuqin =  info.get("attendance_rate")+"";
				String weiji = info.get("violation")+"";
				String weijilv =  info.get("discipline_rate")+"";
				String classname =  info.get("class_name")+"";
				int id = Integer.parseInt(info.get("class_id")+"");
				System.out.println(classname);
				classs.setchuqinlv(chuqin + "%");
				classs.setClass_name(classname);
				//这里也是没有改值
				classs.setshidao(queQin+"");
				
				classs.setweiji(weiji);
				classs.setId(id + "");
				classs.setweijilv(weijilv + "%");
				
				classs.setyingdao(yingdao);
				myClass.add(classs);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initData(String id) {
		id1 = id;
		xUEYUAN = JieKou.QUERY_XUEYUAN;
		date = XueYuanHuiZong_Activity.date1.getText().toString().trim();
		searchdate = date;
		// curr_date2.setText("");
		
		id_tv.setVisibility(View.INVISIBLE);
		activity.progressDialog.show();
		
		if ("".equals(id1)) {
			myClass = new ArrayList<Classs>();
			handler.sendEmptyMessage(0);
		} else {
			parseClass(0,searchdate, searchdate, id1, token);
		}

	}

	private void parseCollage(final int a,String date1, String date2, String token,
			String id) {
		final String url = xUEYUAN + cycle + "&date1=" + date1 + "&date2="
				+ date2 + "&token=" + token + "&depart_id=" + id;
		System.out.println("2017-2-7"+url);
				Volley_LianWang.volley_Get(url, new WangLuo_HuiDiao() {

					@Override
					public void getData(String data) {
						parseJson(data);
						handler.sendEmptyMessage(a);
					}
				}, getActivity());
				
	}

	// 解析学院违纪率等信息
	private void parseJson(String str) {
		System.out.println("2017:标记标记"+str);
		try {
			System.out.println("201608111638:标记标记"+str);
			myCollage = new ArrayList<CollageWeiJi>();
			JSONObject jb = new JSONObject(str);
			JSONObject object = (JSONObject) jb.get("data");
			dateback = (String) object.get("prev_date1");
			dateup = (String) object.get("next_date2");
			JSONArray array = (JSONArray) object.get("list");
			for (int i = 0; i < array.length(); i++) {
				collageWeiJi = new CollageWeiJi();
				JSONObject info = (JSONObject) array.get(i);
				
			
				String yingdaoBackUp =info.get("supposed_attendance")+"";
				int yingDaoRenShu = Integer.parseInt(yingdaoBackUp.trim());
				String yingdao="";
				//20160811
				if (info.isNull("base_number")) {
					yingdao="--";
				}
				else {
					yingdao =info.get("base_number")+"";
				}
				String shidao = info.getString("actual_attendance")+"";
				int shiDaoRenShu=Integer.parseInt(shidao.trim());
				int queQinRenShu=yingDaoRenShu-shiDaoRenShu;
				String weiji = info.get("violation")+"";
				String chuqin =  info.get("attendance_rate")+"";
				String weijilv =  info.get("discipline_rate")+"";
				int id=0;
				if (id1.equals("")) {
					if (info.isNull("depart_id")) {
						id=0;
						collageWeiJi.setCollage_name("全校");
						collageWeiJi.setchuqinlv(chuqin);
						collageWeiJi.setweijilv(weijilv);
					}
					else {
						id = Integer.parseInt(info.get("depart_id")+"");
						if (id == 11) {
							collageWeiJi.setCollage_name("工商");
						}
						if (id == 12) {
							collageWeiJi.setCollage_name("建工");
						}
						if (id == 13) {
							collageWeiJi.setCollage_name("计算机");
						}
						if (id == 14) {
							collageWeiJi.setCollage_name("软工");
						}
						if (id == 15) {
							collageWeiJi.setCollage_name("数字");
						}
						if (id == 16) {
							collageWeiJi.setCollage_name("电商");
						}
						if (id == 17) {
							collageWeiJi.setCollage_name("移动");
						}
						if (id == 18) {
							collageWeiJi.setCollage_name("数据云");
						}
						
						collageWeiJi.setchuqinlv(chuqin+"%");
						collageWeiJi.setweijilv(weijilv+"%");
					}
					collageWeiJi.setid(id);
					//目前被缺勤取代，名字还没换，还是实到
					collageWeiJi.setshidao(queQinRenShu+"");
					//现在设置的学院实际的人数
					collageWeiJi.setyingdao(yingdao.trim());
					collageWeiJi.setweij(weiji);
					collageWeiJi.setMyclass(myClass);
					myCollage.add(collageWeiJi);
					
				
				}
			else {
				
				id = Integer.parseInt(info.get("depart_id")+"");
				if (id == 11) {
					collageWeiJi.setCollage_name("工商");
				}
				if (id == 12) {
					collageWeiJi.setCollage_name("建工");
				}
				if (id == 13) {
					collageWeiJi.setCollage_name("计算机");
				}
				if (id == 14) {
					collageWeiJi.setCollage_name("软工");
				}
				if (id == 15) {
					collageWeiJi.setCollage_name("数字");
				}
				if (id == 16) {
					collageWeiJi.setCollage_name("电商");
				}
				if (id == 17) {
					collageWeiJi.setCollage_name("移动");
				}
				if (id == 18) {
					collageWeiJi.setCollage_name("数据云");
				}
			collageWeiJi.setid(id);
			collageWeiJi.setchuqinlv(chuqin + "%");
			//目前被缺勤取代，名字还没换，还是实到
			collageWeiJi.setshidao(queQinRenShu+"");
			//现在设置的学院实际的人数
			collageWeiJi.setyingdao(yingdao.trim());
			collageWeiJi.setweij(weiji);
			collageWeiJi.setweijilv(weijilv + "%");
			collageWeiJi.setMyclass(myClass);
			myCollage.add(collageWeiJi);
			
		}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// private String getDate() {
	// SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	// String date = sDateFormat.format(new java.util.Date());
	// // tv_riqi.setText(date);
	// return date;
	// }
	private void initView() {
		rilock = true;
		XueYuanHuiZong_Activity.date2.setText("");
		activity = (XueYuanHuiZong_Activity) getActivity();
		curr_date1 = activity.date1;
		cycle = "D";
		// curr_date2 = activity.date2;
		token = JieKou.getSp(getActivity());
		elv_id = (ExpandableListView) v.findViewById(R.id.elv_id);
	}

	@Override
	public void onPause() {
		super.onPause();
		rilock = false;
	}

	@Override
	public void onStart() {
		super.onStart();
		rilock = true;

	}

}
