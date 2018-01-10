package cn.hybj.util;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import cn.hybj.web.form.HybjUserForm;

public class ChartUtils {

	/**  
	* @Name: getUserBarChart
	* @Description:使用JFreeChart统计人员所在的单位及单位下人员的数量
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: List<ElecUserForm> list 人员列表信息
	* @Return: String 生成图片的名称
	*/
	public static String getUserBarChart(List<HybjUserForm> list) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//添加数据
		for(int i=0;list!=null && i<list.size();i++){
			HybjUserForm elecUserForm = list.get(i);
			dataset.addValue(Integer.parseInt(elecUserForm.getJctcount()), "所属单位", elecUserForm.getJctname());
		}
		JFreeChart chart = ChartFactory.createBarChart3D("用户统计报表（所属单位）", //主标题的名称
				                      "所属单位名称",//X轴的标签 
				                      "数量",//Y轴的标签 
				                      dataset, //图标显示的数据集合
				                      PlotOrientation.VERTICAL, //图像的显示形式（水平或者垂直）
				                      true,//是否显示子标题 
				                      true,//是否生成提示的标签 
				                      true); //是否生成URL链接
		//处理图形上的乱码
		//处理主标题的乱码
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,18));
		//处理子标题乱码
		chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
		//获取图表区域对象
		CategoryPlot categoryPlot = (CategoryPlot)chart.getPlot();
		//获取X轴的对象
		CategoryAxis3D categoryAxis3D = (CategoryAxis3D)categoryPlot.getDomainAxis();
		//获取Y轴的对象
		NumberAxis3D numberAxis3D = (NumberAxis3D)categoryPlot.getRangeAxis();
		//处理X轴上的乱码
		categoryAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理X轴外的乱码
		categoryAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴上的乱码
		numberAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴外的乱码
		numberAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴上显示的刻度，以1作为1格
		numberAxis3D.setAutoTickUnitSelection(false);
		NumberTickUnit unit = new NumberTickUnit(1);
		numberAxis3D.setTickUnit(unit);
		//获取绘图区域对象
		BarRenderer3D barRenderer3D = (BarRenderer3D)categoryPlot.getRenderer();
		//设置柱形图的宽度
		barRenderer3D.setMaximumBarWidth(0.07);
		//在图形上显示数字
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBaseItemLabelFont(new Font("宋体",Font.BOLD,15));
		
		ServletContext context = ServletActionContext.getServletContext();
		String realPath = context.getRealPath("/chart");
		String filename = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		//在D盘目录下生成图片
		File file = new File(realPath + "\\" + filename);
		try {
			ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
