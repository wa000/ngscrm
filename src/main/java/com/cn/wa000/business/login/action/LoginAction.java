package com.cn.wa000.business.login.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.wa000.business.vote.bean.VoteBean;
import com.cn.wa000.business.vote.dao.VoteDao;
import com.cn.wa000.framework.common.SqliteJDBC;
import com.cn.wa000.framework.util.CommonUtils;
import com.cn.wa000.framework.util.SpringUtil;
import com.cn.wa000.framework.util.SqliteUtils;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/welcome")
public class LoginAction
{
    public static int LOGINNUM = 0;
    
    public static String LOG = "";
    
    /**
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap modelMap)
    {
        first(modelMap);
        
        test01();
        
        test02();
        
        System.out.println(((VoteDao) SpringUtil.getBean("voteDao")).queryVoteList());
        ((VoteDao) SpringUtil.getBean("voteDao")).updateVoteCount("3", "1");
        
        List<VoteBean> queryVoteBeanList = ((VoteDao) SpringUtil.getBean("voteDao")).queryVoteBeanList("1");
        System.out.println("mybatis:" + queryVoteBeanList);
        
        return "hello";
    }

    private void test02()
    {
        ((SqliteUtils) SpringUtil.getBean("sqliteUtils")).execSql("drop table test");
        ((SqliteUtils) SpringUtil.getBean("sqliteUtils")).execSql("create table test (id int, name text)");
        // ((SqliteUtils) SpringUtil.getBean("sqliteUtils")).execSql("insert into T_VOT_VOTESHOW (VOTEID, title, CREATETIME) values ('3', '投票03', '2015-08-01 12:10:00')");
    }

    private void first(ModelMap modelMap)
    {
        modelMap.addAttribute("message", "Hello World");
        
        Date date = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        String dateStr = sdf.format(date);
        
        System.out.println(dateStr + " hello:" + LOGINNUM);
        
        modelMap.addAttribute("loginNum", LOGINNUM);
        
        modelMap.addAttribute("log", LOG);
        
        LOGINNUM++;
    }

    private void test01()
    {
        Connection instance = ((SqliteJDBC) CommonUtils.getBean("sqliteJDBC")).getConnection();
        
        System.out.println(instance);
    }

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginMain(ModelMap modelMap)
    {
        return "login/login";
    }
}
