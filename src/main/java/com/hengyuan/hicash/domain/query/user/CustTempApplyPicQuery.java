package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.UploadAppPicEvent;
import com.hengyuan.hicash.entity.user.CustTempApplyPicEntity;
import com.hengyuan.hicash.exception.UpdatePicException;
import com.hengyuan.hicash.parameters.response.user.QuerySingleImgResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 身份认证图片信息查询 DAO
 * 
 * @author LiHua.Ren
 * @createDate 2015-07-20
 * 
 */
public class CustTempApplyPicQuery extends AbstractDAO<CustTempApplyPicEntity> {

	// private static final String QUERY_SQL =
	// "SELECT * FROM cust_tempapply_pic WHERE 1 = 1 ";

	private static Logger logger = Logger
			.getLogger(CustTempApplyPicQuery.class);
	private List<String> lists = new ArrayList<String>();

	public CustTempApplyPicQuery() {
		lists.add("pic_id");
		lists.add("username");
		lists.add("create_date");
		lists.add("create_user");
		lists.add("pic_caption");
		lists.add("pic_name");
		lists.add("pic_path");
		lists.add("pic_type");
		lists.add("thum_path");
		lists.add("update_date");
		lists.add("update_user");
		lists.add("status");
	}

	@Override
	public CustTempApplyPicEntity mapping(ResultSet rs) throws SQLException {

		CustTempApplyPicEntity entity = null;
		if (rs != null) {

			entity = new CustTempApplyPicEntity();
			entity.setId(StringUtils.valueOf(rs.getObject("pic_id")));
			entity.setUserName(StringUtils.valueOf(rs.getObject("username")));
			entity.setCreateDate(StringUtils.valueOf(rs
					.getObject("create_date")));
			entity.setCreateUser(StringUtils.valueOf(rs
					.getObject("create_user")));
			entity.setPicCaption(StringUtils.valueOf(rs
					.getObject("pic_caption")));
			entity.setPicName(StringUtils.valueOf(rs.getObject("pic_name")));
			entity.setPicPath(StringUtils.valueOf(rs.getObject("pic_path")));
			entity.setPicType(StringUtils.valueOf(rs.getObject("pic_type")));

			entity.setThumPath(StringUtils.valueOf(rs.getObject("thum_path")));
			entity.setUpdateDate(StringUtils.valueOf(rs
					.getObject("update_date")));
			entity.setUpdateUser(StringUtils.valueOf(rs
					.getObject("update_user")));
			entity.setStatus(StringUtils.valueOf(rs.getObject("status")));

		}

		return entity;
	}

