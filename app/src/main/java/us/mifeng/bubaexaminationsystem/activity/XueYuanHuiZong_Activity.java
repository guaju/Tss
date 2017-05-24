package us.mifeng.bubaexaminationsystem.activity;

import java.text.SimpleDateFormat;

import us.mifeng.bubaexaminationsystem.fragment.Chart__FragmentRi;
import us.mifeng.bubaexaminationsystem.fragment.CollegeTeachers_Fragment;
import us.mifeng.bubaexaminationsystem.fragment.Data_Fragment_Ri;
import us.mifeng.bubaexaminationsystem.fragment.Chart_Fragment;
import us.mifeng.bubaexaminationsystem.fragment.Data_Fragment;
import us.mifeng.bubaexaminationsystem.fragment.SetDateDialog;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.inputmethodservice.Keyboard.Key;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class XueYuanHuiZong_Activity extends FragmentActivity implements
		OnClickListener {
	public  static boolean IS_IN_TEACHERATTENDENCEDETAIL=false;
	public  static boolean IS_IN_TOTAL=false;
	public static FragmentManager fm;
	private PopupWindow popupWindow, pop;
	public static TextView xueyuan_id;
	private TextView tv_ri;
	private TextView tv_zhou;
	private TextView tv_yue;
	private TextView tv_cycle;
	private String today;
	public static TextView date1;
	public static FrameLayout left;
	public static FrameLayout right;
	public Bundle xueyuanExtra;
	public String name;
	public static String id;
	public static TextView date2;
	public TextView bt_date;
//	private RelativeLayout classname;
	private ImageView sandian;
	private TextView tt1, tt3, tt2;
	public  static ProgressDialog progressDialog;
	/**
	 * time 是选择的时间周期 日=1 周=2 月=3 教学周期=4
	 * rightPW 是选择要显示的内容 数据=1 图表=2 老师出勤率=3 
	 */
	public static int time , rightPW ;
	private LinearLayout date_ll;
	public static ImageView fanhuiIv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xueyuanhuizong);
		initView();
		progressDialog=CommonUtils.getProgressDialog(XueYuanHuiZong_Activity.this, "正在加载...");
		// initList();
		iniSanDian();
		initRgSj();
	}
	private void iniSanDian() {
		LayoutInflater inflater = LayoutInflater.from(this);
		// 引入窗口配置文件
		View view = inflater.inflate(R.layout.sandian, null);
		tt1 = (TextView) view.findViewById(R.id.tt1);
		tt2 = (TextView) view.findViewById(R.id.tt2);
		tt3 = (TextView) view.findViewById(R.id.tt3);
		// 创建PopupWindow对象
		pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		sandian = (ImageView) findViewById(R.id.sandian);
		// 需要设置一下此参数，点击外边可消失
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		pop.setFocusable(true);
		sandian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pop.isShowing()) {
					// 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
					pop.dismiss();
				} else {
					// 显示窗口
					pop.showAsDropDown(v);
				}

			}
		});

	}

	public void fanhui(View v) {
		if (IS_IN_TEACHERATTENDENCEDETAIL) {
			FragmentTransaction bt = XueYuanHuiZong_Activity.fm.beginTransaction();
			bt.replace(R.id.fl_id, new CollegeTeachers_Fragment());
			bt.commit();
			IS_IN_TEACHERATTENDENCEDETAIL=false;
		}
		else if (IS_IN_TOTAL) {
			System.out.println("在子页面点击了返回教案");
			FragmentTransaction bt = XueYuanHuiZong_Activity.fm.beginTransaction();
			//TODO
			if(time==1){
				XueYuanHuiZong_Activity.id="";
				bt.replace(R.id.fl_id, new Data_Fragment_Ri());
			}else{
				XueYuanHuiZong_Activity.id="";
				bt.replace(R.id.fl_id, new Data_Fragment());
			}
			bt.commit();
			IS_IN_TOTAL=false;
		}
		else {
			finish();
		}
	}

	private void initRgSj() {
		date1.setOnClickListener(this);
		date2.setOnClickListener(this);
		sandian.setOnClickListener(this);
		tt1.setOnClickListener(this);
		tt2.setOnClickListener(this);
		tt3.setOnClickListener(this);
		bt_date.setOnClickListener(this);
	}

	private void initView() {
		time=1;
		rightPW=1;
		
		fanhuiIv = (ImageView) findViewById(R.id.imageView1);
		date_ll = (LinearLayout) findViewById(R.id.date_ll);
		bt_date = (TextView) findViewById(R.id.date_but);
		date1 = (TextView) findViewById(R.id.tv_date1);
		date2 = (TextView) findViewById(R.id.tv_date2);
		xueyuan_id = (TextView) findViewById(R.id.xueyuan_id);
		left = (FrameLayout) findViewById(R.id.date_left);
		right = (FrameLayout) findViewById(R.id.date_right);
//		classname = (RelativeLayout) findViewById(R.id.classname);
		Intent intent = getIntent();
		xueyuanExtra = intent.getExtras();
		name = xueyuanExtra.getString("name");
		xueyuan_id.setText(name);
		id = xueyuanExtra.getString("id");
		fm = getSupportFragmentManager();
		String setData = setData();
		date1.setText(setData);
		FragmentTransaction bt = fm.beginTransaction();
		bt.replace(R.id.fl_id, new Data_Fragment_Ri());
		bt.commit();
	}

	@Override
	public void onClick(View v) {//点击事件
		FragmentTransaction ft = fm.beginTransaction();
		switch (v.getId()) {
		case R.id.date_but :
			SetDateDialog sdd = new SetDateDialog();
			sdd.show(fm, "hah");
			break;
		case R.id.sandian:
			if (pop.isShowing()) {
				// 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
				pop.dismiss();
			} else {
				// 显示窗口
				pop.showAsDropDown(v);
			}
			break;
		case R.id.tt1 :
			rightPW=1;
			if(time==1){
				ft.replace(R.id.fl_id, new Data_Fragment_Ri());
			}else{
				ft.replace(R.id.fl_id, new Data_Fragment());
			}
			ft.commit();
			pop.dismiss();
			break;
		case R.id.tt2 :
			rightPW=2;
			if(time==1){
				ft.replace(R.id.fl_id, new Chart__FragmentRi());
			}else{
				ft.replace(R.id.fl_id, new Chart_Fragment());
			}
			ft.commit();
			pop.dismiss();
			break;
		case R.id.tt3 :
			if(!"".equals(id)){
				rightPW=3;
				ft.replace(R.id.fl_id, new CollegeTeachers_Fragment());
				ft.commit();
			}else{
				ToShow.show(this, "请去单个学院查询教师信息");
			}
			pop.dismiss();
			break;
		case R.id.tv_date1:
			showPop(left);//放到谁的下面
			break;
		case R.id.tv_date2:
			showPop(left);//放到谁的下面
			break;
		case R.id.ri:
			time=1;
			xueyuan_id.setText("日统计表");
			if(rightPW==1){
				ft.replace(R.id.fl_id, new Data_Fragment_Ri());
			}else if(rightPW==2){
				ft.replace(R.id.fl_id, new Chart__FragmentRi());
			}else {
				//显示老师出勤率的Fragment
			}
			popupWindow.dismiss();
			ft.commit();
//			date1.setText(setData());
			date2.setText("");
			break;
		case R.id.zhou:
			time=2;
			xueyuan_id.setText("周统计表");
			if(rightPW==1){
				ft.replace(R.id.fl_id, new Data_Fragment());
			}else if(rightPW==2){
				ft.replace(R.id.fl_id, new Chart_Fragment());
			}else {
				//显示老师出勤率的Fragment
			}
			popupWindow.dismiss();
			ft.commit();
//			date1.setText(setData());
			date2.setText("");
			break;
		case R.id.yue:
			time=3;
			xueyuan_id.setText("月统计表");
			if(rightPW==1){
				ft.replace(R.id.fl_id, new Data_Fragment());
			}else if(rightPW==2){
				ft.replace(R.id.fl_id, new Chart_Fragment());
			}else {
				//显示老师出勤率的Fragment
			}
			popupWindow.dismiss();
			ft.commit();
//			date1.setText(setData());
			date2.setText("");
			break;
		case R.id.cycle:
			time=4;
			xueyuan_id.setText("教学周期");
			if(rightPW==1){
				ft.replace(R.id.fl_id, new Data_Fragment());
			}else if(rightPW==2){
				ft.replace(R.id.fl_id, new Chart_Fragment());
			}else {
				//显示老师出勤率的Fragment
			}
			popupWindow.dismiss();
			ft.commit();
//			date1.setText(setData());
			date2.setText("");
			break;
		default:
			break;
		}

	}

	private void showPop(View v) {
		View inflate = LayoutInflater.from(XueYuanHuiZong_Activity.this)
				.inflate(R.layout.datechoicepop, (ViewGroup)findViewById(R.id.id_ll_xuanzechaxun),false);
		tv_ri = (TextView) inflate.findViewById(R.id.ri);
		tv_zhou = (TextView) inflate.findViewById(R.id.zhou);
		tv_yue = (TextView) inflate.findViewById(R.id.yue);
		tv_cycle = (TextView) inflate.findViewById(R.id.cycle);
		tv_ri.setOnClickListener(this);
		tv_yue.setOnClickListener(this);
		tv_zhou.setOnClickListener(this);
		tv_cycle.setOnClickListener(this);

		popupWindow = new PopupWindow(inflate, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("mengdd", "onTouch : ");
				if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
					popupWindow.dismiss();
					return true;
				}
				return false;
			}
		});
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.color.huihe));
		popupWindow.showAsDropDown(v);

	}

	private String setData() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return today = sDateFormat.format(new java.util.Date());

	}
	/*
	 * private void zhoudata() { SimpleDateFormat sDateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd"); String date = sDateFormat.format(new
	 * java.util.Date()); int week = getWeek(date); Calendar c =
	 * Calendar.getInstance(); int year = c.get(Calendar.YEAR); int month =
	 * c.get(Calendar.MONTH)+1; int day = c.get(Calendar.DAY_OF_MONTH); String
	 * judge = judge(year, day, month, week); String date2 = getDate(year1,
	 * month1, day1); data.setText(judge+"——"+date2+"  "+"本周"); }
	 */
	/*
	 * private int getWeek(String pTime) { int Week = 0 ; SimpleDateFormat
	 * format = new SimpleDateFormat("yyyy-MM-dd"); Calendar c =
	 * Calendar.getInstance(); try { c.setTime(format.parse(pTime)); } catch
	 * (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } if (c.get(Calendar.DAY_OF_WEEK) == 1) { Week =7; }
	 * if (c.get(Calendar.DAY_OF_WEEK) == 2) { Week = 1; } if
	 * (c.get(Calendar.DAY_OF_WEEK) == 3) { Week = 2; } if
	 * (c.get(Calendar.DAY_OF_WEEK) == 4) { Week= 3; } if
	 * (c.get(Calendar.DAY_OF_WEEK) == 5) { Week = 4; } if
	 * (c.get(Calendar.DAY_OF_WEEK) == 6) { Week = 5; } if
	 * (c.get(Calendar.DAY_OF_WEEK) == 7) { Week= 6; } return Week; } private
	 * String judge(int year,int day,int month,int week){ if (day-week-1<=0) {
	 * if (month-1==0) { year = year - 1; month = 12; }else { month = month -1;
	 * } if
	 * (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
	 * { day = 31 -(week-1-day); }else if
	 * (month==6||month==9||month==11||month==4) { day = 30 -(week-1-day); }else
	 * if (month==2) { if (year%4==0) { day = 29 -(week-1-day); }else { day =
	 * 28-(week -1 -day); } } }else { day = day - (week -1 ); } year1 = year;
	 * month1 = month; day1 = day; return year+"-"+month+"-"+day; } private
	 * String getDate(int year,int month,int day){
	 * 
	 * if
	 * (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
	 * { if (day + 4 > 31) { if (month + 1 == 13) { month = 1; year = year + 1;
	 * }else { month = month + 1; } day = day + 4 - 31; }else { day = day + 4; }
	 * }else if (month==2) { if (year%4==0) { if (day + 4 > 29) { month = month
	 * +1; day = day + 4 -29; }else { day = day + 4; } }else { if (day + 4 > 28)
	 * { month = month +1; day = day + 4 -28; }else { day = day + 4; } } }else
	 * if (month==6||month==9||month==11||month==4) { if (day + 4 > 30) { month
	 * = month + 1 ; day = day + 4 -30 ; }else { day = day + 4 ; } } return
	 * year+"-"+month+"-"+day;
	 * 
	 * } private void getMonth(){ Calendar c = Calendar.getInstance(); int month
	 * = c.get(Calendar.MONTH)+1; int year = c.get(Calendar.YEAR); if
	 * (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
	 * { data.setText(year+"-"+month +"-"+"1"+"——"+year+"-"+month +"-"+
	 * "31"+"   "+ "本月"); }else if (month == 2) { if (year%4==0) {
	 * data.setText(year+"-"+month +"-"+"1"+"——"+year+"-"+month+"-" +
	 * "29"+"   "+ "本月"); }else { data.setText(year+"-"+month
	 * +"-"+"1"+"——"+year+"-"+month +"-"+ "28"+"   "+ "本月"); } }else if
	 * (month==6||month==9||month==11||month==4) { data.setText(year+"-"+month
	 * +"-"+"1"+"——"+year+"-"+month +"-"+ "30"+"   "+ "本月"); }
	 * 
	 * }
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			if (IS_IN_TEACHERATTENDENCEDETAIL) {
				FragmentTransaction bt = XueYuanHuiZong_Activity.fm.beginTransaction();
				bt.replace(R.id.fl_id, new CollegeTeachers_Fragment());
				bt.commit();
				IS_IN_TEACHERATTENDENCEDETAIL=false;
				return true;
			}
			else if (IS_IN_TOTAL) {
				System.out.println("在子页面点击了返回教案");
				FragmentTransaction bt = XueYuanHuiZong_Activity.fm.beginTransaction();
				//TODO
				if(time==1){
					XueYuanHuiZong_Activity.id="";
					bt.replace(R.id.fl_id, new Data_Fragment_Ri());
				}else{
					XueYuanHuiZong_Activity.id="";
					bt.replace(R.id.fl_id, new Data_Fragment());
				}
				bt.commit();
				IS_IN_TOTAL=false;
				return true;
			}
			else {
				finish();
				return true;
			}
		}
		return true;
	}
}
