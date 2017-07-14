package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import InitData.InitBaseData;
import entity.Column;
/**
 * 数据表DAO
 * @author Administrator
 *
 */
public class TableDao {
	
	
	/**
	 * 返回表名称
	 * @param dbName 库名
	 * @return
	 */
	public static List<String>  getTableList(String dbName)
	{
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<String> list=new ArrayList();		
		conn= InitBaseData.getConnection();
		try {
			stmt=conn.prepareStatement("SELECT TABLE_NAME FROM information_schema.tables where TABLE_SCHEMA=?");
			stmt.setString(1, dbName);
			rs=stmt.executeQuery();
			while(rs.next())
			{				
				list.add(rs.getString(1))	;		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			InitBaseData.closeAll(rs, stmt, conn);
		}
		
		return list;
	}
	
	/**
	 * 获得字段列表	
	 * @param tableName 表名称
	 * @return
	 */
	public static List<Column>  getColumnsList(String tableName)
	{
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Column> list=new ArrayList();		
		conn=InitBaseData.getConnection();
		try {
			String sql="SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT, COLUMN_KEY FROM information_schema.columns WHERE TABLE_NAME=?";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, tableName);
			rs=stmt.executeQuery();
			while(rs.next())
			{	
				Column column=new Column();
				column.setColumnName(rs.getString(1));
				column.setColumnType(rs.getString(2));
				column.setColumnComment(rs.getString(3));
				column.setColumnKey(rs.getString(4));
				list.add(column);		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			InitBaseData.closeAll(rs, stmt, conn);
		}
		
		return list;
	}
	

	public static String  ConvertType(String dbtype)
	{
		if(dbtype.equals("bigint"))
		{
			return "Long";
		}
		if(dbtype.equals("varchar") || dbtype.equals("text") || dbtype.equals("longtext"))
		{
			return "String";
		}
		if(dbtype.equals("int"))
		{
			return "Integer";
		}
		if(dbtype.equals("date") )
		{
			return "java.util.Date";
		}
		if(dbtype.equals("datetime") || dbtype.equals("timestamp"))
		{
			return "java.util.Date";
		}
		if(dbtype.equals("double"))
		{
			return "Double";
		}
		if(dbtype.equals("decimal"))
		{
			return "BigDecimal";

		}
		return dbtype;
	}
	
	
}
