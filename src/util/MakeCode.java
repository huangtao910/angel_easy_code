package util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import InitData.InitBaseData;
import entity.Table;
import entity.Templet;

public class MakeCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String, String> publicMap = getPubliceMap();

		List<Table> tableList=dao.XmlDao.getTableList(InitBaseData.dbName);//得到表的列表
		
		Map<String ,String > tableTempletMap= dao.ClientTempletDao.getTempletList(InitBaseData.tablleTempletPath);//表级模板MAP
		Map<String ,String > columnTempletMap= dao.ClientTempletDao.getTempletList(InitBaseData.columnTempletPath);//列级模板MAP

		List<Templet> list=dao.TempletDao.getTempletList(InitBaseData.templetPath);
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
						String outPath= dao.ClientTempletDao.createContent(InitBaseData.codePath+"/"+ t.getPath()+"/"+outFile, publicMap)  ;
						//写入文件
						dao.FileDao.setContent(outPath, outContent);
					}								
				}else //不用循环的文件
				{
					String outPath= dao.ClientTempletDao.createContent(InitBaseData.codePath+"/"+ t.getPath()+"/"+t.getFileName(), publicMap);
					content= dao.ClientTempletDao.createContent(content, publicMap);
					dao.FileDao.setContent(outPath, content);//写入文件
				}				
			}else  //非模板文件直接拷贝
			{
				String newPath= dao.ClientTempletDao.createContent( InitBaseData.codePath+"/"+ t.getPath()+"/"+t.getFileName(), publicMap);
				dao.FileDao.copyFile(t.getAllPath(), newPath);
			}			
		}		
		System.out.println("代码成功生成!");
		
	}

	//定义全局替换符
	private static Map<String, String> getPubliceMap() {

		Map<String,String> publicMap = new HashMap();
		publicMap.put("project", InitBaseData.projectName);//项目名（英文）
		publicMap.put("projectComment", InitBaseData.projectComment);//系统中文名称
		publicMap.put("db", InitBaseData.dbName);//数据库名称
		publicMap.put("dbuser", InitBaseData.dbUsername);//数据库用户名
		publicMap.put("dbpassword", InitBaseData.dbPassword);//数据库密码
		publicMap.put("package", InitBaseData.packageName);//包名称
		publicMap.put("path_1", InitBaseData.path_1);//一级目录
		publicMap.put("path_2", InitBaseData.path_2);//二级目录
		publicMap.put("author", InitBaseData.authorName);//作者
		return publicMap;
	}


}
