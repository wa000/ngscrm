package com.cn.wa000.framework.util;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 使用mybatis操作数据库的工具方法, 在spring中配置了dbcp
 * 
 * @author Administrator
 *
 */
public class SqliteMyBatisDaoUtil
{
    /**
     * 返回SqlSessionFactory接口
     * 
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory()
    {
        // 在spring中配置为单例
        return (SqlSessionFactory) SpringUtil.getBean("sqlSessionFactory");
    }
}
