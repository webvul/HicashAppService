package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.Link;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
* @author blanke.qin
* 2017年4月6日 下午12:37:56
* 类说明
 */
public class PageMessageResp extends ParmResponse {
	
	private String limit_desc;//	最高额度文案说明
	private String is_skip;//芝麻页面是否跳过
	private String skip_msg;//芝麻页面跳过描述
	private String but_msg_1;//学信页面按钮描述
	private String but_msg_2;//学信页面按钮描述
	private String bomb_box_desc;//学信页面弹框内容
	
	private String lady_desc;//嗨女神首页文案
	private String ladylimit_desc;//嗨女神最高额度文案说明
	private String ladybut_msg_1;//嗨女神认证或体现按钮
	private String ladylimit_desc2;//嗨女神可用额度
	private String ladyblow_desc;//嗨女神（外卖，可跳过页面）下面的文字
	private String lady_type;//嗨女神授信类型（1授信状态 2提额状态）
	private String amount_color_1;//嗨女神变色字段
	private String amount_color_2;//嗨女神变色字段2
	
	private String sign_msg;//协议文字
	private String sign_url;//协议h5地址
	
	//协议文字(S001-S006)
    private String plainText;
	
	//地址   数组
    private List<Link>  link;
    //协议弹框提示
    private String bomb_capion;
    //嗨钱来图片地址
    private String dd_img_url;
  //嗨钱来引导h5
    private String dd_h5_url;
    
    
	
	
	public String getLimit_desc() {
		return limit_desc;
	}
	public void setLimit_desc(String limit_desc) {
		this.limit_desc = limit_desc;
	}
	public String getIs_skip() {
		return is_skip;
	}
	public void setIs_skip(String is_skip) {
		this.is_skip = is_skip;
	}
	public String getSkip_msg() {
		return skip_msg;
	}
	public void setSkip_msg(String skip_msg) {
		this.skip_msg = skip_msg;
	}
	public String getBut_msg_1() {
		return but_msg_1;
	}
	public void setBut_msg_1(String but_msg_1) {
		this.but_msg_1 = but_msg_1;
	}
	public String getBut_msg_2() {
		return but_msg_2;
	}
	public void setBut_msg_2(String but_msg_2) {
		this.but_msg_2 = but_msg_2;
	}
	public String getBomb_box_desc() {
		return bomb_box_desc;
	}
	public void setBomb_box_desc(String bomb_box_desc) {
		this.bomb_box_desc = bomb_box_desc;
	}
	public String getLady_desc() {
		return lady_desc;
	}
	public void setLady_desc(String lady_desc) {
		this.lady_desc = lady_desc;
	}
	public String getLadylimit_desc() {
		return ladylimit_desc;
	}
	public void setLadylimit_desc(String ladylimit_desc) {
		this.ladylimit_desc = ladylimit_desc;
	}
	public String getLadybut_msg_1() {
		return ladybut_msg_1;
	}
	public void setLadybut_msg_1(String ladybut_msg_1) {
		this.ladybut_msg_1 = ladybut_msg_1;
	}
	public String getLadylimit_desc2() {
		return ladylimit_desc2;
	}
	public void setLadylimit_desc2(String ladylimit_desc2) {
		this.ladylimit_desc2 = ladylimit_desc2;
	}
	public String getLadyblow_desc() {
		return ladyblow_desc;
	}
	public void setLadyblow_desc(String ladyblow_desc) {
		this.ladyblow_desc = ladyblow_desc;
	}
	public String getLady_type() {
		return lady_type;
	}
	public void setLady_type(String lady_type) {
		this.lady_type = lady_type;
	}
	public String getAmount_color_1() {
		return amount_color_1;
	}
	public void setAmount_color_1(String amount_color_1) {
		this.amount_color_1 = amount_color_1;
	}
	public String getAmount_color_2() {
		return amount_color_2;
	}
	public void setAmount_color_2(String amount_color_2) {
		this.amount_color_2 = amount_color_2;
	}
	public String getSign_msg() {
		return sign_msg;
	}
	public void setSign_msg(String sign_msg) {
		this.sign_msg = sign_msg;
	}
	public String getSign_url() {
		return sign_url;
	}
	public void setSign_url(String sign_url) {
		this.sign_url = sign_url;
	}
	public String getPlainText() {
		return plainText;
	}
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	public List<Link> getLink() {
		return link;
	}
	public void setLink(List<Link> link) {
		this.link = link;
	}
	public String getBomb_capion() {
		return bomb_capion;
	}
	public void setBomb_capion(String bomb_capion) {
		this.bomb_capion = bomb_capion;
	}
	public String getDd_img_url() {
		return dd_img_url;
	}
	public void setDd_img_url(String dd_img_url) {
		this.dd_img_url = dd_img_url;
	}
	public String getDd_h5_url() {
		return dd_h5_url;
	}
	public void setDd_h5_url(String dd_h5_url) {
		this.dd_h5_url = dd_h5_url;
	}
	
	
	
}
