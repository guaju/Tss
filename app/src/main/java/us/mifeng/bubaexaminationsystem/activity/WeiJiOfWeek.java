package us.mifeng.bubaexaminationsystem.activity;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.Classs;
import us.mifeng.bubaexaminationsystem.utils.ConnectInternet;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WeiJiOfWeek extends Activity {
	private ListView lv_zhou;
	private String curr1;
	private String curr2;
	private String id;
	private ArrayList<Classs> list;
	private Classs classs;
	private TextView tv;
	private String xy_id;
	private String time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhou_item);
		initView();
		initList();
		initEvent();

	}
	//listview的条目点击事件
	private void initEvent() {
		
		lv_zhou.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("date1", list.get(position).getDate1());
				bundle.putString("date2", list.get(position).getDate2());
				bundle.putString("class_name", list.get(position).getClass_name());
				bundle.putString("id", list.get(position).getId());
				bundle.putString("xy_id", xy_id);
				intent.putExtras(bundle);
				if("W".equals(time)){
					intent.setClass(WeiJiOfWeek.this,WeiJiOfDay.class);
				}
				else if ("D".equals(time)) {
					intent.setClass(WeiJiOfWeek.this,ClassOverall_Activity.class);
				}
				
				startActivity(intent);
			}
		});
		
	}

	private void initList() {
		String BANJI = JieKou.QUERY_BANJI;
		final String url = BANJI + time + "&date1=" + curr1 + "&date2=" + curr2
				+ "&token=" + JieKou.getSp(WeiJiOfWeek.this) + "&class_id="
				+ id+"&depart_id="+xy_id;
		new JanyTools() {
			public void zhong() {
				try {
					String connect = ConnectInternet.connect(url);
					System.out.println(connect);
					parse(connect);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			public void qian() {

			}

			public void hou() {
				tv.setText(classs.getClass_name());
				lv_zhou.setAdapter(new MyAdapter());
			}
		}.open();
	}

	public void fanhui(View v) {
		finish();
	}

	private void initView() {
		lv_zhou = (ListView) findViewById(R.id.lv_zhou_yue);
		tv = (TextView) findViewById(R.id.banji_title);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		curr1 = bundle.getString("curr1");
		curr2 = bundle.getString("curr2");
		xy_id = bundle.getString("xy_id");
		time = bundle.getString("Time");
		id = bundle.getString("id");
		System.out.println(curr1 + "---" + curr2);

	}

	private void parse(String str) {
		try {
			list = new ArrayList<Classs>();
			JSONObject object = new JSONObject(str);
			JSONObject ob = (JSONObject) object.get("data");
			JSONArray array = (JSONArray) ob.get("list");
			for (int i = 0; i < array.length(); i++) {
				classs = new Classs();
				JSONObject info = (JSONObject) array.get(i);
				String date1 = (String) info.get("date1");
				String date2 = (String) info.get("date2");
				String classname = (String) info.get("class_name");
				int class_id =  Integer.parseInt(info.get("class_id")+"");
				
				String chuqinlv =  info.get("attendance_rate")+"";
				String shidao =  info.get("actual_attendance")+"";
				String time =  info.get("cycle")+"";
				String weiji =  info.get("violation")+"";
				String yingdao =  info.get("supposed_attendance")+"";
				String weijilv = info.get("discipline_rate")+"";
				classs.setDate1(date1);
				classs.setDate2(date2);
				classs.setchuqinlv(chuqinlv);
				classs.setClass_name(classname);
				classs.setId(class_id+"");
				classs.setshidao(shidao);
				classs.setTime(time);
				classs.setweiji(weiji);
				classs.setweijilv(weijilv);
				classs.setyingdao(yingdao);
				list.add(classs);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private class MyAdapter extends BaseAdapter {

		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View v = View.inflate(WeiJiOfWeek.this, R.layout.xinxi_query, null);
			TextView tv_date = (TextView) v.findViewById(R.id.tv_time);
			TextView tv_yingdao = (TextView) v.findViewById(R.id.ydrs);
			TextView tv_shidao = (TextView) v.findViewById(R.id.sdrs);
			TextView tv_chuqinlv = (TextView) v.findViewById(R.id.cql);
			TextView tv_weijilv = (TextView) v.findViewById(R.id.wjl);
			TextView tv_weiji = (TextView) v.findViewById(R.id.wjrs);
			tv_date.setText(list.get(position).getTime());
			tv_yingdao.setText("应到人数：" + list.get(position).getyingdao());
			tv_shidao.setText("实到人数：" + list.get(position).getshidao());
			tv_chuqinlv.setText("出勤率：" + list.get(position).getchuqinlv() + "%");
			tv_weijilv.setText("违纪率：" + list.get(position).getweijilv() + "%");
			tv_weiji.setText("违纪人数：" + list.get(position).getweiji());
			return v;
		}
	}
}
