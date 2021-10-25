package com.JetecCRM.JetecCRM.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminmail")
public class AdminMailBean {
    @Id   
    private String adminmail;
    private Integer adminid;
    private Integer billboardid;
	public String getAdminmail() {
		return adminmail;
	}
	public void setAdminmail(String adminmail) {
		this.adminmail = adminmail;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public Integer getBillboardid() {
		return billboardid;
	}
	public void setBillboardid(Integer billboardid) {
		this.billboardid = billboardid;
	}
	@Override
	public String toString() {
		return "AdminmailBean [adminmail=" + adminmail + ", adminid=" + adminid + ", billboardid=" + billboardid + "]";
	}

    

}
