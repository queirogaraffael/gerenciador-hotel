package org.unifacisa.model.dao.imp;

import org.unifacisa.dtos.ExtratoFuncionarioDTO;
import org.unifacisa.dtos.FuncionarioDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.model.dao.FuncionarioDao;
import org.unifacisa.model.domain.entities.Endereco;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;
import org.unifacisa.model.domain.entities.Funcionario;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

public class FuncionarioDaoHibernate implements FuncionarioDao {

    private final EntityManagerFactory entityManagerFactory;


    public FuncionarioDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void criaFuncionario(Funcionario funcionario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(funcionario);
            transaction.commit();

        } catch (PersistenceException error) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handlePersistenceException(error);

        } catch (Exception error) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handleGeneralException(error);

        } finally {
            entityManager.close();
        }

    }

    @Override
    public void atualizaFuncionario(Funcionario funcionarioModificado) {

        if (funcionarioModificado == null || funcionarioModificado.getId() == null) {
            GlobalExceptionHandler.handleIllegalArgumentException("Funcionario invalido.");
            return;
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Funcionario funcionarioExistente = entityManager.find(Funcionario.class, funcionarioModificado.getId());

            if (funcionarioExistente != null) {

                funcionarioExistente.setNome(funcionarioModificado.getNome());
                funcionarioExistente.setDataNascimento(funcionarioModificado.getDataNascimento());
                funcionarioExistente.setNumeroTelefone(funcionarioModificado.getNumeroTelefone());
                funcionarioExistente.setCargo(funcionarioModificado.getCargo());
                funcionarioExistente.setTurno(funcionarioModificado.getTurno());


                Endereco endereco = funcionarioModificado.getEndereco();
                endereco.setPessoa(funcionarioExistente);
                funcionarioExistente.setEndereco(endereco);


                entityManager.merge(funcionarioExistente);


                transaction.commit();
            } else {
                GlobalExceptionHandler.handleRuntimeException("Funcionario nao encontrado para atualizacao.");
            }

        } catch (PersistenceException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handlePersistenceException(e);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handleGeneralException(e);

        } finally {
            entityManager.close();
        }
    }


    @Override
    public Funcionario getFuncionarioByCPF(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.cpf =: cpf", Funcionario.class).setParameter("cpf", cpf).getSingleResult();

        } catch (NoResultException error) {
            GlobalExceptionHandler.handleNoResultException(error);
            return null;
        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException(error);
            return null;
        } finally {
            entityManager.close();
        }


    }

    @Override
    public List<FuncionarioDTO> getFuncionariosDTOByNome(String nome) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT new org.unifacisa.dtos.FuncionarioDTO(funcionario.id, funcionario.cpf, funcionario.nome) FROM Funcionario funcionario WHERE LOWER(funcionario.nome) LIKE LOWER(CONCAT('%', :nome, '%'))", FuncionarioDTO.class).setParameter("nome", nome).getResultList();

        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException("Sem Funcionario(s) com esse nome.");
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }


    }

    @Override
    public boolean verificaSeHaFuncionarioComMesmoCPF(String cpf) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.cpf = :cpf", Funcionario.class).setParameter("cpf", cpf).getSingleResult();

            return true;

        } catch (Exception error) {
            return false;
        } finally {
            entityManager.close();
        }


    }

    @Override
    public List<ExtratoFuncionarioDTO> getExtratosFuncionarioDTOByCPF(String cpf) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT new org.unifacisa.dtos.ExtratoFuncionarioDTO(e.id, e.mesReferente) " + "FROM ExtratoFuncionario e " + "JOIN e.funcionario f " + "WHERE f.cpf = :cpf " + "ORDER BY e.mesReferente DESC", ExtratoFuncionarioDTO.class).setParameter("cpf", cpf).getResultList();
        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException("Sem Extrato(s) para esse funcionario.");
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }


    }


    @Override
    public boolean existeExtratoFuncionarioPorMesAno(String cpf, YearMonth data) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Long numeroExtratos = entityManager.createQuery(
                            "SELECT COUNT(extrato) " +
                                    "FROM ExtratoFuncionario extrato " +
                                    "WHERE extrato.funcionario.cpf = :cpf " +
                                    "AND extrato.mesReferente = :data", Long.class)
                    .setParameter("cpf", cpf)
                    .setParameter("data", data)
                    .getSingleResult();

            return numeroExtratos > 0;

        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException("Erro ao verificar existencia do extrato.");
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ExtratoFuncionario getExtratoFuncionarioById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT extrato FROM ExtratoFuncionario extrato WHERE extrato.id =: id", ExtratoFuncionario.class).setParameter("id", id).getSingleResult();

        } catch (NoResultException error) {
            GlobalExceptionHandler.handleNoResultException(error);
            return null;
        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException(error);
            return null;
        } finally {
            entityManager.close();
        }


    }

    @Override
    public void criaExtratoFuncionario(Funcionario funcionario, ExtratoFuncionario extratoFuncionario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Funcionario funcionarioGerenciado = entityManager.merge(funcionario);

            extratoFuncionario.setFuncionario(funcionarioGerenciado);

            entityManager.persist(extratoFuncionario);

            funcionarioGerenciado.getExtratoFuncionario().add(extratoFuncionario);

            transaction.commit();

        } catch (PersistenceException error) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handlePersistenceException(error);

        } catch (Exception error) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handleGeneralException(error);

        } finally {
            entityManager.close();
        }


    }


}
