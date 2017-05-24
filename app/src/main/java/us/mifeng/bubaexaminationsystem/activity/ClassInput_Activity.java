package us.mifeng.bubaexaminationsystem.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import us.mifeng.bubaexaminationsystem.adapter.LaoShi_Adapter;
import us.mifeng.bubaexaminationsystem.adapter.Student_Adapter;
import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.app.PublicApplication;
import us.mifeng.bubaexaminationsystem.bean.BanJi_Student;
import us.mifeng.bubaexaminationsystem.bean.BanJi_Teacher;
import us.mifeng.bubaexaminationsystem.bean.StudentRecord;
import us.mifeng.bubaexaminationsystem.bean.TeacherRecord;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import us.mifeng.bubaexaminationsystem.utils.Volley_LianWang;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class ClassInput_Activity extends Activity implements OnClickListener {
	//2017-2-5
	private ImageButton ib_jian_weijinum,ib_add_weijinum;
	private Handler mHandler=new Handler();
	private String neededUrl = JieKou.BANJI_TIJIAO;
	private TextView tv_chuqinlv, tv_kecheng, tv_weijiren, tv_weijilv;
	private GridView gv_student, gv_laoshi;
	private EditText et_student, et_sdrs, et_ydrs;
	private String student, sdrs, period;
	private Spinner sp_keshi;
	private StringBuffer student_Weiji = new StringBuffer();
	private StringBuffer laoShi_QingKuang = new StringBuffer();
	private String connect, keshi;
	private String banji_id, xueyuan_id;
	private ArrayList<BanJi_Student> list_student;
	private ArrayList<BanJi_Teacher> list_laoshi;
	private TextView banji_title;
	private String banji_name;
	private int huise;
	private double lv;
	private Boolean ADDTAG = false;
	private ArrayList<StudentRecord> tempList;
	private int weiji_RenShu = 0;
	private int qingJia_RenShu = 0;
	private int yingdao_RenShu = 1;
	private double weijilv;
	private boolean netWork;
	private String fQingKuang = "", fBianHao, fZhuangTai, fzwBianHao,
			flsQingKuang = "", flsBianHao, flsZhuangTai;
	private String jiaozhou_id;
	private HashMap<String, String> hashMap;
	private JSONObject json;
	String class_id;
	int teachingPeriod_id;
	private String kechengContent;
	private String actual_attendance;
	private String attendance_rate;
	private int violation;
	//2017-2-5新增扩展违纪人数
	private int ext_violation;
	
	private String discipline_rate;
	private String notes;
	private String supposed_attendance;
	private EditText ed_ydrs;
	private String create_time;
	private TextView tv_recorded_time;
	private ArrayList<StudentRecord> studentRecordLists = new ArrayList<StudentRecord>();
	private HashMap<Integer, StudentRecord> studentRecordMap = new HashMap<Integer, StudentRecord>();
	private ProgressDialog progressDialog;
	private ArrayList<TeacherRecord> teacherRecordLists = new ArrayList<TeacherRecord>();;
	private JSONObject overAllRecord;
	private String date;
	private Boolean isKuangke = false;
	private HashMap<Integer, Integer> studentStadus = new HashMap<Integer, Integer>();
	private String studentNotes;
	private Handler myHandler = new Handler();
	private String roles;
	private Intent intenBack;
	private Student_Adapter student_Adapter;
	private ScrollView sv;
	private JSONObject jo;

	private int supposed_attendanceInt;
	private LaoShi_Adapter laoShi_Adapter;
	private String yingdaotrim;
	private double weijilv2;

	
	private String time;
	private ProgressDialog pd2;
	private EditText et_changed_weijinum;

	private int weijirenshuback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amend_activity);
		sv = (ScrollView) findViewById(R.id.scroll_view);
		progressDialog = PublicApplication
				.getProgressDialog(ClassInput_Activity.this);
		if (getIsHasData()) {
			initView();
			period=getIntent().getStringExtra("period");
			time = getIntent().getStringExtra("time");
			class_id=getIntent().getStringExtra("class_id");
			// 找id
//			String stringExtra = getIntent().getStringExtra("jsonString");
//			try {
//				jo = new JSONObject(stringExtra);
//				overAllRecord = (JSONObject) jo.get("overAllRecord");
//				date = overAllRecord.getString("date");
//				period = overAllRecord.getString("period");
//				weiji_RenShu = Integer.parseInt(overAllRecord
//						.getString("violation").trim());
//				discipline_rate = overAllRecord.getString("discipline_rate");
//				System.out.println(discipline_rate + "----------");
//				xueyuan_id = overAllRecord.getString("depart_id");
//				banji_id = overAllRecord.getString("class_id");
//
//				addToRecord("disciplineRecordList");
//				addToRecord("absenceRecordList");
//				System.out.println(studentRecordMap.size() + "1hehehe2"
//						+ studentStadus.size());
//				JSONArray teacherRecordList = jo
//						.getJSONArray("teacherRecordList");
//
//				for (int i = 0; i < teacherRecordList.length(); i++) {
//					JSONObject teacherRecord = (JSONObject) teacherRecordList
//							.get(i);
//					TeacherRecord teacherRecord2 = new TeacherRecord();
//					teacherRecord2.setTeacher_name(teacherRecord
//							.getString("teacher_name"));
//					teacherRecord2.setTeacher_role(teacherRecord
//							.getInt("teacher_role"));
//					teacherRecord2.setOverallrecord_id(teacherRecord
//							.getInt("overallrecord_id"));
//					teacherRecord2.setNotes(teacherRecord.getString("notes"));
//					teacherRecord2.setId(teacherRecord.getInt("id"));
//					teacherRecord2.setTeacher_id(teacherRecord
//							.getInt("teacher_id"));
//					teacherRecord2.setTeacher_status(teacherRecord
//							.getInt("teacher_status"));
//					teacherRecordLists.add(teacherRecord2);
//
//				}
//				JSONObject scheduleObject = (JSONObject) jo.get("schedule");
//				class_id = (String) scheduleObject.get("class_id");
//				teachingPeriod_id = scheduleObject.getInt("teachingPeriod_id");
//				kechengContent = scheduleObject.getString("content");
//				JSONObject overAllRecordObject = (JSONObject) jo
//						.get("overAllRecord");
//				actual_attendance = overAllRecordObject
//						.getString("actual_attendance");
//				attendance_rate = overAllRecordObject
//						.getString("attendance_rate");
//
//				violation = overAllRecordObject.getInt("violation");
//				notes = overAllRecordObject.getString("notes");
//				supposed_attendance = overAllRecordObject
//						.getString("supposed_attendance");
//				create_time = overAllRecordObject.getString("create_time");
//				Log.e("wjl+++", supposed_attendance);
//				// 获取数据
//				String url = JieKou.BANJI_XUESHENG + class_id + "&period="
//						+ period + "&token="
//						+ JieKou.getSp(ClassInput_Activity.this);
//				Log.e("wjl++++++++", url);
//				Listener<String> listener = new Response.Listener<String>() {
//
//					@Override
//					public void onResponse(String connect) {
//						try {
//							getList(connect);
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}// 解析JSOn数据
//							// TODO
//							// 遍历map集合
//						outStudentRecordMap();
//
//						// 更新学生的数据。展示是否缺勤违纪
//						for (int i = 0; i < list_student.size(); i++) {
//							// System.out.println(list_student.get(i).getName()+list_student.get(i).getBianHao());
//							int parseInt = Integer.parseInt(list_student.get(i)
//									.getBianHao().trim());
//							if (studentRecordMap.containsKey(parseInt)) {
//								System.out.println("todo相同的要对比了");
//								BanJi_Student student = list_student.get(i);
//								if ((studentRecordMap.get(parseInt)
//										.getStudent_status() == 9)
//										|| (studentRecordMap.get(parseInt)
//												.getStudent_status() == 3)
//										|| (studentRecordMap.get(parseInt)
//												.getStudent_status() == 4)) {
//									System.out.println("删除啦~");
//									studentRecordMap.remove(parseInt);
//								} else {
//									student.setDisciplinarycategory(studentRecordMap
//											.get(Integer.parseInt(student
//													.getBianHao().trim()))
//											.getDisciplinarycategory_id()
//											.get(0));
//								}
//							}
//						}
//						
//						//更新学生的状态以及处理上传时候的数据
//						updateInsertDataAndStudentStatus();
					
			//20160824
			String url = JieKou.QUERY_DETAIL + class_id + "&period="
					+ period + "&date="+time+"&token="
					+ JieKou.getSp(ClassInput_Activity.this);
			Log.e("wjl++++++++", url);
			Listener<String> listener = new Response.Listener<String>() {

				@Override
				public void onResponse(String connect) {
					
						try {
							parseRecordList(connect);
							System.out.println(list_laoshi==null?"老师的列表现在是空":"老师的列表现在不是空 ");
							gLAdapter();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//	caculateAttendenceAndActual();
						
						banji_title.setText(banji_name);
						sp_keshi.setSelection(Integer.parseInt(period.trim()) - 1);
						tv_kecheng.setText("课程是：" + kechengContent);
						tv_chuqinlv.setText("出勤率" + attendance_rate + "%");
						System.out.println(violation+"--后边的是存入违纪map的大小"+studentRecordMap.size());
						if (violation != studentRecordMap.size()) {
							ToShow.show(ClassInput_Activity.this,
									"录入违纪与计算违纪不符，将展示计算的违纪情况");
							violation = studentRecordMap.size();
						}
						et_ydrs.setText(supposed_attendance);
						et_sdrs.setText(actual_attendance);
						et_student.setText(notes);
						
						//tv_weijiren.setText("违纪人数" + violation + "人");
						
						//设置修改违纪人数的默认值  2017-2-5
						et_changed_weijinum.setText(violation+ext_violation+"");
						
						tv_weijilv.setText("违纪率" + discipline_rate + "%");
						et_student.setText(notes);
						tv_recorded_time.setVisibility(View.VISIBLE);
						tv_recorded_time.setText("创建时间" + create_time);
						System.out.println(create_time);
						progressDialog.dismiss();

						// keCheng();

						shiDao();
						gvJianTing();
					}
				};
				ErrorListener errorListener = new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						ToShow.show(ClassInput_Activity.this, "网络异常，错误码004");
					}
				};
				StringRequest stringRequest = new StringRequest(url, listener,
						errorListener);

				PublicApplication.getRequestQueue(ClassInput_Activity.this)
						.add(stringRequest);

