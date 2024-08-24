package org.unifacisa.model.dao.imp;

import org.unifacisa.dtos.QuartoDTO;
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
            return entityManager.createQuery("SELECT quarto FROM Quarto quarto WHERE quarto.numeroQuarto =: numeroQuarto", Quarto.class).setParameter("numeroQuarto", numeroQuarto).getSingleResult();

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
    public List<QuartoDTO> getQuartosDTOByTipo(TipoQuarto tipoQuarto) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT new org.unifacisa.dtos.QuartoDTO(quarto.numeroQuarto, quarto.tipoQuarto) FROM Quarto quarto WHERE quarto.tipoQuarto = :tipoQuarto ", QuartoDTO.class).setParameter("tipoQuarto", tipoQuarto).getResultList();

        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException("Sem Quarto(s) desse tipo.");
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }


    }

    @Override
    public List<QuartoDTO> getQuartosOcupadosPorTipo(TipoQuarto tipoQuarto, LocalDate dataInicial, LocalDate dataFinal) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT new org.unifacisa.dtos.QuartoDTO(quarto.numeroQuarto, quarto.tipoQuarto) " +
                    "FROM Reserva reserva JOIN reserva.quarto quarto " +
                    "WHERE quarto.tipoQuarto = :tipoQuarto " +
                    "AND reserva.dataEntrada <= :dataFinal " +
                    "AND reserva.dataSaida >= :dataInicial";

            TypedQuery<QuartoDTO> query = entityManager.createQuery(jpql, QuartoDTO.class)
                    .setParameter("tipoQuarto", tipoQuarto)
                    .setParameter("dataInicial", dataInicial)
                    .setParameter("dataFinal", dataFinal);

            return query.getResultList();

        } catch (Exception error) {
            GlobalExceptionHandler.handleGeneralException("Erro ao buscar quartos ocupados: " + error.getMessage());
            return Collections.emptyList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }


    }


    @Override
    public void atualizaDadosQuarto(Quarto quartoModificado) {

        if (quartoModificado == null || quartoModificado.getId() == null) {
            GlobalExceptionHandler.handleIllegalArgumentException("Quarto invalido.");
            return;
        }

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
    public boolean verificaSeHaQuartoComMesmoNumero(String numeroQuarto) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.createQuery("SELECT quarto FROM Quarto quarto WHERE quarto.numeroQuarto = :numeroQuarto", Quarto.class).setParameter("numeroQuarto", numeroQuarto).getSingleResult();

            return true;

        } catch (Exception error) {
            return false;
        } finally {
            entityManager.close();
        }

    }
}
