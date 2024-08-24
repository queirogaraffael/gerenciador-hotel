package org.unifacisa.model.dao.imp;

import org.unifacisa.model.dao.ReservaDao;

import javax.persistence.EntityManagerFactory;

public class ReservaDaoHibernate implements ReservaDao {

    private final EntityManagerFactory entityManagerFactory;

    public ReservaDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
