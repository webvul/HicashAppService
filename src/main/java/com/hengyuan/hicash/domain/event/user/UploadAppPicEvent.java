package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveCustomerException;
import com.hengyuan.hicash.exception.SaveRegisInfoException;
import com.hengyuan.hicash.exception.UpdatePicException;
import com.hengyuan.hicash.exception.UploadAppPicException;
import com.hengyuan.hicash.parameters.request.upload.UploadAppPicReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午6:19:23
 */
public class UploadAppPicEvent {

	private static Logger logger = Logger.getLogger(UploadAppPicEvent.class);

	/**
	 * 新增用户上传图片
	 * 
	 * @param registerReq
	 * @throws SaveCustomerException
	 * @author Cary.Liu
	 * @throws UploadAppPicException
	 * @create 2014-08-01
	 */
	public void insertPic(UploadAppPicReq req) throws UploadAppPicException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("USERNAME", req.getUserName());
		setMap.put("PIC_NAME", req.getPicName());
		setMap.put("PIC_CAPTION", req.getUploadType());
		setMap.put("PIC_PATH", req.getBigPath());
		setMap.put("PIC_TYPE", req.getImgType());
		setMap.put("CREATE_USER", req.getUserName());
		setMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		setMap.put("THUM_PATH", req.getSmallPath());

		String createSql = MapAssemForSql.getInsertSql(TableConsts.CUST_TEMPAPPLY_PIC, setMap);

		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new UploadAppPicException();
		}
	}

	/**
	 * 注册信息资料录入
	 * 
	 * @param registerReq
	 * @return
	 * @throws SaveRegisInfoException
	 */
	public void updatePic(UploadAppPicReq req) throws UploadAppPicException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("USERNAME", req.getUserName());
		setMap.put("PIC_NAME", req.getPicName());
		setMap.put("PIC_CAPTION", req.getUploadType());
		setMap.put("PIC_PATH", req.getBigPath());
		setMap.put("PIC_TYPE", req.getImgType());
		setMap.put("UPDATE_USER", req.getUserName());
		setMap.put("UPDATE_DATE", DateUtils.getDateStr(new Date()));
		setMap.put("THUM_PATH", req.getSmallPath());

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("USERNAME", req.getUserName());
		wheremMap.put("PIC_TYPE", req.getImgType());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_TEMPAPPLY_PIC, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UploadAppPicException();
		}

	}
	
	
	/**
	 * 修改图片状态 按用户
	 * @param entity
	 * @throws UpdatePicException 
	 * @throws CustTempApplyPicException
	 */
	public void updatePicStatusByUser(String username,int status) throws UpdatePicException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		
		setMap.put("status", status);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", username);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_TEMPAPPLY_PIC, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger,null, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdatePicException();
		}
	}

}
