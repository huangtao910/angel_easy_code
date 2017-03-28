package util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * MAP工具类
 * @author [author]
 * 本代码通过《传智代码神器》生成
 */
public class MapUtil {
	
	
	/** 
    * 将一个 JavaBean 对象个  Map 
    * @param bean 要转化的JavaBean 对象 
    * @return 转化出来的  Map 对象 
    * @throws IntrospectionException 如果分析类属性失败 
    * @throws IllegalAccessException 如果实例化 JavaBean 失败 
    * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
    */ 
   public static Map convertBean(Object bean,String prefix) 
           throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
       Class type = bean.getClass(); 
       Map returnMap = new HashMap(); 
       BeanInfo beanInfo = Introspector.getBeanInfo(type); 

       PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
       for (int i = 0; i< propertyDescriptors.length; i++) { 
           PropertyDescriptor descriptor = propertyDescriptors[i]; 
           String propertyName = descriptor.getName(); 
           if (!propertyName.equals("class")) { 
               Method readMethod = descriptor.getReadMethod(); 
               Object result = readMethod.invoke(bean, new Object[0]); 
               
               
               if (result != null) { 
            	   
            	   if( isEntityType(result)  )
            	   {
            		   returnMap.putAll( convertBean(result,prefix+propertyName+"."));
            	   }
            	   else
            	   {
            		   returnMap.put(prefix+propertyName, result); 
            	   }
                   
               } else { 
                   returnMap.put(prefix+propertyName, ""); 
               } 
               
           } 
       } 
       return returnMap; 
   } 
   
   
   
   private static boolean isEntityType(Object object)
   {
	   if(object instanceof String )
	   {
		   return false;
	   }
	   if(object instanceof Long )
	   {
		   return false;
	   }
	   if(object instanceof Integer )
	   {
		   return false;
	   }
	   if(object instanceof Date )
	   {
		   return false;
	   }
	   if(object instanceof Collection )
	   {
		   return false;
	   }
	   if(object instanceof Boolean)
	   {
		   return false;
	   }
	   return true;
   }

}
