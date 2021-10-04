package com.JetecCRM.JetecCRM.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class ContactBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactid;
	private Integer clientid;
	private String name;//聯絡人名稱
	private String company;//公司
	private String jobtitle;//職務
	private String email;//
	private String phone;//
	private String moblie;//手機
	private String address;//
	private String department;//部門
	private String 	director;//直屬主管
	private String fax;//
	private String remark;//備註
	private String user;//負責人
	private String contacttime;//上次聯絡時間
	
	
	@ManyToOne(targetEntity = ClientBean.class)
	@JoinColumn(name = "clientid", referencedColumnName = "clientid", insertable = false, updatable = false)
	private ClientBean client;
	

	
	
	
	
	public ClientBean getClient() {
		return client;
	}
	public void setClient(ClientBean client) {
		this.client = client;
	}
	public Integer getContactid() {
		return contactid;
	}
	public void setContactid(Integer contactid) {
		this.contactid = contactid;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getContacttime() {
		return contacttime;
	}
	public void setContacttime(String contacttime) {
		this.contacttime = contacttime;
	}
	@Override
	public String toString() {
		return "ContactBean [contactid=" + contactid + ", clientid=" + clientid + ", name=" + name + ", company="
				+ company + ", jobtitle=" + jobtitle + ", email=" + email + ", phone=" + phone + ", moblie=" + moblie
				+ ", address=" + address + ", department=" + department + ", director=" + director + ", fax=" + fax
				+ ", remark=" + remark + ", user=" + user + ", contacttime=" + contacttime + "]";
	}
	
	
	
}
