package us.mifeng.bubaexaminationsystem.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.app.JieKou;
import us.mifeng.bubaexaminationsystem.bean.Update;
import us.mifeng.bubaexaminationsystem.bean.WangLuo_HuiDiao;
import us.mifeng.bubaexaminationsystem.engine.ParseJson;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.JanyTools;
import us.mifeng.bubaexaminationsystem.utils.NetUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import us.mifeng.bubaexaminationsystem.utils.Volley_LianWang;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private ProgressDialog pd;
	private String url;
	private String ver;
	private String notes;
	private final int UPDATE = 123;
	private Update update = new Update();
	private boolean LockFlag = false;// 控制按钮
	private Handler handler = new Handler();
	private TextView version;
	private String versionName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initView();
		initData();
	}

	private void initView() {
		version = (TextView) findViewById(R.id.version);
		try {
			PackageManager pm = getPackageManager();
			PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
			versionName = pi.versionName;
			System.out.println(versionName);
			version.setText(versionName);
			System.out.println(versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initData() {
		System.out.println("有没有去看新版本");
		JanyTools janyTools = new JanyTools() {
			private String json;
			private String appUrl;
			@Override
			public void zhong() {
				try {
					URL url= new URL(JieKou.APKURL);
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					int responseCode = conn.getResponseCode();
					if (responseCode!=200) {
						System.out.println("你好中国"+json);
						runOnUiThread( new Runnable() {
							public void run() {
								ToShow.show(SplashActivity.this, "网络异常,请稍后重试");
								if (pd.isShowing()) {
									pd.dismiss();
								}
							}
						});
					}else {
						json = NetUtils.getJson(SplashActivity.this,JieKou.APKURL);
						JSONObject jsonObject = new JSONObject(json);
						JSONObject jo = (JSONObject) jsonObject.get("data");
						ver = (String) jo.get("ver");
						appUrl = (String) jo.get("url");
						System.out.println(ver);
						System.out.println(appUrl);	
					}
				} catch (JSONException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void qian() {
				pd =CommonUtils.getProgressDialog(SplashActivity.this, "正在加载,请稍后...");
				pd.setCancelable(false);
			}
			@Override
			public void hou() {
				if ((ver!=null)&&(!versionName.equals(ver))) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							// 提示更新
							ToShow.show(SplashActivity.this, "该版本已过时，请使用最新版本");
							// 等后台可以上传app时再做处理
						//	final String downLoadApkUrl = "https://guaju.github.io/TSS.apk";
							final String downLoadApkUrl = JieKou.ZHUJI+"/"+appUrl;
							final File file = new File(Environment
									.getExternalStorageDirectory()
									+ "/TSS"
									+ ver + ".apk");
							AlertDialog.Builder builder = new AlertDialog.Builder(
									SplashActivity.this,AlertDialog.THEME_HOLO_LIGHT);
							builder.setTitle("更新版本");
							builder.setMessage("请下载使用最新版本");
							builder.setCancelable(false);
							builder.setPositiveButton("下载",
									new OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// 定义下载的网址
											// 去下载
											new Thread() {
												public void run() {
													if (!pd.isShowing()) {
														pd.show();
													}
													pd.setCancelable(false);
													try {
														byte[] byteArrays=null;
														byteArrays = NetUtils
																.getByteArray(SplashActivity.this,downLoadApkUrl);
														System.out
																.println("这个新包的长度是"+byteArrays.length);
														FileOutputStream fos;
														fos = new FileOutputStream(
																file);
														fos.write(byteArrays,0,byteArrays.length);
													} catch (IOException e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
													}
													handler.post(new Runnable() {
														@Override
														public void run() {
															// TODO
															// Auto-generated
															// method stub
															// 跳转让用户安装应用
															Intent intent = new Intent();
															intent.setAction(Intent.ACTION_VIEW);
															intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
															intent.setDataAndType(
																	Uri.parse("file://"
																			+ file.getAbsolutePath()),
																	"application/vnd.android.package-archive");
															startActivity(intent);
															if (pd.isShowing()) {
																pd.dismiss();
															}
														}
													});
												};
											}.start();
										};
									});
							builder.show();
						}
					});
				} else {
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							if (!LockFlag) {
								startActivity(new Intent(SplashActivity.this,
										LoginActivity.class));
								finish();
							}
						}
					}, 3500);
					if (pd.isShowing()) {
						pd.dismiss();
					}

				}
			}
		};
		janyTools.open();
	}
	public void splash(View v){
			startActivity(new Intent(SplashActivity.this,
			LoginActivity.class));
			LockFlag=true;
			finish();
	}
}