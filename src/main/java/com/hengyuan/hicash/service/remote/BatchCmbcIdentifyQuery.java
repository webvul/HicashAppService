package com.hengyuan.hicash.service.remote;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hengyuan.hicash.domain.service.user.CmbcStatisticsTask;

/**
 * 民生银行代扣业务身份认证-用于验证交易用户查询结果。CP0031
 * 
 * @author LiHua.Ren
 * @create date 2015-12-02
 */
public class BatchCmbcIdentifyQuery implements ServletContextListener {
	private java.util.Timer timer = null;


	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {

		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		timer = new java.util.Timer(true);
		event.getServletContext().log("定时器已启动");
		timer.schedule(new CmbcStatisticsTask(event.getServletContext()), 0,
				60 * 60 * 1000);// 每隔1小时
		event.getServletContext().log("已经添加任务调度表");
	}

}
