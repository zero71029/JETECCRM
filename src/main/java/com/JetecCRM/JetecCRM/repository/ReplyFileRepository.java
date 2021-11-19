package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.ReplyFileBean;

public interface ReplyFileRepository extends JpaRepository<ReplyFileBean, String>{

	List<ReplyFileBean> findByAuthorize(String authorizeId);
	
	

}
