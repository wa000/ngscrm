package com.cn.wa000.business.blog.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人博客首页的action 
 * 
 * @author wa000
 * 
 */
@Controller
@RequestMapping("/blog")
public class BlogMainAction
{
    /**
     * 展示个人博客首页的action方法
     * 
     * @param model spring容器传过来的modelAndVeiw对象
     * @return 处理好的modelAndVeiw对象
     */
    @RequestMapping(value = "/showHomePage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView showBlogHomePage(ModelAndView model)
    {
        model.setViewName("blog/blogHomePage");
        
        return model;
    }
}
