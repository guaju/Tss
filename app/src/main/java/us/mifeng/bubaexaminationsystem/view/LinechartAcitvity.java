package us.mifeng.bubaexaminationsystem.view;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;

public class LinechartAcitvity extends Activity {
	private LineChart mLineChart; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linechart_activity);
        mLineChart = (LineChart) findViewById(R.id.line);    
        LineData mLineData = getLineData(6, 100);    
        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));  
    }
    private void showChart(LineChart lineChart, LineData lineData, int color) {    
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框    
        
        //设置x轴的位置
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        
        // no description text    
        lineChart.setDescription("移动学院出勤率统计");// 数据描述    
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
        mLegend.setFormSize(6f);// 字体    
        mLegend.setTextColor(Color.WHITE);// 颜色    
//      mLegend.setTypeface(mTf);// 字体    
    
        lineChart.animateX(2500); // 立即执行的动画,x轴    
      }   
    private LineData getLineData(int count, float range) {    
        ArrayList<String> xValues = new ArrayList<String>();    
        for (int i = 0; i < count; i++) {    
            // x轴显示的数据，这里默认使用数字下标显示    
            xValues.add("" + i);    
        } 
        	
    
        // y轴的数据    
        ArrayList<Entry> yValues = new ArrayList<Entry>();    
        for (int i = 0; i < count; i++) {    
            float value = (float) (Math.random() * range) ;    
            yValues.add(new Entry(value, i+1));    
        }
        	
    
        // create a dataset and give it a type    
        // y轴的数据集合    
        LineDataSet lineDataSet = new LineDataSet(yValues, "出勤率/%" /*显示在比例图上*/);    
        // mLineDataSet.setFillAlpha(110);    
        // mLineDataSet.setFillColor(Color.RED);    
    
        //用y轴的集合来设置参数    
        lineDataSet.setLineWidth(1.75f); // 线宽    
        lineDataSet.setCircleSize(3f);// 显示的圆形大小    
        lineDataSet.setColor(Color.WHITE);// 显示颜色    
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色    
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色    
    
        ArrayList<ILineDataSet> IlineDataSets = new ArrayList<ILineDataSet>();    
        IlineDataSets.add(lineDataSet); // add the datasets    
    
        // create a data object with the datasets    
        LineData lineData = new LineData(xValues,IlineDataSets);    
    
        return lineData;    
    }    
    


    
    
}
