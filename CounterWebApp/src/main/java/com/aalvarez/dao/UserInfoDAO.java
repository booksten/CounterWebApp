package com.aalvarez.dao;

import javax.persistence.Query;

import com.aalvarez.domain.TSM_UserInfo;

public class UserInfoDAO extends EntityDAO<TSM_UserInfo> {
	
	private UserInfoDAO() {
		super(TSM_UserInfo.class);
	}

	public static final UserInfoDAO instance = new UserInfoDAO();
	
	public TSM_UserInfo getUserInfoById(Long id){
		Query query = getEntityManager().createQuery("Select u from TSM_UserInfo u where u.id = :id");
		query.setParameter("id", id);
		try{
			return (TSM_UserInfo) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
