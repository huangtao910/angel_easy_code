package entity;
/**
 * 列对象
 * @author Administrator
 *
 */
public class Column {
	
	private String columnName;//列名称
	private String columnType;//列类型
	private String columnComment;//列备注
	private String columnKey;//是否是主键
	
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	
	

}
