﻿package [package].biz;

import java.util.List;


/**
 * 基础业务层接口
 * @author [author]
 * 本代码通过《传智代码神器》生成
 */
public interface IBaseBiz<T> {

	List<T> getList( T t1,T t2);
	
	/**
	 * 分页查询
	 * @return
	 */
	List<T> getListByPage( T t1,T t2, int firstIndex,int maxResults);
	
	/**
	 * 统计记录数
	 * @return
	 */
	long getCount(T t1,T t2);
	
	

	/**
	 * 增加方法
	 * @param dao
	 */
	void add(T t);
	
	/**
	 * 删除方法
	 * @param dao
	 */
	void delete(T t);
	
	
	/**
	 * 返回实体方法
	 * @param id 主键
	 * @return
	 */
	T get(long id);
	

	/**
	 * 修改方法
	 * @param dao
	 */
	void update(T t);

}
