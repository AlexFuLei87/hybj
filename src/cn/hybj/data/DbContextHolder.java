package cn.hybj.data;

import org.apache.commons.lang3.StringUtils;

public class DbContextHolder {
	private static final ThreadLocal contextHolder = new ThreadLocal();


	public static void setDbType(String dbType) {
	contextHolder.set(dbType);
	}


	public static String getDbType() {
	String str = (String) contextHolder.get();
	if (StringUtils.isEmpty(str))
	str = "hs";
	return str;
	}


	public static void clearDbType() {
	contextHolder.remove();
	}
}
