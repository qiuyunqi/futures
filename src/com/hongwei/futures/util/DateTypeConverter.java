package com.hongwei.futures.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 日期转换器 配合 struts2使用
 * @author 充满智慧的威哥
 *
 */
@SuppressWarnings("unchecked")
public class DateTypeConverter extends StrutsTypeConverter {   
  
    private static final Logger log = Logger.getLogger(DateTypeConverter.class);   
       
    public static final DateFormat[] ACCEPT_DATE_FORMATS = {   
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),   
            new SimpleDateFormat("yyyy-MM-dd HH:mm"),   
            new SimpleDateFormat("yyyy-MM-dd"),   
            new SimpleDateFormat("yyyy年MM月dd日"),   
            new SimpleDateFormat("yyyy-MM"),
    		new SimpleDateFormat("yyyy/MM/dd")
    };
   
    public DateTypeConverter() {   
    }   
  
	@Override  
    public Object convertFromString(Map context, String[] values, Class toClass) {   
        if (values[0] == null || values[0].trim().equals(""))   
            return null;   
        for (DateFormat format : ACCEPT_DATE_FORMATS) {   
            try {   
                Date date = format.parse(values[0]);
				return date;   
            } catch (ParseException e) {   
                continue;   
            } catch (RuntimeException e) {   
                continue;   
            }   
        }   
        log.debug("can not format date string:" + values[0]);   
        return null;   
    }   
  
    @Override  
    public String convertToString(Map context, Object o) {   
        if (o instanceof Date) {   
        	for (DateFormat format : ACCEPT_DATE_FORMATS) {
	            try {   
	                return format.format((Date) o);   
	            } catch (RuntimeException e) {   
	            	continue;   
	            }   
        	}
        }   
        return "";   
    }   
  
}  
