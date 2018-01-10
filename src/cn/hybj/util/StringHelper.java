package cn.hybj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {

	/**  
	* @Name: stringConvertDate
	* @Description: 将字符串形式的类型转换成日期类型
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-22 （创建日期）
	* @Parameters: String date 字符串类型的日期形式
	* @Return: Date 日期类型
	*/
	public static Date stringConvertDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

}
