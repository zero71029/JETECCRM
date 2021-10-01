package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JetecCRM.JetecCRM.model.ClientBean;

public interface ClientRepository extends JpaRepository<ClientBean, Integer>{

	
	@Query(value ="SELECT clientid FROM `client` WHERE name = ?1  ", nativeQuery=true)
	Integer selectIdByname(String name);

	List<ClientBean> findByNameLikeIgnoreCase(String string);

	List<ClientBean> findByUniformnumberLikeIgnoreCase(String string);

	List<ClientBean> findByPhoneLikeIgnoreCase(String string);

	List<ClientBean> findByUserLikeIgnoreCase(String string);


}
