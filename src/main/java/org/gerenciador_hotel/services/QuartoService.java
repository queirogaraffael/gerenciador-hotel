package org.gerenciador_hotel.services;

import org.gerenciador_hotel.dtos.QuartoDTO;
import org.gerenciador_hotel.dtos.QuartoReservaDTO;
import org.gerenciador_hotel.enums.TipoQuarto;
import org.gerenciador_hotel.model.dao.QuartoDao;
import org.gerenciador_hotel.model.dao.imp.QuartoDaoHibernate;
import org.gerenciador_hotel.model.domain.entities.Quarto;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class QuartoService {

    private final QuartoDao quartoDao;

    public QuartoService(QuartoDao quartoDao) {
        this.quartoDao = quartoDao;
    }

    public void cadastrarQuarto(Quarto quarto) {
        quartoDao.cadastrarQuarto(quarto);
    }

    public Quarto getQuartoByNumero(int numeroQuarto) {
        return quartoDao.getQuartoByNumero(numeroQuarto);
    }


    public List<QuartoDTO> getQuartosDTOByTipo(TipoQuarto tipoQuarto) {
        return quartoDao.getQuartosDTOByTipo(tipoQuarto);

    }


    public void atualizaDadosQuarto(Quarto quartoModificado) {
        quartoDao.atualizaDadosQuarto(quartoModificado);
    }


    public boolean haQuartoComMesmoNumero(int numeroQuarto) {
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

    public List<QuartoDTO> getQuartosEmManutencao(){
        return quartoDao.getQuartosEmManutencao();

    }

    public List<QuartoReservaDTO> getQuartosReservasEmManutencao(){
        return quartoDao.getQuartosReservasEmManutencao();
    }
}
