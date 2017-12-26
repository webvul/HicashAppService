package com.hengyuan.hicash.parameters.response.param;

import java.util.List;

import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 同款商品查询 返回参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-23
 *
 */
public class SameProductResp extends ParmResponse {

	/** 当前页 */
	private String curPage;

	/** 总页数 */
	private Integer countPage;

	/** 展示商品集合 */
	private List<ProShowEntity> proShowList;

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

	public List<ProShowEntity> getProShowList() {
		return proShowList;
	}

	public void setProShowList(List<ProShowEntity> proShowList) {
		this.proShowList = proShowList;
	}

}
