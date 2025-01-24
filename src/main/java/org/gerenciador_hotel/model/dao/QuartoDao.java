package org.gerenciador_hotel.model.dao;

import org.gerenciador_hotel.dtos.QuartoDTO;
import org.gerenciador_hotel.dtos.QuartoReservaDTO;
import org.gerenciador_hotel.enums.TipoQuarto;
import org.gerenciador_hotel.model.domain.entities.Quarto;

import java.time.LocalDate;
import java.util.List;

public interface QuartoDao {
    void cadastrarQuarto(Quarto quarto);

    Quarto getQuartoByNumero(int numeroQuarto);

    List<QuartoDTO> getQuartosDTOByTipo(TipoQuarto tipoQuarto);

    List<QuartoReservaDTO> getQuartosReservasEmManutencao();

    List<QuartoDTO> getQuartosEmManutencao();

    List<QuartoDTO> getQuartosOcupadosPorTipo(TipoQuarto tipo, LocalDate dataInicial, LocalDate dataFinal);

    void atualizaDadosQuarto(Quarto quartoModificado);

    boolean haQuartoComMesmoNumero(int numeroQuarto);

}
