package org.unifacisa.model.dao;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.enums.StatusReserva;
import org.unifacisa.model.domain.entities.Reserva;

import java.util.List;

public interface ReservaDao {

    void criaReserva(Reserva reserva);

    Reserva getReservaById(Long id);

    List<ReservaDTO> getReservasDTOByStatusReservaEByCPFHospede(StatusReserva statusReserva, String cpf);

    void mudaStatusReservaById(StatusReserva statusReserva, Long idReserva);

    void atualizaReserva(Reserva reservaModifica);

}
