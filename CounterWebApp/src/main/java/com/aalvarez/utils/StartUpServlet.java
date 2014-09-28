package com.aalvarez.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.security.core.context.SecurityContextHolder;



public class StartUpServlet implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public void contextInitialized(ServletContextEvent servletContext) {
	}
}
