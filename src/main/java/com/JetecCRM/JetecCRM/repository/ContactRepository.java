package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JetecCRM.JetecCRM.model.ContactBean;

public interface ContactRepository extends JpaRepository<ContactBean, Integer>{

	List<ContactBean> findByCompany(String name);

	List<ContactBean> findByNameLikeIgnoreCase(String string);

	List<ContactBean> findByCompanyLikeIgnoreCase(String string);

	

	List<ContactBean> findByMoblieLikeIgnoreCase(String string);

	List<ContactBean> findByPhoneLikeIgnoreCase(String string);



}
