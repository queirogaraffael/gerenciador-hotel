package org.unifacisa.model.dao;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.model.domain.entities.Reserva;

import java.util.List;

public interface ReservaDao {

    void criaReserva(Reserva reserva);

    Reserva getReservaById(Long id);

    List<ReservaDTO> getReservasDTOHospedeByCPF(String cpf);

    void deleataReservaHospedeById(Long idReserva);
}
