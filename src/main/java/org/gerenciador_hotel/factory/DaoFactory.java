package org.gerenciador_hotel.factory;

import org.gerenciador_hotel.model.dao.FuncionarioDao;
import org.gerenciador_hotel.model.dao.HospedeDao;
import org.gerenciador_hotel.model.dao.QuartoDao;
import org.gerenciador_hotel.model.dao.ReservaDao;
import org.gerenciador_hotel.model.dao.imp.FuncionarioDaoHibernate;
import org.gerenciador_hotel.model.dao.imp.HospedeDaoHibernate;
import org.gerenciador_hotel.model.dao.imp.QuartoDaoHibernate;
import org.gerenciador_hotel.model.dao.imp.ReservaDaoHibernate;

import javax.persistence.EntityManagerFactory;

public class DaoFactory {

    private final EntityManagerFactory entityManagerFactory;

    public DaoFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public FuncionarioDao createFuncionarioDao() {
        return new FuncionarioDaoHibernate(entityManagerFactory);
    }

    public HospedeDao createHospedeDao() {
        return new HospedeDaoHibernate(entityManagerFactory);
    }

    public QuartoDao createQuartoDao() {
        return new QuartoDaoHibernate(entityManagerFactory);
    }

    public ReservaDao createReservaDao() {
        return new ReservaDaoHibernate(entityManagerFactory);
    }
}