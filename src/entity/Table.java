package entity;

import java.util.List;
/**
 * 表实体
 * @author Administrator
 *
 */
public class Table {
	
	private String name;//表名称
	private String comment;//介绍
	private String key;// 主键列
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	private List<Column> columns;//列集合
	
	
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