//			}
//				catch (JSONException e) {
//				e.printStackTrace();
//			}
			tv_weijilv.setText("违纪率" + discipline_rate + "%");
		} else {
			JanyTools janyTools = new JanyTools() {
				public void zhong() {
					// 获取数据
					if (netWork) {// 如果有网络连接就请求数据
						try {
							Log.e("wjl+++",
									JieKou.BANJI_XUESHENG
											+ banji_id
											+ "&period=1"
											+ "&token="
											+ JieKou.getSp(ClassInput_Activity.this));
							connect = ConnectInternet
									.connect(JieKou.BANJI_XUESHENG
											+ banji_id
											+ "&period=1"
											+ "&token="
											+ JieKou.getSp(ClassInput_Activity.this));
							if ("".equals(connect)) {
								runOnUiThread(new Runnable() {
									public void run() {
										ToShow.show(ClassInput_Activity.this, "暂无数据");
										
									}
								});
								finish();

							}
						} catch (IOException e) {// 联网获取JSON数据
							e.printStackTrace();
						}
						try {
							getList(connect);// 解析JSOn数据
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}

				public void qian() {
					initView();
					// keCheng();
					// 找id
				}

				public void hou() {
					if (netWork) {// 在有网络请求的情况下才有操作

						caculateAttendenceAndActual();
						keCheng();
						shiDao();
						gLAdapter();
						gvJianTing();
						initSheZhi();
					}
				}
			};
			janyTools.open();
		
			;}
		// scrollview抗干扰
		sv.smoothScrollTo(0, 0);// 平滑的滚动到一个位置
		// sv.smoothScrollBy(20, 20);//移动的一个距离
		sv.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);// 定义scrollview里面的聚焦模式：这句代码的意思就是阻止里面的view获取焦点，让自己先获取焦点
		sv.setFocusable(true);
		sv.setFocusableInTouchMode(true);
		sv.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				v.requestFocusFromTouch();
				return false;
			}
		});

	}

	protected void parseRecordList(String connect2) throws JSONException {
		JSONObject jo=new JSONObject(connect2);
		JSONObject joData = jo.getJSONObject("data");
		String student_number = joData.get("student_number")+"";//拿到学生数量
		JSONArray jaStudents = joData.getJSONArray("students");
		list_student=new ArrayList<BanJi_Student>();
		for (int i = 0; i < jaStudents.length(); i++) {
			JSONObject joStudent = (JSONObject) jaStudents.get(i);
			BanJi_Student student = new BanJi_Student();
			String record_student_id=joStudent.get("student_id")+"";
			
			int stuent_id=Integer.parseInt(record_student_id);
			student.setBianHao(record_student_id);
			String recordStatus=joStudent.get("student_status")+"";
			student.setZhuangtai(recordStatus);
			String discipline  =joStudent.get("disciplinarycategory_id")+"";
			int disciplinarycategory_id = Integer.parseInt(discipline.trim());
			student.setDisciplinarycategory(disciplinarycategory_id);
			String recordName=joStudent.get("student_name")+"";
			student.setName(recordName);
			list_student.add(student);
			if (disciplinarycategory_id!=0) {
				StudentRecord studentRecord = new StudentRecord();
				studentRecord.setStudent_id(Integer.parseInt(record_student_id));
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				arrayList.add(disciplinarycategory_id);
				studentRecord.setDisciplinarycategory_id(arrayList);
				studentRecord.setStudent_name(recordName);
				studentRecord.setStudent_status(Integer.parseInt(recordStatus));
				studentRecordMap.put(stuent_id,studentRecord);
			}
			if (!"1".equals(recordStatus)) {
				studentStadus.put(stuent_id,Integer.parseInt(recordStatus));
			}
		}
		
		JSONObject joEnrollment = joData.getJSONObject("enrollment");
		JSONObject joOverallrecord = joData.getJSONObject("overallrecord");
		supposed_attendance =joOverallrecord.get("supposed_attendance")+"";
		actual_attendance  =joOverallrecord.get("actual_attendance")+"";
		attendance_rate   =joOverallrecord.get("attendance_rate")+"";
		discipline_rate   =joOverallrecord.get("discipline_rate")+"";
		//违纪人数
		violation=Integer.parseInt(joOverallrecord.get("violation")+"");
		ext_violation=Integer.parseInt(joOverallrecord.get("ext_violation")+"");
		System.out.println(ext_violation+"扩展违纪人数");
		System.out.println("违纪率"+discipline_rate);
		//得到对这个班的评论
		notes=joOverallrecord.get("notes")+"";
		date = joOverallrecord.get("date")+"";
		JSONObject joClass = joData.getJSONObject("class");
		banji_name=joClass.get("class_name")+"";
		banji_id =joClass.get("class_id")+"";
		xueyuan_id=joClass.get("department_id")+"";
		
		
		
		kechengContent = joEnrollment.get("content")+"";
		jiaozhou_id=joEnrollment.get("teachingPeriod_id")+"";
		
		
		if (joOverallrecord.has("update_time")) {
			create_time = joOverallrecord.get("update_time")+"";
		}else {
			create_time = joOverallrecord.get("create_time")+"";
		}
		//初始化老师的列表
		list_laoshi=new ArrayList<BanJi_Teacher>();
		JSONObject joMaster = joData.getJSONObject("master");
		JSONObject joTeacher = joData.getJSONObject("teacher");
		BanJi_Teacher teacher = new BanJi_Teacher();
		teacher.setName(joMaster.get("teacher_name")+"");
		teacher.setBianHao(joMaster.get("teacher_id")+"");
		teacher.setzWBianHao(joMaster.get("teacher_role")+"");
		teacher.setZhuangtai(joMaster.get("teacher_status")+"");
		teacher.setZiwu("班主任");
		if (joMaster.has("notes")) {
			teacher.setNotes(joMaster.get("notes")+"");
		}
		list_laoshi.add(teacher);
		BanJi_Teacher teacher2 = new BanJi_Teacher();
		teacher2.setName(joTeacher.get("teacher_name")+"");
		teacher2.setBianHao(joTeacher.get("teacher_id")+"");
		teacher2.setzWBianHao(joTeacher.get("teacher_role")+"");
		teacher2.setZhuangtai(joTeacher.get("teacher_status")+"");
		teacher2.setZiwu("任课老师");
		if (joTeacher.has("notes")) {
			teacher2.setNotes(joTeacher.get("notes")+"");
		}
		list_laoshi.add(teacher2);
	}
	private void addToRecord(String str) throws JSONException {
		System.out.println("瞧一瞧看一看 " + str);
		JSONArray studentRecordList = jo.getJSONArray(str);

		// 一个保存着学生违纪记录的集合
		// 一个学生可能有多个违纪情况，所以disciplinarycategory_id是集合类型的

		for (int i = 0; i < studentRecordList.length(); i++) {
			JSONObject studentRecord = (JSONObject) studentRecordList.get(i);
			StudentRecord studentRecord2 = new StudentRecord();
			if (studentRecord.has("student_name")) {
				studentRecord2.setStudent_name(studentRecord
						.getString("student_name"));
			} else {
				studentRecord2.setStudent_name(studentRecord
						.getString("student_id"));
			}
			// 违纪的集合
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			arrayList.add(studentRecord.getInt("disciplinarycategory_id"));
			// 添加违纪的集合给这个学生类
			studentRecord2.setDisciplinarycategory_id(arrayList);
			studentRecord2.setNotes(studentRecord.getString("notes"));
			System.out.println(studentRecord.getInt("student_id"));
			System.out.println(studentRecord.getInt("student_status"));
			studentRecord2.setStudent_id(studentRecord.getInt("student_id"));
			studentRecord2.setStudent_status(studentRecord
					.getInt("student_status"));
			// TODO
			// 0804
			if (studentRecord.getInt("student_status") != 1) {
				studentStadus.put(studentRecord.getInt("student_id"),
						studentRecord.getInt("student_status"));
				System.out.println(studentStadus.size() + "长度是---");
			}
			// Integer integer =
			// studentRecord2.getDisciplinarycategory_id().get(0);
			// if (!(integer==4||integer==3||integer==9)) {
			// //如果是请假休学停课 就不放到map里去因为map是控制违纪率的
			// }
			studentRecordMap.put(studentRecord.getInt("student_id"),
					studentRecord2);
			studentRecordLists.add(studentRecord2);
		}
	}

	protected boolean getIsHasData() {
		if (getIntent() != null
				&& getIntent().getStringExtra("period") != null) {
			return true;
		}
		return false;
	}

	// private void showPreview() {
	// Intent intent = getIntent();
	// if (intent==null) {
	// return;
	// }
	// else {
	// String jsonString = intent.getStringExtra("jsonString");
	// Log.e("jsonString", jsonString);
	// Gson gson = new Gson();
	// RecordDetail recorDetail = gson.fromJson(jsonString,RecordDetail.class);
	// }
	// }

	private void gLAdapter() {
		laoShi_Adapter = new LaoShi_Adapter();
		laoShi_Adapter.setList(list_laoshi, ClassInput_Activity.this);
		for (BanJi_Student stu : list_student) {
			System.out.println(stu.getZhuangtai() + "---"
					+ stu.getDisciplinarycategory());
		}

		gv_laoshi.setAdapter(laoShi_Adapter);
		student_Adapter = new Student_Adapter();
		student_Adapter.setList(list_student, ClassInput_Activity.this);
		gv_student.setAdapter(student_Adapter);
	}

	private void initSheZhi() {// 把得到的数据设置到界面上
		//tv_kecheng.setText("课程是：" + content);
		banji_title.setText(banji_name);
		if (TextUtils.isEmpty(et_ydrs.getText().toString().trim())) {
			yingdao_RenShu = list_student.size();
		} else {
			yingdao_RenShu = Integer.parseInt(et_ydrs.getText().toString()
					.trim());
		}
		et_ydrs.setText(yingdao_RenShu + "");
		
		//tv_weijiren.setText("违纪人数" + weiji_RenShu + "人");
		//设置修改违纪人数的默认值  2017-2-5
		
		et_changed_weijinum.setText(weiji_RenShu+ext_violation+"");
		String sdrs = et_sdrs.getText().toString().trim();
		int sdrsInt = Integer.parseInt(sdrs);
		int weijiafter = Integer.parseInt((et_changed_weijinum.getText().toString().trim()));

		if (yingdao_RenShu != 0) {
			weijilv = (weijiafter) * 100d / (sdrsInt+weijiafter);
			System.out.println(weiji_RenShu+"--"+ext_violation+"--"+weijilv);
			
		} else {
			ToShow.show(ClassInput_Activity.this, "暂无数据");
		}
		if(weijilv==0)
		{
			tv_weijilv.setText("违纪率"+" 0 %");
			
		}
		else {
			String formateDouble = CommonUtils.formateDouble(weijilv);
			System.out.println(formateDouble+"----");
			tv_weijilv.setText("违纪率" + CommonUtils.formateDouble(weijilv) + "%");
			
		}
	}

	private void getList(String connect) throws JSONException {// Json解析
		list_laoshi = new ArrayList<BanJi_Teacher>();
		list_student = new ArrayList<BanJi_Student>();
		JSONObject json = new JSONObject(connect);
		JSONObject data = json.getJSONObject("data");
		JSONObject teaching_period = data.getJSONObject("teaching_period");
		jiaozhou_id = (String) teaching_period.get("id");
		JSONObject banji_class = data.getJSONObject("class");
		banji_name = (String) banji_class.get("class_name");
		JSONArray students = data.getJSONArray("students");
		for (int i = 0; i < students.length(); i++) {// 解析学生情况
			BanJi_Student banJi_Student = new BanJi_Student();
			JSONObject student = students.getJSONObject(i);
			String last_name = (String) student.get("last_name");
			String bianhao = (String) student.get("id");
			String sex = (String) student.get("sex");
			String zhuangTai = (String) student.get("status");
			banJi_Student.setZhuangtai(zhuangTai);// 学生状态
			banJi_Student.setBianHao(bianhao);// 学生编号
			banJi_Student.setSex(sex);// 学生性别
			banJi_Student.setName(last_name);// 学生名字
			list_student.add(banJi_Student);
			// studentStadus.put(Integer.parseInt(bianhao),
			// Integer.parseInt(zhuangTai));

		}
		JSONObject teacher = data.getJSONObject("teacher");
		// 解析任课教师情况
		BanJi_Teacher banJi_Teacher = new BanJi_Teacher();
		String last_name = (String) teacher.get("last_name");
		String zhuanTai = (String) teacher.get("status");
		String role = (String) teacher.get("role");
		String bianhao = (String) teacher.get("id");
		String ziwu = "任课老师";
		banJi_Teacher.setBianHao(bianhao);// 老师编号
		banJi_Teacher.setZhuangtai(zhuanTai);// 老师状态编号
		banJi_Teacher.setName(last_name);// 老师姓名
		banJi_Teacher.setZiwu(ziwu);// 老师职务
		banJi_Teacher.setzWBianHao(role + "");// 老师角色编号
		list_laoshi.add(banJi_Teacher);
		JSONObject master = data.getJSONObject("master");
		// 解析班主任情况
		BanJi_Teacher banJi_Master = new BanJi_Teacher();
		String last_name1 = (String) master.get("last_name");
		String zhuanTai1 = (String) master.get("status");
		String role1 = (String) master.get("role");
		String bianhao1 = (String) master.get("id");
		String ziwu1 = "班主任";
		banJi_Master.setBianHao(bianhao1);// 老师编号
		banJi_Master.setZhuangtai(zhuanTai1);// 老师状态编号
		banJi_Master.setName(last_name1);// 老师姓名
		banJi_Master.setZiwu(ziwu1);// 老师职务
		banJi_Master.setzWBianHao(role1 + "");// 老师角色编号
		list_laoshi.add(banJi_Master);
		JSONObject enrollment = data.getJSONObject("enrollment");
		JSONObject curse = enrollment.getJSONObject("curse");
		kechengContent = (String) curse.get("name");
	}

	private void initView() { // 找id
		json = new JSONObject();
		JSONArray jsonA = new JSONArray();
		
		
		huise = getResources().getColor(R.color.huise);
		banji_title = (TextView) findViewById(R.id.banji_title);
		sp_keshi = (Spinner) findViewById(R.id.sp_keshi);
		gv_student = (GridView) findViewById(R.id.gv_student);
		gv_laoshi = (GridView) findViewById(R.id.gv_laoshi);
		et_ydrs = (EditText) findViewById(R.id.ed_ydrs);// 应到人数
		et_sdrs = (EditText) findViewById(R.id.et_sdrs);
		tv_chuqinlv = (TextView) findViewById(R.id.tv_chuqinlv);
		tv_weijiren = (TextView) findViewById(R.id.tv_weijiren);
		ib_jian_weijinum=(ImageButton) findViewById(R.id.ib_jian_weijinum);
		ib_add_weijinum=(ImageButton) findViewById(R.id.ib_add_weijinum);
		//找到要修改的违纪人数字段---2017-2-5
		et_changed_weijinum = (EditText)findViewById(R.id.et_changed_weijinum);
		
		tv_kecheng = (TextView) findViewById(R.id.tv_kecheng);
		tv_weijilv = (TextView) findViewById(R.id.tv_weijilv);
		et_student = (EditText) findViewById(R.id.et_student);
		ed_ydrs = (EditText) findViewById(R.id.ed_ydrs);
		Intent inte = getIntent();
		banji_id = inte.getStringExtra("id");
		xueyuan_id = inte.getStringExtra("xueyuan_id");
		netWork = ConnectInternet.isNetWork(ClassInput_Activity.this);
		keshi = sp_keshi.getSelectedItem().toString();
		tv_recorded_time = (TextView) findViewById(R.id.tv_recorded_time);
		ib_jian_weijinum.setOnClickListener(this);
		ib_add_weijinum.setOnClickListener(this);
		
	}

	private void keCheng() {
		sp_keshi.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				keshi = sp_keshi.getSelectedItem().toString();
				// 可以联网了
				// 把联网得到的东西设置给keshi
				JanyTools janyTools = new JanyTools() {

					public void zhong() {
						// 获取数据
						if (netWork) {// 如果有网络连接就请求数据
							try {
								Log.e("wjl+++",
										JieKou.BANJI_XUESHENG
												+ banji_id
												+ "&period="
												+ keshi
												+ "&token="
												+ JieKou.getSp(ClassInput_Activity.this));
								connect = ConnectInternet.connect(JieKou.BANJI_XUESHENG
										+ banji_id
										+ "&period="
										+ keshi
										+ "&token="
										+ JieKou.getSp(ClassInput_Activity.this));
								if ("".equals(connect)) {
									ToShow.show(ClassInput_Activity.this,
											"暂无数据");
									finish();

								}
							} catch (IOException e) {// 联网获取JSON数据
								e.printStackTrace();
							}
							try {
								getList(connect);// 解析JSOn数据
								
								//更新学生的状态以及处理上传时候的数据
								updateInsertDataAndStudentStatus();
								
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}

					public void qian() {
						initView();
						// TODO
						// 再初始化数据20160804：18：56
						studentRecordMap = new HashMap<Integer, StudentRecord>();
						studentStadus = new HashMap<Integer, Integer>();
						// 找id
					}

					public void hou() {
						if (netWork) {// 在有网络请求的情况下才有操作
							for (BanJi_Teacher t : list_laoshi) {
								System.out.println(t.getName());

							}
							// **************
							caculateAttendenceAndActual();
							shiDao();
							gLAdapter();
							laoShi_Adapter.notifyDataSetChanged();
							student_Adapter.notifyDataSetChanged();
							gvJianTing();
							initSheZhi();
							System.out.println("走了******");
							// **************
						}
					}
				};
				janyTools.open();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	public void shiDao() {
		et_ydrs.addTextChangedListener(new TextWatcher() {// 应到人数的监听事件

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				//2017-2-5
				updateLv(weiji_RenShu);
			}
		});
		et_sdrs.addTextChangedListener(new TextWatcher() {// 实到的监听
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				String sdrs1 = et_sdrs.getText().toString().trim();
				et_sdrs.setSelection(et_sdrs.getText().length());
				if (!TextUtils.isEmpty(et_ydrs.getText())) {// 得到应到人数
					yingdao_RenShu = Integer.parseInt(et_ydrs.getText()
							.toString().trim());
				}
				Log.e(sdrs1, "应到人数：" + yingdao_RenShu);
				if (!TextUtils.isEmpty(sdrs1)
						&& Integer.parseInt(sdrs1.trim()) <= yingdao_RenShu) {
					sdrs = et_sdrs.getText().toString().trim();
					Log.e("2", yingdao_RenShu + "");
					lv = Double.parseDouble(sdrs) * 100d / yingdao_RenShu;
					tv_chuqinlv.setText("出勤率" + CommonUtils.formateDouble(lv) + "%");
				} else if (!TextUtils.isEmpty(sdrs1)
						&& Integer.parseInt(sdrs1.trim()) > yingdao_RenShu) {
					ToShow.show(ClassInput_Activity.this, "sorry,实到人数不能超过应到人数。");
				}
				//2017-2-5
				updateLv(weiji_RenShu);
				
			}
		});
		et_changed_weijinum.addTextChangedListener(new TextWatcher() {// 应到人数的监听事件

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				//2017-2-5
				String ydrs = et_ydrs.getText().toString().trim();
				int intYdrs = Integer.parseInt(ydrs);
				if (ext_violation==0) {
					et_changed_weijinum.setTextColor(Color.parseColor("#000000"));
				}else if (ext_violation>0) {
					et_changed_weijinum.setTextColor(Color.parseColor("#ff0000"));
				}else if (ext_violation+weiji_RenShu>=intYdrs) {
					Toast.makeText(ClassInput_Activity.this, "已经调整到最大违纪人数", 0).show();	
					et_changed_weijinum.setText(ext_violation+weiji_RenShu);
				}
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						int weijinum = Integer.parseInt(et_changed_weijinum.getText().toString().trim());
						
						if (sdrs!=null) {
							int weijinumAfter = Integer.parseInt(et_changed_weijinum.getText().toString().trim());
								weijilv = (weijinumAfter) * 100d / (weijinumAfter+Integer.parseInt(sdrs));
							if (weijilv == 0d) {
								discipline_rate = "0";
							} else {
								discipline_rate = CommonUtils.formateDouble(weijilv);
							}
							weijilv2 = weijilv;
							System.out.println(weijilv2+"----22222");
							tv_weijilv.setText("违纪率" + discipline_rate + "%");
						}	
					}
				});
			}
		});
		
		
	}