	/**
	 * 用户名查询
	 * 
	 * @param userName
	 * @param picType
	 * @return
	 */
	public List<CustTempApplyPicEntity> queryInfoByPicType(String userName) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		// whereMap.put("pic_type", appId);
		String sql = MapAssemForSql.getSelectSql(lists,
				TableConsts.CUST_TEMPAPPLY_PIC, whereMap);
		return ConnManager.executeQuery(sql, this);

	}

	public QuerySingleImgResp queryImage(String userName) throws UpdatePicException {
		List<CustTempApplyPicEntity> list = queryInfoByPicType(userName);
		QuerySingleImgResp imageQuery = new QuerySingleImgResp();
		UploadAppPicEvent uploadPicEvent = new UploadAppPicEvent();
		/* 工作证明图片集合 */
		List<CustTempApplyPicEntity> jobCertifyList = new ArrayList<CustTempApplyPicEntity>();
		/* 收入证明图片集合 */
		List<CustTempApplyPicEntity> inComeCertifyList = new ArrayList<CustTempApplyPicEntity>();

		for (int i = 0; i < list.size(); i++) {
			CustTempApplyPicEntity entity = list.get(i);
			// “身份证件正面”、“身份证件反面”、“现场照”这三张照片留存时间>3个月需重新更新

			if (entity.getUpdateDate() == null||"".equals(entity.getUpdateDate())) {
				entity.setUpdateDate(entity.getCreateDate());
			}

			if (Consts.IMG_TYPE_ZL112.equals(entity.getPicType())) {
				if (DateUtils.compare_date(DateUtils.getNowTime(),
						DateUtils.getAfterMonth(entity.getUpdateDate(), 3)) != 1 && entity.getStatus().equals("1")) {
					imageQuery.setUserIdcardFrontUrl(ResourceUtils
							.getValue("pic_server_url") + entity.getPicPath());
					imageQuery.setUserIdcardFrontUrlThum(ResourceUtils
							.getValue("pic_server_url") + entity.getThumPath());
				} else {
					uploadPicEvent.updatePicStatusByUser(entity.getUserName(), 0);
					imageQuery = new QuerySingleImgResp();
					return imageQuery;
				}
			} else if (Consts.IMG_TYPE_ZL02.equals(entity.getPicType())) {
				if (DateUtils.compare_date(DateUtils.getNowTime(),
						DateUtils.getAfterMonth(entity.getUpdateDate(), 3)) != 1 && entity.getStatus().equals("1")) {
					imageQuery.setIdcardFrontUrl(ResourceUtils
							.getValue("pic_server_url") + entity.getPicPath());
					imageQuery.setIdcardFrontUrlThum(ResourceUtils
							.getValue("pic_server_url") + entity.getThumPath());
				} else {
					uploadPicEvent.updatePicStatusByUser(entity.getUserName(), 0);
					imageQuery = new QuerySingleImgResp();
					return imageQuery;
				}
			} else if (Consts.IMG_TYPE_ZL03.equals(entity.getPicType())) {
				if (DateUtils.compare_date(DateUtils.getNowTime(),
						DateUtils.getAfterMonth(entity.getUpdateDate(), 3)) != 1 && entity.getStatus().equals("1")) {
					imageQuery.setIdcardVersoUrl(ResourceUtils
							.getValue("pic_server_url") + entity.getPicPath());
					imageQuery.setIdcardVersoUrlThum(ResourceUtils
							.getValue("pic_server_url") + entity.getThumPath());
				} else {
					uploadPicEvent.updatePicStatusByUser(entity.getUserName(), 0);
					imageQuery = new QuerySingleImgResp();
					return imageQuery;
				}
			}

			if (Consts.IMG_TYPE_ZL04.equals(entity.getPicType())) {// 学生证封面
				imageQuery.setStuCardFrontUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setStuCardFrontUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			} else if (Consts.IMG_TYPE_ZL05.equals(entity.getPicType())) {
				imageQuery.setStuPhotoInfoUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setStuPhotoInfoUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			} else if (Consts.IMG_TYPE_ZL06.equals(entity.getPicType())) {
				imageQuery.setStuRegistInfoUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setStuRegistInfoUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			} else if (Consts.IMG_TYPE_ZL07.equals(entity.getPicType())) {
				imageQuery.setSchoolCardFrontUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setSchoolCardFrontUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			} else if (Consts.IMG_TYPE_ZL08.equals(entity.getPicType())) {
				imageQuery.setSchoolCardVersoUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setSchoolCardVersoUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			} else if (Consts.IMG_TYPE_ZL84.equals(entity.getPicType())) {
				inComeCertifyList.add(entity);
			} else if (Consts.IMG_TYPE_ZL137.equals(entity.getPicType())) {
				jobCertifyList.add(entity);
			} else if (Consts.IMG_TYPE_ZL40.equals(entity.getPicType())) {
				imageQuery.setBankCardFrontUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setBankCardFrontUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			} else if (Consts.IMG_TYPE_ZL80.equals(entity.getPicType())) {
				imageQuery.setBankCardVersoUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setBankCardVersoUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			}

			else if (Consts.IMG_TYPE_ZL01.equals(entity.getPicType())) {
				imageQuery.setSaleFrontUrl(ResourceUtils
						.getValue("pic_server_url") + entity.getPicPath());
				imageQuery.setSaleFrontUrlThum(ResourceUtils
						.getValue("pic_server_url") + entity.getThumPath());
			}
		}
		imageQuery.setInComeCertifyList(inComeCertifyList);
		imageQuery.setJobCertifyList(jobCertifyList);
		return imageQuery;
	}

	/**
	 * @param userName
	 * @param picType
	 * @return
	 */
	public List<CustTempApplyPicEntity> queryInfoByUnPt(String userName,
			String picType) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		whereMap.put("pic_type", picType);
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(lists,
				TableConsts.CUST_TEMPAPPLY_PIC, whereMap,
				"order by CREATE_DATE desc limit 10");
		return ConnManager.executeQuery(sql, this);
	}
	
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public List<CustTempApplyPicEntity> queryInfoByUnPic(String userName) {

		String sql="SELECT * FROM CUST_TEMPAPPLY_PIC where username = '"+userName+"' and (pic_type ='ZL02' or pic_type ='ZL03')";
		
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * @param userName
	 * @return
	 */
	public CustTempApplyPicEntity queryInfoByPic(String userName) {

		String sql="SELECT * FROM CUST_TEMPAPPLY_PIC where username = '"+userName+"' and (pic_type ='ZL02' or pic_type ='ZL03' or pic_type ='ZL112') order by update_date limit 1";
		
		return ConnManager.singleQuery(sql, this);
	}
	
	/**根据类型查询最新一张
	 * @param userName
	 * @param picType
	 * @return
	 */
	public CustTempApplyPicEntity queryNewPic(String userName,
			String picType) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		whereMap.put("pic_type", picType);
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(lists,
				TableConsts.CUST_TEMPAPPLY_PIC, whereMap,
				"order by CREATE_DATE desc limit 1");
		return ConnManager.singleQuery(sql, this);
	}
	
}