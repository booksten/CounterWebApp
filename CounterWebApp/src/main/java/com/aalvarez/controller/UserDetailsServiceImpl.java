package com.aalvarez.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aalvarez.dao.UserInfoDAO;
import com.aalvarez.domain.TSM_UserInfo;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {

		TSM_UserInfo employee = UserInfoDAO.instance.getEmployeeByEmailOrUserName(email);
		if (employee == null)
			throw new UsernameNotFoundException("user not found");
		
		return employee;
	}
}