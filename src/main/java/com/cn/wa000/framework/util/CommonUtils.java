package com.cn.wa000.framework.util;

import java.io.UnsupportedEncodingException;

/**
 * framework的工具类
 * 
 * @author wa000
 *
 */
public class CommonUtils
{
    
    /**
     * 获取spring上下文中的bean对象
     * 
     * @param beanName spring注入的bean的id
     * @return Object
     * 
     */
    public static Object getBean(String beanName)
    {
        return SpringUtil.getBean(beanName);
    }
    
    /**
     * 更改字符串的编码格式为UTF-8
     * 
     * @param before
     * @return
     */
    public static String changeCodeFormat(String before)
    {
        String result = "";
        
        String code = getEncoding(before);
        
        try
        {
            result = new String(before.getBytes(code), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static String getEncoding(String str) 
    {      
        String encode = "GB2312";      
        try {      
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;      
                return s;
            }      
        } 
        catch (Exception exception) 
        {      
        }      
        encode = "ISO-8859-1";      
        try {      
            if (str.equals(new String(str.getBytes(encode), encode)))
            {      
                String s1 = encode;      
                return s1;      
            }      
        } 
        catch (Exception exception1) 
        {      
        }      
        encode = "UTF-8";      
        try {      
            if (str.equals(new String(str.getBytes(encode), encode))) 
            {      
                String s2 = encode;      
                return s2;      
            }      
        } 
        catch (Exception exception2) 
        {      
        }      
        encode = "GBK";      
        try {      
            if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s3 = encode;      
                return s3;      
            }      
        } 
        catch (Exception exception3) 
        {      
        }      
        return "";      
    } 
    
    public static boolean isNotEmpty(String str)
    {
        boolean result = false;
        
        if(null != str && 0 != str.length())
        {
            result = true;
        }
        
        return result;
    }
    
    public static boolean isEmpty(String str)
    {
        boolean result = false;
        
        if(null == str)
        {
            result = true;
        }
        else
        {
            if(0 == str.length())
            {
                result = true;
            }
        }
        
        return result;
    }
}
