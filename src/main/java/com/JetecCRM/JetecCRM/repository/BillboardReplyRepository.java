package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.BillboardReplyBean;

public interface BillboardReplyRepository extends JpaRepository<BillboardReplyBean, String>{

	List<BillboardReplyBean> getByBillboardid(Integer billboardid, Sort sort);

}
