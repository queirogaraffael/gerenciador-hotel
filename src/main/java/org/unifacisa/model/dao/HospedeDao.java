package org.unifacisa.model.dao;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.model.domain.entities.Hospede;
import org.unifacisa.model.domain.entities.Reserva;

import java.util.List;

public interface HospedeDao {

    void cadastraHospede(Hospede hospede);

    void atualizaHospede(Hospede hospedeModificado);

    Hospede getHospedeByCPF(String cpf);

    boolean verificaSeHaHospedeComMesmoCPF(String cpf);

}
