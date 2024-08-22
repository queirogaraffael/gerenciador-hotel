package org.unifacisa.model.dao;

import org.unifacisa.dtos.ExtratoFuncionarioDTO;
import org.unifacisa.dtos.FuncionarioDTO;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;
import org.unifacisa.model.domain.entities.Funcionario;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface FuncionarioDao {

    void criaFuncionario(Funcionario funcionario);

    void atualizaFuncionario(Funcionario funcionarioModificado);

    Funcionario getFuncionarioByCPF(String cpf);

    List<FuncionarioDTO> getFuncionariosDTOByNome(String nome);

    boolean verificaSeHaFuncionarioComMesmoCPF(String cpf);

    List<ExtratoFuncionarioDTO> getExtratosFuncionarioDTOByCPF(String cpf);

    boolean existeExtratoFuncionarioPorMesAno(String cpf, YearMonth data);

    ExtratoFuncionario getExtratoFuncionarioById(Long id);

    void criaExtratoFuncionario(Funcionario funcionario, ExtratoFuncionario extratoFuncionario);
}
