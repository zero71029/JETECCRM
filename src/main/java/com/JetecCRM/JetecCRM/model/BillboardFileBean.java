package com.JetecCRM.JetecCRM.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billboardfile")
public class BillboardFileBean {
	
    @Id   
    private String fileid;;
    private Integer billboardid	;
    private String url;
    
    
    
    
    
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public Integer getBillboardid() {
		return billboardid;
	}
	public void setBillboardid(Integer billboardid) {
		this.billboardid = billboardid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "BillboardFileBean [fileid=" + fileid + ", billboardid=" + billboardid + ", url=" + url + "]";
	}
    
    
    

}
