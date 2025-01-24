package org.gerenciador_hotel.model.dao.imp;

import org.gerenciador_hotel.dtos.ExtratoFuncionarioDTO;
import org.gerenciador_hotel.dtos.FuncionarioDTO;
import org.gerenciador_hotel.exceptions.GlobalExceptionHandler;
import org.gerenciador_hotel.model.dao.FuncionarioDao;
import org.gerenciador_hotel.model.domain.entities.Endereco;
import org.gerenciador_hotel.model.domain.entities.ExtratoFuncionario;
import org.gerenciador_hotel.model.domain.entities.Funcionario;

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
    public void atualizaFuncionario(Funcionario funcionarioModificado) {
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
            String jpql = "SELECT f FROM Funcionario f " +
                    "WHERE f.cpf =: cpf";

            return entityManager.createQuery(jpql, Funcionario.class).setParameter("cpf", cpf).getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException(e);
            return null;
        } finally {
            entityManager.close();
        }


    }

    @Override
    public List<FuncionarioDTO> getFuncionariosDTOByNome(String nome) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT new org.unifacisa.dtos.FuncionarioDTO(f.id, f.cpf, f.nome) " +
                    "FROM Funcionario f " +
                    "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))";

            return entityManager.createQuery(jpql, FuncionarioDTO.class).setParameter("nome", nome).getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException(e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }


    }

    @Override
    public boolean haFuncionarioComMesmoCPF(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT f " +
                    "FROM Funcionario f " +
                    "WHERE f.cpf = :cpf";

            entityManager.createQuery(jpql, Funcionario.class).setParameter("cpf", cpf).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException(e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }


    }

    @Override
    public List<ExtratoFuncionarioDTO> getExtratosFuncionarioDTOByCPF(String cpf) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT new org.unifacisa.dtos.ExtratoFuncionarioDTO(e.id, e.mesReferente) " +
                    "FROM ExtratoFuncionario e " +
                    "JOIN e.funcionario f " +
                    "WHERE f.cpf = :cpf " +
                    "ORDER BY e.mesReferente DESC";

            return entityManager.createQuery(jpql, ExtratoFuncionarioDTO.class).setParameter("cpf", cpf).getResultList();
        } catch (Exception e) {
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

            String jpql = "SELECT COUNT(ex) " +
                    "FROM ExtratoFuncionario ex " +
                    "WHERE ex.funcionario.cpf = :cpf " +
                    "AND ex.mesReferente = :data";

            Long numeroExtratos = entityManager.createQuery(
                            jpql, Long.class)
                    .setParameter("cpf", cpf)
                    .setParameter("data", data)
                    .getSingleResult();

            return numeroExtratos > 0;

        }catch (Exception e) {
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
            String jpql = "SELECT ex " +
                    "FROM ExtratoFuncionario ex " +
                    "WHERE ex.id =: id";

            return entityManager.createQuery(jpql, ExtratoFuncionario.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException error) {
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
