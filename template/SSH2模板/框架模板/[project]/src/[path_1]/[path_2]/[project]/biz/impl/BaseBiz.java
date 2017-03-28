package [package].biz.impl;

import java.util.List;

import [package].biz.IBaseBiz;

import [package].dao.IBaseDao;


/**
 * 基础业务层
 * @author [author]
 * 本代码通过《传智代码神器》生成
 */
public class BaseBiz<T> implements IBaseBiz<T> {

	private IBaseDao baseDao;//数据访问层
	
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}



	/**
	 * 返回列表
	 */
	public List<T> getList(T t1,T t2) {
		// TODO Auto-generated method stub
		return baseDao.getList(t1,t2);
	}




	public List<T> getListByPage(T t1,T t2,int firstIndex, int maxResults) {
		// TODO Auto-generated method stub
		return baseDao.getListByPage(t1,t2,firstIndex, maxResults);
	}



	
	public long getCount(T t1,T t2) {
		// TODO Auto-generated method stub
		return baseDao.getCount(t1,t2);
	}



	
	public void add(T t) {
		// TODO Auto-generated method stub
		baseDao.add(t);
	}



	
	public void delete(T t) {
		// TODO Auto-generated method stub
		baseDao.delete(t);
	}



	
	public T get(long id) {
		// TODO Auto-generated method stub
		return (T) baseDao.get(id);
	}



	
	public void update(T t) {
		// TODO Auto-generated method stub
		baseDao.update(t);
	}



}
