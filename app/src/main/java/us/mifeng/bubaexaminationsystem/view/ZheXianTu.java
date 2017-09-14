package us.mifeng.bubaexaminationsystem.view;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import android.graphics.Color;
import android.text.TextUtils;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class ZheXianTu {
	private  ArrayList<ZhouBanji> list;
	public  ZheXianTu(ArrayList<ZhouBanji> li){
		list=new ArrayList<ZhouBanji>();
		this.list=li;
		getLineData(2, 5);
	}
	public void showChart(LineChart lineChart, LineData lineData, int color) {    
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框    
        //设置x轴的位置
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(15);
        xAxis.setPosition(XAxisPosition.BOTTOM);
       
        //Y轴左侧的属性设置
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setTextSize(15);
        //Y轴右侧的属性设置
        YAxis yAxis1 = lineChart.getAxisRight();
        yAxis1.setDrawLabels(false);
        // no description text 
        if(list.size()>0){
        	lineChart.setDescription(list.get(0).getName());// 数据描述    
        }
        lineChart.setDescriptionTextSize(13);//显示的学院字体的大小
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview    
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");    
            
        // enable / disable grid background    
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色    
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度    
    
        // enable touch gestures    
        lineChart.setTouchEnabled(false); // 设置是否可以触摸    
        // enable scaling and dragging    
        lineChart.setDragEnabled(false);// 是否可以拖拽    
        lineChart.setScaleEnabled(false);// 是否可以缩放    
    
        // if disabled, scaling can be done on x- and y-axis separately    
        lineChart.setPinchZoom(false);//     
    
        lineChart.setBackgroundColor(color);// 设置背景    
        
        // add data    
        lineChart.setData(lineData); // 设置数据    
        // get the legend (only possible after setting data)    
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的    
    
        // modify the legend ...    
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);    
        mLegend.setForm(LegendForm.CIRCLE);// 样式    
        mLegend.setFormSize(12f);// 字体    
        mLegend.setTextColor(Color.WHITE);// 颜色    
//      mLegend.setTypeface(mTf);// 字体
        mLegend.setTextSize(15);//大小(违纪率、出勤率的字体);
        lineChart.animateX(2500); // 立即执行的动画,x轴 
        
      }   
    public LineData getLineData(int count, float range) {    
    	
        ArrayList<String> xValues = new ArrayList<String>();    //x轴数据是日期
        for (int i = 0; i < list.size(); i++) {
        	String base = list.get(i).getBase();
        	if (!TextUtils.isEmpty(base)) {
				
			
        	if(XueYuanHuiZong_Activity.time==2){
        		String substring = base.substring(5, base.length())+"号";
        		xValues.add(substring);
        	}else{
        		String substring = base.substring(5, base.length())+"周";
        		xValues.add(substring);
        	}
        	
		}
        	}
        	
        // y轴的数据    
        
        ArrayList<Entry> yWeiJiLv = new ArrayList<Entry>();    
        ArrayList<Entry> yValues = new ArrayList<Entry>();    
        for (int i = 0; i < list.size(); i++) {    
        	String depart_name = list.get(i).getDepart_name();
        	if (!TextUtils.isEmpty(depart_name)) {
        		String value1 = list.get(i).getAttendence().trim().replace("%", "");
                float parseFloat = Float.parseFloat(value1);
                String weiJi = list.get(i).getDisciplinerate().trim().replace("%", "");
                float weiJilv = Float.parseFloat(weiJi);
                yWeiJiLv.add(new Entry(weiJilv, i));
                yValues.add(new Entry(parseFloat, i));	
        	}
        	}
        
        // create a dataset and give it a type    
        // y轴的数据集合    
        LineDataSet lineDataSet = new LineDataSet(yValues, "出勤率/%" /*显示在比例图上*/);    
        LineDataSet lineWeiJi = new LineDataSet(yWeiJiLv, "违纪率/%" /*显示在比例图上*/);    
        // mLineDataSet.setFillAlpha(110);    
        // mLineDataSet.setFillColor(Color.RED);    
    
        //出勤率线条设置
        lineDataSet.setLineWidth(1.75f); // 线宽    
        lineDataSet.setCircleSize(3f);// 显示的圆形大小    
        lineDataSet.setColor(Color.GREEN);// 显示颜色    
        lineDataSet.setCircleColor(Color.GREEN);// 圆形的颜色    
        lineDataSet.setHighLightColor(Color.GREEN); // 高亮的线的颜色    
        lineDataSet.setCircleSize(5);//圆点的大小
        lineDataSet.setValueTextSize(10);//圆点代表的数据大小
        //违纪率线条设置
        lineWeiJi.setLineWidth(1.75f); // 线宽    
        lineWeiJi.setCircleSize(3f);// 显示的圆形大小    
        lineWeiJi.setColor(Color.RED);// 显示颜色    
        lineWeiJi.setCircleColor(Color.RED);// 圆形的颜色    
        lineWeiJi.setHighLightColor(Color.RED); // 高亮的线的颜色    
        lineWeiJi.setCircleSize(5);//圆点的大小
        lineWeiJi.setValueTextSize(10);//圆点代表的数据大小
        
        ArrayList<ILineDataSet> IlineDataSets = new ArrayList<ILineDataSet>();    
        IlineDataSets.add(lineDataSet); // add the datasets    
        IlineDataSets.add(lineWeiJi); // add the datasets    
        // create a data object with the datasets    
        LineData lineData = new LineData(xValues,IlineDataSets);    
        return lineData;    
    } 
}
