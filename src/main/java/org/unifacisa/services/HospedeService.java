package org.unifacisa.services;

import org.unifacisa.dtos.HospedeDTO;
import org.unifacisa.model.dao.HospedeDao;
import org.unifacisa.model.dao.imp.HospedeDaoHibernate;
import org.unifacisa.model.domain.entities.Hospede;

import javax.persistence.EntityManagerFactory;
import java.util.List;

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

    public boolean verificaSeHaHospedeComMesmoCPF(String cpf) {
        return hospedeDao.verificaSeHaHospedeComMesmoCPF(cpf);
    }
}
