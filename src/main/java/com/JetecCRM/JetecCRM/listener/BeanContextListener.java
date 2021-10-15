package com.JetecCRM.JetecCRM.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.JetecCRM.JetecCRM.repository.AdminRepository;
import com.JetecCRM.JetecCRM.repository.BillboardGroupRepository;
import com.JetecCRM.JetecCRM.repository.ClientRepository;

@Component
public class BeanContextListener implements ServletContextListener {
	@Autowired
	AdminRepository ar;
	@Autowired
	ClientRepository cr;
	@Autowired
	BillboardGroupRepository bgr;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("bean context 初始化");
		ServletContext app = sce.getServletContext();		
		app.setAttribute("admin", ar.findAll());
		app.setAttribute("client", cr.findAll());
		app.setAttribute("billboardgroup", bgr.findAll());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("bean context 销毁");
	}

}
