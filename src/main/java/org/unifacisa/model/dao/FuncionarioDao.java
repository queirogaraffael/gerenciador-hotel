package org.unifacisa.model.dao;

import org.unifacisa.model.domain.entities.Funcionario;

public interface FuncionarioDao {

    void criaFuncionario(Funcionario funcionario);

    void atualizaFuncionario(Funcionario funcionarioModificado);

    void getFuncionarioByCPF(String cpf);

    void getFuncionariosByNome(String nome);

    void atualizaEnderecoFuncionarioByCPF(String cpf);
}
