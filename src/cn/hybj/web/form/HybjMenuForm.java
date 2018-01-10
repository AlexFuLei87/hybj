package cn.hybj.web.form;


/**
 * VO值对象，对应页面表单的属性值(首页显示)
 */
@SuppressWarnings("serial")
public class HybjMenuForm implements java.io.Serializable {
	
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
