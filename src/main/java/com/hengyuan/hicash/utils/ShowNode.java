package com.hengyuan.hicash.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ProcessConst;
import com.hengyuan.hicash.entity.app.AccountPay;

public class ShowNode {

	public static String showAllNode(String allNode, String status) {

		if (ProcessConst.ALL_NODE_NE.equals(allNode)) {
			return "1";
		}

		if (ProcessConst.ALL_NODE_ZL.equals(allNode)) {
			return "2";
		}

		if (ProcessConst.ALL_NODE_SH.equals(allNode)
				|| ProcessConst.ALL_NODE_TH.equals(allNode)) {
			return "3";
		}
		
		if(ProcessConst.STATUS20.equals(status) || ProcessConst.STATUS21.equals(status)){
			return "5";
		}
		
		return "4";
	}

	public static String isCanCancel(String allNode) {
		if (ProcessConst.ALL_NODE_NE.equals(allNode) // 新申请
				|| ProcessConst.ALL_NODE_ZL.equals(allNode)) {
			return "1";
		} else {
			return "0";
		}
	}

	public static String isFirst(String node, String firstAmt) {
		
		try {
			if (ProcessConst.NODE07.equals(node)
					&& new BigDecimal(firstAmt).compareTo(BigDecimal.ZERO) == 1) {

				return "1";
			} else {
				return "0";
			}

		} catch (Exception e) {
			return "0";
		}

	}

	public static List<AccountPay> createAccList(List<AccountPay> accountPayList) {

		List<AccountPay> list = new ArrayList<AccountPay>();

		for (int i = 0; i < accountPayList.size(); i++) {

			AccountPay pay = accountPayList.get(i);
			pay.setShowAllNode(showAllNode(pay.getAllNode(),pay.getStatus())); // 显示节点
			pay.setIsCancel(isCanCancel(pay.getAllNode()));
			pay.setIsFirst(isFirst(pay.getNode(), pay.getFirstAmount()));
			list.add(pay);
		}

		return list;

	}

}
