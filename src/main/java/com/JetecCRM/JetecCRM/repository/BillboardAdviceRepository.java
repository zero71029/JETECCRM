package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.BillboardAdviceBean;

public interface BillboardAdviceRepository extends JpaRepository<BillboardAdviceBean, String>{

	boolean existsByAdvicetoAndBillboardid(Integer adminid, Integer billboardid);

	void deleteAllByBillboardid(Integer billboardid);

	BillboardAdviceBean getByAdvicetoAndBillboardid(Integer adminid, Integer billboardid);

}
