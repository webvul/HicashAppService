package com.hengyuan.hicash.domain.query.user;



import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.parameters.request.user.HyAddressReq;

/**
 * 
 * @author tan
 *
 */
public class HyAddressAdd {		
	
    /**
     * 添加收货地址
     * @param hyAddresReq
     * @return
     * @throws Exception
     */
	public boolean addAddress(HyAddressReq hyAddresReq) throws Exception{
		String querySql = "INSERT INTO hy_address (REALNAME,USERNAME,MOBILE,MAIL_ADDRESS) VALUES (" 
				+"'" + hyAddresReq.getRealName()+"'" +","
				+"'" + hyAddresReq.getUserName()+"'" +","
				+"'" + hyAddresReq.getMobile()+"'" +","				
				+"'" + hyAddresReq.getEmailAddress()+"')";
		return ConnManager.execute(querySql.toString());

}   
	
}