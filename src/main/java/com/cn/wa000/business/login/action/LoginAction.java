package com.cn.wa000.business.login.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cn.wa000.business.login.bean.LoginBean;
import com.cn.wa000.business.login.dao.LoginDao;
import com.cn.wa000.business.login.domain.LoginMainDomain;
import com.cn.wa000.business.vote.bean.VoteBean;
import com.cn.wa000.business.vote.dao.VoteDao;
import com.cn.wa000.framework.common.SqliteJDBC;
import com.cn.wa000.framework.util.CommonUtils;
import com.cn.wa000.framework.util.SpringUtil;
import com.cn.wa000.framework.util.SqliteUtils;

/**
 * 登陆时使用的action类
 * 
 * @author wa000
 *
 */
@Controller
@RequestMapping("/welcome")
public class LoginAction
{
    // ................................................................. 静态字段
    /**
     * 测试用的LOG字段
     */
    public static String LOG = "";
    
    /**
     * 测试用的计数字段
     */
    public static int LOGINNUM = 0;
    
    // ................................................................. 私有属性
    /**
     * 登录时查询数据库的dao类
     */
    @Autowired
    private LoginDao loginDao;
    
    // ............................................................... action方法
    /**
     * 跳转到登陆页面
     * 
     * @param modelMap
     * @return 返回ModelAndView的view值
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginMain(ModelMap modelMap)
    {
        return "login/login";
    }
    
    /**
     * 检查登陆信息的action方法
     * 
     * @param domain 记录页面所有信息的domain对象
     * @return ModelAndView对象
     */
    @RequestMapping(value = "/login/checkinfo", method = RequestMethod.POST)
    public ModelAndView checkLoginInfo(LoginMainDomain domain)
    {
        ModelAndView modelAndView = new ModelAndView();
        
        String password = domain.getPassword();
        
        // 根据staffId调用dao方法查询出用户信息
        LoginBean loginBean = loginDao.queryOneLoginBean(domain.getStaffId());
        
        String viewName = null;
        
        if(null == loginBean)
        {
            // 如果结果为null则认为登陆失败, 跳转到登陆失败页面
            viewName = "login/loginerror";
        }
        else
        {
            if(checkPassword(password, loginBean))
            {
                // 密码正确,跳转到管理页面
                viewName = "redirect:/vote/manager";
            }
            else 
            {
                // 密码错误, 跳转到登陆失败页面
                viewName = "login/loginerror";
            }
        }
        
        modelAndView.setViewName(viewName);
        
        return modelAndView;
    }
    
    /**
     * 校验密码是否正确
     * 
     * @param password 页面上传过来的密码
     * @param loginBean 从数据库中取出来的用户信息
     * @return 密码是否正确
     */
    private boolean checkPassword(String password, LoginBean loginBean)
    {
        if(CommonUtils.isEmpty(password))
        {
            return false;
        }
        
        if(password.equals(loginBean.getPassword()))
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 测试用
     * 
     * @param modelMap
     * @return
     */
    @SuppressWarnings("deprecation")
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
}
