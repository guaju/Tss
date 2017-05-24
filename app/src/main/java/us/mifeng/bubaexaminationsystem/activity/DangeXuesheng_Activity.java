package us.mifeng.bubaexaminationsystem.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.utils.ToShow;

import android.R.integer;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class DangeXuesheng_Activity extends Activity {

	private TextView banji_title, tv_biaoti;
	// private ImageView iv_photo;
	private TextView tv_name, tv_sex, tv_qingkuang,tingket;
	private EditText et_weiji;
	private String mingzi,zwbianhao;
	private String xingbie,bianhao,zhuangtai;
	private String laoshi_name,banji_name,ziwu;
	private int fHM;
	private LinearLayout qq;
	private LinearLayout qingjia,wshouji,shueijiao,danao,dayouxi,tingke,
	chidongxi,shangcesuo,chidao,daierji,kanshiping,qita,xiuxue,kuangke,junzhuang,sanfangqingjial;
	HashMap<Integer,Boolean> tagMap=new HashMap<Integer,Boolean>();
	private boolean TAG_QUEQIN=false;
	private Boolean budianji2=false;
	private Boolean budianji3=false;
	private Boolean budianji4=false;
	private Boolean budianji5=false;
	private Boolean budianji6=false;
	private Boolean budianji7=false;
	private Boolean budianji8=false;
	private Boolean budianji9=false;
	private Boolean budianji10=false;
	private Boolean budianji11=false;
	private Boolean budianji12=false;
	private Boolean budianji13=false;
	private Boolean budianji14=false;
	private Boolean budianji15=false;
	private Boolean budianji99=false;
	private TextView qingjiat,xiuxuet,shueijiaot,wshoujit,danaot,chidongxit,
	shangcesuot,chidaot,daierjit,kanshipingt,dayouxit,qitat,kuangket,junzhuangt,sanfangqingjiat;
	private ArrayList<String> list;
	private String notes;
	private String disciplinarycategory_id;
	private ArrayList<Integer> fWeijiList= new ArrayList<Integer>();
	String studentStatus="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dangexuesheng_activity);
		tagMap.put(1,TAG_QUEQIN);
		tagMap.put(2,budianji2);
		tagMap.put(3,budianji3);
		tagMap.put(4,budianji4);
		tagMap.put(5,budianji5);
		tagMap.put(6,budianji6);
		tagMap.put(7,budianji7);
		tagMap.put(8,budianji8);
		tagMap.put(9,budianji9);
		tagMap.put(10,budianji10);
		tagMap.put(11,budianji11);
		tagMap.put(12,budianji12);
		tagMap.put(13,budianji13);
		tagMap.put(14,budianji14);
		tagMap.put(15,budianji15);
		tagMap.put(99,budianji99);
		initView();
		initEvent();
		
	}

	public void fanhui(View v) {
		finish();
	}		
	
	private void initView() {
		list = new ArrayList<String>();
		banji_title = (TextView) findViewById(R.id.banji_title);
		tv_biaoti = (TextView) findViewById(R.id.tv_biaoti);
		// iv_photo = (ImageView) findViewById(R.id.iv_photo);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_sex = (TextView) findViewById(R.id.tv_sex);
		tv_qingkuang = (TextView) findViewById(R.id.tv_qingkuang);
		et_weiji = (EditText) findViewById(R.id.et_weiji);
		
		qingjia = (LinearLayout) findViewById(R.id.qingjia);
		xiuxue = (LinearLayout) findViewById(R.id.xiuxue);
		
		kuangke = (LinearLayout) findViewById(R.id.kuangke);
		wshouji = (LinearLayout) findViewById(R.id.wshouji);
		kuangket = (TextView) findViewById(R.id.kuangket);
		wshoujit = (TextView) findViewById(R.id.wshoujit);
		shueijiao = (LinearLayout) findViewById(R.id.shueijiao);
		
		tingket = (TextView) findViewById(R.id.tingket);
		tingke = (LinearLayout) findViewById(R.id.tingke);
		junzhuang = (LinearLayout) findViewById(R.id.junzhuang);
		sanfangqingjial = (LinearLayout) findViewById(R.id.sanfangqingjial);
		shueijiaot = (TextView) findViewById(R.id.shueijiaot);
		danao = (LinearLayout) findViewById(R.id.danao);
		danaot = (TextView) findViewById(R.id.danaot);
		chidongxi = (LinearLayout) findViewById(R.id.chidongxi);
		chidongxit = (TextView) findViewById(R.id.chidongxit);
		shangcesuo = (LinearLayout) findViewById(R.id.shangcesuo);
		shangcesuot = (TextView) findViewById(R.id.shangcesuot);
		chidao = (LinearLayout) findViewById(R.id.chidao);
		chidaot = (TextView) findViewById(R.id.chidaot);
		daierji = (LinearLayout) findViewById(R.id.daierji);
		daierjit = (TextView) findViewById(R.id.daierjit);
		kanshiping = (LinearLayout) findViewById(R.id.kanshiping);
		kanshipingt = (TextView) findViewById(R.id.kanshipingt);
		dayouxi = (LinearLayout) findViewById(R.id.dayouxi);
		qita = (LinearLayout) findViewById(R.id.qita);
		dayouxit = (TextView) findViewById(R.id.dayouxit);
		qingjiat = (TextView) findViewById(R.id.qingjiat);
		xiuxuet = (TextView) findViewById(R.id.xiuxuet);
		qitat = (TextView) findViewById(R.id.qitat);
		junzhuangt = (TextView) findViewById(R.id.junzhuangt);
		sanfangqingjiat = (TextView) findViewById(R.id.sanfangqingjiat);
	}
	//请假的方法
	
	public void qingjia(View v){
		qingjia();
	}
	
	public void tingke(View v){
		tingke();
	}
	public void kuangke(View v){
		kuangke();
	}
	
	public void xiuxue(View v){
		xiuxue();
	}
	public void junzhuang(View v){
		junzhuang();
	}
	public void sanfangqingjia(View v){
		sanfangqingjia();
	}

	private void qingjia() {
		changeStatus(1,qingjia,qingjiat);
	}
	private void junzhuang() {
		changeStatus(11,junzhuang,junzhuangt);
	}
	//修改三方请假的东西
	private void sanfangqingjia() {
		changeStatus(15,sanfangqingjial,sanfangqingjiat);
	}
	
	
	private void tingke() {
		changeStatus(14, tingke, tingket);
		
	}
	private void xiuxue() {
		changeStatus(12, xiuxue, xiuxuet);
	}
	
	private void shuijiao() {
		changeStatus(2, shueijiao, shueijiaot);
	}
	private void kuangke() {
		changeStatus(13, kuangke, kuangket);
	}
	private void danao() {
		changeStatus(10, danao, danaot);
	}
	public void shueijiao(View v){
		shuijiao();
	}

	public void danao(View v){
		danao();
	}

	private void wanshouji() {
		changeStatus(5, wshouji, wshoujit);
	}
	public void wshouji(View v){
		wanshouji();
	}
	private void dayouxi() {
		changeStatus(6, dayouxi, dayouxit);
	}
	public void dayouxi(View v){
		dayouxi();
	}
	private void chidongxi() {
		changeStatus(7, chidongxi, chidongxit);
	}
	public void chidongxi(View v){
		chidongxi();
	}
	private void shangcesuo() {
		changeStatus(8, shangcesuo, shangcesuot);
	}
	public void shangcesuo(View v){
		shangcesuo();
	}
	private void chidao() {
		changeStatus(3, chidao, chidaot);
	}
	public void chidao(View v){
		chidao();
	}
	private void daierji() {
		changeStatus(9, daierji, daierjit);
	}
	
	public void daierji(View v){
		daierji();
	}
	private void kanshipin() {
		changeStatus(4, kanshiping, kanshipingt);
	}
	
	public void kanshiping(View v){
		kanshipin();
	}

	private void qita() {
		changeStatus(99, qita, qitat);
	}
	public void qita(View v){
		qita();
	}

