package org.unifacisa.services;

import org.unifacisa.model.dao.ReservaDao;
import org.unifacisa.model.dao.imp.ReservaDaoHibernate;

import javax.persistence.EntityManagerFactory;

public class ReservaService {

    private final ReservaDao reservaDao;

    public ReservaService(EntityManagerFactory entityManagerFactory) {
        this.reservaDao = new ReservaDaoHibernate(entityManagerFactory);
    }


}
