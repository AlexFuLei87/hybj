package cn.hybj.web.form;




/**
 * VO值对象，对应页面表单的属性值
 *
 */
@SuppressWarnings("serial")
public class HybjRoleForm implements java.io.Serializable {
	
	private String role; //角色ID
	
	private String roleid;//角色ID
	
	private String [] selectoper;//权限编号（权限code）
	
	private String [] selectuser;//用户ID

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String[] getSelectoper() {
		return selectoper;
	}

	public void setSelectoper(String[] selectoper) {
		this.selectoper = selectoper;
	}

	public String[] getSelectuser() {
		return selectuser;
	}

	public void setSelectuser(String[] selectuser) {
		this.selectuser = selectuser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
