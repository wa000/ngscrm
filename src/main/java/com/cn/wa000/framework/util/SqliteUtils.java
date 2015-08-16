package com.cn.wa000.framework.util;

import java.sql.Connection;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cn.wa000.business.login.action.LoginAction;
import com.cn.wa000.framework.common.SqliteJDBC;

/**
 * 
 * 封装sqlite3 数据库 API 的工具类
 * 
 * @author wa000
 *
 */
@Component("sqliteUtils")
public class SqliteUtils
{
    /**
     * JDBC接口
     */
    @Autowired
    private SqliteJDBC sqliteJDBC;
    
    /**
     * 数据库连接对象
     */
    private Connection connection;
    
    /**
     * 完成初始化操作
     */
    private void init()
    {
        if(null == this.connection)
        {
            Connection connection = sqliteJDBC.getConnection();
            
            if(null != connection)
            {
                this.connection = connection;
            }
        }
    }
    
    /**
     * 执行sql
     * @param sql
     */
    public void execSql(String sql)
    {
        init();
        
        Statement stmt = null;
        
        try
        {
            stmt = connection.createStatement();
            
            stmt.executeUpdate(sql);
            
            String x = "SqliteUtils.execSql() success, sql=" + sql;
            System.out.println(x);
            LoginAction.LOG += x;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
