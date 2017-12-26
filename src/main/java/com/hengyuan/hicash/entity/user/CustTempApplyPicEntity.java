package com.hengyuan.hicash.entity.user;

/**
 *身份认证图片保存实体
 * 
 * @author LiHua.Ren
 * @create date 2015-07-20
 */
public class CustTempApplyPicEntity {

	private String id;
	private String userName;
	private String picName;
	private String picCaption;
	private String picPath;
	private String picType;
	private String updateDate;
	
	private String updateUser;
	private String createDate;
	private String createUser;
	private String thumPath;
	/**图片状态，0无效1有效*/
	private String status;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the picName
	 */
	public String getPicName() {
		return picName;
	}
	/**
	 * @param picName the picName to set
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}
	/**
	 * @return the picCaption
	 */
	public String getPicCaption() {
		return picCaption;
	}
	/**
	 * @param picCaption the picCaption to set
	 */
	public void setPicCaption(String picCaption) {
		this.picCaption = picCaption;
	}
	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}
	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	/**
	 * @return the picType
	 */
	public String getPicType() {
		return picType;
	}
	/**
	 * @param picType the picType to set
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * @return the thumPath
	 */
	public String getThumPath() {
		return thumPath;
	}
	/**
	 * @param thumPath the thumPath to set
	 */
	public void setThumPath(String thumPath) {
		this.thumPath = thumPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    
}
