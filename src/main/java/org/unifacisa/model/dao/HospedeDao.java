package org.unifacisa.model.dao;

import org.unifacisa.model.domain.entities.Hospede;

public interface HospedeDao {

    void cadastraHospede(Hospede hospede);

    void atualizaHospede(Hospede hospedeModificado);

    Hospede getHospedeByCPF(String cpf);

    boolean haHospedeComMesmoCPF(String cpf);

}
