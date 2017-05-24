package us.mifeng.bubaexaminationsystem.view;
import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;

import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class PieChartActivity{

	private PieChart mChart;
	protected String[] mParties = new String[] { "违纪率","其他"};
	ArrayList<ZhouBanji> list;
	public PieChartActivity (ArrayList<ZhouBanji> list,PieChart pieChart){
		this.list=list;
		showChart(pieChart);
		setData(1, 2);
	}
	public void showChart(PieChart pieChart){
		mChart = pieChart;
//		mChart.setValueTypeface(tf);
//		mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(),
//				"OpenSans-Light.ttf"));
		mChart.setHoleRadius(60f);
		mChart.setDescription("");//图表默认右下方的描述，参数是String对象

//		mChart.setDrawYValues(true);//画出Y轴的值
		mChart.setDrawCenterText(false);

		mChart.setDrawHoleEnabled(false);
		
		mChart.setDrawSliceText(false);//图上的文字消失
		
		mChart.setTransparentCircleRadius(0); // 半透明圈   
		mChart.setHoleRadius(0);  //半径
		
		mChart.setRotationAngle(0);
		// 在饼状图上画出X的值
//		mChart.setDrawXValues(true);
		// 可以根据手势进行旋转
		mChart.setRotationEnabled(true);
		// 饼状图中心
		mChart.setUsePercentValues(true);
		// mChart.setUnit(" €");//饼图的单位
		// mChart.setDrawUnitsInChart(true);
		// mChart.setTouchEnabled(false);
		
		//mChart.setCenterText("MPAndroidChart\nLibrary");中间圆形上的文字
		setData(1, 100);//里面的参数可以进行修改(饼形图的占比数量)

		//瓶装图的动画
		mChart.animateXY(1500, 1500);//设置x轴上的动画
		// mChart.spin(2000, 0, 360);

		Legend l = mChart.getLegend();//设置图例
		l.setPosition(LegendPosition.RIGHT_OF_CHART_CENTER);//调整图例的位置
		l.setForm(LegendForm.CIRCLE);//图例前面的样式
		l.setXEntrySpace(7f);
		l.setYEntrySpace(5f);
		
	}

	public void setData(int count, float range) {
		Log.i("PieChartActivity", "list的大小"+list.size());
		for (ZhouBanji banji : list) {
			Log.i("PieChartActivity", banji.toString());
		}
		
		
		ArrayList<Entry> yVals1 = new ArrayList<Entry>();//xVals用来表示每个饼块上的内容
		// 随机给饼状图添加数据
		ArrayList<String> xVals = new ArrayList<String>();//yVals用来表示封装每个饼块的实际数据
		
		for (int i = 0; i < list.size(); i++) {
			float parseFloat = Float.parseFloat(list.get(i).getDisciplinerate().replace("%",""));
			yVals1.add(new Entry(parseFloat, i));
			xVals.add(list.get(i).getName());
		}
		PieDataSet set1 = new PieDataSet(yVals1, "违纪率占比图");
		set1.setSliceSpace(2f);//设置个饼状图之间的距离

		// 随机添加颜色
		ArrayList<Integer> colors = new ArrayList<Integer>();
		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);
		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);
		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);
		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);
		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());
		set1.setColors(colors);

		PieData data = new PieData(xVals, set1);
		mChart.setData(data);

		// undo all highlights
		mChart.highlightValues(null);

		mChart.invalidate();
	}

}