//	private void qita() {
//		if (tagMap.get(11)) {
//			tagMap.put(11, false);
//			qita.setBackgroundResource(R.drawable.weiji);
//			Iterator<String> iterator = list.iterator();
//			while (iterator.hasNext()) {
//				String string=iterator.next();
//				if("其它".equals(string)){
//					iterator.remove();
//				}
//			}
//			if (list.size()==0) {
//				et_weiji.setText("");
//			}
//			et_weiji.setText("");
//			for(String tmp:list){
//				et_weiji.setText(et_weiji.getText()+" "+tmp);
//			}
//		}
//		else {
//			tagMap.put(11, true);
//			qita.setBackgroundResource(R.drawable.weiji2);
//			list.add((String) qitat.getText());
//			et_weiji.setText("");
//			for(String tmp:list){
//				et_weiji.setText(et_weiji.getText()+" "+tmp);
//			}
//		}
//	}
	//回显数据的逻辑
	private void initEvent() {
		
//		et_weiji.setInputType(InputType.TYPE_NULL);
		Intent inte = getIntent();
		Bundle extras = inte.getExtras();
		notes = extras.get("notes")+"";
		ArrayList<Integer> weijiList = extras.getIntegerArrayList("weijiList");
		//回显数据
		String string = extras.getString("zhuangtai");
		int status = Integer.parseInt(string);
		switch (status) {
		case 3:
			qingjia();
			break;
		case 5:
			sanfangqingjia();
			break;
		case 9:
			xiuxue();
			break;
		case 4:
			tingke();
			break;
		default:
			break;
		}
		//回显违纪列表的数据
		if (weijiList!=null&&weijiList.size()!=0) {
			
			for (Integer integer : weijiList) {
				System.out.println(integer+"得到的数是");
				switch (integer) {
				
				case 2:
					shuijiao();
					break;
				case 3:
					chidao();
					break;
				case 4:
					kanshipin();
					break;
				case 5:
					wanshouji();
					break;
				case 6:
					dayouxi();
					break;
				case 7:
					chidongxi();
					break;
				case 8:
					shangcesuo();
					break;
				case 9:
					daierji();
					break;
				case 10:
					danao();
					break;
				case 11:
					junzhuang();
					break;
				case 99:
					qita();
					break;
				case 13:
					kuangke();
					break;
				case 1000:
					kuangke();
					break;
				default:
					break;
				}
	
			}
					}
		
		
		
		mingzi = inte.getStringExtra("name");
		String tog = inte.getStringExtra("tog");
		fHM = Integer.parseInt(tog);
		xingbie = inte.getStringExtra("sex");
		laoshi_name = inte.getStringExtra("laoshi_name");
		banji_name = inte.getStringExtra("banji_name");
		System.out.println(mingzi+laoshi_name);
		ziwu = inte.getStringExtra("ziwu");
		bianhao = inte.getStringExtra("bianhao");
		Log.e("wjlwjl", bianhao+"---------------haoba11");
		
		zhuangtai = inte.getStringExtra("zhuangtai");
		zwbianhao = inte.getStringExtra("zwbianhao");
		if("1".equals(tog)){
			tv_name.setText(mingzi);
			tv_sex.setText(xingbie);
			banji_title.setText(banji_name);
		}
		if("2".equals(tog)){
			tv_name.setText(ziwu);
			tv_sex.setText(laoshi_name);
			banji_title.setText(banji_name);
			tv_biaoti.setText("");
			tv_qingkuang.setText("教师情况记录");
			
			
			String notes = inte.getStringExtra("notes");
			et_weiji.setText(notes);
			
			qingjia.setVisibility(View.GONE);
			sanfangqingjial.setVisibility(View.GONE);
			shueijiao.setVisibility(View.GONE);
			chidao.setVisibility(View.GONE);
			kanshiping.setVisibility(View.GONE);
			wshouji.setVisibility(View.GONE);
			dayouxi.setVisibility(View.GONE);
			chidongxi.setVisibility(View.GONE);
			shangcesuo.setVisibility(View.GONE);
			daierji.setVisibility(View.GONE);
			danao.setVisibility(View.GONE);
			qita.setVisibility(View.GONE);
			xiuxue.setVisibility(View.GONE);
			kuangke.setVisibility(View.GONE);
			tingke.setVisibility(View.GONE);
			junzhuang.setVisibility(View.GONE);
		}
		
	}
