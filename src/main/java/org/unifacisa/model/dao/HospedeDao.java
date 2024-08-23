package org.unifacisa.model.dao;

import org.unifacisa.dtos.HospedeDTO;
import org.unifacisa.model.domain.entities.Hospede;

import java.util.List;

public interface HospedeDao {

    void cadastraHospede(Hospede hospede);

    void atualizaHospede(Hospede hospedeModificado);

    Hospede getHospedeByCPF(String cpf);

    boolean verificaSeHaHospedeComMesmoCPF(String cpf);


}
