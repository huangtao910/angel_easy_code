package [package].dao.impl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import [package].dao.I[Table]Dao;
import [package].entity.[Table];
/**
 * [comment]数据层
 * @author [author]
 * 本代码通过《传智代码神器》生成
 */
public class [Table]Dao extends BaseDao<[Table]> implements I[Table]Dao {

	

	public DetachedCriteria getDetachedCriteria([Table] [table]1,[Table] [table]2)
	{
		DetachedCriteria dc=DetachedCriteria.forClass([Table].class);
		
		if([table]1!=null)
		{
<条件查询.String.txt>				
		}
		return dc;
	}

	

}
