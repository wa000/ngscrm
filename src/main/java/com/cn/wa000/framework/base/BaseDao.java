package com.cn.wa000.framework.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.wa000.framework.template.SqliteTemplate;

/**
 * dao的父类, 封装了调用模板方法的公共方法
 * 
 * @author wa000
 *
 */
public class BaseDao
{
    /**
     * 自动注入的SqliteTemplate对象, 配置为非单例的prototype
     */
    @Autowired
    private SqliteTemplate template;
    
    protected SqliteTemplate getTemplate()
    {
        return template;
    }
}
