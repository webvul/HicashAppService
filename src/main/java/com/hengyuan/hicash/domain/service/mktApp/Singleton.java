package com.hengyuan.hicash.domain.service.mktApp;

import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/** 
 * @author dong.liu 
 * 2017-8-8 下午6:28:33
 * 类说明 
 */
public class Singleton {  

    private static final Singleton single = new Singleton();  
    
    private  CreateCreditService createCreditService;
    
    private static class SingletonHolder{  
        private final static Singleton instance=new Singleton();  
    }  
	private Singleton() {
		createCreditService=new CreateCreditService();
	}  
    //静态工厂方法   
    public static synchronized Singleton getInstance() {  
        return single;  
    }  
    
  //添加该类的其他方法  
    public ParmResponse method(ParmRequest parmRequest){  
        return createCreditService.responseValue(parmRequest);  
    }  
    
}  
