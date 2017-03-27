package util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Table;
import entity.Templet;

public class MakeCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//变量定义
		String templetPath="d:\\SSH2模板\\框架模板";//框架模板所在目录
		String tablleTempletPath="d:\\SSH2模板\\表级模板";//表级模板所在目录
		String columnTempletPath="d:\\SSH2模板\\列级模板";//列级模板所在目录		
		String codePath="d:\\newcode";//代码输出目录
		String xmlPath="d:\\newxml";//数据库信息文件
		
		//定义全局替换符
		Map<String,String> publicMap=new HashMap();
		publicMap.put("project", "meituan");//项目名（英文）
		publicMap.put("projectComment", "美团管理系统");//系统中文名称
		publicMap.put("db", "meituan");//数据库名称
		publicMap.put("dbuser", "root");//数据库用户名
		publicMap.put("dbpassword", "123456");//数据库密码
		publicMap.put("package", "com.itheima.meituan");//包名称
		publicMap.put("path_1", "com");//一级目录
		publicMap.put("path_2", "itheima");//二级目录
		publicMap.put("author", "传智.刘备");//作者
		
		List<Table> tableList=dao.XmlDao.getTableList(xmlPath);//得到表的列表
		
		Map<String ,String > tableTempletMap= dao.ClientTempletDao.getTempletList(tablleTempletPath);//表级模板MAP
		Map<String ,String > columnTempletMap= dao.ClientTempletDao.getTempletList(columnTempletPath);//列级模板MAP
		
		List<Templet> list=dao.TempletDao.getTempletList(templetPath);
		//循环所有模板
		for(Templet t:list  )
		{
			System.out.println("处理模板："+ t.getPath()+"   --- "+t.getFileName());
			
			//如果是模板类文件			
			if( dao.FileDao.isTemplatFile( t.getAllPath()) ){
			
				//读取模板文件内容
				String content=dao.FileDao.getContent(t.getAllPath());
				
				//替换表级模板部分
				content= dao.ClientTempletDao.createContentForTable(content, tableTempletMap, tableList);
				
				//如果文件名包含表替换符号则循环输出
				if(t.getFileName().indexOf("[table]")>=0  || t.getFileName().indexOf("[Table]")>=0 )
				{
					for(Table table:tableList)
					{						
						//输出的文件名
						String outFile=t.getFileName().replace("[table]", table.getName());
						outFile=outFile.replace("[Table]", Utils.getClassName(table.getName()) );						
						//得到模板内容
						String outContent=content;						
						//替换列级模板部分
						outContent= dao.ClientTempletDao.createContent(outContent, columnTempletMap, table);
						//全局替换
						outContent= dao.ClientTempletDao.createContent(outContent, publicMap);
											
						//输出的路径(经过全局替换)
						String outPath= dao.ClientTempletDao.createContent(codePath+"/"+ t.getPath()+"/"+outFile, publicMap)  ;						
						//写入文件
						dao.FileDao.setContent(outPath, outContent);
					}								
				}else //不用循环的文件
				{
					String outPath= dao.ClientTempletDao.createContent(codePath+"/"+ t.getPath()+"/"+t.getFileName(), publicMap); 	
					content= dao.ClientTempletDao.createContent(content, publicMap);
					dao.FileDao.setContent(outPath, content);//写入文件
				}				
			}else  //非模板文件直接拷贝
			{
				String newPath= dao.ClientTempletDao.createContent( codePath+"/"+ t.getPath()+"/"+t.getFileName(), publicMap); 
				dao.FileDao.copyFile(t.getAllPath(), newPath);
			}			
		}		
		System.out.println("代码成功生成!");
		
	}
	
	
	

}
