package cn.hybj.util;

import java.lang.reflect.ParameterizedType;


public class GenericSuperClass {

	/**  
	* @Name: getClass
	* @Description: 范类转换，转换成对应的对象
	* @Author: 付磊（作者）
	* @Version: V1.00 （版本号）
	* @Parameters: Class tClass 范类
	* @Return: 返回对象
	*/
	public static Class getClass(Class tClass) {
		ParameterizedType pt = (ParameterizedType) tClass.getGenericSuperclass();
		Class entity = (Class)pt.getActualTypeArguments()[0];
		return entity;
	}
}
