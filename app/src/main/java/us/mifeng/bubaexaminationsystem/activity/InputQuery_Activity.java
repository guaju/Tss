package us.mifeng.bubaexaminationsystem.activity;


import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class InputQuery_Activity extends Activity {

    private String extra;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputquery_main);
        extra = getIntent().getStringExtra("权限");
        System.out.println("收到的消息是"+extra);
      
    }
    public void input(View v){
    	if ("查询".equals(extra)) {
			ToShow.show(InputQuery_Activity.this, "很抱歉，您的权限不足");
			return ;
		}
//    	Intent intent = new Intent(InputQuery_Activity.this,InputQueryActivity.class);
//    	intent.putExtra("录入", 123);
//    	startActivity(intent);
    	else if ("".equals(extra)) {
			System.out.println("录入了");
			startActivity(new Intent(InputQuery_Activity.this,InputCollageChoice_Activity.class));
			
		}
		
    }
    public void query(View v){
//    	Intent intent = new Intent(InputQuery_Activity.this,InputQueryActivity.class);
//    	intent.putExtra("查询", 321);
//    	startActivity(intent);
    	Intent intent = new Intent(InputQuery_Activity.this,SearchChoiceCollage_Activity.class);
    	startActivity(intent);
    	
    }
    public void click(View v){
    	finish();
    }
}