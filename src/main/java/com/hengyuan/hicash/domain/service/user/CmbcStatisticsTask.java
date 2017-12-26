package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.entity.user.CmbcIdentifySendCodeEntity;
import com.hengyuan.hicash.exception.CmbcIdentifyException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;


/**
 * @author Administrator
 *
 */
public class CmbcStatisticsTask extends TimerTask
	{
	private static Logger logger = Logger.getLogger(CmbcStatisticsTask.class);
	    private static boolean isRunning = false;
	    private ServletContext context = null;
		private CmbcIdentifyConfirmService cmbcConfirmService;
	    public CmbcStatisticsTask(ServletContext context)
	    {
	        this.context = context;
	    }
	    
	    @Override
	    public void run()
	    {
//	        Calendar cal = Calendar.getInstance(); 

	        if (!isRunning) 
	        { 
//	            if (STATISTICS_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY)) //查看是否为凌晨
//	            { 
	                isRunning = true; 
	                context.log("开始执行指定任务");
	                
	                logger.info("民生银行实名认证查询批量开始================");
	        		
	        		List<CmbcIdentifySendCodeEntity> list = cmbcConfirmService.queryWaitList();
	        		logger.info("本次批量处理=========="+list.size()+"条记录");
	        		
	        		for (int i = 0; i < list.size(); i++) {
	        			logger.info("当前批量处理第"+(i+1)+"条记录");
	        			logger.info("当前执行的记录为：发送验证码流水号为："+list.get(i).getBussflowNo()+"的记录");
	        			try {
	        				
	        				try {
	        					logger.info("当前更改的验证状态：发送验证码流水号为："+list.get(i).getBussflowNo()+"的记录");
								cmbcConfirmService.queryIdentify(list.get(i));
								
							} catch (HttpReturnException e) {
								logger.info("调用实名认证查询接口返回异常!发送验证码流水号为："+list.get(i).getBussflowNo()+e);
							} catch (HttpUrlRemoteException e) {
								logger.info("调用实名认证查询接口异常!发送验证码流水号为："+list.get(i).getBussflowNo()+e);
							} catch (CmbcIdentifyException e) {
								logger.info("民生银行实名认证更新状态异常!发送验证码流水号为："+list.get(i).getBussflowNo()+e);
							}
	        				
	        			} catch (HttpException e) {
	        				logger.info("调用实名认证查询接口异常!发送验证码流水号为："+list.get(i).getBussflowNo()+e);
	        			} catch (IOException e) {
	        				logger.info("调用实名认证查询接口异常!发送验证码流水号为："+list.get(i).getBussflowNo()+e);
	        			}
	        		}
	                executeTask();
	                
	                //指定任务执行结束
	                isRunning = false;
	                context.log("指定任务执行结束"); 
	                logger.info("批量查询认证结束");
	            } 
//	        } 
//	        else 
//	        {
//	            context.log("上一次任务执行还未结束");
//	        }
	    
	    }

	    /**
	     * 执行任务
	     */
	    public void executeTask()
	    {
	        System.out.println("任务1");
	        System.out.println("任务2");
	    }
	}


