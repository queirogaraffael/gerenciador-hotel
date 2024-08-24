package org.unifacisa.model.dao.imp;

import org.unifacisa.dtos.HospedeDTO;
import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.model.dao.HospedeDao;
import org.unifacisa.model.domain.entities.Endereco;
import org.unifacisa.model.domain.entities.Hospede;
import org.unifacisa.model.domain.entities.Reserva;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

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

        if (hospedeModificado == null || hospedeModificado.getId() == null) {
            GlobalExceptionHandler.handleIllegalArgumentException("Hospede invalido.");
            return;
        }

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
            return entityManager.createQuery("SELECT hospede FROM Hospede hospede WHERE hospede.cpf =: cpf", Hospede.class).setParameter("cpf", cpf).getSingleResult();

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
    public boolean verificaSeHaHospedeComMesmoCPF(String cpf) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.createQuery("SELECT hospede FROM Hospede hospede WHERE hospede.cpf = :cpf", Hospede.class).setParameter("cpf", cpf).getSingleResult();

            return true;

        } catch (Exception error) {
            return false;
        } finally {
            entityManager.close();
        }




    }

    @Override
    public Reserva getReservaHospedeByCPF(String cpf) {
        return null;
    }

    @Override
    public List<ReservaDTO> getReservasDeHospedeByCPF(String cpf) {
        return List.of();
    }
}
