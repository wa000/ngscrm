package com.cn.wa000.framework.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 使用JDBC的方式, 获取sqlite3的connection
 * 
 * @author wa000
 *
 */
@Component("sqliteJDBC")
public class SqliteJDBC
{
    /**
     * sqlite 数据库连接
     */
    private static Connection sqliteConnection;
    
    /**
     * Sqlite数据库 在异步使用的时候可能会出问题, 所以使用时要保证线程安全
     * 
     * @return 连接对象
     */
    public Connection getConnection()
    {
        createConnection();
        
        return sqliteConnection;
    }

    /**
     * 创建数据库连接
     */
    private void createConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            sqliteConnection = DriverManager.getConnection("jdbc:sqlite:ngscrm.db");
            
            Date date = new Date();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            
            String dateStr = sdf.format(date);
            
            System.out.println(dateStr + " | SqliteLiteUtil.getInstance() success");
        } 
        catch (Exception e)
        {
            System.out.println("SqliteLiteUtil.getInstance() error");
            e.printStackTrace();
        }
    }
    
    
    
}
