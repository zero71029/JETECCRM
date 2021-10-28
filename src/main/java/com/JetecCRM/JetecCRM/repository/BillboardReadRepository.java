package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.BillboardReadBean;
import com.JetecCRM.JetecCRM.model.BillboardReplyBean;

public interface BillboardReadRepository extends JpaRepository<BillboardReadBean, String>{

	Boolean existsByBillboardidAndName(Integer billboardid, String name);

	void deleteByBillboardidAndName(Integer billboardid, String username);


}
