package com.JetecCRM.JetecCRM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billboard")
public class BillboardBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billboardid;

	private String user; //發表人	
	private String theme; //主題	
	private String content; //內容	
	private String state; //狀態	
	private Date createtime; //創造時間	
	private String endtime; //結束時間
	public Integer getBillboardid() {
		return billboardid;
	}
	public void setBillboardid(Integer billboardid) {
		this.billboardid = billboardid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	@Override
	public String toString() {
		return "BillboardBean [billboardid=" + billboardid + ", user=" + user + ", theme=" + theme + ", content="
				+ content + ", state=" + state + ", createtime=" + createtime + ", endtime=" + endtime + "]";
	}
	
	

}
