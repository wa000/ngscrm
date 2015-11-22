package com.cn.wa000.business.region.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cn.wa000.business.region.util.RegionUtil;

/**
 * 检测路由ip是否有变化的定时器
 * 
 * @author wa000
 *
 */
@Component
public class RegionJob
{
    // ..................................................................... 静态
    public static String curIp = "";
    
    // ............................................................... 定时器方法
    /**
     * 更新二级域名到路由器IP的映射
     */
    @Scheduled(fixedDelay=60000, initialDelay=10000)
    public void updateIp()
    {
        try
        {
            if(!curIp.equals(RegionUtil.getIp()))
            {
                RegionUtil.updateIp();
                System.out.println("RegionJob.updateIp() success");
                curIp = RegionUtil.getIp();
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
