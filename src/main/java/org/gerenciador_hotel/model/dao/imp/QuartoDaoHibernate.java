package org.gerenciador_hotel.model.dao.imp;

import org.gerenciador_hotel.dtos.QuartoDTO;
import org.gerenciador_hotel.dtos.QuartoReservaDTO;
import org.gerenciador_hotel.enums.StatusQuarto;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.enums.TipoQuarto;
import org.gerenciador_hotel.exceptions.GlobalExceptionHandler;
import org.gerenciador_hotel.model.dao.QuartoDao;
import org.gerenciador_hotel.model.domain.entities.Quarto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class QuartoDaoHibernate implements QuartoDao {

    private final EntityManagerFactory entityManagerFactory;

    public QuartoDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void cadastrarQuarto(Quarto quarto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(quarto);
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
    public Quarto getQuartoByNumero(int numeroQuarto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT q " +
                    "FROM Quarto q " +
                    "WHERE q.numeroQuarto =: numeroQuarto";

            return entityManager.createQuery(jpql, Quarto.class).setParameter("numeroQuarto", numeroQuarto).getSingleResult();

        } catch (NoResultException error) {
            return null;
        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException(e);
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<QuartoDTO> getQuartosDTOByTipo(TipoQuarto tipoQuarto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT new org.gerenciador_hotel.dtos.QuartoDTO(q.numeroQuarto) " +
                    "FROM Quarto q " +
                    "WHERE q.tipoQuarto = :tipoQuarto ";

            return entityManager.createQuery(jpql, QuartoDTO.class).setParameter("tipoQuarto", tipoQuarto).getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException("Erro ao buscar quartos desse tipo: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<QuartoReservaDTO> getQuartosReservasEmManutencao() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT new org.gerenciador_hotel.dtos.QuartoReservaDTO(r.id, r.quarto.numeroQuarto) " +
                    "FROM Reserva r " +
                    "WHERE r.statusReserva = :statusReserva";


            return entityManager.createQuery(jpql, QuartoReservaDTO.class)
                    .setParameter("statusReserva", StatusReserva.MANUTENCAO)
                    .getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException("Erro ao buscar quartos e manutencao: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }



    }

    @Override
    public List<QuartoDTO> getQuartosEmManutencao() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT new org.gerenciador_hotel.dtos.QuartoDTO(q.numeroQuarto) " +
                    "FROM Quarto q " +
                    "WHERE q.statusQuarto = :statusQuarto ";

            return entityManager.createQuery(jpql, QuartoDTO.class).setParameter("statusQuarto", StatusQuarto.MANUTENCAO).getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException("Erro ao buscar quartos e manutencao: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }

    }


    @Override
    public List<QuartoDTO> getQuartosOcupadosPorTipo(TipoQuarto tipoQuarto, LocalDate dataInicial, LocalDate dataFinal) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            StatusReserva statusAgendado = StatusReserva.AGENDADO;
            StatusReserva statusEmUso = StatusReserva.EM_USO;
            StatusReserva statusManutencao = StatusReserva.MANUTENCAO;

            String jpql = "SELECT new org.gerenciador_hotel.dtos.QuartoDTO(q.numeroQuarto) " +
                    "FROM Reserva r JOIN r.quarto q " +
                    "WHERE q.tipoQuarto = :tipoQuarto " +
                    "AND r.dataEntrada <= :dataFinal " +
                    "AND r.dataSaida >= :dataInicial " +
                    "AND (r.statusReserva = :statusAgendado OR r.statusReserva = :statusEmUso or r.statusReserva = :statusManutencao)";

            return entityManager.createQuery(jpql, QuartoDTO.class)
                    .setParameter("tipoQuarto", tipoQuarto)
                    .setParameter("dataInicial", dataInicial)
                    .setParameter("dataFinal", dataFinal)
                    .setParameter("statusAgendado", statusAgendado)
                    .setParameter("statusEmUso", statusEmUso)
                    .setParameter("statusManutencao", statusManutencao)
                    .getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException("Erro ao buscar quartos ocupados: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }


    }


    @Override
    public void atualizaDadosQuarto(Quarto quartoModificado) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Quarto quartoExistente = entityManager.find(Quarto.class, quartoModificado.getId());

            if (quartoExistente != null) {

                quartoExistente.setTipoQuarto(quartoModificado.getTipoQuarto());
                quartoExistente.setPrecoDiaria(quartoModificado.getPrecoDiaria());
                quartoExistente.setCapacidade(quartoModificado.getCapacidade());
                quartoExistente.setStatusQuarto(quartoModificado.getStatusQuarto());

                entityManager.merge(quartoExistente);

                transaction.commit();

            } else {
                GlobalExceptionHandler.handleRuntimeException("Quarto nao encontrado para atualizacao.");
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
    public boolean haQuartoComMesmoNumero(int numeroQuarto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT q " +
                    "FROM Quarto q " +
                    "WHERE q.numeroQuarto = :numeroQuarto";

            entityManager.createQuery(jpql, Quarto.class).setParameter("numeroQuarto", numeroQuarto).getSingleResult();
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
