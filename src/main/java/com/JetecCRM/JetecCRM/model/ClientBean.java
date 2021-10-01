package com.JetecCRM.JetecCRM.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class ClientBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientid;//
	private String name;//
	private String sort;//類別
	private String url;//網站
	private String email;//網站
	private String industry;//產業
	private String uniformnumber;//統一號碼
	private String phone;//
	private String fax;//
	private String peoplenumber;//員工人數
	private String billcity;//帳單城市
	private String billtown;//帳單地區
	private String billpostal;//帳單郵遞區號
	private String billaddress;//帳單地址
	private String delivercity;//送貨城市
	private String delivertown;//送貨地區
	private String deliverpostal;//送貨郵遞區號
	private String deliveraddress;//送貨地址
	private String remark;//描述
	private String user;//負責人
	
	
	
	
	
	
	@JsonIgnore
	@OneToMany(targetEntity = ContactBean.class   ,mappedBy = "clientid", cascade = CascadeType.ALL)
	private List<ContactBean> contact;	
	
	
	
	
	public List<ContactBean> getContact() {
		return contact;
	}
	public void setContact(List<ContactBean> contact) {
		this.contact = contact;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBilltown() {
		return billtown;
	}
	public void setBilltown(String billtown) {
		this.billtown = billtown;
	}
	public String getDelivertown() {
		return delivertown;
	}
	public void setDelivertown(String delivertown) {
		this.delivertown = delivertown;
	}
	public Integer getClientid() {
		return clientid;
	}
	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getUniformnumber() {
		return uniformnumber;
	}
	public void setUniformnumber(String uniformnumber) {
		this.uniformnumber = uniformnumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPeoplenumber() {
		return peoplenumber;
	}
	public void setPeoplenumber(String peoplenumber) {
		this.peoplenumber = peoplenumber;
	}
	public String getBillcity() {
		return billcity;
	}
	public void setBillcity(String billcity) {
		this.billcity = billcity;
	}
	public String getBillpostal() {
		return billpostal;
	}
	public void setBillpostal(String billpostal) {
		this.billpostal = billpostal;
	}
	public String getBilladdress() {
		return billaddress;
	}
	public void setBilladdress(String billaddress) {
		this.billaddress = billaddress;
	}
	public String getDelivercity() {
		return delivercity;
	}
	public void setDelivercity(String delivercity) {
		this.delivercity = delivercity;
	}
	public String getDeliverpostal() {
		return deliverpostal;
	}
	public void setDeliverpostal(String deliverpostal) {
		this.deliverpostal = deliverpostal;
	}
	public String getDeliveraddress() {
		return deliveraddress;
	}
	public void setDeliveraddress(String deliveraddress) {
		this.deliveraddress = deliveraddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ClientBean [clientid=" + clientid + ", name=" + name + ", sort=" + sort + ", url=" + url + ", industry="
				+ industry + ", uniformnumber=" + uniformnumber + ", phone=" + phone + ", fax=" + fax
				+ ", peoplenumber=" + peoplenumber + ", billcity=" + billcity + ", billpostal=" + billpostal
				+ ", billaddress=" + billaddress + ", delivercity=" + delivercity + ", deliverpostal=" + deliverpostal
				+ ", deliveraddress=" + deliveraddress + ", remark=" + remark + "]";
	}
	
}
