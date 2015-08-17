package com.cn.wa000.business.vote.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 正式使用的投票action类
 * 
 * @author wa000
 *
 */
@Controller
@RequestMapping("/vote")
public class VoteMainAction
{
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView showManagerPage(ModelAndView modelAndView)
    {
        modelAndView.setViewName("vote/voteManager");
        
        return modelAndView;
    }
}
