package org.unifacisa.model.dao.imp;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.enums.StatusReserva;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.model.dao.ReservaDao;
import org.unifacisa.model.domain.entities.Reserva;

import javax.persistence.*;
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
    public Reserva getReservaById(Long idReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String consulta = "SELECT r " +
                    "FROM Reserva r " +
                    "WHERE r.id = :idReserva";

            return entityManager.createQuery(consulta, Reserva.class).setParameter("idReserva", idReserva).getSingleResult();

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
    public List<ReservaDTO> getReservasDTOByStatusReservaEByCPFHospede(StatusReserva statusReserva, String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String consulta = "SELECT new org.unifacisa.dtos.ReservaDTO(r.id, r.dataEntrada, r.dataSaida, r.quarto.tipoQuarto) " +
                    "FROM Reserva r " +
                    "JOIN r.hospede h " +
                    "WHERE h.cpf = :cpf AND r.statusReserva = :statusReserva";

            return entityManager.createQuery(consulta, ReservaDTO.class)
                    .setParameter("cpf", cpf)
                    .setParameter("statusReserva", statusReserva)
                    .getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException(e);
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }


    }

    @Override
    public void mudaStatusReservaById(StatusReserva statusReserva, Long idReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Reserva reserva = entityManager.find(Reserva.class, idReserva);

            reserva.setStatusReserva(statusReserva);

            entityManager.merge(reserva);

            transaction.commit();


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
