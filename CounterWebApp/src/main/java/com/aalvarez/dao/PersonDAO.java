package com.aalvarez.dao;

import java.util.List;

import javax.persistence.Query;

import com.aalvarez.domain.TSM_Person;


public class PersonDAO extends EntityDAO<TSM_Person>{

	private PersonDAO() {
		super(TSM_Person.class);
	}

	public static final PersonDAO instance = new PersonDAO();

	@SuppressWarnings("unchecked")
	public List<TSM_Person> getAllEmployees() {
		Query query = getEntityManager().createQuery("Select e from TSM_Person e");
		return query.getResultList();
	}
	
	public TSM_Person getLastEmployees() {
		Query query = getEntityManager().createQuery("Select e from TSM_Person e order by e.id desc");
		query.setMaxResults(1);
		return (TSM_Person) query.getSingleResult();
	}
	
}
