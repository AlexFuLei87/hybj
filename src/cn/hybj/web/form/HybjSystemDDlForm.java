package cn.hybj.web.form;



/**
 * VO值对象，对应页面表单的属性值
 *
 */
@SuppressWarnings("serial")
public class HybjSystemDDlForm implements java.io.Serializable {
	
	private String seqID;       //主键ID(自增长)
	private String keyword;      //查询关键字
	private String ddlCode;     //数据字典的code
	private String ddlName;      //数据字典的value
	
	//保存数据字典的关键字
	private String keywordname;
	/**
	 * 保存数据字典时的状态标识
	 * 值=new:新建一种数据类型，并添加数据项，进行保存
	 * 值=add:在原有的数据类型的基础上进行修改和编辑，编辑对应的数据项，进行保存
	 */
	private String typeflag;
	//保存数据字典的数据项的名称
	private String [] itemname;
	
	public String getKeywordname() {
		return keywordname;
	}
	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}
	public String getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}
	public String[] getItemname() {
		return itemname;
	}
	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}
	public String getSeqID() {
		return seqID;
	}
	public void setSeqID(String seqID) {
		this.seqID = seqID;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(String ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	
	
	
}
