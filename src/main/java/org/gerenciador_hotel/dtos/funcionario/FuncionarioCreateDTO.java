package org.gerenciador_hotel.dtos.funcionario;

import org.gerenciador_hotel.enums.Turno;
import org.gerenciador_hotel.model.domain.entities.Endereco;

import java.time.LocalDate;

public record FuncionarioCreateDTO(String cpf, String nome, LocalDate dataNascimento, String numeroTelefone
        , String cargo, Turno turno, Double salario, Endereco endereco) {
}
