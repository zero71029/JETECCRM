package com.JetecCRM.JetecCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JetecCRM.JetecCRM.model.ClientBean;

public interface ClientRepository extends JpaRepository<ClientBean, Integer>{

}
