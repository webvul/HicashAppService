package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.University;
import com.hengyuan.hicash.exception.UniversityNotFound;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Mary.Luo
 * 
 */
public class UniverstyQuery extends AbstractDAO<University> {
	
	private static Logger logger = Logger.getLogger(UniverstyQuery.class);
	private List<String> lists = new ArrayList<String>();
	public UniverstyQuery(){
		lists.add("shcool_code");
		lists.add("shcool_name");
		lists.add("city");
		lists.add("id");
	}
	@Override
	public University mapping(ResultSet rs) throws SQLException {
		University university = new University();
		if (rs != null) {
			university.setShcoolCode(StringUtils.valueOf(rs.getObject("shcool_code")));
			university.setShcoolName(StringUtils.valueOf(rs.getObject("shcool_name")));
			university.setCity(StringUtils.valueOf(rs.getObject("city")));
			university.setId((Integer) rs.getObject("id"));
		}
		return university;
	}

	/**
	 * 根据schoolId查询school信息
	 */
	public University queryGetUniversityBySchool(String schoolId) {

		String sql = "SELECT city,shcool_code,shcool_name,id FROM university where shcool_code='"
				+ schoolId + "'";
		
		RecordUtils.writeAction(logger, schoolId, sql);
		return ConnManager.singleQuery(sql, this);

	}

	/**
	 * 通过学校ID取得学校信息
	 * @param iniversityId
	 * @return
	 * @throws UniversityNotFound
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public University getUniversityById(String iniversityId) 
			throws UniversityNotFound {

		String sql = "SELECT city,shcool_code,shcool_name,id FROM university where id = '"
				+ iniversityId + "'";
		
		RecordUtils.writeAction(logger, iniversityId, sql);
		University university = ConnManager.singleQuery(sql, this);
		
		if(university != null){
			return university;
		}else{
			throw new UniversityNotFound();
		}
	}
	/**
	 * 根据schoolId查询school信息
	 */
	public List<University> querySchoolByCityCode(String cityCode) {

		String sql = "SELECT city,shcool_name,shcool_code,id FROM university where city='"
				+ cityCode + "'";
		
		RecordUtils.writeAction(logger, cityCode, sql);
		return ConnManager.executeQuery(sql, this);

	}
}
