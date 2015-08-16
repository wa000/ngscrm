package com.cn.wa000.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 封装Spring的相关API的工具类
 * 添加@Component标签, 注入这个类,spring会自动注入应用上下文对象
 * 
 * @author wa000
 *
 */
@Component
public class SpringUtil implements ApplicationContextAware
{

    /**
     * spring应用上下文
     */
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
    {
        SpringUtil.context = applicationContext;
    }
    
    /**
     * 获取spring上下文中的bean对象
     * <li>发现在启动容器的时候调用此方法会获取不到context对象
     * 
     * @param beanName spring注入的bean的id
     * @return Object
     */
    public static Object getBean(String beanName)
    {
        return context.getBean(beanName);
    }
    
}
