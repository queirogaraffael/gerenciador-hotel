package org.gerenciador_hotel.services;

import org.gerenciador_hotel.model.dao.HospedeDao;
import org.gerenciador_hotel.model.dao.imp.HospedeDaoHibernate;
import org.gerenciador_hotel.model.domain.entities.Hospede;

import javax.persistence.EntityManagerFactory;

public class HospedeService {
    private final HospedeDao hospedeDao;

    public HospedeService(EntityManagerFactory entityManagerFactory) {
        this.hospedeDao = new HospedeDaoHibernate(entityManagerFactory);
    }


    public void cadastraHospede(Hospede hospede) {
        hospedeDao.cadastraHospede(hospede);
    }

    public void atualizaHospede(Hospede hospedeModificado) {
        hospedeDao.atualizaHospede(hospedeModificado);
    }

    public Hospede getHospedeByCPF(String cpf) {
        return hospedeDao.getHospedeByCPF(cpf);
    }

    public boolean haHospedeComMesmoCPF(String cpf) {
        return hospedeDao.haHospedeComMesmoCPF(cpf);
    }
}
