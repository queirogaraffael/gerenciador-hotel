package org.gerenciador_hotel.model.dao;

import org.gerenciador_hotel.dtos.ReservaDTO;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.model.domain.entities.Reserva;

import java.util.List;

public interface ReservaDao {

    void criaReserva(Reserva reserva);

    Reserva getReservaById(Long id);

    List<ReservaDTO> getReservasDTOByStatusReservaEByCPFHospede(StatusReserva statusReserva, String cpf);

    void mudaStatusReservaById(StatusReserva statusReserva, Long idReserva);

    void atualizaReserva(Reserva reservaModifica);

}
