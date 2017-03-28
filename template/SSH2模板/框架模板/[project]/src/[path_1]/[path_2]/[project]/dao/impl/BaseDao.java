package [package].dao.impl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * 数据层基类
 * @author [author]
 */
public abstract class BaseDao<T> extends HibernateDaoSupport  {

	//entityClass表示泛型的类型
	private Class<T> entityClass;
	
	//对entityClass进行初始化
	public BaseDao(){
		//1.获取当前实现类的类型
		//当前类型的父类的类型（用于获取泛型类型）
		Type type = getClass().getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		Type[] types = ptype.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
	}
	

	/**
	 * 返回列表
	 */
	public List<T> getList(T t1,T t2) {
		// TODO Auto-generated method stub		
		
		//Criteria
		DetachedCriteria dc=getDetachedCriteria(t1,t2);
		return getHibernateTemplate().findByCriteria(dc);
		
	}


	/**
	 * 分页查询
	 */
	public List<T> getListByPage(T t1,T t2,int firstIndex,int maxResults) {
		// TODO Auto-generated method stub
		
		DetachedCriteria dc=getDetachedCriteria(t1,t2);	
		
		return getHibernateTemplate().findByCriteria(dc, firstIndex,maxResults);
	}


	/**
	 * 统计记录数
	 */
	public long getCount(T t1,T t2) {
		// TODO Auto-generated method stub
		
		DetachedCriteria dc=getDetachedCriteria(t1,t2);
		dc.setProjection(Projections.rowCount());//select count(*)
		List<Long> list= getHibernateTemplate().findByCriteria(dc);
		
		return list.get(0);
	}

	public abstract  DetachedCriteria getDetachedCriteria(T t1,T t2);
	


	/**
	 * 增加数据
	 */
	public void add(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}


	public void delete(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(t);
	}


	
	/**
	 * 返回实体
	 */
	public T get(long id) {
		// TODO Auto-generated method stub
		return (T) getHibernateTemplate().get(entityClass, id);
	}


	public void update(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	
}
