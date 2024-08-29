package org.unifacisa.services;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.enums.StatusReserva;
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


    public Reserva getReservaById(Long idReserva) {
        return reservaDao.getReservaById(idReserva);
    }

    public List<ReservaDTO> getReservasDTOByStatusReservaEByCPFHospede(StatusReserva statusReserva, String cpf) {
        return reservaDao.getReservasDTOByStatusReservaEByCPFHospede(statusReserva, cpf);
    }

    public void mudaStatusReservaById(StatusReserva statusReserva, Long idReserva) {
        reservaDao.mudaStatusReservaById(statusReserva, idReserva);

    }

    public void atualizaReserva(Reserva reservaModifica) {
        reservaDao.atualizaReserva(reservaModifica);
    }


}
