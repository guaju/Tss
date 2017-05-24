package us.mifeng.bubaexaminationsystem.activity;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.BanJi_ID_Name;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.QieYuan;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Grade_Activity extends Activity {
	private ListView grade_list;
	private ArrayList<BanJi_ID_Name> list_banji;
	private String connect;
	private String xueyuan_name, xueyuan_id;
	// private int tu[] = { R.drawable.yi, R.drawable.er, R.drawable.san,
	// R.drawable.si, R.drawable.wu, R.drawable.liu, R.drawable.qi,
	// R.drawable.ba, R.drawable.jiu, R.drawable.shi, R.drawable.shiyi,
	// R.drawable.shier, R.drawable.shisan, R.drawable.shisi,
	// R.drawable.shiwu };
	private TextView title_tv;
	private boolean netWork;
	private ArrayList<String> banji_id;
	private int huise;
	private MyBase myBase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grade);
		JanyTools janyTools = new JanyTools() {

			public void zhong() {
				try {
					if (netWork) {
						connect = ConnectInternet.connect(JieKou.XUEYUAN_BANJI
								+ xueyuan_id + "&token="
								+ JieKou.getSp(Grade_Activity.this));
					}
				} catch (IOException e) {
					// 这里需要提示网络异常
					e.printStackTrace();
				}// 联网获取数据
				try {
					if (netWork) {
						getList(connect);// 解析得到的数据
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			public void qian() {
				initView();
				title_tv.setText(xueyuan_name);
			}

			public void hou() {
				if (netWork) {
					myBase = new MyBase();
					grade_list.setAdapter(myBase);
					listDong();
					dianJi();
				}
			}
		};
		janyTools.open();
	}

	public void fanhui(View v) {
		finish();
	}

	private void listDong() {
		Animation an = AnimationUtils.loadAnimation(Grade_Activity.this,
				R.anim.list_anim_right_to_left);
		LayoutAnimationController lac = new LayoutAnimationController(an);
		lac.setDelay(0.2f);// 动画的间隔时间
		lac.setOrder(LayoutAnimationController.ORDER_NORMAL);// 设置列表的显示顺序
		grade_list.setLayoutAnimation(lac);
	}

	private void initView() {
		huise = getResources().getColor(R.color.huise);
		grade_list = (ListView) findViewById(R.id.grade_list);
		Intent inte = getIntent();
		xueyuan_name = inte.getStringExtra("name");
		xueyuan_id = inte.getStringExtra("id");
		title_tv = (TextView) findViewById(R.id.grade_title);
		netWork = ConnectInternet.isNetWork(Grade_Activity.this);
		if (!netWork) {
			ToShow.show(Grade_Activity.this, "网络异常,请稍后重试");
		}
		banji_id = new ArrayList<String>();
	}

	public void dianJi() {// ListView点击事件
		grade_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent inte = new Intent();
				Bundle bund = new Bundle();
				boolean falg = true;
				BanJi_ID_Name banJi_ID_Name = list_banji.get(arg2);
				for (int i = 0; i < banji_id.size(); i++) {
					if (banji_id.get(i).equals(banJi_ID_Name.getId())) {
						falg = false;
					}
				}
				if (falg) {
					bund.putString("id", banJi_ID_Name.getId());
					bund.putString("xueyuan_id", xueyuan_id);
					inte.putExtras(bund);
					inte.setClass(Grade_Activity.this,
							ClassInput_Activity.class);
					startActivityForResult(inte, 1);
				} else {
					ToShow.show(Grade_Activity.this, "已查");
				}

			}
		});
	}

	public class MyBase extends BaseAdapter {
		public int getCount() {
			return list_banji.size();
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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View v = View.inflate(Grade_Activity.this,
					R.layout.grade_list_fujian, null);
			// ImageView img = (ImageView) v.findViewById(R.id.img);
			// Bitmap bit = BitmapFactory.decodeResource(getResources(),
			// tu[arg0]);
			// img.setImageBitmap(QieYuan.getYuan(bit));
			TextView grade_list_2 = (TextView) v
					.findViewById(R.id.grade_list_2);
			ImageView id_flag_1 = (ImageView) v
					.findViewById(R.id.id_flag_1);
			ImageView id_flag_2 = (ImageView) v
					.findViewById(R.id.id_flag_2);
			ImageView id_flag_3 = (ImageView) v
					.findViewById(R.id.id_flag_3);
			ImageView id_flag_4 = (ImageView) v
					.findViewById(R.id.id_flag_4);
			ImageView id_flag_5 = (ImageView) v
					.findViewById(R.id.id_flag_5);
			BanJi_ID_Name banJi_ID_Name = list_banji.get(arg0);
//			for (int i = 0; i < banji_id.size(); i++) {
//				if (banji_id.get(i).equals(banJi_ID_Name.getId())) {
//					v.setBackgroundColor(huise);
//				}
//			}
			grade_list_2.setText(banJi_ID_Name.getName());
			// add flags2016 11 06
			switch (banJi_ID_Name.getCount()) {
			case 1:
				id_flag_1.setVisibility(View.VISIBLE);
				id_flag_2.setVisibility(View.INVISIBLE);
				id_flag_3.setVisibility(View.INVISIBLE);
				id_flag_4.setVisibility(View.INVISIBLE);
				id_flag_5.setVisibility(View.INVISIBLE);
				break;

			case 2:
				id_flag_1.setVisibility(View.VISIBLE);
				id_flag_2.setVisibility(View.VISIBLE);
				id_flag_3.setVisibility(View.INVISIBLE);
				id_flag_4.setVisibility(View.INVISIBLE);
				id_flag_5.setVisibility(View.INVISIBLE);
				break;
			case 3:
				id_flag_1.setVisibility(View.VISIBLE);
				id_flag_2.setVisibility(View.VISIBLE);
				id_flag_3.setVisibility(View.VISIBLE);
				id_flag_4.setVisibility(View.INVISIBLE);
				id_flag_5.setVisibility(View.INVISIBLE);
				break;
			case 4:
				id_flag_1.setVisibility(View.VISIBLE);
				id_flag_2.setVisibility(View.VISIBLE);
				id_flag_3.setVisibility(View.VISIBLE);
				id_flag_4.setVisibility(View.VISIBLE);
				id_flag_5.setVisibility(View.INVISIBLE);
				break;
			case 5:
				id_flag_1.setVisibility(View.VISIBLE);
				id_flag_2.setVisibility(View.VISIBLE);
				id_flag_3.setVisibility(View.VISIBLE);
				id_flag_4.setVisibility(View.VISIBLE);
				id_flag_5.setVisibility(View.VISIBLE);
				break;
			default:
				
				break;
			}

			return v;
		}
	}

	public ArrayList<BanJi_ID_Name> getList(String a) throws JSONException {// Json解析
		list_banji = new ArrayList<BanJi_ID_Name>();
		JSONObject json = new JSONObject(a);
		JSONArray data = json.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			BanJi_ID_Name banJi_ID_Name = new BanJi_ID_Name();
			JSONObject jsob = (JSONObject) data.get(i);
			String class_name = (String) jsob.get("class_name").toString()
					.trim();
			String class_id = (String) jsob.get("class_id").toString().trim();
			String countString = jsob.get("count").toString().trim();
			int count = Integer.parseInt(countString);
			banJi_ID_Name.setName(class_name);
			banJi_ID_Name.setId(class_id);
			banJi_ID_Name.setCount(count);
			list_banji.add(banJi_ID_Name);
		}
		return list_banji;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			//banji_id.add(data.getStringExtra("id"));
			//System.out.println("返回的班级id" + banji_id);
			if (netWork) {
				connectInternet();
				
				// grade_list.setAdapter(new MyBase());
//				myBase.notifyDataSetChanged();// 刷新Listview
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	public void connectInternet(){
		JanyTools janyTools = new JanyTools() {

			public void zhong() {
				try {
					if (netWork) {
						connect = ConnectInternet.connect(JieKou.XUEYUAN_BANJI
								+ xueyuan_id + "&token="
								+ JieKou.getSp(Grade_Activity.this));
					}
				} catch (IOException e) {
					// 这里需要提示网络异常
					e.printStackTrace();
				}// 联网获取数据
				try {
					if (netWork) {
						getList(connect);// 解析得到的数据
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			public void qian() {
				initView();
				title_tv.setText(xueyuan_name);
			}

			public void hou() {
				if (netWork) {
					if (myBase==null) {
						myBase = new MyBase();						
						grade_list.setAdapter(myBase);
					}
					else {
						myBase.notifyDataSetChanged();
					}
					listDong();
					dianJi();
				}
			}
		};
		janyTools.open();
		
	}
}
