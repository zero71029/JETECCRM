package com.JetecCRM.JetecCRM.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "market")
public class MarketBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer marketid;//
	private String name;//
	private String user;// 業務
	private String createtime;//
	private String endtime;//
	private String message;// 描述
	private Integer cost;// 花費
	private String client;// 客戶
	private String contactperson;// 聯絡人
	private String type;// 類型
	private String source;// 機會來源
	private String clinch;// 成交機率
	private String stage;// 階段
	private String need;// 需求確認
	private String roianalyze;// ROI分析
	private Date ccc;// 創建時間

	//
	@JsonIgnore
	@OneToMany(targetEntity = MarketRemarkBean.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "marketid", referencedColumnName = "marketid")
	private List<MarketRemarkBean> mrb;
	
	
	
	
	

	public List<MarketRemarkBean> getMrb() {
		return mrb;
	}

	public void setMrb(List<MarketRemarkBean> mrb) {
		this.mrb = mrb;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClinch() {
		return clinch;
	}

	public void setClinch(String clinch) {
		this.clinch = clinch;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	public String getRoianalyze() {
		return roianalyze;
	}

	public void setRoianalyze(String roianalyze) {
		this.roianalyze = roianalyze;
	}

	public Date getCcc() {
		return ccc;
	}

	public void setCcc(Date ccc) {
		this.ccc = ccc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getMarketid() {
		return marketid;
	}

	public void setMarketid(Integer marketid) {
		this.marketid = marketid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "MarketBean [marketid=" + marketid + ", name=" + name + ", user=" + user + ", createtime=" + createtime
				+ ", endtime=" + endtime + ", message=" + message + ", cost=" + cost + ", client=" + client
				+ ", contactperson=" + contactperson + ", type=" + type + ", source=" + source + ", clinch=" + clinch
				+ ", stage=" + stage + ", need=" + need + ", roianalyze=" + roianalyze + ", ccc=" + ccc + "]";
	}

}
