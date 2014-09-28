package com.aalvarez.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.aalvarez.domain.AbstractDomain;

public abstract class EntityDAO<T extends AbstractDomain> {
	private static EntityManagerFactory factory;

	public static EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("counterwebappPU");
		}
		return factory.createEntityManager();
	}

	private final Class<T> type;

	public Class<T> getType() {
		return type;
	}

	public EntityDAO(Class<T> type) {
		this.type = type;
	}

	protected void doInTransaction(TransactionCallback callback) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			callback.doInTransaction(em);
			em.getTransaction().commit();

		} catch (Throwable th) {
			th.printStackTrace();
			em.getTransaction().rollback();
			throw new RuntimeException("Error: " + th.getMessage());
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("hiding")
	public <T> void save(final T entity) {
		doInTransaction(new TransactionCallback() {
			public void doInTransaction(EntityManager em) {
				em.persist(entity);
			}
		});
	}

	@SuppressWarnings("hiding")
	public <T> void update(final T entity) {
		doInTransaction(new TransactionCallback() {
			public void doInTransaction(EntityManager em) {
				em.merge(entity);
			}
		});
	}
	
	@SuppressWarnings("hiding")
	public <T> void delete(final T entity) {
		doInTransaction(new TransactionCallback() {
			public void doInTransaction(EntityManager em) {
				em.remove(em.contains(entity) ? entity : em.merge(entity));
			}
		});
	}

	@SuppressWarnings("hiding")
	protected <T extends AbstractDomain> void saveOrUpdate(final T entity) {
		doInTransaction(new TransactionCallback() {
			public void doInTransaction(EntityManager em) {
				if (entity.getId() != null)
					em.merge(entity);
				else
					em.persist(entity);
			}
		});
	}

	public AbstractDomain findById(Long id) {
		return getEntityManager().find(this.type, id);
	}

}
