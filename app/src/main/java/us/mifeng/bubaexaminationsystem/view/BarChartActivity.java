package us.mifeng.bubaexaminationsystem.view;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;

public class BarChartActivity extends Activity {

    private BarChart mBarChart;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        
        mBarChart = (BarChart) findViewById(R.id.spread_bar_chart);
        BarData mBarData =getBarData(7, 1);
        showBarChart(mBarChart, mBarData);
       }
       
       private void showBarChart(BarChart barChart, BarData barData) {
//    	   XAxis xAxis = barChart.getXAxis();//x轴的位置
//    	   xAxis.setPosition(XAxisPosition.BOTTOM);
    	   
           barChart.setDrawBorders(false);  ////是否在折线图上添加边框
       
           barChart.setDescription("");// 数据描述
           
           // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
           barChart.setNoDataTextDescription("You need to provide data for the chart.");    

           barChart.setDrawGridBackground(false); // 是否显示表格颜色
           barChart.setGridBackgroundColor(Color.WHITE& 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

           barChart.setTouchEnabled(true); // 设置是否可以触摸

           barChart.setDragEnabled(true);// 是否可以拖拽
           barChart.setScaleEnabled(true);// 是否可以缩放

           barChart.setPinchZoom(true);//     

//           barChart.setBackgroundColor(Color.parseColor("#0ff"));// 设置背景
           
           barChart.setDrawBarShadow(true);

           barChart.setData(barData); // 设置数据

           Legend mLegend = barChart.getLegend(); // 设置比例图标示

           mLegend.setForm(LegendForm.CIRCLE);// 样式
           mLegend.setFormSize(6f);// 字体
           mLegend.setTextColor(Color.BLACK);// 颜色

//         X轴设定
//         XAxisxAxis = barChart.getXAxis();
//         xAxis.setPosition(XAxisPosition.BOTTOM);

           barChart.animateX(2500); // 立即执行的动画,x轴    
       
    }

	private BarData getBarData(int count, float range) {
		 ArrayList<String> xValues = new ArrayList<String>();
		 
		 xValues.add("工商管理");
		 xValues.add("移动通信");
		 xValues.add("软工工程");
		 xValues.add("数字媒体");
		 xValues.add("建筑工程");
		 xValues.add("计算机学院");
		 xValues.add("网络营销");
		 
		 /* for (int i = 0; i< count; i++) {
	            xValues.add("第" + (i + 1) + "季度");
	        }*/
		  
		  ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
		  
		  for (int i = 0; i< count; i++) {    
	            float value = (float) (Math.random() * range/*100以内的随机数*/);
	            	yValues.add(new BarEntry(value, i));    
	        }
		  
		  // y轴的数据集合
		  BarDataSet barDataSet = new BarDataSet(yValues, "柱状图"); 
		  
		  
		  barDataSet.setColor(Color.rgb(114, 188, 223));
		  
		  ArrayList<IBarDataSet> barDataSets = new ArrayList<IBarDataSet>();    
		  barDataSets.add(barDataSet); // add the datasets    

		  BarData barData = new BarData(xValues, barDataSets);
		          
		          return barData;
	}


   
    
}