//提交返回的逻辑
	public void click(View v) {
		Set<Entry<Integer, Boolean>> entrySet = tagMap.entrySet();
		for (Entry<Integer, Boolean> entry : entrySet) {
			System.out.println(entry.getKey()+"-----"+entry.getValue());
			if (entry.getValue()&&(entry.getKey()!=1)&&(entry.getKey()!=12)&&(entry.getKey()!=13)&&(entry.getKey()!=14)&&(entry.getKey()!=15)) {
				fWeijiList.add(entry.getKey());
				studentStatus=1+"";
			}else if (entry.getValue()&&(entry.getKey()==1)) {
				fWeijiList.add(10000);
				studentStatus=3+"";
			}else if (entry.getValue()&&(entry.getKey()==12)) {
				fWeijiList.add(10000);
				studentStatus=9+"";
			}else if (entry.getValue()&&(entry.getKey()==13)) {
				fWeijiList.add(1000);
				studentStatus=1000+"";
			}else if (entry.getValue()&&(entry.getKey()==14)) {
				//只加到存状态的一个集合里面
				fWeijiList.add(10000);
				studentStatus=4+"";
			}else if (entry.getValue()&&(entry.getKey()==15)) {
				//只加到存状态的一个集合里面
				fWeijiList.add(10000);
				studentStatus=5+"";
			}
		}
		
		
		System.out.println(fWeijiList.size()+"hahahaha");
		
		
		
		
			if(fHM==1){//是学生返回
				if(fWeijiList.size()==0&&studentStatus==""){			
					ToShow.show(DangeXuesheng_Activity.this, "没有计入违纪信息，请确认。");
				}
				Intent intent1 = new Intent();
				Bundle bun= new Bundle();
				bun.putIntegerArrayList("fweijilist", fWeijiList);
				bun.putString("bianhao", bianhao);
				bun.putString("status", studentStatus);
				bun.putString("notes",et_weiji.getText().toString().trim());
				intent1.putExtras(bun);
				setResult(fHM, intent1);
				finish();	
			}
			if(fHM==2){//是老师返回
				Intent intent2 = new Intent();
				Bundle bund= new Bundle();
				String fNotes = et_weiji.getText().toString().trim();
				if (TextUtils.isEmpty(fNotes)) {
					ToShow.show(DangeXuesheng_Activity.this, "没有输入信息，请确认。");
				}
				//bund.putString("qingkuang", student_qingkuang);
				System.out.println("roles"+ziwu+"bianhao---"+bianhao+"ffsdfasdfasd"+fNotes);
				bund.putString("roles",ziwu);
				bund.putString("zhuangtai", zhuangtai);
				bund.putString("Tbianhao", bianhao);
				bund.putString("Tnotes", fNotes);
				intent2.putExtras(bund);
				this.setResult(2, intent2);
				System.out.println(intent2==null?"是null":"不是null");
				finish();	
			}
		}	
	
	
	private void changeStatus(int index,LinearLayout linearLayout,TextView textView) {
		if (tagMap.get(index)) {
			tagMap.put(index, false);
			linearLayout.setBackgroundResource(R.drawable.weiji);
			et_weiji.setText("");
		}
		else {
			
			qingjia.setBackgroundResource(R.drawable.weiji);
			wshouji.setBackgroundResource(R.drawable.weiji);
			shueijiao.setBackgroundResource(R.drawable.weiji);
			danao.setBackgroundResource(R.drawable.weiji);
			chidongxi.setBackgroundResource(R.drawable.weiji);
			shangcesuo.setBackgroundResource(R.drawable.weiji);
			chidao.setBackgroundResource(R.drawable.weiji);
			daierji.setBackgroundResource(R.drawable.weiji);
			kanshiping.setBackgroundResource(R.drawable.weiji);
			dayouxi.setBackgroundResource(R.drawable.weiji);
			qita.setBackgroundResource(R.drawable.weiji);
			xiuxue.setBackgroundResource(R.drawable.weiji);
			kuangke.setBackgroundResource(R.drawable.weiji);
			tingke.setBackgroundResource(R.drawable.weiji);
			junzhuang.setBackgroundResource(R.drawable.weiji);
			sanfangqingjial.setBackgroundResource(R.drawable.weiji);
			
			linearLayout.setBackgroundResource(R.drawable.weiji2);
			et_weiji.setText(textView.getText());
			if (99!=index) {
				et_weiji.setInputType(InputType.TYPE_NULL);
				et_weiji.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
					ToShow.show(DangeXuesheng_Activity.this, "此选项下不可编辑,只可在'其他'选项处可编辑");	
					et_weiji.setInputType(InputType.TYPE_NULL);
					}
				});
			}
			else {
				et_weiji.setInputType(InputType.TYPE_CLASS_TEXT);
				et_weiji.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
							et_weiji.setText("");
					}
				});
			}
			tagMap.put(1,false);
			tagMap.put(2,false);
			tagMap.put(3,false);
			tagMap.put(4,false);
			tagMap.put(5,false);
			tagMap.put(6,false);
			tagMap.put(7,false);
			tagMap.put(8,false);
			tagMap.put(9,false);
			tagMap.put(10,false);
			tagMap.put(11,false);
			tagMap.put(12,false);
			tagMap.put(13,false);
			tagMap.put(14,false);
			tagMap.put(15,false);
			tagMap.put(99,false);
			tagMap.put(index, true);
			
		}
	}
	}				

