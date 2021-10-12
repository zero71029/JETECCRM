package com.JetecCRM.JetecCRM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminmail")
public class AdminmailBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String adminmail;
    private Integer adminid	;
    private Integer billboardid;

    

}
