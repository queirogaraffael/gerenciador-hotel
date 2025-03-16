package org.gerenciador_hotel.model.dao.imp;

import org.gerenciador_hotel.dtos.ReservaDTO;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.exceptions.GlobalExceptionHandler;
import org.gerenciador_hotel.model.dao.ReservaDao;
import org.gerenciador_hotel.model.domain.entities.Reserva;

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
            String jpql = "SELECT r " +
                    "FROM Reserva r " +
                    "WHERE r.id = :idReserva";

            return entityManager.createQuery(jpql, Reserva.class).setParameter("idReserva", idReserva).getSingleResult();

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
            String jpql = "SELECT new org.gerenciador_hotel.dtos.ReservaDTO(r.id, r.dataEntrada, r.dataSaida, r.quarto.tipoQuarto) " +
                    "FROM Reserva r " +
                    "JOIN r.hospede h " +
                    "WHERE h.cpf = :cpf AND r.statusReserva = :statusReserva";

            return entityManager.createQuery(jpql, ReservaDTO.class)
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

    @Override
    public void atualizaReserva(Reserva reservaModifica) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Reserva reservaExistente = entityManager.find(Reserva.class, reservaModifica.getId());

            if (reservaExistente != null) {

                reservaExistente.setDataEntrada(reservaModifica.getDataEntrada());
                reservaExistente.setDataSaida(reservaModifica.getDataSaida());
                reservaExistente.setStatusReserva(reservaModifica.getStatusReserva());
                reservaExistente.setValorTotal(reservaModifica.getValorTotal());

                entityManager.merge(reservaExistente);

                transaction.commit();
            } else {
                GlobalExceptionHandler.handleRuntimeException("Reserva nao encontrada para atualizacao.");
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
}
