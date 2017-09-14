package us.mifeng.bubaexaminationsystem.app;

import android.content.Context;
import android.content.SharedPreferences;

public class JieKou {
	/**
	 * 服务器的主机地址
	 */
	
//	http://192.168.32.121/TSS
	
	public static String ZHUJI1 = "http://192.168.120.120/JRE";
	public static String ZHUJI2 = "http://192.168.188.237/TSS";
	public static String ZHUJI3 = "http://192.168.8.125/TSS";
	public static String ZHUJI4 = "http://192.168.190.110/TSS";
	public static String ZHUJI5 = "http://192.168.120.240/TSS";
	public static String ZHUJI = "http://223.72.255.100/JRE";
		public static String ZHUJI111 = "192.168.40.40/TSS";
	public static String ZHUJI6= "http://223.72.255.100/TSS";
	public static String ZHUJI7 = "http://192.168.186.46/TSS";
	public static String ZHUJI9999 = "http://192.168.4.188/TSS";

	
	/**
	 * 登陆的接口
	 */
	public static String DENGLU = ZHUJI + "/app/login.json";
	/**
	 * 获取学院列表的接口
	 */
	public static String XUEYUAN_LIST = ZHUJI + "/app/collegeList.json?token=";
	/**
	 * 获取各学院班级的接口
	 */
	public static String XUEYUAN_BANJI = ZHUJI+ "/app/classList.json?xueyuan_id=";
	/**
	 * 获取班级学生信息/老师信息的接口
	 */
	public static String BANJI_XUESHENG = ZHUJI+ "/app/classDetail.json?class_id=";
	/**
	 * 提交班级录入的接口
	 */
	public static String BANJI_TIJIAO = ZHUJI + "/app/commit.json?token=";// 提交的接口
	/**
	 * 修改查课记录的提交接口
	 */
	public static String BANJI_MODIFY = ZHUJI + "/app/modify.json?token=";// 提交的接口
	/**
	 * 查询学院统计信息的接口
	 */
	public static String QUERY_XUEYUAN = ZHUJI + "/app/departInfo.json?cycle=";
	/**
	 * 查询班级统计信息的接口
	 */
	public static String QUERY_BANJI = ZHUJI + "/app/classInfo.json?cycle=";
	/**
	 * 查询教学周期的接口
	 */
	public static String QUERY_JIAOXUEZHOUQI = ZHUJI+ "/app/teachingPeriodList.json?token=";
	
	public static String 	QUERY_KESHI = ZHUJI+"/app/recordList.json?date=";
	/**
	 * 获取各学院老师姓名的接口
	 */	
	public static String QUERY_LAOSHI_LIST = ZHUJI+ "/app/teacherList.json?depart_id=";
	/**
	 * 获取全院出勤违纪情况的接口
	 */	
	public static String QUANYUAN_CHUQINWEIJI = ZHUJI+ "/app/wholeRate.json?cycle=";
	/**
	 * 获取全院违纪类型的接口
	 */	
	public static String QUANYUAN_WEIJILEIXING = ZHUJI+ "/app/wholeDiscipline.json?cycle=";
	/**
	 * 获取学院违纪类型的接口
	 */	
	public static String XUEYUAN_WEIJILEIXING = ZHUJI+ "/app/disciplineInfo.json?cycle=";
	/**
	 * 获取每位老师的出勤率的接口
	 */
	public static String QUERY_LAOSHI_XINXI = ZHUJI+ "/app/teacherInfo.json?cycle=";
	/**
	 * 获取全校违纪类型的接口
	 */
	public static String QUERY_WEIJI= ZHUJI+ "/app/wholeDiscipline.json?cycle=";
	//教师出勤率统计(学院)
	public static String ATTENDENCE_RATE_COLLEGE=ZHUJI+"/app/teacherDepartAttendacne.json";
	//教师出勤率统计(班级)
	public static String ATTENDENCE_RATE_PER=ZHUJI+"/app/teacherClassAttendacne.json";
	
	public static String APKURL=ZHUJI+"/app/lastVersion.json";
	
	//查询班级详情
	public static String QUERY_DETAIL=ZHUJI+"/app/queryDetail.json?class_id=";
	
	/**
	 * 获取登录后的TOKEN值
	 */
	public static String getSp(Context context){
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String token = sp.getString("token", null);
		return token;
	}
	/**
	 * 获取账户信息
	 */
	public static int getType(Context context){
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Integer type = sp.getInt("type", 0);
		return type;
	}
	/**
	 * 获取当前账号的名字
	 */
	public static String getName(Context context){
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String name = sp.getString("name", "");
		
		return name;
		
	}
}