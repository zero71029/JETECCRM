package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.JetecCRM.JetecCRM.model.BillboardBean;

public interface BillboardRepository extends JpaRepository<BillboardBean, Integer>{

	List<BillboardBean> getByState(String string, Sort sort);

	List<BillboardBean> getByStateAndTop(String state, String string, Sort sort);

	List<BillboardBean> getByStateAndBillboardgroupidAndTop(String state, String billboardgroupid, String string,
			Sort sort);

	List<BillboardBean> getByStateAndBilltowngroupAndTop(String state, String string, String string2, Sort sort);

	boolean existsByBillboardgroupid(String billboardgroupid);

	List<BillboardBean> findByThemeLikeIgnoreCaseAndState(String Theme, String state, Sort sort);

	List<BillboardBean> findByUserLikeIgnoreCaseAndState(String User, String State, Sort sort);

	
	@Query(value ="SELECT * FROM `billboard` WHERE state = '發佈'  AND  date_sub(curdate(), interval 7 day) <= createtime  ORDER BY createtime DESC", nativeQuery=true)
	List<BillboardBean> getBillboardByTime();

	

}
