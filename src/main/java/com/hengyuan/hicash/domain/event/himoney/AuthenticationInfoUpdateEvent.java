package com.hengyuan.hicash.domain.event.himoney;



import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;

/**
 * 选择认证项保存Dao
 * 
 * @author xuexin
 * @create 2017年7月17日 10:29:07
 */
public class AuthenticationInfoUpdateEvent {

	/**
	 * 选择认证项保存
	 * 
	 * @param req
	 * @return
	 * @throws SaveException
	 */
	public boolean saveAuthTicaInfo(AuthenticationInfoReq req) {
		String sql = "INSERT INTO authentication_info (temp_App_No,auth_id,status,userName,createDate)"
					 + "VALUES"
					 +"('"+req.getTempAppNo()+"','"+req.getAuthId()+"','"+req.getStatus()+"','"+req.getUserName()+"',NOW())";
		return ConnManager.execute(sql);
	}

}
