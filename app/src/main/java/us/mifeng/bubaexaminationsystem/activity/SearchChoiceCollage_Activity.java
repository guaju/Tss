package us.mifeng.bubaexaminationsystem.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import us.mifeng.bubaexaminationsystem.view.RippleView;
import us.mifeng.bubaexaminationsystem.view.RippleView.OnRippleCompleteListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class SearchChoiceCollage_Activity extends Activity implements OnRippleCompleteListener{

	private RelativeLayout tv_syidong,tv_sdianzi,tv_sgongshang,tv_sshuzi,tv_ssjianzhu,tv_sjisuan,tv_sruanjian,tv_allcollage;
	private RippleView rp_yd,rp_rg,rp_jg,rp_wl,rp_sz,rp_gs,rp_jsj,rp_quan,rv_cloud;
	private String extra;
	private List<String> list = new ArrayList<String>();
	private Intent intent;
	private String name;
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collagechoice_activity);
		initView();
		initData();
		initEvent();

	}

	private void initData() {
		String xueyuan_LIST = JieKou.XUEYUAN_LIST;
		String token = JieKou.getSp(SearchChoiceCollage_Activity.this);
		final String url = xueyuan_LIST+token;
		new JanyTools() {
			private String str;
			@Override
			public void zhong() {
				try {
					str = ConnectInternet.connect(url);
					Log.e("收到的数据是", str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void qian() {
			}
			@Override
			public void hou() {
				jiexi(str);
			}
		}.open();
	}

	private void jiexi(String str) {
		try {
			JSONObject jo = new JSONObject(str);
			JSONArray ja = jo.getJSONArray("data");
			for (int i = 0; i < ja.length(); i++) {
				list.add(ja.get(i)+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initEvent() {
		rp_yd.setOnRippleCompleteListener(this);
		rp_rg.setOnRippleCompleteListener(this);
		rp_jg.setOnRippleCompleteListener(this);
		rp_wl.setOnRippleCompleteListener(this);
		rp_sz.setOnRippleCompleteListener(this);
		rp_gs.setOnRippleCompleteListener(this);
		rp_jsj.setOnRippleCompleteListener(this);
		rp_quan.setOnRippleCompleteListener(this);
		rv_cloud.setOnRippleCompleteListener(this);
	}

	private void initView() {
		tv_syidong = (RelativeLayout) findViewById(R.id.tv_syidong);
		tv_sdianzi = (RelativeLayout) findViewById(R.id.tv_sdianzi);
		tv_sgongshang = (RelativeLayout) findViewById(R.id.tv_sgongshang);
		tv_sshuzi = (RelativeLayout) findViewById(R.id.tv_sshuzi);
		tv_ssjianzhu = (RelativeLayout) findViewById(R.id.tv_sjianzhu);
		tv_sjisuan = (RelativeLayout) findViewById(R.id.tv_sjisuan);
		tv_sruanjian = (RelativeLayout) findViewById(R.id.tv_sruanjian);
		tv_allcollage = (RelativeLayout) findViewById(R.id.searchcollage);

		rv_cloud = (RippleView) findViewById(R.id.rv_cloud);
		rp_yd = (RippleView) findViewById(R.id.rp_yd);
		rp_rg = (RippleView) findViewById(R.id.rp_rg);
		rp_jg = (RippleView) findViewById(R.id.rp_jg);
		rp_wl = (RippleView) findViewById(R.id.rp_wl);
		rp_sz = (RippleView) findViewById(R.id.rp_sz);
		rp_gs = (RippleView) findViewById(R.id.rp_gs);
		rp_jsj = (RippleView) findViewById(R.id.rp_jsj);
		rp_quan = (RippleView) findViewById(R.id.rp_quan);
		name = JieKou.getName(SearchChoiceCollage_Activity.this);
		type = JieKou.getType(SearchChoiceCollage_Activity.this);
		Log.e("接收到的学院名称是", name);
	}


	public void click(View v){
		finish();
	}

	@Override
	public void onComplete(RippleView rippleView) {
		
		switch (rippleView.getId()){
		case R.id.rp_yd:
			Bundle bundle = new Bundle();
			intent = new Intent();
			bundle.putString("name", "移动");
			bundle.putString("id", "17");
			intent.putExtras(bundle);
			panDuan("移动");
			
			
//			xueYuan("移动");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
			
			
		case R.id.rv_cloud:
			Bundle bundle8 = new Bundle();
			intent = new Intent();
			bundle8.putString("name", "数据云");
			bundle8.putString("id", "18");
			intent.putExtras(bundle8);
			panDuan("数据云");
			
			
//			xueYuan("移动");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_wl:
			Bundle bundle1 = new Bundle();
			intent = new Intent();
			bundle1.putString("name", "电商");
			bundle1.putString("id", "16");
			intent.putExtras(bundle1);
			panDuan("电商");
			
			
//			xueYuan("电商");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_gs:
			Bundle bundle2 = new Bundle();
			intent = new Intent();
			bundle2.putString("name", "工商");
			bundle2.putString("id", "11");
			intent.putExtras(bundle2);
			panDuan("工商");
			
			
//			xueYuan("工商");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_sz:
			Bundle bundle3 = new Bundle();
			intent = new Intent();
			bundle3.putString("name", "数字");
			bundle3.putString("id", "15");
			intent.putExtras(bundle3);
			panDuan("数字");
			
			
//			xueYuan("数字");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_jg:
			Bundle bundle4 = new Bundle();
			intent = new Intent();
			bundle4.putString("name", "建工");
			bundle4.putString("id", "12");
			intent.putExtras(bundle4);
			panDuan("建工");
			
			
//			xueYuan("建工");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_jsj:
			Bundle bundle5 = new Bundle();
			intent = new Intent();
			bundle5.putString("name", "计算机");
			bundle5.putString("id", "13");
			intent.putExtras(bundle5);
			panDuan("计算机");
			
			
//			xueYuan("计算机");
//			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_rg:
			Bundle bundle6 = new Bundle();
			intent = new Intent();
			bundle6.putString("name", "软工");
			bundle6.putString("id","14");
			intent.putExtras(bundle6);
			panDuan("软工");
			
			
//			xueYuan("软工");
//			intent.setClass (SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
//			startActivity(intent);
			break;
		case R.id.rp_quan:
			Bundle bundle7 = new Bundle();
			intent = new Intent();
			bundle7.putString("name", "全部学院");
			bundle7.putString("id","");
			intent.putExtras(bundle7);
			if (type>1100) {
				intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
				startActivity(intent);
			}else {
				ToShow.show(SearchChoiceCollage_Activity.this, "并没有权限- -");
			}
			
			break;
		}
	}
	public void panDuan(String str){
		if (type>1100) {
			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
			startActivity(intent);
		}
		else {
			if (str.equals(name)) {
				intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
				startActivity(intent);
			}
			else {
				ToShow.show(SearchChoiceCollage_Activity.this, "并没有权限- -");
			}
		}
	}
	/*public void xueYuan(String str){
		Log.e("-------------",""+list.size());
		if (list.size()>1) {
			
			intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
		}
		else {
			for (int i = 0; i < list.size(); i++) {
				if (str.equals(list.get(i))) {
					intent.setClass(SearchChoiceCollage_Activity.this, XueYuanHuiZong_Activity.class);
				}else {
					ToShow.show(SearchChoiceCollage_Activity.this, "并没有权限- -");
				}
			}
		}
		startActivity(intent);
	}*/
	
}