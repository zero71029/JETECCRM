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
@Table(name="potentialcustomer")
public class PotentialCustomerBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerid;
	private String name;
	private String company;
	private String jobtitle;
	private String email;
	private String phone;
	private String moblie;
	private String fax;
	private String department;
	private String 	director;
	private String industry;
	private Integer companynum;
	private String source;
	private String 	fromactivity;
	private String user;
	private String contacttime;
	private String status;
	private String address;
	private String remark;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerid", cascade = CascadeType.ALL)
	private List<TrackBean> trackbean;	
	
	
	public List<TrackBean> getTrackbean() {
		return trackbean;
	}
	public void setTrackbean(List<TrackBean> trackbean) {
		this.trackbean = trackbean;
	}
	@Override
	public String toString() {
		return "PotentialCustomerBean [customerid=" + customerid + ", name=" + name + ", company=" + company
				+ ", jobtitle=" + jobtitle + ", email=" + email + ", phone=" + phone + ", moblie=" + moblie + ", fax="
				+ fax + ", department=" + department + ", director=" + director + ", industry=" + industry
				+ ", companynum=" + companynum + ", source=" + source + ", fromactivity=" + fromactivity + ", user="
				+ user + ", contacttime=" + contacttime + ", status=" + status + ", address=" + address +  "]";
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public Integer getCompanynum() {
		return companynum;
	}
	public void setCompanynum(Integer companynum) {
		this.companynum = companynum;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFromactivity() {
		return fromactivity;
	}
	public void setFromactivity(String fromactivity) {
		this.fromactivity = fromactivity;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	
}
