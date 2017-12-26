package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.user.QueryMyMsgEntity;
import com.hengyuan.hicash.parameters.request.user.QueryMyMsgReq;
import com.hengyuan.hicash.parameters.response.user.QueryMyMsgResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class QueryMyMsgQuery extends AbstractDAO<QueryMyMsgEntity>{

	private static Logger logger = Logger.getLogger(QueryMyMsgQuery.class);
	private List<String> lists = new ArrayList<String>();
	
	public QueryMyMsgQuery() {
		lists.add("id");
		lists.add("title");
		lists.add("content");
		lists.add("is_read");
		lists.add("type");
		lists.add("operate");
		lists.add("create_time");
		lists.add("type");
		lists.add("appNo");
	}
	@Override
	public QueryMyMsgEntity mapping(ResultSet rs) throws SQLException {
		QueryMyMsgEntity queryMyMsgEntity = new QueryMyMsgEntity();
		if(rs!=null){
			queryMyMsgEntity.setId(rs.getString("id"));
			queryMyMsgEntity.setTitle(rs.getString("title"));
			queryMyMsgEntity.setContent(rs.getString("content"));
			queryMyMsgEntity.setIs_read(rs.getString("is_read"));
			queryMyMsgEntity.setType(rs.getString("type"));
			queryMyMsgEntity.setOperate(rs.getString("operate"));
			queryMyMsgEntity.setCreateTime(DateUtils.timeFormatToDateStr(rs.getString("create_time")));
			if("hinslb".equals(rs.getString("type"))){
				queryMyMsgEntity.setCategoryId("5");
			}
			queryMyMsgEntity.setAppNo(rs.getString("appNo"));
		}
		
		return queryMyMsgEntity;
	}

	public QueryMyMsgResp  queryMyMsg(QueryMyMsgReq queryMyMsgReq){
		List<QueryMyMsgEntity> list=new ArrayList<QueryMyMsgEntity>();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", queryMyMsgReq.getUserName());
		String sql = null;
		sql = MapAssemForSql.getSelectSqlByGroupOrOrder(lists, TableConsts.CUST_USER_MESSAGE, whereMap," order by create_time desc ");
		System.out.println(sql);
		logger.info("通过用户名查询我的消息");
		RecordUtils.writeAction(logger, null, sql);
		list= ConnManager.executeQuery(sql, this);
		QueryMyMsgResp resp=null;
		if(list.size()>0){
			Integer maxLine = queryMyMsgReq.getMaxLine();
			Integer countPage=0;
			if(maxLine!=0){
				countPage = countPage(maxLine, list.size());
			}
			if (countPage != 0 && !queryMyMsgReq.getCurPage().equals("0")) {
				resp = new QueryMyMsgResp();
				String listSql = "";
			         // 当前页
					Integer curPage = sumCurPage(queryMyMsgReq.getCurPage(), countPage);
						// 查询起始行
						Integer firstLine = (curPage - 1) * queryMyMsgReq.getMaxLine();
						listSql = sql.toString()
									+ " LIMIT " + firstLine + ","
									+ maxLine + "";
						
						List<QueryMyMsgEntity> queryMyMsglist = ConnManager.executeQuery(
								listSql.toString(), this);
						List<QueryMyMsgEntity> myMsglist = new ArrayList<QueryMyMsgEntity>();
						ApplicationQuery payQuery = new ApplicationQuery();
						QueryMyMsgResp msgResp = null;
						for(QueryMyMsgEntity myMsg:queryMyMsglist){

							myMsg.setIsCancel("N");
							
							if(StringUtils.isNotBlank(myMsg.getAppNo())){
								ApplicationEntity pay = payQuery.queryAppPayByAppNO(myMsg.getAppNo());
								if(pay!=null){
									if("STATUS20".equals(pay.getStatus())){
										myMsg.setIsCancel("Y");
									}
									if(("HKNODE".equals(pay.getAllnode())||"GBNODE".equals(pay.getAllnode())||"WCNODE".equals(pay.getAllnode()))
											&&("立即签约".equals(myMsg.getOperate())||"签约拿钱".equals(myMsg.getOperate()))){
										myMsg.setOperate("");
									}
								}
								
							}
							
							msgResp = new QueryMyMsgResp();
							msgResp.setMyMsg(myMsg);
							myMsglist.add(myMsg);
						}
						
						resp.setList(myMsglist);
						resp.setCurPage(curPage + "");
						resp.setCountPage(!list.isEmpty() ? countPage : 0);
					}else{
						resp = new QueryMyMsgResp();
						resp.setList(list);
					}
				}else {
					resp = new QueryMyMsgResp();
					resp.setList(list);
				}
		return resp;
   }
	
	
	
	/**
	 * 查看未读数
	 * @param queryMyMsgReq
	 * @return
	 */
	public QueryMyMsgResp  queryNOReadMyMsg(QueryMyMsgReq queryMyMsgReq,QueryMyMsgResp resp){
		List<QueryMyMsgEntity> list=new ArrayList<QueryMyMsgEntity>();
		String sql = "select id,title,content,is_read,type,operate,create_time,type,appNo from cust_user_message where username = '"+queryMyMsgReq.getUserName()+"' and is_read=0";
 		//记录日志
 		RecordUtils.writeAction(logger, queryMyMsgReq.getUserName(), sql.toString());
		logger.info("通过用户名查询我的消息");
		RecordUtils.writeAction(logger, null, sql);
		
		list=  ConnManager.executeQuery(sql, this);
		if(list!=null && list.size()>0){
			resp.setNoMsgNum(list.size()+"");
		}else{
			resp.setNoMsgNum("0");
		}
		
		
		return resp;
   }
}