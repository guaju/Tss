package us.mifeng.bubaexaminationsystem.view;
import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class PieChart_BingTu{

	private PieChart mChart;
	ArrayList<ZhouBanji> list;
	public PieChart_BingTu (ArrayList<ZhouBanji> li,PieChart pieChart){
		this.list=li;
		for (ZhouBanji zhouBanji : li) {
			System.out.println(zhouBanji.getAttendence()+"--"+zhouBanji.getDisciplinerate());
		}
		if(list.size()>0){
			showChart(pieChart);
			setData();
		}
	}
	public void showChart(PieChart pieChart){
		mChart = pieChart;
//		mChart.setValueTypeface(tf);
//		mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(),
//				"OpenSans-Light.ttf"));
		mChart.setHoleRadius(60f);//半径
		mChart.setDescription(list.get(0).getName()+"违纪类型分布图");//图表默认右下方的描述，参数是String对象
		mChart.setDescriptionTextSize(23f);//描述文字的大小
//		mChart.setDrawYValues(true);//画出Y轴的值
		mChart.setDrawCenterText(true);//饼图中间可以添加文字
		mChart.setDrawSliceText(false);//扇形去显示x值
		mChart.setDrawHoleEnabled(true);
		mChart.setTransparentCircleRadius(0); // 半透明圈    
		mChart.setHoleRadius(0);  //半径
		
		mChart.setRotationAngle(0);
		// 在饼状图上画出X的值
//		mChart.setDrawXValues(true);
		// 可以根据手势进行旋转
		mChart.setRotationEnabled(true);
		// 显示成百分比
		mChart.setUsePercentValues(true);
//		mChart.setUnit(" €");//饼图的单位
		// mChart.setDrawUnitsInChart(true);
//		 mChart.setTouchEnabled(false);是否可触摸，旋转
		//mChart.setCenterText("MPAndroidChart\nLibrary");中间圆形上的文字
		setData();//里面的参数可以进行修改(饼形图的占比数量)

		//瓶装图的动画
		mChart.animateXY(1500, 1500);//设置x轴上的动画
		// mChart.spin(2000, 0, 360);

		Legend l = mChart.getLegend();//设置标示，就是那个一组y的value的
		l.setPosition(LegendPosition.RIGHT_OF_CHART_CENTER);//调整饼状图xy值得位置
		l.setXEntrySpace(7f);
		l.setYEntrySpace(5f);
	}
	public void setData() {
		ArrayList<Entry> yVals1 = new ArrayList<Entry>();//xVals用来表示每个饼块上的内容
		// 随机给饼状图添加数据
		for (int i = 0; i < list.size(); i++) {
			if (Float.parseFloat(list.get(i).getDisciplinerate())==0) {
				return;
			}
			yVals1.add(new Entry(Float.parseFloat(list.get(i).getDisciplinerate()),i));
		}
		ArrayList<String> xVals = new ArrayList<String>();//yVals用来表示封装每个饼块的实际数据
		for (int i = 0; i < list.size(); i++)
			xVals.add(list.get(i).getAttendence());
		PieDataSet set1 = new PieDataSet(yVals1, "");
		set1.setSliceSpace(1f);//设置个饼状图之间的距离

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
			mChart.highlightValues(null);
			mChart.invalidate();
	}
	
}
