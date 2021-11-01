package com.JetecCRM.JetecCRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.AdminBean;

public interface AdminRepository extends JpaRepository<AdminBean, Integer>{

	boolean existsByEmailAndPassword(String userName, String userPassword);

	AdminBean findByEmailAndPassword(String userName, String userPassword);

	AdminBean findByName(String user);

	boolean existsByEmail(String email);

	List<AdminBean> getByDepartment(String d);

	List<AdminBean> getByPosition(String d);

}
