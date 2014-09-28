package com.aalvarez.dao;

import javax.persistence.EntityManager;

public interface TransactionCallback {

    public void doInTransaction(EntityManager em);
}
