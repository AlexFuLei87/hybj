package cn.hybj.domain;



/**
 * PO持久层对象，对应数据库表hybj_User_Role
 *
 */
@SuppressWarnings("serial")
public class HybjUserRole implements java.io.Serializable {

	private Integer seqID;      //主键ID
	private String userID;      //用户ID
	private String roleID;      //角色ID
	private String remark;      //备注
	
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
