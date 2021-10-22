package com.JetecCRM.JetecCRM.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JetecCRM.JetecCRM.model.AdminBean;

public class SystemFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		servletRequest.setCharacterEncoding("utf-8");
		servletResponse.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		AdminBean user = (AdminBean) request.getSession().getAttribute("user");
		if (user != null) {
			if ("主管".equals(user.getPosition()) || "系統".equals(user.getPosition())) {
				chain.doFilter(servletRequest, servletResponse);
			} else {
				response.sendRedirect(request.getContextPath() + "/CRM.jsp?mess=1");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/CRM.jsp?mess=1");
		}
	}
}
