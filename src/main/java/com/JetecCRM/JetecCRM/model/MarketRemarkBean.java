package com.JetecCRM.JetecCRM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marketremark")
public class MarketRemarkBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer marketid;//外建
	private String user;//留言人
	private String remark;//留言
	private Date createtime;//創建時間
	

	
	@ManyToOne(targetEntity = MarketBean.class)
	@JoinColumn(name = "marketid", referencedColumnName = "marketid", insertable = false, updatable = false)
	private MarketBean mb;// 分類
	
	
	

	public MarketBean getMb() {
		return mb;
	}
	public void setMb(MarketBean mb) {
		this.mb = mb;
	}
	public Integer getMarketid() {
		return marketid;
	}
	public void setMarketid(Integer marketid) {
		this.marketid = marketid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "MarketRemarkBean [id=" + id + ", user=" + user + ", remark=" + remark + ", createtime=" + createtime
				+ "]";
	}
	
	
}
