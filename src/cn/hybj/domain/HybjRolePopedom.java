package cn.hybj.domain;



/**
 * PO持久层对象，对应数据库表hybj_Role_Popedom
 *
 */
@SuppressWarnings("serial")
public class HybjRolePopedom implements java.io.Serializable {
	private String roleID;         //主键ID
	private String popedomcode;    //配置web文件中权限的编码code的字符串连接
	private String remark;         //备注
	
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getPopedomcode() {
		return popedomcode;
	}
	public void setPopedomcode(String popedomcode) {
		this.popedomcode = popedomcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
