package [package].action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import [package].biz.IBaseBiz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ModelDriven;

import sun.org.mozilla.javascript.internal.json.JsonParser;


/**
 * 基础控制层
 * @author [author]
 */
public class BaseAction<T>  {
	
	private IBaseBiz baseBiz;

	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz = baseBiz;
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	public String list()
	{
		List<T> list= baseBiz.getList(t1,t2);
		
		
		String jsonText = JSON.toJSONString(list, true);  
		
		write(jsonText);
		
		return null;
	}
	
	private int rows;//每页显示记录数
	private int page;
	
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	private T t1;//查询条件
	private T t2;//查询条件

	private T t;//部门实体
	
	
	public T getT1() {
		return t1;
	}

	public void setT1(T t1) {
		this.t1 = t1;
	}

	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	/**
	 * 保存方法
	 * @return
	 */
	public String add()
	{		
		baseBiz.add(t);		
		return null;
	}
	
	/**
	 * 保存方法
	 * @return
	 */
	public String update()
	{		
		baseBiz.update(t);				
		return null;
	}
	
	public String delete()
	{
		baseBiz.delete(t);
		return null;
	}
	
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 查询实体
	 * @return
	 */
	public String get()
	{
		T	 t=  (T) baseBiz.get(id);//得到实体
		//JsonUtil jsonUtil=new JsonUtil("t");
		//String json=jsonUtil.beanToJson(t);
		try {
			Map map=util.MapUtil.convertBean(t,"t.");
			
			String jsonText =JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat) ;
			write(jsonText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	/**
	 * 查询分页数据
	 * @return
	 */
	public void listByPage()
	{
		
		int firstIndex=(page-1)*rows;
		
		List<T> list= baseBiz.getListByPage( t1,t2,  firstIndex, rows);
		long count=baseBiz.getCount(t1,t2);//得到记录数
		
		Map map=new HashMap();
		map.put("rows", list);
		map.put("total", count);
		
		String jsonText = JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);  	
		
		write(jsonText);
		
		
	}
	

	
	/**
	 * 输出字符串
	 * @param json
	 */
	private void write(String json)
	{
		
		HttpServletResponse response= ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
