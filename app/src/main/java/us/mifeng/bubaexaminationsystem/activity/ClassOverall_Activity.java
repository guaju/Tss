package us.mifeng.bubaexaminationsystem.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.adapter.MyAp;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.app.PublicApplication;
import us.mifeng.bubaexaminationsystem.bean.KeShi_BanJI;
import us.mifeng.bubaexaminationsystem.bean.Weiji;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.app.Activity;
import android.app.ProgressDialog;
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

public class ClassOverall_Activity extends Activity {
	private ListView lv;
	private TextView banji_title;
	// private String c_id, c_name, c_time;
	private ArrayList<KeShi_BanJI> list;
	private ArrayList<ArrayList<Weiji>> weijiTotal;
	private ArrayList<Weiji> alist;
	private String date1;
	private String date2;
	private String id;
	private String class_name;
	private ListView lv_my;
	private String connect;
	private ProgressDialog progressDialog;
	JSONObject perJsonObject;
	private MyAdapter myAdapter;
	private String queqin;
	private String weiJiRenShu;
	private int queqinInt = 0;
	private String tingke="";
	int tingkeInt=0;
	private String wjrm;
	private String yingDao;
	private String shiDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classoverall_activity);
		progressDialog = PublicApplication
				.getProgressDialog(ClassOverall_Activity.this);

		requestNet();
	}

	private void requestNet() {
		new JanyTools() {
			@Override
			public void zhong() {
				list = new ArrayList<KeShi_BanJI>();
				try {
					System.out.println(date1);
					connect = ConnectInternet.connect(JieKou.QUERY_KESHI
							+ date1 + "&class_id=" + id + "&token="
							+ JieKou.getSp(ClassOverall_Activity.this));
					System.out.println(JieKou.QUERY_KESHI + date1
							+ "&class_id=" + id + "&token="
							+ JieKou.getSp(ClassOverall_Activity.this));

					System.out.println(connect);
					jieXi(connect);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void qian() {
				initView();
			}

			@Override
			public void hou() {
				System.out.println("列表的长度是222"+list.size());
				
				Collections.sort(list,new Comparator<KeShi_BanJI>() {

					@Override
					public int compare(KeShi_BanJI lhs, KeShi_BanJI rhs) {
						if (lhs.getKeShi()>rhs.getKeShi()) {
							return 1;
						}else if(lhs.getKeShi()<rhs.getKeShi()) {
							return -1;
						}
						else {
							return 0;
						}
					}
				});
				
				myAdapter = new MyAdapter(list);
				lv.setAdapter(myAdapter);
				progressDialog.dismiss();
			}
		}.open();
	}

	private void jieXi(String connect) throws JSONException {
		weijiTotal = new ArrayList<ArrayList<Weiji>>();
		queqinInt = 0;
		Log.e("周琪超", connect);
		JSONObject obj = new JSONObject(connect);
		JSONArray data = obj.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			tingkeInt=0;
			KeShi_BanJI keShi_BanJI = new KeShi_BanJI();
			JSONObject suoyin = data.getJSONObject(i);
			// overAllRecord 查课记录数组
			JSONObject oar = suoyin.getJSONObject("overAllRecord");
			String keshi = (String) oar.get("period");
			String class_id = (String) oar.get("class_id");
			String bzr = (String) oar.get("master_name");
			String rkls = (String) oar.get("teacher_name");
			yingDao = (String) oar.get("supposed_attendance");
			shiDao = (String) oar.get("actual_attendance");
			System.out.println(yingDao+"应道人数"+"实到人数是"+shiDao);
			// int queqinInt=Integer.parseInt(yingDao)-Integer.parseInt(shiDao);
			// if (queqinInt<=0) {
			// queqin = "";
			// }
			// else {
			// queqin="  |  缺勤人数："+queqinInt;
			// }
			String chuqinlv = (String) oar.get("attendance_rate");
			String weiJilv = (String) oar.get("discipline_rate");
			weiJiRenShu = (String) oar.get("violation");
			String ctime = (String) oar.get("create_time");
			String qingKuang = (String) oar.get("notes");

			JSONObject schedule = suoyin.getJSONObject("schedule");
			String jxjd = (String) schedule.get("content");

			JSONArray teacherRecordList = suoyin
					.getJSONArray("teacherRecordList");
			for (int j = 0; j < teacherRecordList.length(); j++) {
				JSONObject trl = teacherRecordList.getJSONObject(j);
				if (trl.has("notes")) {
					String notes = (String) trl.get("notes");
					keShi_BanJI.setLsqk("教师情况:" + notes);
				}
			}

			JSONArray studentRecordList = suoyin
					.getJSONArray("disciplineRecordList");
			alist = new ArrayList<Weiji>();
			weiJiRenShu = studentRecordList.length() + "";
			for (int h = 0; h < studentRecordList.length(); h++) {
				JSONObject srl = studentRecordList.getJSONObject(h);
				Weiji weiji = new Weiji();
				weiji.setKeshi(keshi);
				String wjrm = (String) srl.get("student_name");
				String wjqk = srl.get("disciplinarycategory_id")+"";
				
				if ("1000".equals(wjqk)) {
					queqinInt++;
					wjqk="旷课";
				}
				else if("2".equals(wjqk)){
					wjqk="睡觉";
				}
				else if("3".equals(wjqk)){
					wjqk="迟到";
				}
				else if("4".equals(wjqk)){
					wjqk="看视频";
				}
				else if("5".equals(wjqk)){
					wjqk="玩手机";
				}
				else if("6".equals(wjqk)){
					wjqk="打游戏";
				}
				else if("7".equals(wjqk.trim())){
					wjqk="吃东西";
				}
				else if("8".equals(wjqk)){
					wjqk="上厕所";
				}
				else if("9".equals(wjqk)){
					wjqk="带耳机";
				}
				else if("10".equals(wjqk)){
					wjqk="打闹聊天";
				}
				else if("11".equals(wjqk)){
					wjqk="不穿军装";
				}
				else if("99".equals(wjqk)){
					wjqk="其他违纪";
				}
				else if("13".equals(wjqk)){
					wjqk="旷课";
				}
				weiji.setQingkuang("违纪情况：" + wjqk);
				weiji.setName("违纪人名：" + wjrm);
				if (!wjqk.equals("0")) {
					alist.add(weiji);
				}
			}

			// 拿到缺勤人记录
			JSONArray studentRecord2List = suoyin
					.getJSONArray("absenceRecordList");
			for (int h = 0; h < studentRecord2List.length(); h++) {
				JSONObject srl = studentRecord2List.getJSONObject(h);
				
				if (srl.has("student_name")) {
					wjrm = (String) srl.get("student_name");
				}
				
				String wjqk = (String) srl.get("student_status");
				if ((!"1000".equals(wjqk))) {
					if (!"4".equals(wjqk)) {
						queqinInt=queqinInt+1;
					}else if("4".equals(wjqk)){
						tingkeInt=tingkeInt+1;
						wjqk="停课";
					}
					System.out.println(tingkeInt+"停课人数是");
					Weiji weiji2 = new Weiji();
					if ("2".equals(wjqk)) {
						wjqk="离校";
					}
//					if ("7".equals(wjqk)) {
//						wjqk="就业指导";
//					}
//					if ("8".equals(wjqk)) {
//						wjqk="毕业";
//					}
					else if ("3".equals(wjqk)) {
						wjqk="请假";
					}
					else if ("5".equals(wjqk)) {
						wjqk="三方请假";
					}
					else if ("9".equals(wjqk)) {
						wjqk="休学";
					}
					
					weiji2.setQingkuang("缺勤情况：" + wjqk);
					weiji2.setName("缺勤人名：" + wjrm);
					if (!wjqk.equals("0")&&(!wjqk.equals("7"))&&(!wjqk.equals("8"))) {
						alist.add(weiji2);
					}
				}
			}
			
			queqinInt=Integer.parseInt(yingDao)-Integer.parseInt(shiDao);
			
			queqin = "  |  缺勤人数：" + queqinInt;
			tingke = "  |  停课人数：" + tingkeInt;
			
			weijiTotal.add(alist);
			keShi_BanJI.setWeijiLists(alist);
			
			keShi_BanJI.setClass_id(class_id + "");
			keShi_BanJI.setTime("查课时间是：" + ctime);
			keShi_BanJI.setQingKuang("学生情况：" + qingKuang);
			keShi_BanJI.setTeacher("任课老师：" + rkls);
			keShi_BanJI.setJiaoxuejindu("教学进度：" + jxjd);
			keShi_BanJI.setClass_teacher("班主任：" + bzr);
			keShi_BanJI.setYingDao("应到人数：" + yingDao);
			keShi_BanJI.setShiDao("实到人数：" + shiDao + "");
			keShi_BanJI.setChuQinLv("出勤率：" + chuqinlv + "%");
			keShi_BanJI.setWeiJiLv("违纪率：" + weiJilv + "%");
			//keShi_BanJI.setKeShi("第 " + keshi + " 节 课");
			keShi_BanJI.setKeShi(Integer.parseInt(keshi));
			keShi_BanJI.setWeiJiRenShu("违纪人数：" + weiJiRenShu);
			keShi_BanJI.setQueqin(queqin);
			//TODO
			keShi_BanJI.setTingke(tingke);
			list.add(keShi_BanJI);
		}
		System.out.println("列表的长度是"+list.size());
	}

	@Override
	protected void onRestart() {
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		super.onRestart();
	}

	// @Override
	// protected void onResume() {
	// if(progressDialog.isShowing()){
	// progressDialog.dismiss();
	// }
	// super.onResume();
	// }
	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		banji_title = (TextView) findViewById(R.id.banji_title);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		date1 = bundle.getString("date1");
		id = bundle.getString("id");
		class_name = bundle.getString("class_name");
		// xy_id = bundle.getString("xy_id");
		System.out.println(date1 + "---" + date2);
		banji_title.setText(class_name);
		Log.e("接到的数据", id);
		initEvent();
	}

	private void initEvent() {

		Log.e("heeeeeeee", "调用了");
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("heeeeeeee", "点击了listview了");
				Intent intent2 = new Intent(ClassOverall_Activity.this,
						ClassInput_Activity.class);
				KeShi_BanJI keShi_BanJI = list.get(position);
				String keShiTemp = keShi_BanJI.getKeShi()+"";
				String class_id = keShi_BanJI.getClass_id();
				String time = keShi_BanJI.getTime();
//				try {
//					JSONObject jo = new JSONObject(connect);
//					JSONArray dataArray = (JSONArray) jo.get("data");
//					//perJsonObject = (JSONObject) dataArray.get(position);
//				for (int i = 0; i < dataArray.length(); i++) {
//						JSONObject tempJo = dataArray.getJSONObject(i).getJSONObject("overAllRecord");
//						String tempString=tempJo.getString("period");
//						if (tempString.equals(keShiTemp)) {
//							position=i;
//							System.err.println(i+"这是第几个");
//						}
//						perJsonObject=dataArray.getJSONObject(position);
//				}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
				time=time.replace("查课时间是：", "");
				time=time.substring(0, 10);
				System.out.println(keShi_BanJI.getKeShi()+"时间是"+time);
//				System.out.println(perJsonObject.toString());
				intent2.putExtra("period",keShiTemp);
				intent2.putExtra("class_id",class_id);
				intent2.putExtra("time", time);
				
				
				
				int type = JieKou.getType(ClassOverall_Activity.this);
				if (type == 1100 || type == 1200) {
					ToShow.show(ClassOverall_Activity.this, "并没有权限- -");
				} else {
					startActivityForResult(intent2, 888);
				}
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
			}
		});

	}

	public void fanhui(View v) {
		finish();
	}

	public class MyAdapter extends BaseAdapter {
		ArrayList<KeShi_BanJI> mList;
		public MyAdapter(ArrayList<KeShi_BanJI> list){
			mList=list;
		}
		
		private Weiji remove;
		private ArrayList<Weiji> weijiCurrent;
		private LinkedList<Weiji> weijiCurrent22;
		
		@Override
		public int getCount() {
			return mList.size();
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
		public View getView(int position, View arg1, ViewGroup arg2) {
			View v = View.inflate(ClassOverall_Activity.this,
					R.layout.class_overall_fujian, null);
			TextView tv_class_teacher = (TextView) v
					.findViewById(R.id.tv_class_teacher);
			TextView tv_teacher = (TextView) v.findViewById(R.id.tv_teacher);
			TextView tv_jxjd = (TextView) v.findViewById(R.id.tv_jxjd);
			TextView tv_weijiren = (TextView) v.findViewById(R.id.tv_weijiren);
			TextView tv_weijilv = (TextView) v.findViewById(R.id.tv_weijilv);
			TextView tv_chuqinlv = (TextView) v.findViewById(R.id.tv_chuqinlv);
			TextView tv_student_condition = (TextView) v
					.findViewById(R.id.tv_student_condition);
			TextView tv_teacher_condition = (TextView) v
					.findViewById(R.id.tv_teacher_condition);
			TextView tv_yingdao = (TextView) v.findViewById(R.id.tv_yingdao);
			TextView tv_ctime = (TextView) v.findViewById(R.id.tv_ctime);
			TextView tv_keshi = (TextView) v.findViewById(R.id.tv_keshi);
			TextView tv_shidao = (TextView) v.findViewById(R.id.tv_shidao);
			lv_my = (ListView) v.findViewById(R.id.lv_mylv);
			KeShi_BanJI currentBean = mList.get(position);
			//collections
			//Collections.sort(weijiTotal,new Comparator<ArrayList<Weiji>>() {
			//	@Override
			//public int compare(ArrayList<Weiji> listL, ArrayList<Weiji> listF) {
			//		return listL.get(0).getKeshi().compareTo(listF.get(0).getKeshi());
			//	}}
			//);
			
			
			//TODO 
				//WJL
				weijiCurrent = currentBean.getWeijiLists();
				weijiCurrent22 = new LinkedList<Weiji>();

				if (weijiCurrent != null) {
					
					int temp=1;
					for (int i = 0; i < weijiCurrent.size(); i++) {
						System.out.println(weijiCurrent.get(i).getQingkuang());
						if (weijiCurrent.get(i).getQingkuang()
								.equals("违纪情况：旷课")) {
							remove = weijiCurrent.get(i);
							remove.setTag(-1);
							weijiCurrent22.addFirst(remove);
						}
						else if (!weijiCurrent.get(i).getQingkuang()
								.equals("缺勤情况：停课")) {
							weijiCurrent.get(i).setTag(temp++);
							weijiCurrent22.add(weijiCurrent.get(i));
						}else {
							weijiCurrent.get(i).setTag(Integer.MAX_VALUE);
							weijiCurrent22.addLast(weijiCurrent.get(i));
						}
						Collections.sort(weijiCurrent22, new Comparator<Weiji>() {
							@Override
							public int compare(Weiji lhs, Weiji rhs) {
								if (lhs.getTag()>rhs.getTag()) {
									return 1;
								}
								else if (lhs.getTag()<rhs.getTag()) {
									return -1;
								}
								else {
									return  0;
								}
							}
						});
					}
					lv_my.setAdapter(new MyAp(ClassOverall_Activity.this,
							weijiCurrent22));

					tv_teacher.setText(currentBean.getTeacher());
					tv_class_teacher.setText(currentBean.getClass_teacher());
					
					tv_weijiren.setText(currentBean.getWeiJiRenShu()
							+ currentBean.getQueqin()+currentBean.getTingke());
					
					tv_jxjd.setText(currentBean.getJiaoxuejindu());
					tv_weijilv.setText(currentBean.getWeiJiLv());
					tv_chuqinlv.setText(currentBean.getChuQinLv());
					tv_student_condition.setText(currentBean.getLsqk());
					tv_teacher_condition.setText(currentBean.getQingKuang());
					tv_yingdao.setText(currentBean.getYingDao());
					tv_shidao.setText(currentBean.getShiDao());
					tv_ctime.setText(currentBean.getTime());
					tv_keshi.setText("第 " + currentBean.getKeShi() + " 节 课");
					
			}
				
				return v;
//				ToShow.show(ClassOverall_Activity.this, "数据录入错误！");
//				return View.inflate(ClassOverall_Activity.this, R.layout.error,
//						null);
		
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			final Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (requestCode == 888) {
				if (myAdapter != null && lv != null) {
					new JanyTools() {
						@Override
						public void zhong() {
							list = new ArrayList<KeShi_BanJI>();
							try {
								System.out.println(date1);
								connect = ConnectInternet
										.connect(JieKou.QUERY_KESHI
												+ date1
												+ "&class_id="
												+ id
												+ "&token="
												+ JieKou.getSp(ClassOverall_Activity.this));
								System.out
										.println(JieKou.QUERY_KESHI
												+ date1
												+ "&class_id="
												+ id
												+ "&token="
												+ JieKou.getSp(ClassOverall_Activity.this));

								System.out.println(connect);
								jieXi(connect);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						@Override
						public void qian() {
							initView();
							if (!progressDialog.isShowing()) {
								progressDialog=CommonUtils.getProgressDialog(ClassOverall_Activity.this);
							}
						}
						@Override
						public void hou() {
							for (KeShi_BanJI banji : list) {
								System.out.println(banji.getKeShi()+"排序前");
							}
							//打印一遍list
							System.out.println(list.size());
							for (KeShi_BanJI banji : list) {
								System.out.println(banji.toString());
							}
							
							
							
							
							Collections.sort(list,new Comparator<KeShi_BanJI>() {
								@Override
								public int compare(KeShi_BanJI lhs, KeShi_BanJI rhs) {
									if (lhs.getKeShi()>rhs.getKeShi()) {
										return 1;
									}else if(lhs.getKeShi()<rhs.getKeShi()) {
										return -1;
									}
									else {
										return 0;
									}
								}
							});
							for (KeShi_BanJI banji : list) {
								System.out.println(banji.getKeShi()+"排序后");
							}
							myAdapter = new MyAdapter(list);
							lv.setAdapter(myAdapter);
							int intExtra = data.getIntExtra("position", 0);
							if (intExtra != 0) {
								lv.setSelection(intExtra);
								if (progressDialog.isShowing()) {
									progressDialog.dismiss();
								}
							}
						}
					}.open();

				}
			}
		}
	}
}
