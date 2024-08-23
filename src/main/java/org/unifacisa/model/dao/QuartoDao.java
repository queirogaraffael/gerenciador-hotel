package org.unifacisa.model.dao;

import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.model.domain.entities.Quarto;

import java.util.List;

public interface QuartoDao {
    void cadastrarQuarto(Quarto quarto);
    Quarto getQuartoByNumero(String numeroQuarto);
    List<QuartoDTO> getQuartosDTOByTipo(TipoQuarto tipoQuarto);
    // buscar por tipo disponiveis para uma data
    void atualizaDadosQuarto(Quarto quartoModificado);
    boolean verificaSeHaQuartoComMesmoNumero(String numeroQuarto);

}
