package org.gerenciador_hotel.services;

import org.gerenciador_hotel.dtos.ReservaDTO;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.model.dao.ReservaDao;
import org.gerenciador_hotel.model.dao.imp.ReservaDaoHibernate;
import org.gerenciador_hotel.model.domain.entities.Reserva;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ReservaService {

    private final ReservaDao reservaDao;

    public ReservaService(ReservaDao reservaDao) {
        this.reservaDao = reservaDao;
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
