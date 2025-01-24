package org.gerenciador_hotel.model.dao;

import org.gerenciador_hotel.model.domain.entities.Hospede;

public interface HospedeDao {

    void cadastraHospede(Hospede hospede);

    void atualizaHospede(Hospede hospedeModificado);

    Hospede getHospedeByCPF(String cpf);

    boolean haHospedeComMesmoCPF(String cpf);

}
