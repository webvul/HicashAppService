package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.QueryMyMsgEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

public class QueryMyMsgResp extends ParmResponse{
	
	
	private List<QueryMyMsgEntity> list;
		
	private QueryMyMsgEntity myMsg;
	
	/** 当前页 */
	private String curPage;

	/** 总页数 */
	private Integer countPage;
	/**未读总数*/
	private String noMsgNum;

	public QueryMyMsgEntity getMyMsg() {
		return myMsg;
	}

	public void setMyMsg(QueryMyMsgEntity myMsg) {
		this.myMsg = myMsg;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public Integer getCountPage() {
		return countPage;
	}

	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
	}

	public List<QueryMyMsgEntity> getList() {
		return list;
	}

	public void setList(List<QueryMyMsgEntity> list) {
		this.list = list;
	}

	public String getNoMsgNum() {
		return noMsgNum;
	}

	public void setNoMsgNum(String noMsgNum) {
		this.noMsgNum = noMsgNum;
	}

		
	
}
