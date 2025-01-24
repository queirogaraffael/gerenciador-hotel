package org.gerenciador_hotel.model.dao;

import org.gerenciador_hotel.dtos.ExtratoFuncionarioDTO;
import org.gerenciador_hotel.dtos.FuncionarioDTO;
import org.gerenciador_hotel.model.domain.entities.ExtratoFuncionario;
import org.gerenciador_hotel.model.domain.entities.Funcionario;

import java.time.YearMonth;
import java.util.List;

public interface FuncionarioDao {

    void criaFuncionario(Funcionario funcionario);

    void atualizaFuncionario(Funcionario funcionarioModificado);

    Funcionario getFuncionarioByCPF(String cpf);

    List<FuncionarioDTO> getFuncionariosDTOByNome(String nome);

    boolean haFuncionarioComMesmoCPF(String cpf);

    List<ExtratoFuncionarioDTO> getExtratosFuncionarioDTOByCPF(String cpf);

    boolean existeExtratoFuncionarioPorMesAno(String cpf, YearMonth data);

    ExtratoFuncionario getExtratoFuncionarioById(Long id);

    void criaExtratoFuncionario(Funcionario funcionario, ExtratoFuncionario extratoFuncionario);
}
