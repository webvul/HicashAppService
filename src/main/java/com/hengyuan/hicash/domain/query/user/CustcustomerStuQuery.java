package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
* @author dong.liu 
* 2017-1-10 下午4:33:11
* 类说明
 */
public class CustcustomerStuQuery extends AbstractDAO<CustCustomer> {

	private static Logger logger = Logger.getLogger(CustcustomerStuQuery.class);

	@Override
	public CustCustomer mapping(ResultSet rs) throws SQLException {

		CustCustomer custCustomer = new CustCustomer();

		if (rs != null) {

			custCustomer.setUserName(StringUtils.valueOf(rs.getObject("USERNAME"))); // 用户名
			custCustomer.setRealName(StringUtils.valueOf(rs.getObject("NAME"))); // 用户真实姓名
			custCustomer.setIdentityNo(StringUtils.valueOf(rs.getObject("IDENTITY_NO"))); // 身份证号码
			custCustomer.setMaritalStatus(StringUtils.valueOf(rs.getObject("MARITAL_STATUS")));// 婚姻状况

			custCustomer.setOtherAdressProvince(StringUtils.valueOf(rs.getObject("OTHER_ADRESS_PROVINCE")));// 现居住省
			custCustomer.setOtherAdressCity(StringUtils.valueOf(rs.getObject("OTHER_ADRESS_CITY"))); // 现居住市
			custCustomer.setOtherAdressArea(StringUtils.valueOf(rs.getObject("OTHER_ADRESS_AREA")));// 现居住区
			custCustomer.setOtherAccommodationAddress(StringUtils.valueOf(rs.getObject("OTHER_ACCOMMODATION_ADDRESS")));// 现居街道住址

			custCustomer.setEmailAdress(StringUtils.valueOf(rs.getObject("EMAIL_ADRESS"))); // 电子邮箱
			custCustomer.setIdCardValStartDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_STARTDATE")));	//身份证有效期开始时间
			custCustomer.setIdCardValEndDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_ENDDATE")));	//身份证有效期结束时间 
            /**
             * 2017-09-05新增 查询用户是否验证
             */
			custCustomer.setIsUpName(StringUtils.valueOf(rs.getObject("NICK_NAME")));
			custCustomer.setLoanUse(StringUtils.valueOf(rs.getObject("borrow_money_use")));
			
		} else {
			custCustomer = null;

		}
		return custCustomer;
	}

	/**
	 * 根据username查询客户信息
	 * 
	 * @param userName
	 * @return
	 */
	public CustCustomer queryCustCustomer(String userName) {

		List<String> selects = new ArrayList<String>();

		selects.add("USERNAME");
		selects.add("NAME");
		selects.add("IDENTITY_NO");
		selects.add("MARITAL_STATUS");
		selects.add("NOW_EDUCATION");
		selects.add("OTHER_ADRESS_PROVINCE");
		selects.add("OTHER_ADRESS_CITY");
		selects.add("OTHER_ADRESS_AREA");
		selects.add("OTHER_ACCOMMODATION_ADDRESS");
		selects.add("EMAIL_ADRESS");
		selects.add("IDCARD_VALIDITY_STARTDATE");
		selects.add("IDCARD_VALIDITY_ENDDATE");
		selects.add("NICK_NAME");
		selects.add("borrow_money_use");
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("username", userName);

		String custSql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, conMap);
		RecordUtils.writeAction(logger, userName, custSql);
		return ConnManager.singleQuery(custSql, this);

	}
	
	
	

}
