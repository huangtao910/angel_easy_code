package [package].biz.impl;

import java.util.List;

import [package].biz.I[Table]Biz;
import [package].dao.I[Table]Dao;
import [package].entity.[Table];
/**
 * [comment] 业务层
 * @author [author]
 */
public class [Table]Biz extends BaseBiz<[Table]> implements I[Table]Biz {

	private I[Table]Dao [table]Dao;//数据访问层
	
	public void set[Table]Dao(I[Table]Dao [table]Dao) {
		this.[table]Dao = [table]Dao;
		setBaseDao([table]Dao);
	}


}
