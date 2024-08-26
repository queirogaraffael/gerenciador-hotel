package org.unifacisa.services;

import org.unifacisa.dtos.ExtratoFuncionarioDTO;
import org.unifacisa.dtos.FuncionarioDTO;
import org.unifacisa.model.dao.FuncionarioDao;
import org.unifacisa.model.dao.imp.FuncionarioDaoHibernate;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;
import org.unifacisa.model.domain.entities.Funcionario;

import javax.persistence.EntityManagerFactory;
import java.time.YearMonth;
import java.util.List;

public class FuncionarioService {

    private final FuncionarioDao funcionarioDao;

    public FuncionarioService(EntityManagerFactory entityManagerFactory) {
        this.funcionarioDao = new FuncionarioDaoHibernate(entityManagerFactory);
    }


    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarioDao.criaFuncionario(funcionario);
    }


    public void atualizaFuncionario(Funcionario funcionarioModificado) {
        funcionarioDao.atualizaFuncionario(funcionarioModificado);
    }


    public Funcionario getFuncionarioByCPF(String cpf) {
        return funcionarioDao.getFuncionarioByCPF(cpf);
    }


    public List<FuncionarioDTO> getFuncionariosDTOByNome(String nome) {
        return funcionarioDao.getFuncionariosDTOByNome(nome);
    }


    public boolean haFuncionarioComMesmoCPF(String cpf) {
        return funcionarioDao.haFuncionarioComMesmoCPF(cpf);
    }

    public List<ExtratoFuncionarioDTO> getExtratosFuncionarioDTOByCPF(String cpf) {
        return funcionarioDao.getExtratosFuncionarioDTOByCPF(cpf);
    }

    public boolean existeExtratoFuncionarioPorMesAno(String cpf, YearMonth data){
        return funcionarioDao.existeExtratoFuncionarioPorMesAno(cpf, data);
    }

    public ExtratoFuncionario getExtratoFuncionarioById(Long id){
        return funcionarioDao.getExtratoFuncionarioById(id);
    }

    public void criaExtratoFuncionario(Funcionario funcionario, ExtratoFuncionario extratoFuncionario){
        funcionarioDao.criaExtratoFuncionario(funcionario, extratoFuncionario);
    }
}
