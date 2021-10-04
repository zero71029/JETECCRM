package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.PotentialCustomerBean;

public interface PotentialCustomerRepository extends JpaRepository<PotentialCustomerBean, Integer>{

	List<PotentialCustomerBean> findByNameLikeIgnoreCase(String string);

	List<PotentialCustomerBean> findByUserLikeIgnoreCase(String string);

	List<PotentialCustomerBean> findByCompanyLikeIgnoreCase(String string);

}
