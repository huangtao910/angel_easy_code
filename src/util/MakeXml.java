package util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import dao.TableDao;

import entity.Column;

/**
 * 创建xml文件
 * @author Administrator
 *
 */
public class MakeXml {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String outPath="d:\\newxml";//XML保存目录
		String dbName="meituan";		//数据库名
		
		//自动创建文件夹
		File file=new File(outPath);
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		
		
		Document doc=DocumentHelper.createDocument();//建立XML文档
		doc.setXMLEncoding("utf-8");
				
		Element root= doc.addElement("db");
		root.addAttribute("name", dbName);//数据库名称
		
		
		
		List<String> tableList= dao.TableDao.getTableList(dbName);
		
		for(String tableName : tableList) 
		{
			System.out.println("表名称："+tableName);
			
			Element tableElement=root.addElement("table");//table节点
			tableElement.addAttribute("name", tableName );
			tableElement.addAttribute("comment", tableName );
						
			List<Column> columnList= dao.TableDao.getColumnsList(tableName);
			String key="";
			for(Column column : columnList) 
			{				
				System.out.println("   列名称："+column.getColumnName());
				
				Element columnElement=  tableElement.addElement("column");
				columnElement.addAttribute("name", column.getColumnName());
				columnElement.addAttribute("type", TableDao.ConvertType( column.getColumnType() ));
				columnElement.addAttribute("comment", column.getColumnComment());	
				columnElement.addAttribute("key", column.getColumnKey());
				if(column.getColumnKey().equals("PRI"))//主键
				{
					key=column.getColumnName();
				}
			}
			tableElement.addAttribute("key", key );			
		}
		
		writeXml(outPath, doc);
        
       
		
		
	}
	
	
	/**
	 * 写入文档
	 * @param outPath
	 * @param doc
	 */
	private static void writeXml(String outPath,Document doc)
	{
		try {
			String xmlFileName="db.xml";
    		OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");// 设置XML文件的编码格式 
            
            XMLWriter writer = null;// 声明写XML的对象  
            
            File file = new File(outPath+"/"+xmlFileName);  
            
			writer = new XMLWriter(new FileWriter(file), format);
			writer.write(doc);  
		    writer.close();  
		    System.out.println("生成成功！保存在"+outPath+"/"+xmlFileName);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("生成出错！"+e.toString());
		}  
	}

}
