package org.gerenciador_hotel.services;

import org.gerenciador_hotel.dtos.ExtratoFuncionarioDTO;
import org.gerenciador_hotel.dtos.FuncionarioDTO;
import org.gerenciador_hotel.model.dao.FuncionarioDao;
import org.gerenciador_hotel.model.dao.imp.FuncionarioDaoHibernate;
import org.gerenciador_hotel.model.domain.entities.ExtratoFuncionario;
import org.gerenciador_hotel.model.domain.entities.Funcionario;

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
