package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import entity.Column;
import entity.Table;

public class XmlDao {
	

	/**
	 * 返回表名称
	 * @param xmlPath 库名
	 * @return
	 */
	public static List<Table> getTableList(String xmlPath)
	{
		List<Table> list=new ArrayList<Table>();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new BufferedReader(new InputStreamReader(new FileInputStream(new File(xmlPath+"\\db.xml")),"UTF-8")));
			
			
			Element dbe= doc.getRootElement();
			List<Element> elist= dbe.elements();
			for(Element e:elist)
			{
				Table table=new Table();
				table.setName(e.attributeValue("name"));
				table.setComment(e.attributeValue("comment"));
				table.setKey(e.attributeValue("key"));
				
				List<Column> columns=new ArrayList();
				List<Element> elist2=  e.elements(); //字段列表
				
				for(Element e2:elist2)
				{
					Column column=new Column();
					column.setColumnName(e2.attributeValue("name"));
					column.setColumnType(e2.attributeValue("type"));
					column.setColumnComment(e2.attributeValue("comment"));
					column.setColumnKey(e2.attributeValue("key"));
					columns.add(column);
				}
				table.setColumns(columns);
				list.add(table);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
