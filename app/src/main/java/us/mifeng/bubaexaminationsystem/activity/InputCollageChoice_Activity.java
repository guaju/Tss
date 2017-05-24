package us.mifeng.bubaexaminationsystem.activity;

import us.mifeng.bubaexaminationsystem.view.RippleView;
import us.mifeng.bubaexaminationsystem.view.RippleView.OnRippleCompleteListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class InputCollageChoice_Activity extends Activity implements
		OnRippleCompleteListener {

	private RelativeLayout tv_quanbu, tv_yidong, tv_dianzi, tv_gongshang,
			tv_jianzhu, tv_jisuan, tv_ruanjian, tv_shuzi;
	private RippleView rv_yd, rv_rg, rv_gs, rv_js, rv_jg, rv_sz, rv_dz,rv_cloud;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collage_activity);
		initView();
		initEvent();
	}

	public void initEvent() {
		rv_dz.setOnRippleCompleteListener(this);
		rv_gs.setOnRippleCompleteListener(this);
		rv_jg.setOnRippleCompleteListener(this);
		rv_js.setOnRippleCompleteListener(this);
		rv_rg.setOnRippleCompleteListener(this);
		rv_sz.setOnRippleCompleteListener(this);
		rv_yd.setOnRippleCompleteListener(this);
		rv_cloud.setOnRippleCompleteListener(this);
	}

	private void initView() {
		tv_yidong = (RelativeLayout) findViewById(R.id.rl_yidong);
		tv_dianzi = (RelativeLayout) findViewById(R.id.rl_dianzi);
		tv_gongshang = (RelativeLayout) findViewById(R.id.rl_gongshang);
		tv_jianzhu = (RelativeLayout) findViewById(R.id.rl_jianzhu);
		tv_jisuan = (RelativeLayout) findViewById(R.id.rl_jisuan);
		tv_ruanjian = (RelativeLayout) findViewById(R.id.rl_ruanjian);
		tv_shuzi = (RelativeLayout) findViewById(R.id.rl_shuzi);
		tv_quanbu = (RelativeLayout) findViewById(R.id.rl_allcollage);
		tv_quanbu.setVisibility(View.GONE);

		rv_yd = (RippleView) findViewById(R.id.rv_yd);
		rv_cloud = (RippleView) findViewById(R.id.rv_cloud);
		rv_sz = (RippleView) findViewById(R.id.rv_sz);
		rv_rg = (RippleView) findViewById(R.id.rv_rg);
		rv_jg = (RippleView) findViewById(R.id.rv_jg);
		rv_js = (RippleView) findViewById(R.id.rv_js);
		rv_dz = (RippleView) findViewById(R.id.rv_dz);
		rv_gs = (RippleView) findViewById(R.id.rv_gs);

	}

	public void click(View v) {
		finish();
	}

	@Override
	public void onComplete(RippleView rippleView) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		Intent intent = new Intent();
		switch (rippleView.getId()) {
		case R.id.rv_dz:
			// 这里需要传数据
			Log.e("wjl", "donghuawanchengle");
			bundle.putString("name", "电子商务学院");
			bundle.putString("id", "16");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_yd:
			// 这里需要传数据
			bundle.putString("name", "移动通信学院");
			bundle.putString("id", "17");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_cloud:
			// 这里需要传数据
			bundle.putString("name", "大数据云计算学院");
			bundle.putString("id", "18");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_gs:
			// 这里需要传数据
			bundle.putString("name", "工商学院");
			bundle.putString("id", "11");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_jg:
			// 这里需要传数据
			bundle.putString("name", "建筑工程学院");
			bundle.putString("id", "12");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_js:
			// 这里需要传数据
			bundle.putString("name", "计算机学院");
			bundle.putString("id", "13");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_sz:
			// 这里需要传数据
			bundle.putString("name", "数字媒体学院");
			bundle.putString("id", "15");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;
		case R.id.rv_rg:
			// 这里需要传数据
			bundle.putString("name", "软件工程学院");
			bundle.putString("id", "14");
			intent.putExtras(bundle);
			intent.setClass(InputCollageChoice_Activity.this,
					Grade_Activity.class);
			startActivity(intent);
			break;

		}

	}

}
