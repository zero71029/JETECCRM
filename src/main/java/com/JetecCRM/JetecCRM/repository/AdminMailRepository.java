package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.AdminMailBean;

public interface AdminMailRepository extends JpaRepository<AdminMailBean, String>{

	boolean existsByBillboardidAndAdminid(Integer billboardid, Integer adminid);

	void deleteByBillboardidAndAdminid(Integer billboardid, Integer adminid);

	boolean existsByBillboardid(Integer id);

	void deleteAllByBillboardid(Integer id);



}
