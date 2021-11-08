package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.BillboardFileBean;

public interface BillboardFileRepository extends JpaRepository<BillboardFileBean, String>{

	List<BillboardFileBean> findByAuthorize(String authorizeId);

	BillboardFileBean getByUrl(String url);



}
