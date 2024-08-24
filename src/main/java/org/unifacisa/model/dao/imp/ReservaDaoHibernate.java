package org.unifacisa.model.dao.imp;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.model.dao.ReservaDao;
import org.unifacisa.model.domain.entities.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;

public class ReservaDaoHibernate implements ReservaDao {

    private final EntityManagerFactory entityManagerFactory;

    public ReservaDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void criaReserva(Reserva reserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            if (reserva.getQuarto() != null && reserva.getQuarto().getId() == null) {
                entityManager.persist(reserva.getQuarto());
            }

            if (reserva.getHospede() != null && reserva.getHospede().getId() == null) {
                entityManager.persist(reserva.getHospede());
            }

            entityManager.persist(reserva);

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
    public Reserva getReservaById(Long id) {
        return null;
    }

    @Override
    public List<ReservaDTO> getReservasDTOHospedeByCPF(String cpf) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            return entityManager.createQuery("SELECT new org.unifacisa.dtos.ReservaDTO(reserva.id, reserva.dataEntrada, reserva.dataSaida, reserva.quarto.tipoQuarto) " +
                            "FROM Reserva reserva " +
                            "JOIN reserva.hospede hospede " +
                            "WHERE hospede.cpf = :cpf", ReservaDTO.class)
                    .setParameter("cpf", cpf)
                    .getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException(e);
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }


    }


    @Override
    public void deleataReservaHospedeById(Long idReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Reserva reserva = entityManager.find(Reserva.class, idReserva);

            if (reserva != null) {
                entityManager.remove(reserva);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            GlobalExceptionHandler.handleGeneralException(e);
        } finally {
            entityManager.close();
        }


    }


}
