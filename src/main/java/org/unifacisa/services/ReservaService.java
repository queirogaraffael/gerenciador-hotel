package org.unifacisa.services;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.model.dao.ReservaDao;
import org.unifacisa.model.dao.imp.ReservaDaoHibernate;
import org.unifacisa.model.domain.entities.Reserva;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ReservaService {

    private final ReservaDao reservaDao;

    public ReservaService(EntityManagerFactory entityManagerFactory) {
        this.reservaDao = new ReservaDaoHibernate(entityManagerFactory);
    }


    public void criaReserva(Reserva reserva) {
        reservaDao.criaReserva(reserva);
    }

    public List<ReservaDTO> getReservasDTOHospedeByCPF(String cpf) {
        return reservaDao.getReservasDTOHospedeByCPF(cpf);
    }

    public void deleataReservaHospedeById(Long idReserva) {
        reservaDao.deleataReservaHospedeById(idReserva);
    }

}
