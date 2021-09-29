package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.TrackBean;

public interface TrackRepository extends JpaRepository<TrackBean, Integer>{

	void deleteByCustomerid(Integer id);

}
