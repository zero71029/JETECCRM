package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.BillboardGroupBean;

public interface BillboardGroupRepository extends JpaRepository<BillboardGroupBean, String>{

	BillboardGroupBean findByBillboardgroupAndBillboardoption(String billtowngroup, String Billboardgroupid);

	boolean existsByBillboardgroupAndBillboardoption(String group, String option);



}
