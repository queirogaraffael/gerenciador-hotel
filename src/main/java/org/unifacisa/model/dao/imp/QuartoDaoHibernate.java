package org.unifacisa.model.dao.imp;

import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.enums.StatusReserva;
import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.model.dao.QuartoDao;
import org.unifacisa.model.domain.entities.Quarto;

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
    public Quarto getQuartoByNumero(String numeroQuarto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String consulta = "SELECT q " +
                    "FROM Quarto q " +
                    "WHERE q.numeroQuarto =: numeroQuarto";

            return entityManager.createQuery(consulta, Quarto.class).setParameter("numeroQuarto", numeroQuarto).getSingleResult();

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
            String consulta = "SELECT new org.unifacisa.dtos.QuartoDTO(q.numeroQuarto, q.tipoQuarto) " +
                    "FROM Quarto q " +
                    "WHERE q.tipoQuarto = :tipoQuarto ";

            return entityManager.createQuery(consulta, QuartoDTO.class).setParameter("tipoQuarto", tipoQuarto).getResultList();

        } catch (Exception e) {
            GlobalExceptionHandler.handleGeneralException("Erro ao buscar quartos desse tipo: " + e.getMessage());
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

            String consulta = "SELECT new org.unifacisa.dtos.QuartoDTO(q.numeroQuarto, q.tipoQuarto) " +
                    "FROM Reserva r JOIN r.quarto q " +
                    "WHERE q.tipoQuarto = :tipoQuarto " +
                    "AND r.dataEntrada <= :dataFinal " +
                    "AND r.dataSaida >= :dataInicial " +
                    "AND (r.statusReserva = :statusAgendado OR r.statusReserva = :statusEmUso)";

            return entityManager.createQuery(consulta, QuartoDTO.class)
                    .setParameter("tipoQuarto", tipoQuarto)
                    .setParameter("dataInicial", dataInicial)
                    .setParameter("dataFinal", dataFinal)
                    .setParameter("statusAgendado", statusAgendado)
                    .setParameter("statusEmUso", statusEmUso)
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

                entityManager.merge(quartoExistente);

                transaction.commit();

                GlobalExceptionHandler.handleRuntimeException("Quarto atualizacao com sucesso.");

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
    public boolean haQuartoComMesmoNumero(String numeroQuarto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String consulta = "SELECT q " +
                    "FROM Quarto q " +
                    "WHERE q.numeroQuarto = :numeroQuarto";

            entityManager.createQuery(consulta, Quarto.class).setParameter("numeroQuarto", numeroQuarto).getSingleResult();
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
