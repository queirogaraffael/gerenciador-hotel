package org.unifacisa.services;

import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.model.dao.QuartoDao;
import org.unifacisa.model.dao.imp.QuartoDaoHibernate;
import org.unifacisa.model.domain.entities.Quarto;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class QuartoService {

    private QuartoDao quartoDao;

    public QuartoService(EntityManagerFactory entityManagerFactory) {
        this.quartoDao = new QuartoDaoHibernate(entityManagerFactory);
    }

    public void cadastrarQuarto(Quarto quarto) {
        quartoDao.cadastrarQuarto(quarto);
    }

    public Quarto getQuartoByNumero(String numeroQuarto) {
        return quartoDao.getQuartoByNumero(numeroQuarto);
    }


    public List<QuartoDTO> getQuartosDTOByTipo(TipoQuarto tipoQuarto) {
        return quartoDao.getQuartosDTOByTipo(tipoQuarto);

    }


    public void atualizaDadosQuarto(Quarto quartoModificado) {
        quartoDao.atualizaDadosQuarto(quartoModificado);
    }


    public boolean haQuartoComMesmoNumero(String numeroQuarto) {
        return quartoDao.haQuartoComMesmoNumero(numeroQuarto);
    }


    public List<QuartoDTO> getQuartosOcupadosPorTipo(TipoQuarto tipo, LocalDate dataInicial, LocalDate dataFinal) {
        return quartoDao.getQuartosOcupadosPorTipo(tipo, dataInicial, dataFinal);
    }


    public List<QuartoDTO> getQuartosDisponiveisPorTipo(TipoQuarto tipoQuarto, LocalDate dataEntrada, LocalDate dataSaida) {

        List<QuartoDTO> quartosOcupados = getQuartosOcupadosPorTipo(tipoQuarto, dataEntrada, dataSaida);


        List<QuartoDTO> quartosDisponiveis = getQuartosDTOByTipo(tipoQuarto);


        return quartosDisponiveis.stream()
                .filter(quarto -> !quartosOcupados.contains(quarto))
                .toList();

    }
}
