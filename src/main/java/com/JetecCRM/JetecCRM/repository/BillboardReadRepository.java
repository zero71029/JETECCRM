package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.BillboardReadBean;

public interface BillboardReadRepository extends JpaRepository<BillboardReadBean, String>{

	Boolean existsByBillboardidAndName(Integer billboardid, String name);

	void deleteByBillboardidAndName(Integer billboardid, String username);


}
