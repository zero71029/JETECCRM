package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.MarketRemarkBean;
import java.lang.Integer;
import java.util.List;

public interface MarketRemarkRepository extends JpaRepository<MarketRemarkBean, Integer>{
List<MarketRemarkBean> findByMarketid(Integer marketid);


void deleteByMarketid(Integer i);
}
