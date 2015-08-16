package com.cn.wa000.business.vote.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.wa000.business.vote.cache.VoteCache;
import com.cn.wa000.business.vote.dao.VoteDao;
import com.cn.wa000.business.vote.domain.VoteDomain;
import com.cn.wa000.framework.util.CommonUtils;

/**
 * 定义拦截器防止重复投票
 * 
 * @author wa000
 *
 */
@Component
public class VoteInterceptor implements HandlerInterceptor
{
    @Autowired
    private VoteDao voteDao;
    
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
        Object obj = modelAndView.getModelMap().get("domain");
        String voteid = null;
        if(null != obj)
        {
            String selectId = ((VoteDomain) obj).getSelectId();
            
            if(CommonUtils.isNotEmpty(selectId)) 
            {
                voteid = selectId.split("@_@")[0];
            }
        }
        
        if(CommonUtils.isEmpty(voteid))
        {
            voteid = request.getParameter("voteid");
        }
        
        String ip = request.getRemoteAddr();
        
        boolean result = false;
        
        for(String oneIp : VoteCache.ipCache)
        {
            if(CommonUtils.isNotEmpty(ip))
            {
                if((ip + "," + voteid).equals(oneIp))
                {
                    result = true;
                    break;
                }
            }
        }
        
        if(result)
        {
            response.sendRedirect(request.getContextPath() + "/vote/showlist/showvoteresult2?voteid=" + voteid);
        }
        else
        {
            VoteCache.ipCache.add(ip + "," +voteid);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        
    }

}
