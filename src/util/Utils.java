package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public  static String getClassName(String str)
	{
		
		String s0=str.substring(0, 1).toUpperCase();
		
		return s0+str.substring(1);
	}
	
	
	
}
