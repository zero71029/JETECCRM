package com.JetecCRM.JetecCRM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billboardreply")
public class BillboardReplyBean {
	@Id
	private String replyid;	
	private Integer billboardid;	
	private String name;
	private String content;
	private Date createtime;
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public Integer getBillboardid() {
		return billboardid;
	}
	public void setBillboardid(Integer billboardid) {
		this.billboardid = billboardid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "BillboardReplyBean [replyid=" + replyid + ", billboardid=" + billboardid + ", name=" + name
				+ ", content=" + content + ", createtime=" + createtime + "]";
	}
	
	
}