private void updateLv(int weiji_RenShu) {
	String ydrs = et_ydrs.getText().toString().trim();
	String sdrs = et_sdrs.getText().toString().trim();
	et_ydrs.setSelection(et_ydrs.getText().length());
	if (!TextUtils.isEmpty(ydrs) & !TextUtils.isEmpty(sdrs)
			& !"0".equals(ydrs)) {
		yingdao_RenShu = Integer.parseInt(ydrs);
		if (!TextUtils.isEmpty(et_sdrs.getText().toString().trim())) {
			sdrs = et_sdrs.getText().toString().trim();
		}

		if (!"".equals(et_sdrs.getText().toString())) {
			lv = Double.parseDouble(sdrs) * 100d
					/ Double.parseDouble(ydrs);
			attendance_rate = CommonUtils.formateDouble(lv);
			tv_chuqinlv.setText("出勤率" + attendance_rate + "%");
		}
		//违纪率=违纪人数/（违纪人数+实到人数）
		if (sdrs!=null) {
			int weijinumAfter = Integer.parseInt(et_changed_weijinum.getText().toString().trim());
				weijilv = (weijinumAfter) * 100d / (weijinumAfter+Integer.parseInt(sdrs));
			if (weijilv == 0d) {
				discipline_rate = "0";
			} else {
				discipline_rate = CommonUtils.formateDouble(weijilv);
			}

			tv_weijilv.setText("违纪率" + discipline_rate + "%");
		}	
		}
		

}
	private void initWay() {// 得到我们输入的内容
		// 实到人数，应到人数，出勤率是提交的时候重新计算的
		sdrs = et_sdrs.getText().toString().trim();
		yingdaotrim = et_ydrs.getText().toString().trim();
		student = et_student.getText().toString().trim();
		if (!(TextUtils.isEmpty(sdrs) && TextUtils.isEmpty(yingdaotrim))) {
			lv = Double.parseDouble(sdrs) * 100d
					/ Double.parseDouble(yingdaotrim);
		}
		weijirenshuback = studentRecordMap.size()+ext_violation;
		System.out.println(weijirenshuback+"--");
		//违纪率是违纪人数/违纪人数+实到人数
		weijilv2 = weijirenshuback * 100d / (Integer.parseInt(sdrs)+weijirenshuback);
		System.out.println(weijilv2+"----22222");
	}

	// 提交的方法
	public void commit(View v) {// 确认提交的点击事件
		pd2 = CommonUtils.getProgressDialog(ClassInput_Activity.this, "正在提交,请稍后");
		initWay();// 得到我们修改后的内容
		if (TextUtils.isEmpty(yingdaotrim)) {
			ToShow.show(this, "应到人数未填写");
		} else if (TextUtils.isEmpty(sdrs)) {
			ToShow.show(this, "实到人数未填写");
		} else if (TextUtils.isEmpty(student)) {
			ToShow.show(this, "学生情况未填写");
		} else {
			keshi = sp_keshi.getSelectedItem().toString();
			hashMap = new HashMap<String, String>();
			hashMap.put("teachingperiod_id", jiaozhou_id + "");

			hashMap.put("depart_id", xueyuan_id);
			hashMap.put("class_id", banji_id);
			hashMap.put("class_period", keshi);
			hashMap.put("supposed_attendance", yingdaotrim + "");
			hashMap.put("actual_attendance", sdrs + "");
			hashMap.put("student_rate", "" + CommonUtils.formateDouble(lv));
			hashMap.put("violation", "" + studentRecordMap.size());
			hashMap.put("ext_violation", "" + ext_violation);
			
			hashMap.put("disciplinary_rate",
					"" + CommonUtils.formateDouble(weijilv2));
			hashMap.put("notes", student);
			if (!TextUtils.isEmpty(date)) {
				neededUrl = JieKou.BANJI_MODIFY;
				hashMap.put("date", date);
				System.out.println("提交的时间是" + date);
			}
			// -----------------------------------学生违纪情况json-------------------------------------------
			JSONArray studentsJsonArray = new JSONArray();
//			Set<Entry<Integer, Integer>> entrySet3 = studentStadus.entrySet();
//			for (Entry<Integer, Integer> entry : entrySet3) {
//				System.out.println("我是保存缺勤情况的" + entry.getKey()
//						+ "hhahashfkasdlfhskad" + entry.getValue());
//			}
//			Set<Entry<Integer, StudentRecord>> entrySet = studentRecordMap
//					.entrySet();
//			for (Entry<Integer, StudentRecord> entry : entrySet) {
//				System.out.println("我是保存违纪情况的情况的" + entry.getKey()
//						+ entry.getValue().getStudent_status() + "违纪类别是"
//						+ entry.getValue().getDisciplinarycategory_id());
//				if (entry.getValue().getStudent_status() == 1
//						|| entry.getValue().getStudent_status() == 1000) {
//					// System.out.println("我是保存违纪情况的情况的"+entry.getValue().getNotes()+""+entry.getValue().getStudent_name());
//
//					if (!((studentStadus.containsKey(entry.getKey())) && (studentStadus
//							.get(entry.getKey()) != 1))) {
//						System.out.println("啦啦啦啦啦啦");
//						HashMap<String, String> hashMap2 = new HashMap<String, String>();
//						hashMap2.put("student_id", entry.getValue()
//								.getStudent_id() + "");
//						hashMap2.put("student_status", entry.getValue()
//								.getStudent_status() == 1000 ? "1" : entry
//								.getValue().getStudent_status() + "");
//						hashMap2.put("notes", entry.getValue().getNotes());
//						hashMap2.put("disciplinarycategory_id",
//								entry.getValue().getDisciplinarycategory_id()
//										.get(0) == 1000 ? "1" : entry
//										.getValue()
//										.getDisciplinarycategory_id().get(0)
//										+ "");
//						JSONObject jsonObject = new JSONObject(hashMap2);
//						studentsJsonArray.put(jsonObject);
//					} else if (((studentStadus.containsKey(entry.getKey())) && (studentStadus
//							.get(entry.getKey()) == 1000))) {
//						System.out.println("啦啦啦啦啦啦");
//						HashMap<String, String> hashMap2 = new HashMap<String, String>();
//						hashMap2.put("student_id", entry.getValue()
//								.getStudent_id() + "");
//						hashMap2.put("student_status", entry.getValue()
//								.getStudent_status() == 1000 ? "1" : entry
//								.getValue().getStudent_status() + "");
//						hashMap2.put("notes", entry.getValue().getNotes());
//						hashMap2.put("disciplinarycategory_id",
//								entry.getValue().getDisciplinarycategory_id()
//										.get(0) == 1000 ? "1" : entry
//										.getValue()
//										.getDisciplinarycategory_id().get(0)
//										+ "");
//						JSONObject jsonObject = new JSONObject(hashMap2);
//						studentsJsonArray.put(jsonObject);
//					}
//
//				}
//			}
//
//			System.out.println(studentsJsonArray + "第一次");
//			// 是记录 学生状态 3 和9和4的 的 除此之外的都不要
//			Set<Entry<Integer, Integer>> entrySet2 = studentStadus.entrySet();
//			for (Entry<Integer, Integer> entry : entrySet2) {
//				// if (!studentRecordMap.containsKey(entry.getKey())) {
//				if (entry.getValue() == 3 || entry.getValue() == 9
//						|| entry.getValue() == 4) {
//					HashMap<String, String> hashMap3 = new HashMap<String, String>();
//					hashMap3.put("student_id", entry.getKey() + "");
//					hashMap3.put("student_status", entry.getValue() + "");
//					if (entry.getValue() == 1 || entry.getValue() == 0) {
//						hashMap3.put("notes", "在校");
//					} else if (entry.getValue() == 2) {
//						hashMap3.put("notes", "离校");
//					} else if (entry.getValue() == 3) {
//						hashMap3.put("notes", "请假");
//					} else if (entry.getValue() == 9) {
//						hashMap3.put("notes", "休学");
//					} else if (entry.getValue() == 4) {
//						hashMap3.put("notes", "停课");
//					}
//					// 暂定旷课是1，没有违纪状态就是0
//					hashMap3.put("disciplinarycategory_id", "0");
//					JSONObject jsonObject = new JSONObject(hashMap3);
//					studentsJsonArray.put(jsonObject);
//				}
				for (BanJi_Student student : list_student) {
					HashMap<String, String> hashMap2 = new HashMap<String, String>();
					hashMap2.put("student_id", student.getBianHao().trim()+ "");
					hashMap2.put("student_status", student.getZhuangtai().trim()+"");
					hashMap2.put("notes", student.getNotes());
					hashMap2.put("disciplinarycategory_id",student.getDisciplinarycategory()+"");
					JSONObject jsonObject = new JSONObject(hashMap2);
					studentsJsonArray.put(jsonObject);
				}
				// }
			
			System.out.println(studentsJsonArray.toString() + "天王盖地虎11");
			hashMap.put("student_json", studentsJsonArray.toString());
			// -----------------------------------老师情况json-------------------------------------------
			// if (studentRecordLists!=null&&studentRecordLists.size()>0) {
			// for (StudentRecord record : studentRecordLists) {
			// int student_id = record.getStudent_id();
			// int student_status = record.getStudent_status();
			// String notes2 = record.getNotes();
			// ArrayList<Integer> disciplinarycategory_id =
			// record.getDisciplinarycategory_id();
			// }
			// }
			// 下面是教师情况
			JSONArray teachersJsonArray = new JSONArray();
//			if (teacherRecordLists.size() > 0) {
//				for (TeacherRecord tr : teacherRecordLists) {
//					HashMap<String, String> hashMap4 = new HashMap<String, String>();
//					hashMap4.put("teacher_id", tr.getTeacher_id() + "");// 教师编号
//					hashMap4.put("teacher_status", tr.getTeacher_status() + "");// 状态编号
//					hashMap4.put("teacher_role", tr.getTeacher_role() + "");// 角色编号
//					hashMap4.put("notes", tr.getNotes());// 教师情况描述
//					JSONObject jsonObject = new JSONObject(hashMap4);
//					teachersJsonArray.put(jsonObject);
//
//				}
			if (list_laoshi.size() > 0) {
				
				
				
				
				for (BanJi_Teacher tr : list_laoshi) {
					
					
					System.out.println("获得"+tr.getName()+"----"+tr.getzWBianHao());
					HashMap<String, String> hashMap4 = new HashMap<String, String>();
					hashMap4.put("teacher_id", tr.getBianHao()+ "");// 教师编号
					hashMap4.put("teacher_status", tr.getZhuangtai() + "");// 状态编号
					hashMap4.put("teacher_role", tr.getzWBianHao()+ "");// 角色编号
					hashMap4.put("notes", tr.getNotes());// 教师情况描述
					JSONObject jsonObject = new JSONObject(hashMap4);
					teachersJsonArray.put(jsonObject);

				}

			}
			System.out.println(teachersJsonArray.toString() + "天王盖地虎");

			hashMap.put("teacher_json", teachersJsonArray.toString());
			Set<Entry<String, String>> entrySet = hashMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				System.out.println(entry.getKey()+"---"+entry.getValue());
			}
			
			if (hashMap.containsValue(null)) {
				ToShow.show(ClassInput_Activity.this, "请求参数异常");
			} else {
				JSONObject jsonObject = new JSONObject(hashMap);
				System.out.println(jsonObject);
				try {
					Volley_LianWang.volley_Post(
							neededUrl + JieKou.getSp(ClassInput_Activity.this),
							new WangLuo_HuiDiao() {

								@Override
								public void getData(String data) {
									Log.e("提交", data + "123");
									try {
										JSONObject jso = new JSONObject(data);
										String status = (String) jso
												.get("status");
										if ("201".equals(status)) {
											ToShow.show(
													ClassInput_Activity.this,
													"请勿重复提交");
											if (pd2.isShowing()) {
												pd2.dismiss();
											}
											
										} else {
											if ("200".equals(status)) {

												System.out.println("提交成功");
												ToShow.show(
														ClassInput_Activity.this,
														"信息提交成功");
												// 如果是从列表那回来的，那么就再回去刚才的列表
												if (getIsHasData()) {
													intenBack = new Intent(
															ClassInput_Activity.this,
															ClassOverall_Activity.class);
													intenBack
															.putExtra(
																	"position",
																	getIntent()
																			.getIntExtra(
																					"position",
																					0));
													setResult(11, intenBack);
													overridePendingTransition(
															android.R.anim.fade_in,
															android.R.anim.fade_out);
													if (pd2.isShowing()) {
														pd2.dismiss();
													}
													finish();
													return;
												}
												Intent inten = new Intent();
												inten.putExtra("id", banji_id);
												setResult(2, inten);// 给班级列表返回一个id
												finish();
											} else {
												if (pd2.isShowing()) {
													pd2.dismiss();
												}
												ToShow.show(
														ClassInput_Activity.this,
														"信息提交失败，请重新登陆");

											}
										}
									} catch (JSONException e) {
										ToShow.show(ClassInput_Activity.this,
												"信息提交失败，网络异常，错误号500");
										e.printStackTrace();
									}
								}
							}, hashMap, ClassInput_Activity.this);
				} catch (Exception e) {
					ToShow.show(ClassInput_Activity.this, "信息提交失败，网络异常");
					e.printStackTrace();
				}
			}
		}
	}

	public void gvJianTing() {
		gv_student.setOnItemClickListener(new OnItemClickListener() {// 学生列表的点击事件
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						System.out.println(studentRecordMap.size()
								+ "刚进来时候存储违纪率的这个map是多大");
						// 打印这个map
						outStudentRecordMap();
						// 删除map中为状态的数据
						deleteStatusFromRecordMap();
						// 每次点学生的时候清空违纪人数
						weiji_RenShu = 0;

						// 得到对应的学生类
						BanJi_Student banJi_student = list_student
								.get(position);
						Bundle bundle = new Bundle();// 创建一个Bundle
						Intent inte = new Intent();
						bundle.putString("name", banJi_student.getName());// 把数据放到Bundle里面
						bundle.putString("sex", banJi_student.getSex());
						bundle.putString("banji_name", banji_name);
						bundle.putString("bianhao", banJi_student.getBianHao());
						bundle.putString("zhuangtai",
								banJi_student.getZhuangtai());
						bundle.putString("tog", "1");
						int parseInt = Integer.parseInt(banJi_student
								.getBianHao().trim());
						ArrayList<Integer> weijiList = new ArrayList<Integer>();
						// 读取保存的数据，如果没有就不操作，有的话，查找到对应的学生，进行数据的发送，并且把学生的违纪类型作为bundle中的值传递过去
						if (studentRecordMap != null
								&& studentRecordMap.size() > 0) {
							if (studentRecordMap.containsKey(parseInt)) {
								if ((studentRecordMap.get(parseInt)
										.getDisciplinarycategory_id() != null)
										&& studentRecordMap.get(parseInt)
												.getDisciplinarycategory_id()
												.size() > 0) {
									ArrayList<Integer> arrayList = new ArrayList<Integer>();
									arrayList.add(1000);
									weijiList
											.addAll((studentRecordMap
													.get(parseInt)
													.getDisciplinarycategory_id()
													.get(0) == 1) ? arrayList
													: studentRecordMap
															.get(parseInt)
															.getDisciplinarycategory_id());
									System.out.println(weijiList.get(0)
											+ "hahahahahah");
								}
							}
							bundle.putIntegerArrayList("weijiList", weijiList);

						}
						if (studentStadus.containsKey(parseInt)) {
							if (!studentStadus.containsKey(1)) {
								System.out.println("点击学生列表的时候，学生的状态是"
										+ studentStadus.get(parseInt) + "");
								bundle.putString("zhuangtai",
										+studentStadus.get(parseInt) + "");
							}
						}
						inte.putExtras(bundle);// 发送Bundle
						inte.setClass(ClassInput_Activity.this,
								DangeXuesheng_Activity.class);
						startActivityForResult(inte, 1);
					}

				});
		gv_laoshi.setOnItemClickListener(new OnItemClickListener() {// 老师列表点击事件
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						BanJi_Teacher banJi_Teacher = list_laoshi.get(arg2);
						Bundle bun = new Bundle();
						Intent inte = new Intent();
						bun.putString("laoshi_name", banJi_Teacher.getName());
						bun.putString("ziwu", banJi_Teacher.getZiwu());
						bun.putString("bianhao", banJi_Teacher.getBianHao());
						bun.putString("zhuangtai", banJi_Teacher.getZhuangtai());
						bun.putString("zwbianhao", banJi_Teacher.getzWBianHao());
						bun.putString("banji_name", banji_name);
						bun.putString("tog", "2");

						if (teacherRecordLists != null
								&& teacherRecordLists.size() > 0) {
							for (TeacherRecord tr : teacherRecordLists) {
								if ((tr.getTeacher_id() + "")
										.equals(banJi_Teacher.getBianHao())) {
									bun.putString("notes", tr.getNotes());
								}
							}
						}
						inte.putExtras(bun);
						inte.setClass(ClassInput_Activity.this,
								DangeXuesheng_Activity.class);
						startActivityForResult(inte, 2);
					}
				});
	}

	public void fanhui(View v) {// 返回事件
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			System.out.println("走了");
			return;
		}
		System.out.println(data == null ? "回来了是null" : "回来了不是null");
		if (requestCode == 1) {// 是学生返回的参数
			deleteStatusFromRecordMap();
			Bundle extras = data.getExtras();
			ArrayList<Integer> fWeijiList = extras
					.getIntegerArrayList("fweijilist");
			fBianHao = extras.getString("bianhao");
			fZhuangTai = extras.getString("status");
			studentNotes = extras.getString("notes");
			System.out.println(fBianHao + "20160729最新的状态是" + flsZhuangTai);
			// 不管怎样，都是把传过来的状态，改成最新更改的状态hashmap
			studentStadus.put(Integer.parseInt(fBianHao.trim()), TextUtils
					.isEmpty(fZhuangTai) ? 0 : Integer.parseInt(fZhuangTai.trim()));
			System.out.println(studentStadus.size() + "---------大小是");
			int parseInt = Integer.parseInt(fBianHao.trim());
			// 如果接受的有违纪，那么就放到之前的集合中去
			if (studentRecordMap != null && studentRecordMap.size() > 0) {
				if (studentRecordMap.containsKey(parseInt)) {
					// TODO
					if (fWeijiList.size() > 0) {
						Integer integer = fWeijiList.get(0);
						// 若当前的违纪是有值得，那么就覆盖
						System.out.println("20160729啦啦啦啦啦啦" + integer);
						// TODO
						if (integer != 10000) {
							studentRecordMap.get(parseInt)
									.setDisciplinarycategory_id(fWeijiList);
							studentRecordMap.get(parseInt).setNotes(
									studentNotes);
						} else {
							System.out.println("20160729删除了一条记录");
							studentRecordMap.remove(parseInt);
						}
					}
					// 当前记录没有，就添加记录
					else if (fWeijiList.size() == 0) {
						// TODO
						studentRecordMap.remove(parseInt);
					}
				} else {
					addRecord(fWeijiList);
				}

			}
			// 当总集合为空的时候进去此
			if (studentRecordMap == null || studentRecordMap.size() == 0) {
				{
					System.out.println("加进去了一条数据");
					addRecord(fWeijiList);
				}
			}
			// 更新显示
			// 累计缺勤人数
			Set<Entry<Integer, Integer>> entrySet = studentStadus.entrySet();
			int temp = 0;
			for (Entry<Integer, Integer> entry : entrySet) {
				if (entry.getValue() == 3 || entry.getValue() == 9
						|| entry.getValue() == 1000) {
					temp++;
				}
			}
			System.out.println("缺勤人数是" + temp);
			// et_sdrs.setText(yingdao_RenShu-temp+"");

			// 累计违纪人数,这是在记录违纪人数
			// TODO
			weiji_RenShu = studentRecordMap.size();
			System.out.println(weiji_RenShu+"--"+ext_violation);
			initSheZhi();
			// TODO
			updateStudentList();
			caculateAttendenceAndActual();
			student_Adapter.notifyDataSetChanged();
			
			
			String ydrs = et_ydrs.getText().toString().trim();
			int intYdrs = Integer.parseInt(ydrs);
			 if (ext_violation+weiji_RenShu>=intYdrs) {
				 ext_violation=(intYdrs-weiji_RenShu);
				 
				Toast.makeText(ClassInput_Activity.this, "已经调整到最大违纪人数", 0).show();	
				et_changed_weijinum.setText(ext_violation+weiji_RenShu+"");
			}
			
		}
		if (requestCode == 2) {
			setTeacherRecord(data);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void updateStudentList() {
		for (BanJi_Student student : list_student) {
			//把学号进行验证
			if (studentStadus
					.containsKey(Integer.parseInt(student.getBianHao().trim()))) {
				int status = studentStadus.get(Integer.parseInt(student.getBianHao().trim()));
				student.setZhuangtai(status + "");
				switch (status) {
				case 3:
					student.setNotes("请假");
					break;
				case 9:
					student.setNotes("休学");
					break;
				case 4:
					student.setNotes("停课");
					break;
				case 5:
					student.setNotes("三方请假");
					break;
				default:
					break;
				}
 			}
		}
		for (BanJi_Student student : list_student) {
			System.out.println(student.getName() + student.getBianHao());
			if (studentRecordMap.containsKey(Integer.parseInt(student
					.getBianHao().trim()))) {
				student.setDisciplinarycategory(studentRecordMap
						.get(Integer.parseInt(student.getBianHao().trim()))
						.getDisciplinarycategory_id().get(0));
				student.setNotes(studentRecordMap.get(Integer.parseInt(student.getBianHao().trim())).getNotes());
				
			} else {
				student.setDisciplinarycategory(0);
			}
		}
	}

	private void setTeacherRecord(Intent data) {
		System.out.println(teacherRecordLists == null ? "竟然是null" : "还好不是");
		// 老师返回的参数
		if (teacherRecordLists != null && teacherRecordLists.size() > 0) {
			System.out.println("走了吗1");
			if ((teacherRecordLists.get(0).getTeacher_id() + "").equals(data
					.getStringExtra("Tbianhao"))) {
				flsQingKuang = data.getStringExtra("Tnotes");
				flsBianHao = data.getStringExtra("Tbianhao");
				flsZhuangTai = data.getStringExtra("zhuangtai");
				roles = data.getStringExtra("roles");
				teacherRecordLists.get(0).setTeacher_id(
						Integer.parseInt(flsBianHao.trim()));
				teacherRecordLists.get(0).setNotes(flsQingKuang);
				teacherRecordLists.get(0).setTeacher_status(
						Integer.parseInt(flsZhuangTai.trim()));
				teacherRecordLists.get(0).setTeacher_role(
						roles.equals("任课老师") ? 1 : 0);
			} else if ((teacherRecordLists.size() == 2)
					&& (teacherRecordLists.get(1).getTeacher_id() + "")
							.equals(data.getStringExtra("Tbianhao"))) {
				flsQingKuang = data.getStringExtra("Tnotes");
				flsBianHao = data.getStringExtra("Tbianhao");
				flsZhuangTai = data.getStringExtra("zhuangtai");
				roles = data.getStringExtra("roles");
				teacherRecordLists.get(1).setTeacher_id(
						Integer.parseInt(flsBianHao.trim()));
				teacherRecordLists.get(1).setNotes(flsQingKuang);
				teacherRecordLists.get(1).setTeacher_status(
						Integer.parseInt(flsZhuangTai.trim()));
				teacherRecordLists.get(1).setTeacher_role(
						roles.equals("任课老师") ? 1 : 0);
			} else {
				TeacherRecord teacherRecord = new TeacherRecord();
				flsQingKuang = data.getStringExtra("Tnotes");
				flsBianHao = data.getStringExtra("Tbianhao");
				flsZhuangTai = data.getStringExtra("zhuangtai");
				roles = data.getStringExtra("roles");
				teacherRecord.setTeacher_id(Integer.parseInt(flsBianHao.trim()));
				teacherRecord.setNotes(flsQingKuang);
				teacherRecord.setTeacher_status(Integer.parseInt(flsZhuangTai.trim()));
				teacherRecord.setTeacher_role(roles.equals("任课老师") ? 1 : 0);
				teacherRecordLists.add(teacherRecord);
			}
		}
		if (teacherRecordLists == null || teacherRecordLists.size() == 0) {
			System.out.println("走了吗2");
			TeacherRecord teacherRecord = new TeacherRecord();
			Bundle extras = data.getExtras();
			flsQingKuang = extras.getString("Tnotes");
			flsBianHao = extras.getString("Tbianhao");
			flsZhuangTai = extras.getString("zhuangtai");
			roles = extras.getString("roles");
			teacherRecord.setTeacher_id(Integer.parseInt(flsBianHao.trim()));
			teacherRecord.setNotes(flsQingKuang);
			teacherRecord.setTeacher_status(Integer.parseInt(flsZhuangTai.trim()));
			teacherRecord.setTeacher_role(roles.equals("任课老师") ? 1 : 0);
			teacherRecordLists.add(teacherRecord);
		}
		//回写到本来的教师列表
		for (TeacherRecord record : teacherRecordLists) {
			for (BanJi_Teacher laoshi : list_laoshi) {
				if (laoshi.getBianHao().trim().equals(record.getTeacher_id()+"")) {
					laoshi.setNotes(record.getNotes());
				}
			}
		}
		
		
	}

	private void addRecord(ArrayList<Integer> fWeijiList) {
		if ((!fWeijiList.isEmpty()) && fWeijiList.get(0) != 10000) {
			StudentRecord studentRecord = new StudentRecord();
			studentRecord.setStudent_id(Integer.parseInt(fBianHao.trim()));
			studentRecord.setStudent_status(fZhuangTai.equals("") ? 0 : Integer
					.parseInt(fZhuangTai));
			studentRecord.setNotes(studentNotes);
			System.out.println("添加了一条记录");
			studentRecord.setDisciplinarycategory_id(fWeijiList);
			studentRecordMap.put(studentRecord.getStudent_id(), studentRecord);
		}
	}

	private void caculateAttendenceAndActual() {
		for (BanJi_Student stu : list_student) {
			System.out.println(stu.getZhuangtai() + "---"
					+ stu.getDisciplinarycategory());
		}

		// 遍历学生的集合 看看有没有停课的
		int queqinInt = 0;
		int supposed_attendanceInt = list_student.size();
		System.out.println(supposed_attendanceInt + "现在学生的集合大小");
		for (BanJi_Student student : list_student) {
			System.out.println(student.getName() + "状态是"
					+ student.getZhuangtai());
			if ("4".equals(student.getZhuangtai())||("5".equals(student.getZhuangtai()))) {
				supposed_attendanceInt--;
			}
			if ("9".equals(student.getZhuangtai())
					|| "3".equals(student.getZhuangtai())
					|| 1 == student.getDisciplinarycategory()
					|| 1000 == student.getDisciplinarycategory()
					|| "1000".equals(student.getZhuangtai())) {
				queqinInt++;
			}

		}
		et_sdrs.setText(supposed_attendanceInt - queqinInt + "");
		ed_ydrs.setText(supposed_attendanceInt + "");
	}

	// 打印map
	private void outStudentRecordMap() {
		Set<Entry<Integer, StudentRecord>> entrySet = studentRecordMap
				.entrySet();
		for (Entry<Integer, StudentRecord> entry : entrySet) {
			System.out.println(entry.getKey() + "值" + entry.getValue());
		}
	}

	private void deleteStatusFromRecordMap() {
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		Set<Entry<Integer, StudentRecord>> entrySet = studentRecordMap
				.entrySet();
		Iterator<Entry<Integer, StudentRecord>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, StudentRecord> entry = iterator.next();
			if (entry.getValue().getStudent_status() == 4
					|| entry.getValue().getStudent_status() == 3
					|| entry.getValue().getStudent_status() == 9
					|| entry.getValue().getStudent_status() == 5) {
				tempList.add(entry.getKey());
			}
		}
		for (int i : tempList) {
			studentRecordMap.remove(i);
		}
	}
	
	private void updateInsertDataAndStudentStatus() {
		// 更新学生的数据。展示是否缺勤违纪
		for (BanJi_Student student : list_student) {
			System.out.println(student.getName()
					+ student.getBianHao());
			//TODO
			if (studentStadus.containsKey(Integer
					.parseInt(student.getBianHao().trim()))) {
				System.out.println(student.getName() + "天王盖地虎");
				student.setZhuangtai(studentStadus.get(Integer
						.parseInt(student.getBianHao())) + "");
			}
			int status=Integer.parseInt(student.getZhuangtai().trim());
			if (status!=1) {
				//说明后台做了登记
				int studentId=Integer.parseInt(student.getBianHao().trim());
				studentStadus.put(studentId, status);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_add_weijinum:
			String trim = et_ydrs.getText().toString().trim();
			int ydrs = Integer.parseInt(trim);
			if (ext_violation+weiji_RenShu>=ydrs) {
				Toast.makeText(ClassInput_Activity.this, "已经调整到最大违纪人数", 0).show();	
			}
			else{
				ext_violation=ext_violation+1;
			}
			et_changed_weijinum.setText(weiji_RenShu+ext_violation+"");
			updateLv(weiji_RenShu+ext_violation);
			break;
		case R.id.ib_jian_weijinum:
			if (weiji_RenShu!=0) {
			if (ext_violation==0) {
				Toast.makeText(ClassInput_Activity.this, "已经调整到最小违纪人数", 0).show();	
			}else {
				
				ext_violation=ext_violation-1;
			}
			}else {
				if (ext_violation==0) {
					Toast.makeText(ClassInput_Activity.this, "已经调整到最小违纪人数", 0).show();	
				}else {
					ext_violation=ext_violation-1;
				}
			}
			et_changed_weijinum.setText(weiji_RenShu+ext_violation+"");
			updateLv(weiji_RenShu+ext_violation);
			break;
		default:
			break;
		}
	}
	
	
}