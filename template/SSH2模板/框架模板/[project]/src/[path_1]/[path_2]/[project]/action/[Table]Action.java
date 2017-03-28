package [package].action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ModelDriven;

import sun.org.mozilla.javascript.internal.json.JsonParser;


import [package].biz.I[Table]Biz;
import [package].entity.[Table];
/**
 * [comment] 控制层
 * @author [author]
 */
public class [Table]Action extends BaseAction<[Table]> {
	
	private I[Table]Biz [table]Biz;

	public void set[Table]Biz(I[Table]Biz [table]Biz) {
		this.[table]Biz = [table]Biz;
		setBaseBiz([table]Biz);
	}
	
	
	
}
