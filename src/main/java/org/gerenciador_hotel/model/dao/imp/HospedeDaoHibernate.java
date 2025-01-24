package org.gerenciador_hotel.model.dao.imp;

import org.gerenciador_hotel.exceptions.GlobalExceptionHandler;
import org.gerenciador_hotel.model.dao.HospedeDao;
import org.gerenciador_hotel.model.domain.entities.Endereco;
import org.gerenciador_hotel.model.domain.entities.Hospede;

import javax.persistence.*;

public class HospedeDaoHibernate implements HospedeDao {

    private final EntityManagerFactory entityManagerFactory;

    public HospedeDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public void cadastraHospede(Hospede hospede) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(hospede);
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
    public void atualizaHospede(Hospede hospedeModificado) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Hospede hospedeExistente = entityManager.find(Hospede.class, hospedeModificado.getId());

            if (hospedeExistente != null) {

                hospedeExistente.setNome(hospedeModificado.getNome());
                hospedeExistente.setNumeroTelefone(hospedeModificado.getNumeroTelefone());

                Endereco endereco = hospedeModificado.getEndereco();
                endereco.setPessoa(hospedeExistente);
                hospedeExistente.setEndereco(endereco);


                entityManager.merge(hospedeExistente);


                transaction.commit();
            } else {
                GlobalExceptionHandler.handleRuntimeException("Hospede nao encontrado para atualizacao.");
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
    public Hospede getHospedeByCPF(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT h " +
                    "FROM Hospede h " +
                    "WHERE h.cpf =: cpf";

            return entityManager.createQuery(jpql, Hospede.class).setParameter("cpf", cpf).getSingleResult();

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
    public boolean haHospedeComMesmoCPF(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT h " +
                    "FROM Hospede h " +
                    "WHERE h.cpf = :cpf";

            entityManager.createQuery(jpql, Hospede.class).setParameter("cpf", cpf).getSingleResult();

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

}
