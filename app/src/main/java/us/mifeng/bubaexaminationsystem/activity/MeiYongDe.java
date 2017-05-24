package us.mifeng.bubaexaminationsystem.activity;

import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MeiYongDe extends Activity {
	private TextView tv_tv2,tv_tv3,tv2,tv3;
	private TextView tv_ydrs,tv_chuqinlv,tv_weijiren,tv_weijilv;
	private ImageView iv1,iv2;
	private GridView gv_student;
	private EditText et_period,et_sdrs;
	private EditText et_student,et_teacher,et_class;
	private String trim,trim2,trim3,trim4,trim5;
	private int shuzi=13;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amend_activity);
		JanyTools janyTools = new JanyTools() {
			public void zhong() {
				// 获取数据
				
			}
			public void qian() {
				initView();
				// 找id
				Intent inte = getIntent();
				inte.getStringArrayExtra("");// 得到传过来的数据
			}
			public void hou() { 
				initSet();//把我们从数据库得到的内容设置到文本中
				shiDao();
				gv_student.setAdapter(new MyAdap());
				gvJianTing();
			}
		};
		janyTools.open();
	}
	private void initSet() {
		tv_ydrs.setText("应到"+shuzi+"人，实到");
		et_sdrs.setText("13");
		tv_chuqinlv.setText("出勤率"+"100"+"%");
		tv_weijiren.setText("违纪人数"+"2"+"人");
		tv_weijilv.setText("违纪率"+"98"+"%");
		et_period.setText("2");
		et_sdrs.setText("10");
		et_student.setText("学生在好好学习");
		et_teacher.setText("老师在认真辅导");
		et_class.setText("一切正常");
	}
	private void initWay() {//得到我们输入的内容
		trim = et_period.getText().toString().trim();
		trim2 = et_sdrs.getText().toString().trim();
		trim3 = et_student.getText().toString().trim();
		trim4 = et_teacher.getText().toString().trim();
		trim5 = et_class.getText().toString().trim();
	}
	//找id
	private void initView() {
		gv_student = (GridView) findViewById(R.id.gv_student);
		et_sdrs = (EditText) findViewById(R.id.et_sdrs);
		tv_chuqinlv = (TextView) findViewById(R.id.tv_chuqinlv);
		tv_weijiren = (TextView) findViewById(R.id.tv_weijiren);
		tv_weijilv = (TextView) findViewById(R.id.tv_weijilv);
		et_student = (EditText) findViewById(R.id.et_student);
	}
	public void shiDao(){
		et_sdrs.addTextChangedListener(new TextWatcher(){
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			@Override
			public void afterTextChanged(Editable arg0) {
				String sdrs = et_sdrs.getText().toString().trim();
				if(!TextUtils.isEmpty(sdrs)){
					int lv=(Integer.parseInt(sdrs))*100/shuzi;
					tv_chuqinlv.setText("出勤率"+lv+"%");
				}
			}
		});
	}
	public void commit(View v){
		initWay();//得到我们修改后的内容
		if(TextUtils.isEmpty(trim)){
			ToShow.show(this, "课时未填写");
		}else if(TextUtils.isEmpty(trim2)){
			ToShow.show(this, "实到人数未填写");
		}else if(TextUtils.isEmpty(trim3)){	
			ToShow.show(this, "教师情况未填写");
		}else if(TextUtils.isEmpty(trim4)){
			ToShow.show(this, "学生情况未填写");
		}else{
			ToShow.show(this, "信息提交成功");
			//这里需要传数据
			Intent inte = getIntent();
			inte.putExtra("name", "name");
			setResult(1, inte);
			finish();
		}
	}
	public void gvJianTing(){
		gv_student.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				ToShow.show(Amend_Activity.this, "我点的是"+arg2);
				//这里需要传数据
				startActivity(new Intent(MeiYongDe.this,DangeXuesheng_Activity.class));
				//这里其实是一个跳转页面，传入数据的操作
			}
		});
	}
	public class MyAdap extends BaseAdapter{
		@Override
		public int getCount() {
			return 9;
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
			View v = View.inflate(MeiYongDe.this, R.layout.grivlist_fujian, null);
			TextView tv_2=(TextView) v.findViewById(R.id.gv_fujian_tv2);
			TextView tv_1=(TextView) v.findViewById(R.id.gv_fujian_tv1);
			ImageView iv_1=(ImageView) v.findViewById(R.id.gv_fujian_iv);
			tv_1.setText(arg0+"");
			tv_2.setText(arg0+"");
			return v;
		}
		
	}
}