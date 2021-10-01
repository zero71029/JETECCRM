package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.MarketBean;

public interface MarketRepository extends JpaRepository<MarketBean, Integer>{



	List<MarketBean> findByNameLikeIgnoreCase(String string);

	List<MarketBean>findByUserLikeIgnoreCase(String string);

	List<MarketBean> findByClientLikeIgnoreCase(String string);
	
	List<MarketBean> findByClient(String name);

}
